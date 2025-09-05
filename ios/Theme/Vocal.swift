import AVFoundation
import FaceTecSDK
import Foundation
import UIKit

public class Vocal: NSObject, FaceTecCustomAnimationDelegate {
  public enum VocalGuidanceMode {
    case OFF
    case MINIMAL
    case FULL
  }

  public static var vocalGuidanceMode: VocalGuidanceMode!
  public var vocalGuidanceOnPlayer: AVAudioPlayer!
  public var vocalGuidanceOffPlayer: AVAudioPlayer!
  public var themeTransitionTextTimer: Timer!
  public var networkIssueDetected = false
  internal let module: AzifaceViewController!

  init(controller: AzifaceViewController) {
    module = controller

    if #available(iOS 13.0, *) {
      if let roundedDescriptor = UIFontDescriptor.preferredFontDescriptor(withTextStyle: .body)
        .withDesign(.rounded)
      {
        let roundedMessageFont = UIFont(descriptor: roundedDescriptor, size: module.statusLabel.font.pointSize)
        module.statusLabel.font = roundedMessageFont
      }
    }
  }

  func addDismissableImageToInterface(image: UIImage) {
    let imageView = UIImageView(image: image)
    imageView.frame = UIScreen.main.bounds

    let screenSize = UIScreen.main.bounds
    let ratio = screenSize.width / image.size.width
    let size = (image.size).applying(CGAffineTransform(scaleX: 0.5 * ratio, y: 0.5 * ratio))
    let hasAlpha = false
    let scale: CGFloat = 0.0
    UIGraphicsBeginImageContextWithOptions(size, hasAlpha, scale)
    image.draw(in: CGRect(x: 0, y: 0, width: size.width, height: size.height))
    let scaledImage = UIGraphicsGetImageFromCurrentImageContext()
    UIGraphicsEndImageContext()
    imageView.image = scaledImage
    imageView.contentMode = .center

    imageView.isUserInteractionEnabled = true

    module.view.addSubview(imageView)
  }

  func setUpVocalGuidancePlayers() {
    Vocal.vocalGuidanceMode = .MINIMAL

    guard
      let vocalGuidanceOnUrl = Bundle.main.url(
        forResource: "vocal_guidance_on", withExtension: "mp3")
    else { return }
    guard
      let vocalGuidanceOffUrl = Bundle.main.url(
        forResource: "vocal_guidance_off", withExtension: "mp3")
    else { return }

    do {
      try AVAudioSession.sharedInstance().setCategory(AVAudioSession.Category.playback)
      try AVAudioSession.sharedInstance().setActive(true)
      vocalGuidanceOnPlayer = try AVAudioPlayer(contentsOf: vocalGuidanceOnUrl)
      vocalGuidanceOffPlayer = try AVAudioPlayer(contentsOf: vocalGuidanceOffUrl)
    } catch let error {
      print(error.localizedDescription)
    }
  }

  func setVocalGuidanceMode() {
    if !(AVAudioSession.sharedInstance().outputVolume > 0) {
      // TODO: Add custom message for UIAlertAction
      let alert = UIAlertController(
        title: nil, message: "Vocal Guidance is disabled when the device is muted",
        preferredStyle: UIAlertController.Style.alert)
      alert.addAction(UIAlertAction(title: "OK", style: UIAlertAction.Style.default, handler: nil))
      self.module.present(alert, animated: true, completion: nil)
      return
    }

    if vocalGuidanceOnPlayer == nil || vocalGuidanceOffPlayer == nil
      || vocalGuidanceOnPlayer.isPlaying || vocalGuidanceOffPlayer.isPlaying
    {
      return
    }

    DispatchQueue.main.async {
      switch Vocal.vocalGuidanceMode {
      case .OFF:
        Vocal.vocalGuidanceMode = .MINIMAL
        self.module.vocalGuidanceSettingButton.setImage(
          UIImage(named: "vocal_minimal.png"), for: .normal)
        self.vocalGuidanceOnPlayer.play()
        Config.currentCustomization.vocalGuidanceCustomization.mode =
          FaceTecVocalGuidanceMode.minimalVocalGuidance
      case .MINIMAL:
        Vocal.vocalGuidanceMode = .FULL
        self.module.vocalGuidanceSettingButton.setImage(
          UIImage(named: "vocal_full.png"), for: .normal)
        self.vocalGuidanceOnPlayer.play()
        Config.currentCustomization.vocalGuidanceCustomization.mode =
          FaceTecVocalGuidanceMode.fullVocalGuidance
      case .FULL:
        Vocal.vocalGuidanceMode = .OFF
        self.module.vocalGuidanceSettingButton.setImage(
          UIImage(named: "vocal_off.png"), for: .normal)
        self.vocalGuidanceOffPlayer.play()
        Config.currentCustomization.vocalGuidanceCustomization.mode =
          FaceTecVocalGuidanceMode.noVocalGuidance
      default: break
      }
      Vocal.setVocalGuidanceSoundFiles()
      FaceTec.sdk.setCustomization(Config.currentCustomization)
    }
  }

  public static func setVocalGuidanceSoundFiles() {
    Config.currentCustomization.vocalGuidanceCustomization.pleaseFrameYourFaceInTheOvalSoundFile =
      Bundle.main.path(forResource: "please_frame_your_face_sound_file", ofType: "mp3") ?? ""
    Config.currentCustomization.vocalGuidanceCustomization.pleaseMoveCloserSoundFile =
      Bundle.main.path(forResource: "please_move_closer_sound_file", ofType: "mp3") ?? ""
    Config.currentCustomization.vocalGuidanceCustomization.pleaseRetrySoundFile =
      Bundle.main.path(forResource: "please_retry_sound_file", ofType: "mp3") ?? ""
    Config.currentCustomization.vocalGuidanceCustomization.uploadingSoundFile =
      Bundle.main.path(forResource: "uploading_sound_file", ofType: "mp3") ?? ""
    Config.currentCustomization.vocalGuidanceCustomization.facescanSuccessfulSoundFile =
      Bundle.main.path(forResource: "facescan_successful_sound_file", ofType: "mp3") ?? ""
    Config.currentCustomization.vocalGuidanceCustomization.pleasePressTheButtonToStartSoundFile =
      Bundle.main.path(forResource: "please_press_button_sound_file", ofType: "mp3") ?? ""

    switch Vocal.vocalGuidanceMode {
    case .OFF:
      Config.currentCustomization.vocalGuidanceCustomization.mode =
        FaceTecVocalGuidanceMode.noVocalGuidance
    case .MINIMAL:
      Config.currentCustomization.vocalGuidanceCustomization.mode =
        FaceTecVocalGuidanceMode.minimalVocalGuidance
    case .FULL:
      Config.currentCustomization.vocalGuidanceCustomization.mode =
        FaceTecVocalGuidanceMode.fullVocalGuidance
    default: break
    }
  }

  public static func setOCRLocalization() {
    if let path = Bundle.main.path(forResource: "FaceTec_OCR_Customization", ofType: "json") {
      do {
        let jsonData = try Data(contentsOf: URL(fileURLWithPath: path), options: .mappedIfSafe)
        let jsonObject = try JSONSerialization.jsonObject(with: jsonData, options: .mutableLeaves)
        if let jsonDictionary = jsonObject as? [String: AnyObject] {
          FaceTec.sdk.configureOCRLocalization(dictionary: jsonDictionary)
        }
      } catch {
        print("Error loading JSON for OCR Localization")
      }
    }
  }
}

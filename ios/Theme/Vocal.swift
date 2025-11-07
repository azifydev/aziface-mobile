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

  private static var module: UIViewController?
  public static var vocalGuidanceMode: VocalGuidanceMode! = .MINIMAL
  public static var vocalGuidanceOnPlayer: AVAudioPlayer!
  public static var vocalGuidanceOffPlayer: AVAudioPlayer!
  public var themeTransitionTextTimer: Timer!
  public var networkIssueDetected = false

  init(controller: UIViewController) {
    Vocal.module = controller
  }

  private static func copyBundleFileToURL(fileName: String, fileExtension: String) -> URL? {
    guard let bundleUrl = Bundle.main.url(forResource: fileName, withExtension: fileExtension)
    else {
      return nil
    }

    let documentsPath = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask)[0]
    let destinationUrl = documentsPath.appendingPathComponent("\(fileName).\(fileExtension)")

    if FileManager.default.fileExists(atPath: destinationUrl.path) {
      return destinationUrl
    }

    do {
      try FileManager.default.copyItem(at: bundleUrl, to: destinationUrl)
      return destinationUrl
    } catch {
      return nil
    }
  }

  public static func cleanUp() {
    let documentsPath = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask)[0]
    let files = ["vocal_guidance_on.mp3", "vocal_guidance_off.mp3"]

    for fileName in files {
      let fileUrl = documentsPath.appendingPathComponent(fileName)
      do {
        if FileManager.default.fileExists(atPath: fileUrl.path) {
          try FileManager.default.removeItem(at: fileUrl)
        }
      } catch {
        print("Error removing \(fileName): \(error)")
      }
    }
  }

  public static func setUpVocalGuidancePlayers() {
    Vocal.vocalGuidanceMode = .MINIMAL

    guard
      let vocalGuidanceOnUrl = copyBundleFileToURL(
        fileName: "vocal_guidance_on",
        fileExtension: "mp3"
      )
    else { return }
    guard
      let vocalGuidanceOffUrl = copyBundleFileToURL(
        fileName: "vocal_guidance_off",
        fileExtension: "mp3"
      )
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

  public static func isDeviceMuted() -> Bool {
    return !(AVAudioSession.sharedInstance().outputVolume > 0)
  }

  public static func setVocalGuidanceMode() {
    if Vocal.isDeviceMuted() {
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
        self.vocalGuidanceOnPlayer.play()
        Config.currentCustomization.vocalGuidanceCustomization.mode =
          FaceTecVocalGuidanceMode.minimalVocalGuidance
      case .MINIMAL:
        Vocal.vocalGuidanceMode = .FULL
        self.vocalGuidanceOnPlayer.play()
        Config.currentCustomization.vocalGuidanceCustomization.mode =
          FaceTecVocalGuidanceMode.fullVocalGuidance
      case .FULL:
        Vocal.vocalGuidanceMode = .OFF
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

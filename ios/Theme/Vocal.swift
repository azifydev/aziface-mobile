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

  public static var vocalGuidanceMode: VocalGuidanceMode! = .OFF
  public static var vocalGuidanceOnPlayer: AVAudioPlayer!
  public static var vocalGuidanceOffPlayer: AVAudioPlayer!

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

  public static func setUpVocalGuidancePlayers() {
    if vocalGuidanceOnPlayer != nil && vocalGuidanceOffPlayer != nil {
      return
    }

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

  public static func setVocalGuidanceMode(_ isEnabled: Bool) {
    Vocal.vocalGuidanceMode = isEnabled ? .FULL : .OFF

    let player: AVAudioPlayer? = isEnabled ? vocalGuidanceOnPlayer : vocalGuidanceOffPlayer
    player?.play()

    Vocal.setVocalGuidanceSoundFiles()
    FaceTec.sdk.setCustomization(Config.currentCustomization)
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

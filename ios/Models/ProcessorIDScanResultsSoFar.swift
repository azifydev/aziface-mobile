import Foundation

public class ProcessorIDScanResultsSoFar: JSONParams {
  private var object: [AnyHashable: Any?]

  override init(target: [AnyHashable: Any]?) {
    self.object = [:]

    super.init(target: target)

    self.parseJSON()
  }

  private func parseJSON() {
    let photoIDNextStepEnumInt = self.getParam("photoIDNextStepEnumInt", type: Int.self)
    let fullIDStatusEnumInt = self.getParam("fullIDStatusEnumInt", type: Int.self)
    let faceOnDocumentStatusEnumInt = self.getParam("faceOnDocumentStatusEnumInt", type: Int.self)
    let textOnDocumentStatusEnumInt = self.getParam("textOnDocumentStatusEnumInt", type: Int.self)
    let expectedMediaStatusEnumInt = self.getParam("expectedMediaStatusEnumInt", type: Int.self)
    let unexpectedMediaEncounteredAtLeastOnce = self.getParam(
      "unexpectedMediaEncounteredAtLeastOnce", type: Bool.self)
    let documentData = self.getParam("documentData", type: String.self)
    let nfcStatusEnumInt = self.getParam("nfcStatusEnumInt", type: Int.self)
    let nfcAuthenticationStatusEnumInt = self.getParam(
      "nfcAuthenticationStatusEnumInt", type: Int.self)
    let barcodeStatusEnumInt = self.getParam("barcodeStatusEnumInt", type: Int.self)
    let mrzStatusEnumInt = self.getParam("mrzStatusEnumInt", type: Int.self)
    let idFoundWithoutQualityIssueDetected = self.getParam(
      "idFoundWithoutQualityIssueDetected", type: Bool.self)
    let idFacePhotoFoundWithoutQualityIssueDetected = self.getParam(
      "idFacePhotoFoundWithoutQualityIssueDetected", type: Bool.self)
    let idScanAgeV2GroupEnumInt = self.getParam("idScanAgeV2GroupEnumInt", type: Int.self)
    let didMatchIDScanToOCRTemplate = self.getParam("didMatchIDScanToOCRTemplate", type: Bool.self)
    let isUniversalIDMode = self.getParam("isUniversalIDMode", type: Bool.self)
    let matchLevel = self.getParam("matchLevel", type: Int.self)
    let matchLevelNFCToFaceMap = self.getParam("matchLevelNFCToFaceMap", type: Int.self)
    let faceMapAgeV2GroupEnumInt = self.getParam("faceMapAgeV2GroupEnumInt", type: Int.self)
    let watermarkAndHologramStatusEnumInt = self.getParam(
      "watermarkAndHologramStatusEnumInt", type: Int.self)

    self.object["documentData"] = documentData
    self.object["photoIDNextStepEnumInt"] = self.defaultIfNull(
      photoIDNextStepEnumInt, defaultValue: -1)
    self.object["fullIDStatusEnumInt"] = self.defaultIfNull(fullIDStatusEnumInt, defaultValue: -1)
    self.object["faceOnDocumentStatusEnumInt"] = self.defaultIfNull(
      faceOnDocumentStatusEnumInt, defaultValue: -1)
    self.object["textOnDocumentStatusEnumInt"] = self.defaultIfNull(
      textOnDocumentStatusEnumInt, defaultValue: -1)
    self.object["expectedMediaStatusEnumInt"] = self.defaultIfNull(
      expectedMediaStatusEnumInt, defaultValue: -1)
    self.object["unexpectedMediaEncounteredAtLeastOnce"] =
      unexpectedMediaEncounteredAtLeastOnce == true
    self.object["nfcStatusEnumInt"] = self.defaultIfNull(nfcStatusEnumInt, defaultValue: -1)
    self.object["nfcAuthenticationStatusEnumInt"] = self.defaultIfNull(
      nfcAuthenticationStatusEnumInt, defaultValue: -1)
    self.object["barcodeStatusEnumInt"] = self.defaultIfNull(barcodeStatusEnumInt, defaultValue: -1)
    self.object["mrzStatusEnumInt"] = self.defaultIfNull(mrzStatusEnumInt, defaultValue: -1)
    self.object["idFoundWithoutQualityIssueDetected"] = idFoundWithoutQualityIssueDetected == true
    self.object["idFacePhotoFoundWithoutQualityIssueDetected"] =
      idFacePhotoFoundWithoutQualityIssueDetected == true
    self.object["idScanAgeV2GroupEnumInt"] = self.defaultIfNull(
      idScanAgeV2GroupEnumInt, defaultValue: -1)
    self.object["didMatchIDScanToOCRTemplate"] = didMatchIDScanToOCRTemplate == true
    self.object["isUniversalIDMode"] = isUniversalIDMode == true
    self.object["matchLevel"] = self.defaultIfNull(matchLevel, defaultValue: -1)
    self.object["matchLevelNFCToFaceMap"] = self.defaultIfNull(
      matchLevelNFCToFaceMap, defaultValue: -1)
    self.object["faceMapAgeV2GroupEnumInt"] = self.defaultIfNull(
      faceMapAgeV2GroupEnumInt, defaultValue: -1)
    self.object["watermarkAndHologramStatusEnumInt"] = self.defaultIfNull(
      watermarkAndHologramStatusEnumInt, defaultValue: -1)
  }

  func getDict() -> NSDictionary {
    return NSDictionary(dictionary: self.object as [AnyHashable: Any])
  }
}

package com.azifacemobile.models;

import com.azifacemobile.models.abstracts.JSONParams;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;

import org.json.JSONObject;

public class ProcessorIDScanResultsSoFar extends JSONParams {
  private final WritableMap object;

  public ProcessorIDScanResultsSoFar(JSONObject target) {
    super(target);

    this.object = Arguments.createMap();

    this.parseJSON();
  }

  private void parseJSON() {
    Integer photoIDNextStepEnumInt = this.getParam( "photoIDNextStepEnumInt", Integer.class);
    Integer fullIDStatusEnumInt = this.getParam("fullIDStatusEnumInt", Integer.class);
    Integer faceOnDocumentStatusEnumInt = this.getParam("faceOnDocumentStatusEnumInt", Integer.class);
    Integer textOnDocumentStatusEnumInt = this.getParam("textOnDocumentStatusEnumInt", Integer.class);
    Integer expectedMediaStatusEnumInt = this.getParam("expectedMediaStatusEnumInt", Integer.class);
    Boolean unexpectedMediaEncounteredAtLeastOnce = this.getParam("unexpectedMediaEncounteredAtLeastOnce", Boolean.class);
    String documentData = this.getParam("documentData", String.class);
    Integer nfcStatusEnumInt = this.getParam("nfcStatusEnumInt", Integer.class);
    Integer nfcAuthenticationStatusEnumInt = this.getParam("nfcAuthenticationStatusEnumInt", Integer.class);
    Integer barcodeStatusEnumInt = this.getParam("barcodeStatusEnumInt", Integer.class);
    Integer mrzStatusEnumInt = this.getParam("mrzStatusEnumInt", Integer.class);
    Boolean idFoundWithoutQualityIssueDetected = this.getParam("idFoundWithoutQualityIssueDetected", Boolean.class);
    Boolean idFacePhotoFoundWithoutQualityIssueDetected = this.getParam("idFacePhotoFoundWithoutQualityIssueDetected", Boolean.class);
    Integer idScanAgeV2GroupEnumInt = this.getParam("idScanAgeV2GroupEnumInt", Integer.class);
    Boolean didMatchIDScanToOCRTemplate = this.getParam("didMatchIDScanToOCRTemplate", Boolean.class);
    Boolean isUniversalIDMode = this.getParam("isUniversalIDMode", Boolean.class);
    Integer matchLevel = this.getParam("matchLevel", Integer.class);
    Integer matchLevelNFCToFaceMap = this.getParam("matchLevelNFCToFaceMap", Integer.class);
    Integer faceMapAgeV2GroupEnumInt = this.getParam("faceMapAgeV2GroupEnumInt", Integer.class);
    Integer watermarkAndHologramStatusEnumInt = this.getParam("watermarkAndHologramStatusEnumInt", Integer.class);

    this.object.putString("documentData", documentData);
    this.object.putInt("photoIDNextStepEnumInt", this.defaultIfNull(photoIDNextStepEnumInt, -1));
    this.object.putInt("fullIDStatusEnumInt", this.defaultIfNull(fullIDStatusEnumInt, -1));
    this.object.putInt("faceOnDocumentStatusEnumInt", this.defaultIfNull(faceOnDocumentStatusEnumInt, -1));
    this.object.putInt("textOnDocumentStatusEnumInt", this.defaultIfNull(textOnDocumentStatusEnumInt, -1));
    this.object.putInt("expectedMediaStatusEnumInt", this.defaultIfNull(expectedMediaStatusEnumInt, -1));
    this.object.putBoolean("unexpectedMediaEncounteredAtLeastOnce", Boolean.TRUE.equals(unexpectedMediaEncounteredAtLeastOnce));
    this.object.putInt("nfcStatusEnumInt", this.defaultIfNull(nfcStatusEnumInt, -1));
    this.object.putInt("nfcAuthenticationStatusEnumInt", this.defaultIfNull(nfcAuthenticationStatusEnumInt, -1));
    this.object.putInt("barcodeStatusEnumInt", this.defaultIfNull(barcodeStatusEnumInt, -1));
    this.object.putInt("mrzStatusEnumInt", this.defaultIfNull(mrzStatusEnumInt, -1));
    this.object.putBoolean("idFoundWithoutQualityIssueDetected", Boolean.TRUE.equals(idFoundWithoutQualityIssueDetected));
    this.object.putBoolean("idFacePhotoFoundWithoutQualityIssueDetected", Boolean.TRUE.equals(idFacePhotoFoundWithoutQualityIssueDetected));
    this.object.putInt("idScanAgeV2GroupEnumInt", this.defaultIfNull(idScanAgeV2GroupEnumInt, -1));
    this.object.putBoolean("didMatchIDScanToOCRTemplate", Boolean.TRUE.equals(didMatchIDScanToOCRTemplate));
    this.object.putBoolean("isUniversalIDMode", Boolean.TRUE.equals(isUniversalIDMode));
    this.object.putInt("matchLevel", this.defaultIfNull(matchLevel, -1));
    this.object.putInt("matchLevelNFCToFaceMap", this.defaultIfNull(matchLevelNFCToFaceMap, -1));
    this.object.putInt("faceMapAgeV2GroupEnumInt", this.defaultIfNull(faceMapAgeV2GroupEnumInt, -1));
    this.object.putInt("watermarkAndHologramStatusEnumInt", this.defaultIfNull(watermarkAndHologramStatusEnumInt, -1));
  }

  public WritableMap getMap() {
    return this.object.copy();
  }
}

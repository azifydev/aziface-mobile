public class IdScan {
  private let style: Style
  private let target: NSDictionary?
  private let font: Font
  private let button: Button
  private let image: Image
  private let additionalReview: AdditionalReview
  private let idFeedback: IdFeedback
  private let selectionScreen: SelectionScreen
  private let captureScreen: CaptureScreen
  private let reviewScreen: ReviewScreen

  init() {
    self.style = Style()
    
    self.target = self.style.getTarget("idScan")
    self.font = Font()
    self.button = Button(target: self.target)
    self.image = Image(target: self.target)
    self.additionalReview = AdditionalReview(target: self.target)
    self.idFeedback = IdFeedback(target: self.target)
    self.selectionScreen = SelectionScreen(target: self.target)
    self.captureScreen = CaptureScreen(target: self.target)
    self.reviewScreen = ReviewScreen(target: self.target)
  }

  
  public func getShowFaceMatchToIDBrandingImage() -> Bool {
    let key = "isShowFaceMatchToIDBrandingImage"
    if !self.style.exists(self.target, key: key) {
      return false
    }

    return self.target?[key] as! Bool
  }

  public func getHeaderFont() -> UIFont {
    return self.font.getTypography(theme: self.target, key: "headerFont")
  }

  public func getSubtextFont() -> UIFont {
    return self.font.getTypography(theme: self.target, key: "subtextFont")
  }

  public func getInactiveTorchImage() -> UIImage? {
    return self.image.getSource("inactiveTorchImage")
  }

  public func getActiveTorchImage() -> UIImage? {
    return self.image.getSource("activeTorchImage")
  }

  public func getFaceMatchToIDBrandingImage() -> UIImage? {
    return self.image.getSource("faceMatchToIDBrandingImage")
  }
  
  public func getButton() -> Button {
    return self.button
  }
  
  public func getAdditionalReview() -> AdditionalReview {
    return self.additionalReview
  }
  
  public func getIdFeedback() -> IdFeedback {
    return self.idFeedback
  }

  public func getSelectionScreen() -> SelectionScreen {
    return self.selectionScreen
  }

  public func getCaptureScreen() -> CaptureScreen {
    return self.captureScreen
  }

  public func getReviewScreen() -> ReviewScreen {
    return self.reviewScreen
  }
}

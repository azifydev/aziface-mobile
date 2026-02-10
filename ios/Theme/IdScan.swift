public class IdScan {
  private let target: NSDictionary?
  private let font: Font
  private let button: Button
  private let selectionScreen: SelectionScreen
  private let captureScreen: CaptureScreen
  private let reviewScreen: ReviewScreen

  init() {
    self.target = Style().getTarget("idScan")

    self.font = Font()
    self.button = Button(target: self.target)
    self.selectionScreen = SelectionScreen(target: self.target)
    self.captureScreen = CaptureScreen(target: self.target)
    self.reviewScreen = ReviewScreen(target: self.target)
  }
  
  public func getHeaderFont() -> UIFont {
    return self.font.getTypography(theme: self.target, key: "headerFont")
  }
  
  public func getSubtextFont() -> UIFont {
    return self.font.getTypography(theme: self.target, key: "subtextFont")
  }

  public func getButton() -> Button {
    return self.button
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

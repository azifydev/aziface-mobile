public class IdScan {
  private let button: Button
  private let selectionScreen: SelectionScreen
  private let captureScreen: CaptureScreen
  private let reviewScreen: ReviewScreen

  init() {
    let target = Style().getTarget("idScan")

    self.button = Button(target: target)
    self.selectionScreen = SelectionScreen(target: target)
    self.captureScreen = CaptureScreen(target: target)
    self.reviewScreen = ReviewScreen(target: target)
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

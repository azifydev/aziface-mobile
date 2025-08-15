//
//  IdScan.swift
//  Pods
//
//  Created by Daniel Sansão Araldi on 15/08/25.
//

public class IdScan {
  private let button: Button
  private let selectionScreen: SelectionScreen
  private let captureScreen: CaptureScreen
  private let reviewScreen: ReviewScreen

  init() {
    let theme = Style().getTarget("idScan")

    self.button = Button(theme: theme)
    self.selectionScreen = SelectionScreen(theme: theme)
    self.captureScreen = CaptureScreen(theme: theme)
    self.reviewScreen = ReviewScreen(theme: theme)
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

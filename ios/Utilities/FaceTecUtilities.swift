//
//  FaceTecUtilities.swift
//  AzifaceMobileSdk
//
//  Created by Nayara Dias on 08/03/24.
//  Copyright © 2024 Azify. All rights reserved.
//

import Foundation

class FaceTecUtilities: NSObject {
  public static let DefaultStatusBarStyle = UIStatusBarStyle.default
  var themeTransitionTextTimer: Timer!
  let viewController: AziFaceViewController! = nil
  
  public static func getTopMostViewController() -> UIViewController? {
    let theme = Theme()
    UIApplication.shared.statusBarStyle = theme.getGeneral().getStatusBarStyle("statusBarColor")
    
    var topMostViewController = UIApplication.shared.windows[0].rootViewController
    
    while let presentedViewController = topMostViewController?.presentedViewController {
      topMostViewController = presentedViewController
    }
    
    return topMostViewController
  }
  
  @objc func showSessionTokenConnectionText() {
    UIView.animate(withDuration: 0.6) {
      self.viewController.themeTransitionText.alpha = 1
    }
  }
  
  func startSessionTokenConnectionTextTimer() {
    themeTransitionTextTimer = Timer.scheduledTimer(
      timeInterval: 3.0, target: self, selector: #selector(showSessionTokenConnectionText),
      userInfo: nil, repeats: false)
  }
  
  func handleErrorGettingServerSessionToken() {
    print("Session could not be started due to an unexpected issue during the network request.")
  }
}

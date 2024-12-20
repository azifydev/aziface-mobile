//
//  FaceTecUtilities.swift
//  AzifaceMobileSdk
//
//  Created by Nayara Dias on 08/03/24.
//  Copyright © 2024 Azify. All rights reserved.
//

import Foundation

class FaceTecUtilities: NSObject {
    private static let AziThemeUtils: ThemeUtils! = ThemeUtils();
    public static let DefaultStatusBarStyle = UIStatusBarStyle.default;
    var themeTransitionTextTimer: Timer!
    let sampleAppVC: AziFaceViewController! = nil

    private static func preferredStatusBarStyle() -> UIStatusBarStyle {
        if #available(iOS 13, *) {
            let statusBarColor: UIStatusBarStyle = AziThemeUtils.handleStatusBarStyle("defaultStatusBarColorIos")
            return statusBarColor;
        } else {
            return DefaultStatusBarStyle;
        }
    }

    public static func getTopMostViewController() -> UIViewController? {
        UIApplication.shared.statusBarStyle = preferredStatusBarStyle();

        var topMostViewController = UIApplication.shared.windows[0].rootViewController;

        while let presentedViewController = topMostViewController?.presentedViewController {
            topMostViewController = presentedViewController;
        }

        return topMostViewController;
    }
    
    @objc func showSessionTokenConnectionText() {
        UIView.animate(withDuration: 0.6) {
            self.sampleAppVC.themeTransitionText.alpha = 1
        }
    }

    func startSessionTokenConnectionTextTimer() {
        themeTransitionTextTimer = Timer.scheduledTimer(timeInterval: 3.0, target: self, selector: #selector(showSessionTokenConnectionText), userInfo: nil, repeats: false)
    }
    
    func handleErrorGettingServerSessionToken() {
        print("Session could not be started due to an unexpected issue during the network request.")
    }
}

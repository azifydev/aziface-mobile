//
//  FaceTecUtilities.swift
//  AzifaceMobileSdk
//
//  Created by Nayara Dias on 08/03/24.
//  Copyright © 2024 Azify. All rights reserved.
//

import Foundation

class FaceTecUtilities: NSObject {
    private static let CapThemeUtils: ThemeUtils! = ThemeUtils();
    public static let DefaultStatusBarStyle = UIStatusBarStyle.default;

    private static func preferredStatusBarStyle() -> UIStatusBarStyle {
        if #available(iOS 13, *) {
            return CapThemeUtils.handleStatusBarStyle("defaultStatusBarColorIos")
        } else {
            return DefaultStatusBarStyle
        }
    }

    public static func getTopMostViewController() -> UIViewController? {
        if let topViewController = UIApplication.shared.windows.first?.rootViewController {
            var topMostViewController = topViewController

            while let presentedViewController = topMostViewController.presentedViewController {
                topMostViewController = presentedViewController
            }

            topMostViewController.setNeedsStatusBarAppearanceUpdate()

            return topMostViewController
        }

        return nil
    }
}

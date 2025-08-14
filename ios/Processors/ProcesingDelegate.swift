//
//  ProcesingDelegate.swift
//  AzifaceMobileSdk
//
//  Created by Nayara Dias on 08/03/24.
//  Copyright © 2024 Azify. All rights reserved.
//

import FaceTecSDK

protocol Processor: AnyObject {
  func isSuccess() -> Bool
}

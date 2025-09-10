//
//  AzifaceModule.m
//  AzifaceModule
//
//  Created by Nayara Dias on 13/03/24.
//  Copyright © 2024 Azify. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <React/RCTBridgeModule.h>
#import <React/RCTEventEmitter.h>
#import <React/RCTViewManager.h>

@interface RCT_EXTERN_MODULE (AzifaceModule, RCTEventEmitter)

RCT_EXTERN_METHOD(initialize : (NSDictionary *)params headers : (NSDictionary *)
                      headers resolve : (RCTPromiseResolveBlock)
                          resolve reject : (RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(liveness : (NSDictionary *)data resolve : (
    RCTPromiseResolveBlock)resolve reject : (RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(enroll : (NSDictionary *)data resolve : (
    RCTPromiseResolveBlock)resolve reject : (RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(authenticate : (NSDictionary *)data resolve : (
    RCTPromiseResolveBlock)resolve reject : (RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(photoIDMatch : (NSDictionary *)data resolve : (
    RCTPromiseResolveBlock)resolve reject : (RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(photoIDScanOnly : (NSDictionary *)data resolve : (
    RCTPromiseResolveBlock)resolve reject : (RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(setTheme : (NSDictionary *)options)
RCT_EXTERN_METHOD(vocal)
RCT_EXTERN_METHOD(supportedEvents)

@end

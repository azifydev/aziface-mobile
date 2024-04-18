//
//  AzifaceMobileSdk.m
//  AzifaceMobileSdk
//
//  Created by Nayara Dias on 13/03/24.
//  Copyright © 2024 Azify. All rights reserved.
//


#import <Foundation/Foundation.h>
#import <React/RCTBridgeModule.h>
#import <React/RCTEventEmitter.h>
#import <React/RCTViewManager.h>

@interface RCT_EXTERN_MODULE(AzifaceMobileSdk, RCTEventEmitter)

RCT_EXTERN_METHOD(initializeSdk:(NSDictionary *)params
                  headers:(NSDictionary *)headers
                  callback:(RCTResponseSenderBlock)callback)
RCT_EXTERN_METHOD(handleLivenessCheck:(NSDictionary *)data
                  resolve:(RCTPromiseResolveBlock)resolve
                  reject:(RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(handleEnrollUser:(NSDictionary *)data
                  resolve:(RCTPromiseResolveBlock)resolve
                  reject:(RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(handleAuthenticateUser:(NSDictionary *)data
                  resolve:(RCTPromiseResolveBlock)resolve
                  reject:(RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(handlePhotoIDMatch:(NSDictionary *)data
                  resolve:(RCTPromiseResolveBlock)resolve
                  reject:(RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(handlePhotoIDScan:(NSDictionary *)data
                  resolve:(RCTPromiseResolveBlock)resolve
                  reject:(RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(handleTheme:(NSDictionary *)options)
RCT_EXTERN_METHOD(supportedEvents)

@end

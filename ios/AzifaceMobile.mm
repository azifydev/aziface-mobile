#import "AzifaceMobile.h"
#import "AzifaceMobile-Swift.h"

@interface AzifaceMobile () <Emitter>
@end

@implementation AzifaceMobile {
  Aziface * _aziface;
}

RCT_EXPORT_MODULE()

- (instancetype)init {
  if (self = [super init]) {
    _aziface = [[Aziface alloc] initWithEmit:self];
  }
  return self;
}

- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params {
  return std::make_shared<facebook::react::NativeAzifaceMobileSpecJSI>(params);
}

- (void)initialize:(nonnull NSDictionary *)params
           headers:(nonnull NSDictionary *)headers
           resolve:(nonnull RCTPromiseResolveBlock)resolve
            reject:(nonnull RCTPromiseRejectBlock)reject {
  return [_aziface initialize:params headers:headers resolve:resolve reject:reject];
}

- (void)authenticate:(nonnull NSDictionary *)data
             resolve:(nonnull RCTPromiseResolveBlock)resolve
              reject:(nonnull RCTPromiseRejectBlock)reject {
  return [_aziface authenticate:data resolve:resolve reject:reject];
}

- (void)enroll:(nonnull NSDictionary *)data
       resolve:(nonnull RCTPromiseResolveBlock)resolve
        reject:(nonnull RCTPromiseRejectBlock)reject {
  return [_aziface enroll:data resolve:resolve reject:reject];
}

- (void)liveness:(nonnull NSDictionary *)data
         resolve:(nonnull RCTPromiseResolveBlock)resolve
          reject:(nonnull RCTPromiseRejectBlock)reject {
  return [_aziface liveness:data resolve:resolve reject:reject];
}

- (void)photoIDMatch:(nonnull NSDictionary *)data
             resolve:(nonnull RCTPromiseResolveBlock)resolve
              reject:(nonnull RCTPromiseRejectBlock)reject {
  return [_aziface photoIDMatch:data resolve:resolve reject:reject];
}

- (void)photoIDScanOnly:(nonnull NSDictionary *)data
                resolve:(nonnull RCTPromiseResolveBlock)resolve
                 reject:(nonnull RCTPromiseRejectBlock)reject {
  return [_aziface photoIDScanOnly:data resolve:resolve reject:reject];
}

- (void)setTheme:(nonnull NSDictionary *)options {
  return [_aziface setTheme:options];
}

- (void)vocal {
  return [_aziface vocal];
}

+ (BOOL)requiresMainQueueSetup {
  return true;
}

- (void)dealloc
{
  _aziface = nil;
}

@end

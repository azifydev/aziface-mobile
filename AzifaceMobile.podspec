require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))

Pod::Spec.new do |s|
  s.name         = "AzifaceMobile"
  s.version      = package["version"]
  s.summary      = package["description"]
  s.homepage     = package["homepage"]
  s.license      = package["license"]
  s.authors      = package["author"]

  s.platforms    = { :ios => 12.0 }
  s.source       = { :git => "https://github.com/azifydev/aziface-mobile.git", :tag => "#{s.version}" }

  s.vendored_frameworks = "ios/Frameworks/FaceTecSDK.xcframework"
  s.resources = ['ios/VocalGuidanceSoundFiles/*', 'ios/Assets/*', 'ios/*.json']
  s.source_files = "ios/**/*.{h,m,mm,swift,json,png,mp3}"
  s.private_header_files = "ios/**/*.h"
  s.requires_arc = true

  if respond_to?(:install_modules_dependencies, true)
    install_modules_dependencies(s)
  else
    s.dependency "React-Core"

    s.compiler_flags = "-DRCT_NEW_ARCH_ENABLED=1"

    s.pod_target_xcconfig    = {
      "HEADER_SEARCH_PATHS" => "\"$(PODS_ROOT)/boost\"",
      "OTHER_CPLUSPLUSFLAGS" => "-DFOLLY_NO_CONFIG -DFOLLY_MOBILE=1 -DFOLLY_USE_LIBCPP=1",
      "CLANG_CXX_LANGUAGE_STANDARD" => "c++17"
    }

    s.dependency "React-Codegen"
    s.dependency "RCT-Folly"
    s.dependency "RCTRequired"
    s.dependency "RCTTypeSafety"
    s.dependency "ReactCommon/turbomodule/core"
    s.dependency "React-RCTFabric"
  end
end

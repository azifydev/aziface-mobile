require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))

Pod::Spec.new do |s|
  s.name         = "AzifaceMobile"
  s.version      = package["version"]
  s.summary      = package["description"]
  s.homepage     = package["homepage"]
  s.license      = package["license"]
  s.authors      = package["author"]

  s.platforms    = { :ios => 15.0 }
  s.source       = { :git => "https://github.com/azifydev/aziface-mobile.git", :tag => "#{s.version}" }

  s.vendored_frameworks = "ios/Frameworks/FaceTecSDK.xcframework"
  s.preserve_paths = "ios/Frameworks/FaceTecSDKForDevelopment.xcframework"
  s.resources = ['ios/VocalGuidanceSoundFiles/*', 'ios/Assets/*', 'ios/*.json']
  s.source_files = "ios/**/*.{m,mm,swift,json,png,mp3}"
  s.private_header_files = "ios/**/*.h"
  s.requires_arc = true

  # The production SDK misbehaves when run from Xcode on a device, so Debug
  # device builds embed FaceTecSDKForDevelopment instead. Opt out with
  # `USE_FACETEC_DEVELOPMENT_FRAMEWORK=NO pod install`.
  s.pod_target_xcconfig = {
    "ENABLE_USER_SCRIPT_SANDBOXING" => "NO",
    "USE_FACETEC_DEVELOPMENT_FRAMEWORK[sdk=iphoneos*]" => ENV.fetch("USE_FACETEC_DEVELOPMENT_FRAMEWORK", "YES")
  }

  s.script_phase = {
    :name => "[AZIFACE] Swap SDK For Development",
    :execution_position => :after_compile,
    :always_out_of_date => "1",
    :shell_path => "/bin/bash",
    :script => <<~'SH'
      set -euo pipefail

      if [[ "${USE_FACETEC_DEVELOPMENT_FRAMEWORK:-NO}" != "YES" || " ${GCC_PREPROCESSOR_DEFINITIONS:-} " != *" DEBUG=1 "* ]]; then
        echo "Skipping! FaceTecSDKForDevelopment not requested."
        exit 0
      fi

      DEVELOPMENT_SLICE="${PODS_TARGET_SRCROOT}/ios/Frameworks/FaceTecSDKForDevelopment.xcframework/ios-arm64/FaceTecSDK.framework"
      # Copied here by "[CP] Copy XCFrameworks" and embedded later by the app's "[CP] Embed Pods Frameworks"
      COPIED_FRAMEWORK="${PODS_XCFRAMEWORKS_BUILD_DIR}/AzifaceMobile/FaceTecSDK.framework"

      [[ -d "$DEVELOPMENT_SLICE" ]] || { echo "warning: FaceTecSDKForDevelopment not found at ${DEVELOPMENT_SLICE}. Keeping the production FaceTecSDK."; exit 0; }
      [[ -d "$COPIED_FRAMEWORK" ]] || { echo "error: FaceTecSDK.framework not found at ${COPIED_FRAMEWORK}."; exit 1; }

      COPIED_VERSION=$(/usr/libexec/PlistBuddy -c "Print :CFBundleShortVersionString" "${COPIED_FRAMEWORK}/Info.plist")
      DEVELOPMENT_VERSION=$(/usr/libexec/PlistBuddy -c "Print :CFBundleShortVersionString" "${DEVELOPMENT_SLICE}/Info.plist")

      if [[ "$COPIED_VERSION" != "$DEVELOPMENT_VERSION" ]]; then
        echo "error: FaceTecSDK ${COPIED_VERSION} != FaceTecSDKForDevelopment ${DEVELOPMENT_VERSION}."
        exit 1
      fi

      rsync -a --delete "${DEVELOPMENT_SLICE}/" "${COPIED_FRAMEWORK}/"
      echo "FaceTecSDK: Framework has been replaced with FaceTecSDKForDevelopment"
    SH
  }

  install_modules_dependencies(s)
end

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
  s.source_files = "ios/**/*.{m,mm,swift,json,png,mp3}"
  s.private_header_files = "ios/**/*.h"
  s.requires_arc = true

  install_modules_dependencies(s)
end

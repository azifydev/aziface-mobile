import Foundation

@objc public protocol Emitter: AnyObject {
  func emitOnInitialize(_ value: Bool)
  func emitOnOpen(_ value: Bool)
  func emitOnClose(_ value: Bool)
  func emitOnCancel(_ value: Bool)
  func emitOnError(_ value: Bool)
  func emitOnVocal(_ value: Bool)
}

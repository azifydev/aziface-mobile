import Foundation

public class JSONParams {
  private let target: [AnyHashable: Any]?

  init(target: [AnyHashable: Any]?) {
    self.target = target
  }

  func defaultIfNull<T>(_ value: T?, defaultValue: T) -> T {
    return value ?? defaultValue
  }

  func getParam<T>(_ field: String, type: T.Type) -> T? {
    guard let target = self.target else { return nil }

    guard let value = target[field] else { return nil }

    return value as? T
  }
}

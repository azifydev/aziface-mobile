public class ReadyScreen {
  private let style: Style
  private let target: NSDictionary?
  private let color: Color

  init(target: NSDictionary?) {
    self.style = Style()
    self.color = Color()

    self.target = self.style.getTarget(target, key: "readyScreen")
  }

  public func getHeaderTextColor() -> UIColor {
    return self.color.getColor(self.target, key: "headerTextColor", defaultColor: "#000000")
  }

  public func getSubtextColor() -> UIColor {
    return self.color.getColor(self.target, key: "subtextColor", defaultColor: "#000000")
  }

  public func getOvalFillColor() -> UIColor {
    let transparent = "#00000000"
    return self.color.getColor(self.target, key: "ovalFillColor", defaultColor: transparent)
  }
}

import UIKit

public class AzifaceViewController: UIViewController, URLSessionDelegate {
  @IBOutlet weak var statusLabel: UILabel!
  @IBOutlet weak var mainInterfaceStackView: UIStackView!
  @IBOutlet weak var themeTransitionImageView: UIImageView!
  @IBOutlet weak var themeTransitionText: UILabel!
  @IBOutlet weak var vocalGuidanceSettingButton: UIButton!

  public override func viewDidLoad() {
    super.viewDidLoad()
  }
}

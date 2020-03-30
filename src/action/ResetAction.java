package action;

import javafx.scene.image.ImageView;
import service.ChangeService;

public class ResetAction {
	public ResetAction(ImageView imageView) {
//		imageView = ChangeService.origin;
		imageView.setFitHeight(ChangeService.originHeight);
		imageView.setFitWidth(ChangeService.originWidth);
	}
}

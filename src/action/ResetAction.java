package action;

import controller.ViewUIController;
import javafx.scene.image.ImageView;
import service.ChangeService;

public class ResetAction extends ViewUIController {
	private ImageView selectedImage;
	public ResetAction(ImageView imageView) {
		selectedImage=imageView;
		selectedImage.setRotate(ChangeService.origin.getRotate());
		selectedImage.setNodeOrientation(ChangeService.origin.getNodeOrientation());
		selectedImage.setScaleX(ChangeService.origin.getScaleX());
		selectedImage.setScaleY(ChangeService.origin.getScaleY());
	}

}

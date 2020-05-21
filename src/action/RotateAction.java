package action;

import controller.ViewUIController;
import javafx.scene.image.ImageView;

public class RotateAction extends ViewUIController {

	private ImageView selectedImage;
	public RotateAction(ImageView imageView) {
		selectedImage = imageView;
		selectedImage.setRotate((selectedImage.getRotate() + 90) % 360);
	}
}

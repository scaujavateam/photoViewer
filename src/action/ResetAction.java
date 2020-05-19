package action;

import javafx.scene.image.ImageView;

public class ResetAction {
	public ResetAction(ImageView imageView) {
		imageView.setFitHeight(564);
		imageView.setFitWidth(1010);
		Enlarge_Small_Action.changeNum = 1;
	}
}

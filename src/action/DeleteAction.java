package action;

import controller.MainUIController;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.PictureNode;
import java.util.Optional;

public class DeleteAction {
	MainUIController mainUIController;

	public DeleteAction(MainUIController mainUI) {
		mainUIController = mainUI;

		if(PictureNode.getSelectedPictures().size()<=0) {
			return;
		}
		if(PictureNode.getCutedPictures().size() > 0) {
			for(PictureNode pNode : PictureNode.getCutedPictures()) {
				pNode.getImageView().setEffect(null);
			}
			PictureNode.getCutedPictures().clear();
		}

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("注意！");
		alert.setHeaderText("是否确认删除选中的图片？");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			for(PictureNode pNode : PictureNode.getSelectedPictures()) {
				mainUIController.getFlowPane().getChildren().remove(pNode);
				pNode.getImageFile().delete();
			}
			PictureNode.getSelectedPictureFiles().clear();
		}
	}
}

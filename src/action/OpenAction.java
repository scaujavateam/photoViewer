package action;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.PictureNode;
import service.ChangeService;

public class OpenAction {
	public OpenAction() {
		if (PictureNode.getSelectedPictures().size() <= 0) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("提示");
			alert.headerTextProperty().set("没有选中图片！");
			alert.showAndWait();
		} else {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/View/ViewUI.fxml"));
				Pane root = (Pane) loader.load();
				Scene scene = new Scene(root);
//				scene.getStylesheets().add("view/iVCSS.css");
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.setTitle("iViewer 1.0");
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}

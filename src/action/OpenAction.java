package action;

import controller.MainUIController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import service.ChangeService;

public class OpenAction extends MainUIController {
	// static Stage openStage;
	// ViewUIController viewUIController;
	public OpenAction() {
		// openStage=Main.stage;
		if (ChangeService.files == null) {

			String text = "没有选中图片";
			Button button = new Button(text);
			Pane root = new Pane(button);
			Scene scene = new Scene(root);
			scene.getStylesheets().add("view/iVCSS.css");
			Stage Stage = null;
			Stage = new Stage();
			Stage.setTitle("提示");
			Stage.setScene(scene);
			Stage.show();
			return;
		
		} else {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/view/ViewUI.fxml"));
				Parent root = (Parent) loader.load();
				Scene scene = new Scene(root);
				scene.getStylesheets().add("view/iVCSS.css");
				ChangeService.stage.setScene(scene);
				ChangeService.stage.setTitle("照片");
				// ChangeService.stage.setResizable(false);
				ChangeService.stage.centerOnScreen();
				ChangeService.stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}

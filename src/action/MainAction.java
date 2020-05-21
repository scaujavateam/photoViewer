package action;

import controller.ViewUIController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import service.ChangeService;

public class MainAction extends ViewUIController {
	public MainAction() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/MainUI.fxml"));
			Parent root = (Parent)loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add("view/iVCSS.css");
			ChangeService.stage.setScene(scene);
			ChangeService.stage.setTitle("照片");
			ChangeService.stage.setResizable(false);
			ChangeService.stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

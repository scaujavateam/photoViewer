package action;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import service.ChangeService;

public class BeautyAction {
	public BeautyAction() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/View/Beautiful.fxml"));
			Pane root = (Pane)loader.load();
			Scene scene = new Scene(root);
//			scene.getStylesheets().add("view/iVCSS.css");
			Stage stage = new Stage();
			ChangeService.stage = stage;
			stage.setScene(scene);
			stage.setTitle("图片美化");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

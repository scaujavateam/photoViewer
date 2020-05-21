package action;

import controller.More;
import controller.ViewUIController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import service.ChangeService;

public class BeautyAction extends More {
	public BeautyAction() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/Beautiful.fxml"));
			Parent root = (Parent)loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add("view/iVCSS.css");
			ChangeService.stage.setScene(scene);
			ChangeService.stage.setTitle("图片美化");
		//	Main.stage.setResizable(false);
			ChangeService.stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

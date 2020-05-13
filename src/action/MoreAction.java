package action;

import controller.More;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import service.ChangeService;

public class MoreAction {
	public MoreAction() {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/view/more.fxml"));
				Parent root = (Parent) loader.load();
				Scene scene = new Scene(root);
				ChangeService.stage.setScene(scene);
				ChangeService.stage.setTitle("更多");
				// Main.stage.setResizable(false);
				ChangeService.stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


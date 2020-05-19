package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import service.ChangeService;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main3 extends Application {
	public static Window mainStage;
	//public  static Stage stage;
	
	@Override
	public void start(Stage stage) throws Exception {
//		ChangeService.stage = stage;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(loader.getClassLoader().getResource("view/MainUI.fxml"));
			Pane root = (Pane) loader.load();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
//			scene.getStylesheets().add("view/iVCSS.css");
//			ChangeService.stage.setScene(scene);
//			ChangeService.stage.setTitle("iViewer-1.0");
//			ChangeService.stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

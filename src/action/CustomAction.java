package action;

import controller.More;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import service.ChangeService;

public class CustomAction extends More {
    public CustomAction() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/custom.fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add("view/iVCSS.css");
            ChangeService.stage.setScene(scene);
            ChangeService.stage.setTitle("图片自定义" );
            //	Main.stage.setResizable(false);
            ChangeService.stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

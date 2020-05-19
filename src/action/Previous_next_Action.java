package action;

import java.io.File;
import java.net.MalformedURLException;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import service.ChangeService;

public class Previous_next_Action {

	private static int page = 0;
	private static Image image;

	public static void changePicture(ImageView imageView,Boolean Previous_or_next) {
	    if(Previous_or_next) {
	    	page++;
	    }
	    if(!Previous_or_next) {
	    	page--;
	    }
		if (page < 0) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("提示");
			alert.headerTextProperty().set("这是第一张图片！");
			alert.showAndWait();
			page++;
			return;
		}
		if (page > ChangeService.files.size() - 1) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("提示");
			alert.headerTextProperty().set("这是最后一张图片！");
			alert.showAndWait();
			page--;
			return;
		}
		File file = ChangeService.files.get(page);
		try {
			image = new Image(file.toURI().toURL().toString());
			imageView.setImage(image);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}

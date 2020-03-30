package action;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

import controller.MainUIController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.PictureFile;
import model.PictureNode;
import service.ChangeService;

public class Previous_next_Action {

	private static int page = 0;
	private static Image image;
	private boolean Previous_or_next = true;

//	public Previous_next_Action(ImageView imageView, boolean Previous_or_next) {
//
//		this.Previous_or_next = Previous_or_next;
//		this.imageView = imageView;
//		iniData();
//		change();
//	}

	public static void changePicture(ImageView imageView,Boolean Previous_or_next,Button button,Button button2,int order) {
		if (page==0){
			page=order;
		}
	    if(Previous_or_next) {
	    	page++;
			if (page==1){
				button.setVisible(true);
			}
			if (page>ChangeService.files.size()-1){
				button2.setVisible(false);
				page--;
				return;
			}
	    }
	    if(!Previous_or_next) {
	    	page--;
			if (page==ChangeService.files.size()-2){
				button2.setVisible(true);
			}
			if (page < 0) {
				button.setVisible(false);
				page++;
				return;
			}
	    }
	    if (page==0){
	    	button.setVisible(false);
		}
	    if (page==ChangeService.files.size()-1){
	    	button2.setVisible(false);
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

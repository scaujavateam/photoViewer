package controller;

import action.BeautyAction;
import action.ClipAction;
import action.OpenAction;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import service.ChangeService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class More implements Initializable {



	@FXML
	private ImageView imageview;
	@FXML
	private Button Clip, beautyBtn1,Custom;
	int index;
	@FXML
	private void clipAticon(ActionEvent e) {
		new ClipAction();
	}

	@FXML
	private void beautyAction1(ActionEvent e) {
		ChangeService.change = this.imageview;
		new BeautyAction();
	}
	@FXML
	private void customAction(ActionEvent e){}


	@FXML
	private void Back(ActionEvent e) {
		new OpenAction();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		imageview.setImage(ChangeService.change.getImage());
		imageview.setEffect(ChangeService.change.getEffect());
		imageview.setViewport(ChangeService.change.getViewport());
		imageview.setNodeOrientation(ChangeService.change.getNodeOrientation());
		imageview.setRotate(ChangeService.change.getRotate());

	}

}

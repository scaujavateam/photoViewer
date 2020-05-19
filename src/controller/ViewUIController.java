package controller;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Button;

import model.PictureNode;
import service.ChangeService;
import action.PPTAction;
import action.BeautyAction;
import action.Enlarge_Small_Action;
import action.Previous_next_Action;
import action.ResetAction;
import action.RotateAction;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Tooltip;

public class ViewUIController{
	@FXML
	private ImageView imageView;
	@FXML
	private Button pptBtn, enlargeBtn, smallBtn, resetBtn, rotateBtn, beautyBtn, previousImageBtn, nextImageBtn;

	private Image image;

	@FXML
	public void initialize() {
		image = new Image(PictureNode.getSelectedPictures().get(0).getURL());
		imageView.setImage(image);
		imageView.setPreserveRatio(true);
		imageView.setSmooth(true);

		ChangeService.file = PictureNode.getSelectedPictures().get(0).getImageFile();
		ChangeService.origin = new ImageView(image);
		ChangeService.change = new ImageView(image);

//		toolbar.setVisible(true);
		pptBtn.setTooltip(new Tooltip("幻灯片播放"));
		enlargeBtn.setTooltip(new Tooltip("放大"));
		smallBtn.setTooltip(new Tooltip("缩小"));
		resetBtn.setTooltip(new Tooltip("重置"));
		rotateBtn.setTooltip(new Tooltip("旋转"));
		beautyBtn.setTooltip(new Tooltip("美化"));
		previousImageBtn.setTooltip(new Tooltip("上一张"));
		nextImageBtn.setTooltip(new Tooltip("下一张"));
	}
	


//	public ImageView getImageView() {
//		return imageView;
//	}
	@FXML
	private void PPTAction(ActionEvent e) {
		new PPTAction();
	}
	@FXML
	private void DaozhiAction(ActionEvent e) {
		if (imageView.getNodeOrientation().name().equals("RIGHT_TO_LEFT")) {
			imageView.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
		} else {
			imageView.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		}
	}
	@FXML
	public void enlargeAction(ActionEvent event) {
		Enlarge_Small_Action.enlarge(imageView);
	}
	@FXML
	public void smallAction(ActionEvent event) {
		Enlarge_Small_Action.small(imageView);
	}
	@FXML
	public void resetAction(ActionEvent event) {
		new ResetAction(imageView);
	}
	@FXML
	public void rotateAction(ActionEvent event) {
		new RotateAction(imageView);
	}
	@FXML
	public void beautyAction(ActionEvent event) {
		ChangeService.change = this.imageView;
		new BeautyAction();

	}
	@FXML
	public void previousAction(ActionEvent event) {
		Previous_next_Action.changePicture(imageView, false);
	}
	@FXML
	public void nextAction(ActionEvent event) {
		Previous_next_Action.changePicture(imageView, true);
	}

	public ImageView getImageView() {
		return imageView;
	}
}

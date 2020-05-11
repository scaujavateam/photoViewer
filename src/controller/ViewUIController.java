package controller;

import action.*;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Button;

import model.PictureNode;
import service.ChangeService;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.control.Menu;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;

public class ViewUIController implements Initializable {
	@FXML
	private HBox hbox;
	@FXML
	private ImageView imageView;
	@FXML
	private Button enlargeBtn;
	@FXML
	private Button smallBtn;
	@FXML
	private Button resetBtn;
	@FXML
	private Button rotateBtn;
	@FXML
	private Button beautyBtn;
	@FXML
	private Button moreBtn;
	@FXML
	private Menu MenuMore;
	@FXML
	private Menu MenuHelp;
	// @FXML
	// private Text text;
	@FXML
	private Button previousImageBtn;
	@FXML
	private Button nextImageBtn;
	@FXML
	private ToolBar toolbar;

	private Image image;
	private ImageView origin;

	// public ViewUIController() {
	// viewUIController = this;
	//
	// }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initData();
	}
	

	private void initData() {

		image = new Image(PictureNode.getSelectedPictures().get(0).getURL());
		ChangeService.file = PictureNode.getSelectedPictures().get(0).getImageFile();

		ChangeService.origin = new ImageView(image);
		ChangeService.change = new ImageView(image);

		ChangeService.originHeight = imageView.getFitHeight();
		ChangeService.originWidth = imageView.getFitWidth();
		System.out.println(ChangeService.files.indexOf(ChangeService.file));
		if (ChangeService.files.indexOf(ChangeService.file)==0){
			previousImageBtn.setVisible(false);
		}
		if (ChangeService.files.indexOf(ChangeService.file)==ChangeService.files.size()-1){
			nextImageBtn.setVisible(false);
		}

		imageView.setPreserveRatio(true);
		imageView.setSmooth(true);
		imageView.setImage(image);
		origin=imageView;
		//toolbar.setVisible(true);
		enlargeBtn.setTooltip(new Tooltip("放大"));
		smallBtn.setTooltip(new Tooltip("缩小"));
		resetBtn.setTooltip(new Tooltip("重置"));
		rotateBtn.setTooltip(new Tooltip("旋转"));
		beautyBtn.setTooltip(new Tooltip("美化"));
		moreBtn.setTooltip(new Tooltip("更多"));
		previousImageBtn.setTooltip(new Tooltip("上一张"));
		nextImageBtn.setTooltip(new Tooltip("下一张"));

	}

	public ImageView getImageView() {
		return imageView;
	}

	@FXML
	private void Back(ActionEvent e) {
		new MainAction();
		Previous_next_Action.changePicture(imageView, true,previousImageBtn,nextImageBtn,-1);
	}

	@FXML
	private void Press() {
		if (toolbar.isVisible()) {
			toolbar.setVisible(false);
		} else {
			toolbar.setVisible(true);
		}
	}

	@FXML
	private void PPTAction(ActionEvent e) {
		new PPTAction();
	}

	@FXML
	private void Daozhi(ActionEvent e) {
		if (imageView.getNodeOrientation().name().equals("RIGHT_TO_LEFT")) {
			imageView.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
		} else {
			imageView.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		}
	}

	// Event Listener on Button[#enlargeBtn].onAction
	@FXML
	public void enlargeAction(ActionEvent event) {
		Enlarge_Small_Action.enlarge(imageView);

	}

	// Event Listener on Button[#smallBtn].onAction
	@FXML
	public void smallAction(ActionEvent event) {
		Enlarge_Small_Action.small(imageView);
	}

	// Event Listener on Button[#resetBtn].onAction
	@FXML
	public void resetAction(ActionEvent event) {
		new ResetAction(imageView);
	}

	// Event Listener on Button[#rotateBtn].onAction
	@FXML
	public void rotateAction(ActionEvent event) {
		new RotateAction(imageView);
	}

	// Event Listener on Button[#beautyBtn].onAction
	@FXML
	public void beautyAction(ActionEvent event) {
		ChangeService.change = this.imageView;
		new BeautyAction();
	}
	@FXML
	public void moreAction(ActionEvent event) {
		ChangeService.change = this.imageView;
		new MoreAction(); }
	// Event Listener on Button[#previousImageBtn].onAction
	@FXML
	public void previousAction(ActionEvent event) {
		Previous_next_Action.changePicture(imageView, false,previousImageBtn,nextImageBtn,ChangeService.files.indexOf(ChangeService.file));
	}

	// Event Listener on Button[#nextImageBtn].onAction
	@FXML
	public void nextAction(ActionEvent event) {
		Previous_next_Action.changePicture(imageView, true,previousImageBtn,nextImageBtn,ChangeService.files.indexOf(ChangeService.file));
	}
}

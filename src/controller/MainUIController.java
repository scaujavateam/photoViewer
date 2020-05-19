package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import model.FileTree;
import model.MyContextMenu;
import model.PictureFile;
import model.PictureNode;
import service.ChangeService;
import service.PaneListener;
import javafx.scene.Node;
import javafx.scene.control.Button;
import java.io.File;
import java.util.ArrayList;
import action.CopyAction;
import action.DeleteAction;
import action.OpenAction;
import action.PasteAction;
import action.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TreeView;

public class MainUIController{

	private MainUIController mainUI;
	private ArrayList<PictureNode> pictures;
	private ArrayList<File> files;
	public static String theFilePath;
	@FXML
	private TreeView<PictureFile> treeView;
	@FXML
	private FlowPane flowPane;
	@FXML
	private Text text, textTwo;

	public MainUIController() {	
		mainUI = this;
	}

	@FXML
	public void initialize() {
		pictures = new ArrayList<>();
		treeView = new FileTree(mainUI, treeView).gettreeView();
		new PaneListener(flowPane,mainUI);
		new MyContextMenu(flowPane, mainUI,false);
	}

	public FlowPane getFlowPane() {
		return flowPane;
	}
	public  ObservableList<Node> getFlowPaneChildren() {
		return flowPane.getChildren();
	}
	public Text getText() {
		return text;
	}
	public Text getTextTwo() {
		return textTwo;
	}
	public ArrayList<PictureNode> getPictures() {
		return pictures;
	}

	public void addPictures(PictureNode pNode) {
		pictures.add(pNode);
	}
	public void showPicture() {
		flowPane.getChildren().remove(0, flowPane.getChildren().size());
		for (PictureNode pNode : pictures) {
			flowPane.getChildren().add(pNode);
		}
		files = new ArrayList<File>();
    	for(int i=0;i<pictures.size();i++) {
    		files.add(pictures.get(i).getImageFile());
    	}
    	ChangeService.files=files;
	}
	public void clearPictures() {
		pictures.clear();
	}
	public void removePictures(PictureNode pNode) {
		for (PictureNode pictureNode : pictures) {
			if (pictureNode.equals(pNode)) {
				pictures.remove(pNode);
				break;
			}
		}
	}

	@FXML
	private void openBtnAction(ActionEvent event) {
		 new OpenAction();
	}
	@FXML
	private void copyBtnAction(ActionEvent event) {
		new CopyAction();
	}
	@FXML
	private void pasteBtnAction(ActionEvent event) {
		new PasteAction(mainUI);
	}
	@FXML
	private void deleteBtnAction(ActionEvent event) {
		new DeleteAction(mainUI);
	}
	@FXML
	private void PPTAction(ActionEvent event) {
		new PPTAction();
	}

}

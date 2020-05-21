package service;

import controller.MainUIController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import model.PictureNode;

import java.awt.*;
import java.io.IOException;


public class PaneListener {
	Node node;
	MainUIController mainUIController;
	private Rectangle selectRectangle;
	private double sx,sy;
	private boolean isDragged;

	public PaneListener(Node node,MainUIController mainUIController) {
		this.node = node;
		this.mainUIController = mainUIController;
		selectRectangle = new Rectangle(0,0,100,100);
		System.out.println("new");
		mainUIController.getPaneChildren().add(selectRectangle);
		selectRectangle.setWidth(0);
		selectRectangle.setHeight(0);
		selectRectangle.setFill(Color.valueOf("#cce8ff7f"));
		selectRectangle.setStrokeWidth(0.2);
		selectRectangle.setStroke(Color.BLUE);
		selectRectangle.setVisible(false);

		addListener();
	}
	private void addListener() {
		//鼠标按下，初始化选择矩阵的左上角点

		node.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				sx=event.getX();
				sy=event.getY();
				isDragged=false;
				selectRectangle.setX(sx);
				selectRectangle.setY(sy);


				selectRectangle.setVisible(true);
			}
		});
		node.setOnDragDetected(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				node.startFullDrag();
				isDragged = true;
			}
		});
		node.setOnMouseDragOver(new EventHandler<MouseDragEvent>() {
			@Override
			public void handle(MouseDragEvent event) {
				double sceneX=event.getX();
				double sceneY=event.getY();

				double width=Math.abs(sceneX-sx);
				double height=Math.abs(sceneY-sy);
				selectRectangle.setX(Math.min(sceneX,sx));
				selectRectangle.setY(Math.min(sceneY,sy));
				selectRectangle.setWidth(width);
				selectRectangle.setHeight(height);
			}

		});



		node.setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				double sceneX=event.getX();
				double sceneY=event.getY();

				double width=Math.abs(sceneX-sx);
				selectRectangle.setX(Math.min(sceneX,sx));
				selectRectangle.setY(Math.min(sceneY,sy));
				selectRectangle.setWidth(width);
				double height=Math.abs(sceneY-sy);
				selectRectangle.setHeight(height);

				if (isDragged){
				PictureNode.clearSelected();
				for(Node childrenNode:  mainUIController.getFlowPaneChildren()) {
					if(childrenNode instanceof PictureNode) {
						if(isRectOverlap((PictureNode)childrenNode))
							((PictureNode)childrenNode).setSelected(true);
					}
//					((PictureNode)childrenNode).setSelected(false);
				}

				selectRectangle.setWidth(0);
				selectRectangle.setHeight(0);
				selectRectangle.setVisible(false);
			}
			}
		});
	}
	private boolean isRectOverlap(PictureNode  pictureNode) {
		double imageNodeCenterPointX = pictureNode.getLayoutX() + pictureNode.getWidth()/2.0;
		double imageNodeCenterPointY = pictureNode.getLayoutY() + pictureNode.getHeight()/2.0;
		double selectRectangleCenterPointX = selectRectangle.getX() + selectRectangle.getWidth()/2.0;
		double selectRectangleCenterPointY = selectRectangle.getY() + selectRectangle.getHeight()/2.0;
		return Math.abs(imageNodeCenterPointX - selectRectangleCenterPointX) <= (pictureNode.getWidth()/2.0 + selectRectangle.getWidth()/2.0) &&
				Math.abs(imageNodeCenterPointY - selectRectangleCenterPointY) <= (pictureNode.getHeight()/2.0 + selectRectangle.getHeight()/2.0);
	}
}

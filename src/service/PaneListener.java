package service;

import controller.MainUIController;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.PictureNode;


public class PaneListener {
	Node node;
	MainUIController mainUIController;
	private Rectangle selectRectangle;
	private double sx,sy;

	public PaneListener(Node node,MainUIController mainUIController) {
		this.node = node;
		this.mainUIController = mainUIController;
		selectRectangle = new Rectangle(0,0,100,100);

		addListener();
	}
	private void addListener() {
		//鼠标按下，初始化选择矩阵的左上角点
		node.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
			this.sx = e.getX();
			this.sy = e.getY();
			System.out.println(this.sx+" "+this.sy);
			if(mainUIController.getPaneChildren().indexOf(selectRectangle)==-1){
				mainUIController.getPaneChildren().add(selectRectangle);
				selectRectangle.relocate(2,1.5);}
			selectRectangle.setX(sx);
			selectRectangle.setY(sy);
			selectRectangle.setHeight(0);
			selectRectangle.setWidth(0);
			selectRectangle.setFill(Color.valueOf("#aabbff7f"));
			selectRectangle.setStrokeWidth(0.2);
			selectRectangle.setStroke(Color.BLUE);

			selectRectangle.setVisible(true);
		});

		node.addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent e) -> {
			double w=e.getX()-this.sx;
			double h=e.getY()-this.sy;
			if(w<0){
				selectRectangle.setWidth(-w);
				selectRectangle.setX(this.sx+w);
			}
			else{
				selectRectangle.setWidth(w);
			}
			if(h<0){
				selectRectangle.setHeight(-h);
				selectRectangle.setY(this.sy+h);
			}
			else {
				selectRectangle.setHeight(h);
			}
		});


		//鼠标放开，更新选择矩阵的左上角点以及边长
		node.addEventHandler(MouseEvent.MOUSE_RELEASED, (MouseEvent e) -> {
			double w=e.getX()-this.sx;
			double h=e.getY()-this.sy;
			if(w<0){
				selectRectangle.setWidth(-w);
				selectRectangle.setX(this.sx+w);
			}
			else{
				selectRectangle.setWidth(w);
			}
			if(h<0){
				selectRectangle.setHeight(-h);
				selectRectangle.setY(this.sy+h);
			}
			else {
				selectRectangle.setHeight(h);
			}
//			System.out.println(selectRectangle);

			//图片和选择矩阵的判断
			PictureNode.clearSelected();
			for(Node childrenNode:  mainUIController.getFlowPaneChildren()) {
				if(childrenNode instanceof PictureNode) {
					if(isRectOverlap((PictureNode)childrenNode))
						((PictureNode)childrenNode).setSelected(true);
				}
//					((PictureNode)childrenNode).setSelected(false);
			}

			selectRectangle.setVisible(false);
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

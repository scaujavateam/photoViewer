package action;

import controller.ViewUIController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import service.ChangeService;

public class Enlarge_Small_Action extends ViewUIController {

	private static int changeNum = 0;

	public static void enlarge(ImageView imageView) {
		changeNum+=2;
		 Timeline timeline = new Timeline(60);
		 timeline.setCycleCount(1);
		KeyValue keyValue = new KeyValue(imageView.scaleXProperty(), changeNum*0.1+1);
		KeyValue keyValue2 = new KeyValue(imageView.scaleYProperty(), changeNum*0.1+1);
		Duration duration = Duration.seconds(0.3);

		KeyFrame keyFrame1 = new KeyFrame(duration, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

			}
		}, keyValue, keyValue2);

		timeline.getKeyFrames().add(keyFrame1);
		timeline.play();





	}
	public static void small(ImageView imageView) {
		changeNum-=2;
		Timeline timeline = new Timeline(60);
		timeline.setCycleCount(1);
		KeyValue keyValue = new KeyValue(imageView.scaleXProperty(), changeNum*0.1+1);
		KeyValue keyValue2 = new KeyValue(imageView.scaleYProperty(), changeNum*0.1+1);
		Duration duration = Duration.seconds(0.2);

		KeyFrame keyFrame1 = new KeyFrame(duration, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

			}
		}, keyValue, keyValue2);

		timeline.getKeyFrames().add(keyFrame1);
		timeline.play();
	}

}

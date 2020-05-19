package action;

import javafx.scene.image.ImageView;

public class Enlarge_Small_Action {

	public static int changeNum = 1;

	public static void enlarge(ImageView imageView) {
		changeNum +=1;
		imageView.setFitWidth(1010*(changeNum*0.1+1));
		imageView.setFitHeight(564*(changeNum*0.1+1));
	}
	public static void small(ImageView imageView) {
		changeNum -=1;
		imageView.setFitWidth(1010*(changeNum*0.1+1));
		imageView.setFitHeight(564*(changeNum*0.1+1));
	}

}

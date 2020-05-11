package action;

import com.sun.org.apache.bcel.internal.generic.NEW;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.MatrixType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.paint.*;
import service.ChangeService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.awt.Paint.*;

public class ClipAction {
    ImageView imageView;
    Stage stage;
    HBox hBox;
    Rectangle rect;
    AnchorPane an;
    public static int num=1;
    double sceneX_start,screenX_start;
    double sceneY_start,screenY_start;
    double sceneX_end;
    double sceneY_end;





    public ClipAction() {
        stage = new Stage();
        an = new AnchorPane();
        an.setStyle("-fx-background-color:#B5B5B522");
        Scene scene = new Scene(an);
        scene.setFill(Paint.valueOf("#ffffff00"));
        stage.setScene(scene);
//        stage.setFullScreen(true);

//        stage.setX(ChangeService.stage.getX()+7);
//        stage.setY(ChangeService.stage.getY());
//        ChangeService.change.getX();
//        ChangeService.origin
        Bounds bo=ChangeService.change.getLayoutBounds();
        Point2D point1=ChangeService.change.localToScene(bo.getMinX(),bo.getMinY());
        System.out.println( point1.getX()+" "+point1.getY());
        System.out.println(ChangeService.change.getLayoutX()+" "+ChangeService.change.getLayoutY());
        stage.setX(ChangeService.change.getLayoutX()+164);
        stage.setY(ChangeService.change.getLayoutY()+60);
        stage.setHeight(ChangeService.change.prefHeight(-1));
        stage.setWidth(ChangeService.change.prefWidth(-1));

//        stage.setHeight(ChangeService.stage.getHeight());
//        stage.setWidth(ChangeService.stage.getWidth()-7);
//        System.out.println("stageX"+stage.getX());
        stage.setFullScreenExitHint("");
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
        drag(an);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode()== KeyCode.ESCAPE){
                    stage.close();
                }
            }
        });

    }

    public void drag(AnchorPane an){
        an.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                an.getChildren().clear();
                rect=new Rectangle();
                rect.setFill(Color.TRANSPARENT);
                rect.setStroke(Paint.valueOf("#4f8bf5"));
                rect.setStrokeWidth(2.0);
                sceneX_start=event.getX();
                screenX_start=event.getScreenX();
                sceneY_start=event.getY();
                screenY_start=event.getScreenY();

                rect.setX(sceneX_start);
                rect.setY(sceneY_start);
                System.out.println(sceneX_start+" "+sceneY_start);
                an.getChildren().add(rect);

            }
        });
        an.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                an.startFullDrag();
            }
        });
        an.setOnMouseDragOver(new EventHandler<MouseDragEvent>() {
            @Override
            public void handle(MouseDragEvent event) {
                Label la=new Label();
                la.setAlignment(Pos.CENTER);
                la.setPrefHeight(20);
                la.setPrefWidth(100);

                an.getChildren().add(la);
                AnchorPane.setLeftAnchor(la,sceneX_start);
                AnchorPane.setTopAnchor(la,sceneY_start-la.getPrefHeight());
                la.setTextFill(Paint.valueOf("#ffffff"));
                la.setStyle("-fx-background-color: #000000");
                double sceneX=event.getX();
                double sceneY=event.getY();

                double width=Math.abs(sceneX-sceneX_start);
                double height=Math.abs(sceneY-sceneY_start);
                rect.setX(Math.min(sceneX,sceneX_start));
                rect.setY(Math.min(sceneY,sceneY_start));
                rect.setWidth(width);
                rect.setHeight(height);
                la.setText("宽度:"+(int)width+"高度:"+(int)height);
            }

        });
        an.setOnMouseDragExited(new EventHandler<MouseDragEvent>() {
            @Override
            public void handle(MouseDragEvent event) {
                sceneX_end=event.getX();
                sceneY_end=event.getY();

                Button button=new Button("选择完成");
                button.setPrefWidth(70);
                an.getChildren().add(button);
                AnchorPane.setLeftAnchor(button,sceneX_end-button.getPrefWidth());
                AnchorPane.setTopAnchor(button,sceneY_end+button.getPrefHeight());
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            getScreenImg();
                        } catch (AWTException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });

    }
    public void getScreenImg() throws AWTException, IOException {
        stage.close();
        double w=Math.abs(sceneX_end-sceneX_start);
        double h=Math.abs(sceneY_end-sceneY_start);
        Robot robot=new Robot();
        java.awt.Rectangle rec=new java.awt.Rectangle((int)(screenX_start*1.5), (int)(screenY_start*1.5), (int)(w*1.5),(int)(h*1.5));
        BufferedImage bufferedImage=robot.createScreenCapture(rec);
        WritableImage wi= SwingFXUtils.toFXImage(bufferedImage,null);
        int i=ChangeService.file.toURI().getPath().lastIndexOf(".");
        String str=new String(ChangeService.file.toURI().getPath().substring(0, i)
                +num+".png");
        ImageIO.write(bufferedImage,"png", new File(str));
        num++;
    }

}

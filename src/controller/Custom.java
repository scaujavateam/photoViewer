package controller;

import java.awt.Desktop;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import action.ClipAction;
import action.OpenAction;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.effect.Blend;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.Effect;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.ChangeService;

public class Custom implements Initializable {
    private final Desktop desktop = Desktop.getDesktop();
    private final FileChooser fileChooser = new FileChooser();
    // private Image image;
    private Stage stage;
    private  int num=1;

    @FXML
    private Button small;

    @FXML
    private Button cancel;

    @FXML
    private ProgressIndicator indicator;

    @FXML
    private Button big;

    @FXML
    private AnchorPane rightpane;

    @FXML
    private Button sure;

    @FXML
    private Region veil;

    @FXML
    private HBox toppane;

    @FXML
    private BorderPane borderpane;

    @FXML
    private TextArea textarea;

    @FXML
    private Button SaveButton;

    @FXML
    private Slider saturation;

    @FXML
    private Slider brightness;

    @FXML
    private AnchorPane leftpane;

    @FXML
    private ImageView imageview;

    @FXML
    private Slider contrast;

    @FXML
    private AnchorPane backpane;

    @FXML
    private Slider hue;

    @FXML
    private AnchorPane Existpane;

    @FXML
    private Label savelabel;

    @FXML
    private void Change(ActionEvent event) {
        ChangeService.change=imageview;
        // App.setStage(stage);
    }

    @FXML
    private void Back(ActionEvent event) {
        backpane.setVisible(true);
        textarea.setStyle("-fx-background-color:  #2e2d2d;");
        sure.setDisable(false);
        cancel.setDisable(false);
    }

    @FXML
    private void Sure(ActionEvent event) {
        ChangeService.change=ChangeService.origin;
        // App.setStage(stage);
        new OpenAction();;
    }

    @FXML
    private void Cancel(ActionEvent event) {

        backpane.setVisible(false);
        sure.setDisable(true);
        cancel.setDisable(true);
    }

    public void setImageViewImage(ImageView image) {
        image.setImage(ChangeService.change.getImage());
        image.setEffect(ChangeService.change.getEffect());
        image.setViewport(ChangeService.change.getViewport());
        image.setNodeOrientation(ChangeService.change.getNodeOrientation());
        image.setRotate(ChangeService.change.getRotate());
    }

    private ColorAdjust colorAdjust;

    private void setImageViewEffect() {
        colorAdjust=new ColorAdjust();
        //亮度
        brightness.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                System.out.println("亮度"+newValue);
                colorAdjust.setBrightness(newValue.doubleValue());
            }
        });
        //对比度
        contrast.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                System.out.println("对比度"+newValue);
                colorAdjust.setContrast(newValue.doubleValue());
            }
        });
        //色调
        hue.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                System.out.println("色调"+newValue);
                colorAdjust.setHue(newValue.doubleValue());
            }
        });
        //饱和度
        saturation.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                System.out.println("饱和度"+newValue);
                colorAdjust.setSaturation(newValue.doubleValue());
            }
        });

        brightness.setCursor(Cursor.HAND);
        contrast.setCursor(Cursor.HAND);
        hue.setCursor(Cursor.HAND);
        saturation.setCursor(Cursor.HAND);

        imageview.setEffect(colorAdjust);


    }

    public void setImage() {
        System.out.println(imageview);
        imageview.setImage(ChangeService.change.getImage());
        imageview.setEffect(ChangeService.change.getEffect());
        imageview.setViewport(ChangeService.change.getViewport());
        imageview.setNodeOrientation(ChangeService.change.getNodeOrientation());
        imageview.setRotate(ChangeService.change.getRotate());
        setImageViewEffect();
    }

    @FXML
    private void Undo(ActionEvent e) {
        imageview.setImage(ChangeService.change.getImage());
    }

    @FXML
    private void Close(ActionEvent e) {
        Existpane.setVisible(false);
    }

    public class SaveTask extends Task<Integer> {

        @Override

        protected Integer call() throws Exception {

            for (int i = 0; i < 250; i++) {

                updateProgress(i, 250);

                Thread.sleep(5);

            }

            return 1;
        }

    }

    @FXML
    private void Copy(ActionEvent event) {

        File file1 =ChangeService.file;

        if (file1.exists()) {
            Task<Integer> task = new SaveTask();

            veil.visibleProperty().bind(task.runningProperty());
            savelabel.visibleProperty().bind(task.runningProperty());
            indicator.visibleProperty().bind(task.runningProperty());
            new Thread(task).start();

            WritableImage image = imageview.snapshot(new SnapshotParameters(), null);
            String copyfilepath = null;

            String filename = file1.getName();
            String fileParentPath = file1.getParent();

            String name1 = filename.substring(0, filename.lastIndexOf("."));
            System.out.println(name1);
            int a = name1.lastIndexOf("-");
            int b = name1.lastIndexOf("-");
            if (a != -1 && b != -1) {
                String index = name1.substring(name1.lastIndexOf("-") + 1, name1.lastIndexOf("-"));
                if (index != "" && index != null) {
                    int n = Integer.valueOf(index);
                    n++;
                    copyfilepath = fileParentPath + "\\" + name1 + "-" + n + "-.jpg";
                }

            } else {
                copyfilepath = fileParentPath + "\\" + name1 + "-" + 1 + "-.jpg";
            }
            System.out.println(copyfilepath);
            File files = new File(copyfilepath);
            System.out.println(file1.getPath());
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", files);
            } catch (IOException e) {
                e.printStackTrace();
            }

            ChangeService.change=imageview;
        } else {
            Existpane.setVisible(true);
        }

    }

    @FXML
    private void Save(ActionEvent event) {
        int i=ChangeService.file.toURI().getPath().lastIndexOf(".");
        String str=new String(ChangeService.file.toURI().getPath().substring(0, i)
                +num+".png");
        File file1 = new File (str);
        num++;
        if (file1.exists()) {
            Task<Integer> task = new SaveTask();

            veil.visibleProperty().bind(task.runningProperty());
            savelabel.visibleProperty().bind(task.runningProperty());
            indicator.visibleProperty().bind(task.runningProperty());
            new Thread(task).start();
            WritableImage image = imageview.snapshot(new SnapshotParameters(), null);

            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file1);
                ChangeService.change=imageview;
                ChangeService.change=imageview;
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Existpane.setVisible(true);
        }
    }

    @FXML
    private void Scroll(ScrollEvent e) {
        if (imageview.getBoundsInParent().getWidth() >= imageview.getFitWidth() * 2.5) {
            big.setDisable(true);
            big.setOpacity(0.6);
            if (e.getDeltaY() < 0) {
                scrollcount += (int) (e.getDeltaY() / 26);
                big.setDisable(false);
                big.setOpacity(1.0);
                imageview.setScaleX(1 + count * 0.1 + scrollcount * 0.1);
                imageview.setScaleY(1 + count * 0.1 + scrollcount * 0.1);
            }
        } else if (imageview.getBoundsInParent().getWidth() < imageview.getFitWidth()*0.7) {
            small.setDisable(true);
            small.setOpacity(0.6);
            if (e.getDeltaY() > 0) {
                scrollcount += (int) (e.getDeltaY() / 26);
                small.setDisable(false);
                small.setOpacity(1.0);
                imageview.setScaleX(1 + count * 0.1 + scrollcount * 0.1);
                imageview.setScaleY(1 + count * 0.1 + scrollcount * 0.1);
            }
        } else {
            scrollcount += (int) (e.getDeltaY() / 26);
            imageview.setScaleX(1 + count * 0.1 + scrollcount * 0.1);
            imageview.setScaleY(1 + count * 0.1 + scrollcount * 0.1);
        }

    }

    private int count = 0;
    private int scrollcount = 0;
    @FXML
    private void Big(ActionEvent e) {
        if (imageview.getBoundsInParent().getWidth() >= imageview.getFitWidth() * 2.5) {
            big.setDisable(true);
            big.setOpacity(0.6);
        } else {
            count++;
            small.setDisable(false);
            small.setOpacity(1.0);
            imageview.setScaleX(1 + count * 0.1 + scrollcount * 0.1);
            imageview.setScaleY(1 + count * 0.1 + scrollcount * 0.1);
        }

    }

    @FXML
    private void Small(ActionEvent e) {
        if (imageview.getBoundsInParent().getWidth() < imageview.getFitWidth() * 0.7) {
            small.setDisable(true);
            small.setOpacity(0.6);
        } else {
            count--;
            imageview.setScaleX(1 + count * 0.1 + scrollcount * 0.1);
            imageview.setScaleY(1 + count * 0.1 + scrollcount * 0.1);
            big.setDisable(false);
            big.setOpacity(1.0);

        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.setImage();
        ChangeService.stage.widthProperty().addListener((a) -> {
            imageview.setScaleX(1.0);
            imageview.setScaleY(1.0);
        });
        ChangeService.stage.heightProperty().addListener((a) -> {
            imageview.setScaleX(1.0);
            imageview.setScaleY(1.0);
        });
        imageview.fitWidthProperty()
                .bind(ChangeService.stage.widthProperty().subtract(rightpane.widthProperty()).divide(4).multiply(3));
        imageview.fitHeightProperty()
                .bind(ChangeService.stage.heightProperty().subtract(toppane.heightProperty()).divide(4).multiply(3));

//        ChangeButton.setTooltip(new Tooltip("裁剪和旋转"));
    }
}

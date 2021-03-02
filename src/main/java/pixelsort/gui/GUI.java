package pixelsort.gui;

import java.awt.image.BufferedImage;
import java.io.File;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.stage.FileChooser;
import javafx.scene.control.Button;

import pixelsort.filehandler.FileHandler;
import pixelsort.sorter.PixelSwapper;
import pixelsort.sorter.Sorter;

public class GUI extends Application {

    FileHandler fileHandler;
    Stage mainStage;
    ImageView imageView;

    public static void main(String[] args) {
        //launch gui
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Pixelsort");
        //set window size properties
        stage.setHeight(600);
        stage.setWidth(900);
        stage.setMinHeight(600);
        stage.setMinWidth(900);

        fileHandler = new FileHandler(this);
        mainStage = stage;
        
        setMainScene(stage);
    }

    public void setMainScene(Stage stage) {

        GridPane root = new GridPane();
        root.setPadding(new Insets(20, 20, 20, 20));

        GridPane imagePane = new GridPane();
        imagePane.setMaxSize(300, 400);

        GridPane titlePane = new GridPane();
        titlePane.setHgap(20);

        GridPane IO = new GridPane();
        IO.setHgap(20);
        IO.setVgap(20);
        IO.setPadding(new Insets(0, 20, 0, 40));

        Label title = new Label("Pixelsort");
        title.setId("title");

        Label version = new Label("V 0.1");
        version.setId("version");

        Label fileName = new Label("no file selected");
        fileName.setId("fileName");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("jpg Files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter filter2 = new FileChooser.ExtensionFilter("png Files (*.png)", "*.png");
        FileChooser.ExtensionFilter filter3 = new FileChooser.ExtensionFilter("tif Files (*.tif)", "*.tif");
        fileChooser.getExtensionFilters().add(filter);
        fileChooser.getExtensionFilters().add(filter2);
        fileChooser.getExtensionFilters().add(filter3);

        ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableArrayList("Vertical Pixel Sort", "Horizontal Pixel Sort", "Pixel Merge", "avg. Vertical Pixel Sort", "avg. Horizontal Pixel Sort"));

        imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(imagePane.widthProperty());
        BufferedImage bufferedImage = new BufferedImage(400, 300, BufferedImage.TYPE_INT_RGB);

        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        imageView.setImage(image);

        Button openFile = new Button();
        openFile.setText("Open Image");
        openFile.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                fileHandler.loadImage(file);
                fileName.setText(fileHandler.getFileName());
                imageView.setImage(fileHandler.getImage());
            }
        });

        Button createImage = new Button();
        createImage.setText("Run");
        createImage.setOnAction(e -> {
            int mode = choiceBox.getSelectionModel().getSelectedIndex();
            if (mode != -1) {
                if (mode == 2) {
                    File mergeFile = fileChooser.showOpenDialog(stage);
                    FileHandler swapFileHandler = new FileHandler(this);
                    swapFileHandler.loadImage(mergeFile);
                    PixelSwapper swapper = new PixelSwapper(fileHandler, swapFileHandler);
                    swapper.swap();
                } else {
                    Sorter sorter = new Sorter(mode, fileHandler);
                    sorter.sort();
                }
            }
        });

        Button saveFile = new Button();
        saveFile.setText("Save Image");
        saveFile.setOnAction(e -> {
            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                fileHandler.saveImage(file);
            }
        });

        Button restoreImage = new Button();
        restoreImage.setText("Restore Image");
        restoreImage.setOnAction(e -> {
            fileHandler.restoreImage();
        });

        titlePane.add(title, 0, 0);
        titlePane.add(version, 1, 0);

        imagePane.add(imageView, 0, 0);

        IO.add(openFile, 0, 0);
        IO.add(choiceBox, 0, 1);
        IO.add(createImage, 1, 1);
        IO.add(saveFile, 0, 2);
        IO.add(restoreImage, 1, 2);

        root.add(titlePane, 0, 0);
        root.add(imagePane, 0, 1);
        root.add(fileName, 0, 2);
        root.add(IO, 1, 1);

        root.setVgap(20);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(GUI.class.getResource("/css/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void updateImageView() {
        imageView.setImage(fileHandler.getImage());
    }
}
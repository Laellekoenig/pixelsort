package pixelsort.filehandler;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import pixelsort.gui.GUI;

public class FileHandler {

    BufferedImage image;
    BufferedImage original;
    String fileName;
    GUI gui;

    public FileHandler(GUI gui) {
        this.gui = gui;
    }

    public void loadImage(File file) {

        this.fileName = file.getName();

        try {
            image = ImageIO.read(file);
            this.original = this.image;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveImage(File file) {

        try {
            if (file.createNewFile()) {
                ImageIO.write(image, "jpg", file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getFileName() {
        return fileName;
    }

    public BufferedImage getBufferedImage() {
        return image;
    }

    public BufferedImage getOriginalBufferedImage() {
        return original;
    }

    public Image getImage() {
        return SwingFXUtils.toFXImage(image, null);
    }

    public void updateImage(BufferedImage image) {
        this.image = image;
        gui.updateImageView();
    }

    public void restoreImage() {
        image = original;
        gui.updateImageView();
    }
}
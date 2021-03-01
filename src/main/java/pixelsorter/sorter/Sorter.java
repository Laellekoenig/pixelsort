package pixelsorter.sorter;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import pixelsorter.filehandler.FileHandler;
import pixelsorter.gui.GUI;

public class Sorter {

    int mode;   //0 = verticalSort, 1 = horizontalSort
    FileHandler handler;

    public Sorter(int mode, FileHandler handler) {
        this.mode = mode;
        this.handler = handler;
    }

    public void sort() {
        if (mode == 0) verticalPixelSort();
        if (mode == 1) horizontalPixelSort();
    }

    private void verticalPixelSort() {

        BufferedImage img = handler.getBufferedImage();
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage sorted = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < h; i++) {
            int[] pixels = new int[w];
            for (int j = 0; j < w; j++) {
                pixels[j] = img.getRGB(j, i);
            }

            Arrays.sort(pixels);

            for (int j = 0; j < w; j++) {
                sorted.setRGB(j, i, pixels[j]);
            }
        }

        handler.updateImage(sorted);
    }

    private void horizontalPixelSort() {

        BufferedImage img = handler.getBufferedImage();
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage sorted = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < w; i++) {
            int[] pixels = new int[h];
            for (int j = 0; j < h; j++) {
                pixels[j] = img.getRGB(i, j);
            }

            Arrays.sort(pixels);

            for (int j = 0; j < h; j++) {
                sorted.setRGB(i, j, pixels[j]);
            }
        }

        handler.updateImage(sorted);
    }
}

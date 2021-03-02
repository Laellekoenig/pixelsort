package pixelsort.sorter;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import pixelsort.filehandler.FileHandler;
import pixelsort.sorter.image.Pixel;
import pixelsort.sorter.image.SortbyAverage;
import pixelsort.sorter.image.Utilities;

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
        if (mode == 3) averageVerticalPixelSort();
    }

    private void horizontalPixelSort() {

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

    private void verticalPixelSort() {

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

    private void averageVerticalPixelSort() {

        BufferedImage img = handler.getBufferedImage();
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage sorted = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        Pixel[] pixels = Utilities.getPixels(img);
        Arrays.sort(pixels, new SortbyAverage());
        sorted = Utilities.imageFromPixels(pixels, w, h);

        handler.updateImage(sorted);
    }
}

package pixelsort.sorter;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import pixelsort.filehandler.FileHandler;
import pixelsort.sorter.image.Pixel;
import pixelsort.sorter.image.SortByAverage;
import pixelsort.sorter.image.Utilities;

public class Sorter {

    int mode;   //0 = verticalSort,
                // 1 = horizontalSort,
                // 3 = averageVerticalSort,
                // 4 = averageHorizontalSort
                // 5 = weird af sort
                // 6 = average pixels
    FileHandler handler;

    public Sorter(int mode, FileHandler handler) {
        this.mode = mode;
        this.handler = handler;
    }

    public void sort() {
        if (mode == 0) verticalPixelSort();
        if (mode == 1) horizontalPixelSort();
        if (mode == 3) averageVerticalPixelSort();
        if (mode == 4) averageHorizontalPixelSort();
        if (mode == 5) theOtherOne();
        if (mode == 6) averagePixelSort();
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

        for (int i = 0; i < w; i++) {
            Pixel[] pixels = new Pixel[h];
            for (int j = 0; j < h; j++) {
                pixels[j] = new Pixel(-1, img.getRGB(i, j));
            }

            Arrays.sort(pixels, new SortByAverage());

            for (int j = 0; j < h; j++) {
                sorted.setRGB(i, j, pixels[j].getRGB());
            }
        }

        handler.updateImage(sorted);
    }

    private void averageHorizontalPixelSort() {

        BufferedImage img = handler.getBufferedImage();
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage sorted = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < h; i++) {
            Pixel[] pixels = new Pixel[w];
            for (int j = 0; j < w; j++) {
                pixels[j] = new Pixel(-1, img.getRGB(j, i));
            }

            Arrays.sort(pixels, new SortByAverage());

            for (int j = 0; j < w; j++) {
                sorted.setRGB(j, i, pixels[j].getRGB());
            }
        }

        handler.updateImage(sorted);
    }

    //TODO: Not in UI yet
    private void averagePixelSort() {

        BufferedImage img = handler.getBufferedImage();
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage sorted = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        Pixel[] pixels = Utilities.getPixels(img);
        Arrays.sort(pixels, new SortByAverage());
        sorted = Utilities.imageFromPixels(pixels, w, h);

        handler.updateImage(sorted);
    }

    private void theOtherOne() {

        BufferedImage img = handler.getBufferedImage();
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage sorted = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                sorted.setRGB(j, i, img.getRGB(j, i) * 2);
            }
        }

        handler.updateImage(sorted);
    }
}

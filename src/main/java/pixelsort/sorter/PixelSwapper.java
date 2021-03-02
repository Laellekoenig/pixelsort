package pixelsort.sorter;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import pixelsort.filehandler.FileHandler;
import pixelsort.sorter.image.Pixel;
import pixelsort.sorter.image.SortBy;
import pixelsort.sorter.image.Utilities;

public class PixelSwapper {

    FileHandler fileHandler;
    FileHandler swapFileHandler;

    public PixelSwapper(FileHandler fileHandler, FileHandler swapFileHandler) {
        this.fileHandler = fileHandler;
        this.swapFileHandler = swapFileHandler;
    }

    public void swap() {

        BufferedImage img = fileHandler.getBufferedImage();
        BufferedImage img2 = swapFileHandler.getBufferedImage();

        int w = img.getWidth();
        int h = img.getHeight();
        int numPixels = w * h;

        int w2 = img2.getWidth();
        int h2 = img.getHeight();
        int numPixels2 = w2 * h2;

        if (numPixels > numPixels2) return;

        int[] pixels = Utilities.getIntPixels(img);
        int[] pixels2 = Utilities.getIntPixels(img2);

        Pixel[] p = new Pixel[pixels.length];
        Pixel[] p2 = new Pixel[pixels2.length];

        int count = 0;
        for (int pixel : pixels) {
            p[count] = new Pixel(count, pixel);
            count++;
        }

        count = 0;
        for (int pixel : pixels2) {
            p2[count] = new Pixel(count, pixel);
            count++;
        }

        Arrays.sort(p, new SortBy());
        Arrays.sort(p2, new SortBy());

        int[] swappedPixels = new int[numPixels];

        for (int i = 0; i < numPixels; i++) {
            int index = p[i].getIndex();
            swappedPixels[index] = p2[i].getRGB();
        }

        fileHandler.updateImage(Utilities.imageFrom1DArray(swappedPixels, w, h));
    }
}

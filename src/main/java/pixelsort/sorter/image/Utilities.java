package pixelsort.sorter.image;

import java.awt.image.BufferedImage;

public class Utilities {

    public static int[] getIntPixels(BufferedImage img) {

        int h = img.getHeight();
        int w = img.getWidth();
        int[] pixels = new int[w * h];

        int count = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                pixels[count] = img.getRGB(j, i);
                count += 1;
            }
        }

        return pixels;
    }

    public static Pixel[] getPixels(BufferedImage img) {

        int w = img.getWidth();
        int h = img.getHeight();
        Pixel[] pixels = new Pixel[w * h];

        int count = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                pixels[count] = new Pixel(count, img.getRGB(j, i));
                count++;
            }
        }
        return pixels;
    }

    public static BufferedImage imageFrom1DArray(int[] a, int w, int h) {

        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        int count = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                int rgb = a[count];
                count += 1;
                img.setRGB(j, i, rgb);
            }
        }

        return img;
    }

    public static BufferedImage imageFromPixels(Pixel[] pixels, int w, int h) {

        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        int count = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                int rgb = pixels[count].getRGB();
                count++;
                img.setRGB(j, i, rgb);
            }
        }

        return img;
    }
}

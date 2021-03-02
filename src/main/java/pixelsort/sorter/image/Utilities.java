package pixelsort.sorter.image;

import java.awt.image.BufferedImage;

public class Utilities {

    public static int[] getPixels(BufferedImage img) {

        int h = img.getHeight();
        int w = img.getWidth();
        int[] pixels = new int[h * w];


        int c = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                pixels[c] = img.getRGB(j, i);
                c += 1;
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
}

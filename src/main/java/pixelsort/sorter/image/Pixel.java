package pixelsort.sorter.image;

import java.awt.Color;

public class Pixel {

    int index;
    int rgb;

    public Pixel(int index, int rgb) {
        this.index = index;
        this.rgb = rgb;
    }

    public int getIndex() {
        return index;
    }

    public int getRGB() {
        return rgb;
    }

    public int getRed() {
        Color c = new Color(rgb);
        return c.getRed();
    }

    public int getGreen() {
        Color c = new Color(rgb);
        return c.getGreen();
    }

    public int getBlue() {
        Color c = new Color(rgb);
        return c.getBlue();
    }
}

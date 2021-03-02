package pixelsort.sorter.image;

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
}

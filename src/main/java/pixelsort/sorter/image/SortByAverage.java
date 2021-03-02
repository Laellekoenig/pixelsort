package pixelsort.sorter.image;

import java.util.Comparator;

public class SortByAverage implements Comparator<Pixel> {

    public int compare(Pixel a, Pixel b) {
        int difference = 0;

        int red = 0;
        int green = 0;
        int blue = 0;

        int red2 = 0;
        int green2 = 0;
        int blue2 = 0;

        red += a.getRed();
        green += a.getGreen();
        blue += a.getBlue();

        red2 += b.getRed();
        green2 += b.getGreen();
        blue2 += b.getBlue() ;

        difference += red - red2;
        difference += green - green2;
        difference += blue - blue2;

        return difference;
    }
}

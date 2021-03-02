package pixelsort.sorter.image;

import java.util.Comparator;

public class SortBy implements Comparator<Pixel> {

    public int compare(Pixel a, Pixel b) {
        return a.rgb - b.rgb;
    }
}

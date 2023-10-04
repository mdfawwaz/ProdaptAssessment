package range;
import range.Range;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class Tests {

    @Test
    public void testIntersection() {
        // Test case 1: Ranges intersect
        Range<Integer> range1 = Range.of(1, 10);
        Range<Integer> range2 = Range.of(5, 15);

        Range<Integer> intersectionRange = range1.intersection(range2);

        assertNotNull(intersectionRange);
        assertEquals(5, intersectionRange.getStart());
        assertEquals(10, intersectionRange.getEnd());

        // Test case 2: Ranges do not intersect
        Range<Integer> noIntersectionRange = range1.intersection(Range.of(15, 20));
        assertNull(noIntersectionRange);
    }

    @Test
    public void testFit() {
        // Test case 1: Element within the range
        Range<Integer> intRange = Range.of(1, 10);

        assertEquals(Integer.valueOf(5), intRange.fit(5));

        // Test case 2: Element below the range
        assertEquals(Integer.valueOf(1), intRange.fit(0));

        // Test case 3: Element above the range
        assertEquals(Integer.valueOf(10), intRange.fit(15));
    }
}

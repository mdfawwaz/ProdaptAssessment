package range;

import java.util.Scanner;
import java.util.Objects;

public class Range<T extends Comparable<T>> {
    private T start;
    private T end;

    private Range(T start, T end) {
        this.start = start;
        this.end = end;
    }

    public static <T extends Comparable<T>> Range<T> of(T start, T end) {
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException("Start is greater than end.");
        }
        return new Range<>(start, end);
    }

    public boolean contains(T value) {
        return value.compareTo(start) >= 0 && value.compareTo(end) <= 0;
    }

    public boolean containsRange(Range<T> otherRange) {
        return start.compareTo(otherRange.start) <= 0 && end.compareTo(otherRange.end) >= 0;
    }

    public Range<T> merge(Range<T> otherRange) {
        if (end.compareTo(otherRange.start) < 0 || start.compareTo(otherRange.end) > 0) {
            throw new RangeMergeException("Ranges cannot be merged");
        }

        T newStart = start.compareTo(otherRange.start) < 0 ? start : otherRange.start;
        T newEnd = end.compareTo(otherRange.end) > 0 ? end : otherRange.end;
        return new Range<>(newStart, newEnd);
    }

    public Range<T> intersection(Range<T> otherRange) {
        if (end.compareTo(otherRange.start) < 0 || start.compareTo(otherRange.end) > 0) {
            throw new RangeMergeException("Ranges can't intersect.");
        }

        T newStart = start.compareTo(otherRange.start) < 0 ? otherRange.start : start;
        T newEnd = end.compareTo(otherRange.end) > 0 ? otherRange.end : end;
        return new Range<>(newStart, newEnd);
    }

    public T fit(T element) {
        if (element.compareTo(start) < 0) {
            return start;
        } else if (element.compareTo(end) > 0) {
            return end;
        } else {
            return element;
        }
    }

    @Override
    public String toString() {
        return "Range:[" + start + ", " + end + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Range<?> range = (Range<?>) obj;
        return start.equals(range.start) && end.equals(range.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    public static class RangeMergeException extends RuntimeException {
        public RangeMergeException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Start value: ");
        int startValue = scanner.nextInt();

        System.out.println("Enter End value: ");
        int endValue = scanner.nextInt();

        Range<Integer> intRange = Range.of(startValue, endValue);

        System.out.println("Enter value to check in the range: ");
        int checkValue = scanner.nextInt();
        System.out.println("Value " + checkValue + " is in the range: " + intRange.contains(checkValue));

        System.out.println("Enter the start value of otherRange to merge: ");
        int mergeStart = scanner.nextInt();
        System.out.println("Enter the end value of otherRange to merge: ");
        int mergeEnd = scanner.nextInt();
        Range<Integer> mergeRange = Range.of(mergeStart, mergeEnd);

        try {
            Range<Integer> merged = intRange.merge(mergeRange);
            System.out.println("Ranges are Merged.\nNew " + merged);
        } catch (IllegalArgumentException e) {
            System.out.println("Ranges cannot be merged.");
        }
        System.out.println("Enter Start value for Range 1: ");
        int startValue1 = scanner.nextInt();

        System.out.println("Enter End value for Range 1: ");
        int endValue1 = scanner.nextInt();

        Range<Integer> range1 = Range.of(startValue1, endValue1);

        System.out.println("Enter Start value for Range 2: ");
        int startValue2 = scanner.nextInt();

        System.out.println("Enter End value for Range 2: ");
        int endValue2 = scanner.nextInt();

        Range<Integer> range2 = Range.of(startValue2, endValue2);

        // Demonstrate intersection
        Range<Integer> intersectionRange = range1.intersection(range2);
        if (intersectionRange != null) {
            System.out.println("Intersection Range: " + intersectionRange);
        } else {
            System.out.println("Ranges do not intersect.");
        }

        System.out.println("Enter a value to fit within the range: ");
        int fitValue = scanner.nextInt();
        int fittedValue = range1.fit(fitValue);
        System.out.println("Fitted Value: " + fittedValue);

        scanner.close();
    }

	public Integer getStart() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getEnd() {
		// TODO Auto-generated method stub
		return null;
	}
}


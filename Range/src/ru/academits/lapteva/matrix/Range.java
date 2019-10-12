package ru.academits.lapteva.matrix;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double x) {
        return x >= from && x <= to;
    }

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public Range getIntersection(Range range) {
        double start = Math.max(from, range.from);
        double end = Math.min(to, range.to);

        if (start >= end) {
            return null;
        }

        return new Range(start, end);
    }

    public Range[] getUnion(Range range) {
        if (Math.max(from, range.from) > Math.min(to, range.to)) {
            return new Range[]{new Range(from, to), new Range(range.from, range.to)};
        }

        double start = Math.min(from, range.from);
        double end = Math.max(to, range.to);
        return new Range[]{new Range(start, end)};
    }

    public Range[] getDifference(Range range) {
        if (range.from > to || from > range.to) {
            return new Range[]{new Range(from, to)};
        }

        if (from < range.from && to > range.to) {
            return new Range[]{new Range(from, range.from), new Range(range.to, to)};
        }

        if (from >= range.from && to <= range.to) {
            return new Range[]{};
        }

        if (from <= range.from && to <= range.to) {
            return new Range[]{new Range(from, range.from)};
        }

        return new Range[]{new Range(range.to, to)};
    }

    public void print() {
        System.out.printf("(%f, %f) ", from, to);
    }
}

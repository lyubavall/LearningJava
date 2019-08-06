package ru.academits.range;

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
        return (x >= from && x <= to);
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
        if (range.from >= to || from >= range.to) {
            return null;
        }

        if (from <= range.from && to >= range.to) {
            return new Range(range.from, range.to);
        }

        if (from > range.from && to < range.to) {
            return new Range(from, to);
        }

        if (from <= range.from && to <= range.to) {
            return new Range(range.from, to);
        }

        return new Range(from, range.to);
    }

    public Range[] getUnion(Range range) {
        if (range.from > to || from > range.to) {
            return new Range[]{new Range(from, to), new Range(range.from, range.to)};
        }

        if (from <= range.from && to >= range.to) {
            return new Range[]{new Range(from, to)};
        }

        if (from > range.from && to < range.to) {
            return new Range[]{new Range(range.from, range.to)};
        }

        if (from <= range.from && to <= range.to) {
            return new Range[]{new Range(from, range.to)};
        }

        return new Range[]{new Range(range.from, to)};
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

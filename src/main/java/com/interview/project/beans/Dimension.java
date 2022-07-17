package com.interview.project.beans;

import java.util.Objects;

public class Dimension implements  Comparable<Dimension>  {
    private int length;
    private int breadth;
    private int height;

    @Override
    public int compareTo(final Dimension that) {
        int thisVolume = this.length * this.breadth * this.height;
        int thatVolume = that.length * that.breadth * that.height;
        if (thisVolume < thatVolume) {
            return -1;
        } else if (thisVolume > thatVolume) {
            return 1;
        } else {
            return 0;
        }
    }

    public Dimension(int length, int breadth, int height) {
        this.length = length;
        this.breadth = breadth;
        this.height = height;
    }

    @Override
	public String toString() {
		return "Dimension [length=" + length + ", breadth=" + breadth + ", height=" + height + "]";
	}

	public int getLength() {
        return length;
    }

    public int getBreadth() {
        return breadth;
    }

    public int getHeight() {
        return height;
    }

    public void addDimension(final Dimension that) {
        this.length += that.length;
        this.breadth += that.breadth;
        this.height += that.height;
    }
}

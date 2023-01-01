class SliceData implements Comparable {

    private int frameNumber;

    private float difference;

    public void setDifference(float difference) {
        this.difference = difference;
    }

    public void setFrameNumber(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public float getDifference() {
        return difference;
    }

    public int compareTo(Object otherData) throws ClassCastException {
        if (!(otherData instanceof SliceData)) {
            throw new ClassCastException("A sliceData object expected.");
        }
        float otherDifference = ((SliceData) otherData).getDifference();
        if (difference < otherDifference) {
            return -1;
        } else if (difference > otherDifference) {
            return 1;
        } else {
            return 0;
        }
    }
}

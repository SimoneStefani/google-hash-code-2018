public class Ride {
    public int startRow;
    public int startCol;
    public int endRow;
    public int endCol;
    public int earlyStart;
    public int lateFinish;
    public int id;

    public Ride(int id, int startRow, int startCol, int endRow, int endCol, int earlyStart, int lateFinish) {
        this.id = id;
        this.startRow = startRow;
        this.startCol = startCol;
        this.endRow = endRow;
        this.endCol = endCol;
        this.earlyStart = earlyStart;
        this.lateFinish = lateFinish;
    }

    public int rideDistance() {
        return Math.abs(endRow - startRow) + Math.abs(endCol -  startCol);
    }

    @Override
    public String toString() {
        return "Ride{" +
                "startRow=" + startRow +
                ", startCol=" + startCol +
                ", endRow=" + endRow +
                ", endCol=" + endCol +
                ", earlyStart=" + earlyStart +
                ", lateFinish=" + lateFinish +
                ", id=" + id +
                '}';
    }


}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Vehicle {
    public int id;
    public List<Ride> rides;
    public int x;
    public int y;
    public int currentStep = 0;
    public int bonus;

//    public Vehicle(int id, int[] rides, int x, int y, int bonus) {
//        this.id = id;
//        this.rides = rides;
//        this.x = x;
//        this.y = y;
//        this.bonus = bonus;
//    }

    public Vehicle(int id, int x, int y, int bonus) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.bonus = bonus;
        this.rides = new ArrayList<>();
    }

    public int reward(Ride r) {
        int estimate = r.rideDistance();

        if (r.rideDistance() + distanceToRide(r) + this.currentStep > r.lateFinish) {
            return 0;
        }

        if (distanceToRide(r) + this.currentStep < r.earlyStart) {
            estimate += this.bonus;
        }

        return estimate;
    }

    public int distanceToRide(Ride r) {
        return Math.abs(this.x - r.startCol) + Math.abs(this.y - r.startRow);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", currentStep=" + currentStep +
                '}';
    }
}

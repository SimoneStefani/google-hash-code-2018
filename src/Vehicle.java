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


    public Vehicle(int id, int x, int y, int bonus) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.bonus = bonus;
        this.rides = new ArrayList<>();
    }

    public int reward(Ride r) {
//        int estimate = r.rideDistance();
        int estimate = distanceToRide(r);
        int deltaWait = this.currentStep + this.distanceToRide(r) - r.earlyStart;

        if (deltaWait < 0) deltaWait = 0;

        if (r.rideDistance() + distanceToRide(r) + this.currentStep + deltaWait > r.lateFinish) {
            return Integer.MAX_VALUE;
        }

        return estimate + deltaWait;

//
//        if (distanceToRide(r) + this.currentStep < r.earlyStart) {
//            estimate += this.bonus;
//        }
//
//        return estimate - deltaWait;
    }

    public int rewardBonus(Ride r) {
        int estimate = r.rideDistance();
//        int estimate = distanceToRide(r);
        int deltaWait = this.currentStep + this.distanceToRide(r) - r.earlyStart;

        if (deltaWait < 0) deltaWait = 0;

        if (r.rideDistance() + distanceToRide(r) + this.currentStep + deltaWait > r.lateFinish) {
            return Integer.MAX_VALUE;
        }

        if (distanceToRide(r) + this.currentStep < r.earlyStart) {
            estimate += this.bonus;
        }

        return estimate - deltaWait;
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

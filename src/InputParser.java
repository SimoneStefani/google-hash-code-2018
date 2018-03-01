/**
 * InputParser
 * <p>
 * Google HashCode 2017
 * Created by Marcel Eschmann, Cedric Seger and Simone Stefani on 01/03/2017.
 */

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class InputParser {

    /**
     * Input file
     */
    private String fileName;
    private String fileExtension;

    /**
     * Parsed input
     */
    public int numRows, numColumns, numVehicles, numRides, bonus, steps;

    public int BONUS_TRESHOLD = 25;

    private List<Ride> rides;
    private Ride[] ridesDone;
    private List<Vehicle> vehicles;
//    private Endpoint[] endpoints;
//    private PriorityQueue<Request> requests;

    /**
     * Constructor
     *
     * @param file
     */
    public InputParser(String file) {
        this.fileName = file.substring(0, file.lastIndexOf('.'));
        this.fileExtension = file.substring(file.lastIndexOf('.'), file.length());
        this.readInput();
    }

    /**
     * Run simulation
     */
    public void run() {

        this.rides.sort((o1, o2) -> {
            if (o1.earlyStart < o2.earlyStart) return -1;
            if (o1.earlyStart == o2.earlyStart) return 0;
            return 1;
        });

//        for (Ride r : this.rides) {
//            System.out.println(r);
//        }

        int tempCount = 0;
        boolean done = false;

        while (!done) {
            done = true;
            for (Vehicle v : this.vehicles) {

                Ride current = null;

                tempCount = 0;
                for (Ride r : this.rides) {
                    if (v.currentStep > r.lateFinish) continue;
                    tempCount++;
                    if (current == null || v.reward(r) < v.reward(current)) {
                        current = r;
                    }

                    if (tempCount > 50) break;
                }

                if (current == null) break;
                if (v.currentStep < this.steps) done = false;
                v.x = current.endCol;
                v.y = current.endRow;

                int deltaWait = v.currentStep + v.distanceToRide(current) - current.earlyStart;

                v.currentStep += current.rideDistance() + v.distanceToRide(current);
                if (deltaWait < 0) {
                    v.currentStep += Math.abs(deltaWait);
                }

                v.rides.add(current);

                this.rides.remove(current);
            }
        }


    }

    /**
     * Parses input file
     */
    private void readInput() {
        File inputFile = new File(this.fileName + this.fileExtension);

        try {
            Scanner in = new Scanner(inputFile);

            this.numRows = in.nextInt();
            this.numColumns = in.nextInt();
            this.numVehicles = in.nextInt();
            this.numRides = in.nextInt();
            this.bonus = in.nextInt();
            this.steps = in.nextInt();

            // RIDES
            this.rides = new ArrayList<>(this.numRides);

            for (int i = 0; i < this.numRides; i++) {
                int startRow = in.nextInt();
                int startCol = in.nextInt();
                int endRow = in.nextInt();
                int endCol = in.nextInt();
                int earlyStart = in.nextInt();
                int lateFinish = in.nextInt();
                this.rides.add(new Ride(i, startRow, startCol, endRow, endCol, earlyStart, lateFinish));

//                System.out.println(this.rides[i]);
            }

            this.vehicles = new ArrayList<>(this.numVehicles);

            for (int j = 0; j < this.numVehicles; j++) {
                this.vehicles.add(new Vehicle(j, 0, 0, this.bonus));
            }


            in.close();

        } catch (FileNotFoundException e) {
            // file not found.
            System.out.print("[ERROR] " + e.getMessage());
        }
    }

    /**
     * Write output to filename.out
     */
    public void writeOutput() {
        FileWriter out;
        File outputFile = new File(this.fileName + ".out");

        try {
            out = new FileWriter(outputFile);
            BufferedWriter bw = new BufferedWriter(out);

            for (Vehicle v : this.vehicles) {
                StringBuilder sb = new StringBuilder();
                sb.append(v.rides.size());
                for (Ride r : v.rides) {
                    sb.append(" " + r.id);
                }
                bw.write(sb.toString());
                bw.newLine();
            }


            bw.flush();
            bw.close();

        } catch (IOException e) {
            // file not found.
            System.out.print("[ERROR] " + e.getMessage());
        }


    }
}
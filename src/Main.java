/**
 * Main
 *
 * Google HashCode 2017
 * Created by Marcel Eschmann, Cedric Seger and Simone Stefani on 1/03/2018.
 */

public class Main {
    public static void main(String[] args) {
        String [] names = {"a_example.in", "b_should_be_easy.in", "c_no_hurry.in", "d_metropolis.in", "e_high_bonus.in"};

        for (String file : names) {
            InputParser parser = new InputParser(file);
            parser.run();
            parser.writeOutput();
            System.out.println();
        }
    }
}
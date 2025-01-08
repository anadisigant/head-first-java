package teste;

public class SimpleShipTestDrive {
        public static void main(String[] args) {
            SimpleShip theShip = new SimpleShip();

            int[] locations = {2, 3, 4};

            theShip.setLocationCells(locations);

            String userGuess = "2";

            String result = theShip.checkYourself(userGuess);
            String testResult = "failed";
            if (result.equals("hit")) {
                testResult = "passed";
            }

            System.out.println(testResult);
        }
    }
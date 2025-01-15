import java.util.*;

public class ShipBust {
    private GameHelper helper = new GameHelper();
    private ArrayList<Ship> shipList = new ArrayList<Ship>();
    private int numOfGuesses = 0;

    private void setUpGame() {
        Ship one = new Ship();
        one.setName("USSA");
        Ship two = new Ship();
        two.setName("USSB");
        Ship three = new Ship();
        three.setName("USSC");
        shipList.add(one);
        shipList.add(two);
        shipList.add(three);

        System.out.println("Seu objetivo é afundar três navios.");
        System.out.println("USSA, USSB, USSC");
        System.out.println("Tente afundá-los em menos tentativas possíveis");

        for (Ship shipToSet : shipList) {
            ArrayList<String> newLocation = helper.placeShip(3);
            shipToSet.setLocationCells(newLocation);
        }
    }

    private void startPlaying() {
        while (!shipList.isEmpty()) {
            String userGuess = helper.getUserInput("Insira um palpite");
            checkUserGuess(userGuess);
        }
        finishGame();
    }

    private void checkUserGuess(String userGuess) {
        numOfGuesses++;
        String result = "miss";

        for (Ship shipToTest : shipList) {
            result = shipToTest.checkYourself(userGuess);
            if (result.equals("hit")) {
                break;
            }
            if (result.equals("kill")) {
                shipList.remove(shipToTest);
                break;
            }
        }
        System.out.println(result);
    }

    private void finishGame() {
        System.out.println("Todas as naves foram afundadas!");
        if(numOfGuesses <= 18) {
            System.out.println("Você só precisou de " + numOfGuesses + " tentativas.");
            System.out.println("Você conseguiu!");
        } else {
            System.out.println("Demorou demais. " + numOfGuesses + " tentativas.");
            System.out.println("Você não conseguiu.");
        }
    }

    public static void main(String[] args) {
        ShipBust game = new ShipBust();
        game.setUpGame();
        game.startPlaying();
    }
}

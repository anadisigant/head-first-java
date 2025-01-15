package linha_de_comando;

public class ShipBust {
    public static void main(String[] args) {
        int numOfGuesses = 0;
        GameHelper helper = new GameHelper();
        SimpleShip theSimpleShip = new SimpleShip();
        int randomNum = (int) (Math.random() * 5);
        int[] locations = {randomNum, randomNum + 1, randomNum + 2};
        theSimpleShip.setLocationCells(locations);
        boolean isAlive = true;

        while (isAlive == true) {
            String guess = helper.getUserInput("Insira um número");
            String result = theSimpleShip.checkYourself(guess);
            numOfGuesses++;
            if (result.equals("kill")) {
                isAlive = false;
                System.out.println("Você afundou o navio em " + numOfGuesses + " tentativas");
            }
        }
    }
}

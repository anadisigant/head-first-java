package linha_de_comando;

public class Ship {
    private int[] locationCells;
    private int numOfHits = 0;

    public void setLocationCells(int[] locs) {
        locationCells = locs;
    }

    public String checkYourself(String userInput) {
        int guess = Integer.parseInt(userInput);
        String result = "miss";
    
        for (int i = 0; i < locationCells.length; i++) {
            if (locationCells[i] == guess) {
                result = "hit";
                locationCells[i] = -1;  
                numOfHits++;
                break;  
            }
        }
    
        if (numOfHits == locationCells.length) {
            result = "kill";
        }
        
        System.out.println(result);  
        return result;
    }
}
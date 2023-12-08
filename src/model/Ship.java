package model;

public class Ship {
    private ShipType type;
    private int size;
    private int[][] position;  // Coordinates of ship cells
    private boolean isHorizontal;

    public Ship(ShipType type) {
        this.type = type;
        switch(type){
            case AIRCRAFT_CARRIER:
                this.size = 5;
                break;
            case BATTLESHIP:
                this.size = 4;
                break;
            case SUBMARINE:
                this.size = 3;
                break;
            case CRUISER:
                this.size = 2;
                break;
            case DESTROYER:
                this.size = 3;
                break;
        }
        this.position = new int[size][2];
        this.isHorizontal = true;  // Default orientation is horizontal
    }

    public void rotate() {
        // Rotate the ship 90 degree


        isHorizontal = !isHorizontal;
    }

    // Getters and setters...

    public int getSize(){
        return size;
    }

    public int[][] getPosition() {
        return position;
    }

    // Additional methods...
}

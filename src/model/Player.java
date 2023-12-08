package model;

public class Player{
    boolean[][] ships;
    boolean [][] shipPlacements; //ti track placed ships
    public Player(){
        this.ships = new boolean[10][10];
        this.shipPlacements = new boolean[10][10];
        //initializing ships, just for testing
        // for (int i = 0; i < 10; i++){
        //     ships[i][i] = true;
        // }
    }
    public boolean isOccupied(int x, int y){
        if (ships[x][y]) return true;
        return false;
    }
    public void addShip(Ship ship, int x, int y, boolean isHorizontal) {
        if (isPlacementValid(ship, x, y, isHorizontal)) {
            // Update shipPlacements array accordingly
            updateShipPlacements(ship, x, y, isHorizontal);

            // Add the ship to the board
            int size = ship.getSize();
            for (int i = 0; i < size; i++) {
                if (isHorizontal) {
                    ships[x][y + i] = true;
                    ship.getPosition()[i] = new int[]{x, y + i};
                } else {
                    ships[x + i][y] = true;
                    ship.getPosition()[i] = new int[]{x + i, y};
                }
            }
        }
    }

    private boolean isPlacementValid(Ship ship, int x, int y, boolean isHorizontal) {
        // Add logic to check if the placement is valid based on your game rules
        // You need to consider ship size, boundaries, and whether cells are already occupied
        // Return true if the placement is valid, false otherwise
        return true;  // Placeholder; replace with actual logic
    }

    private void updateShipPlacements(Ship ship, int x, int y, boolean isHorizontal) {
        // Update the shipPlacements array based on the ship's placement
        // This is where you mark cells as occupied by ships
    }
}
package control; // Adjust package as needed

import view.*;
import model.*;
import javax.swing.*;
import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

public class GameController implements CellClickListener { //providing implementation for CellClickListener
    private GUIView guiView; //right now, is player 1's attack board; red = player 2's ships they've downed
    private GUIView guiView2;
    private Ship selectedShip;
    //right now, both players occupy the same spaces on the board
    Player p1 = new Player();
    Player p2 = new Player();
    boolean[][] p1hits = new boolean[10][10];  //ships on player 1's board that player 2 has hit
    boolean[][] p2hits = new boolean[10][10];
    private boolean isHorizontal;
    int p1hitCount = 0;
    int p2hitCount = 0;
    int iterations = 0;
    boolean isP1 = true;
    boolean turnActive = false;
    private Timer timer = new Timer();

    public GameController(GUIView guiView, GUIView guiView2) {
        this.guiView = guiView; 
        guiView.setCellClickListener(this); //makes GameController a CellClickListener
        this.guiView2 = guiView2; 
        guiView2.setCellClickListener(this);
    }
    //implementation
    @Override
    public void CellClick(int row, int col, JPanel cellPanel, GUIView currView) {
        //only listen for clicks once the game has started
        if (turnActive){
            //panel has been clicked, it's player 1's turn...
            if (isP1){
                //if they clicked the correct grid (bottom)
                guiView2.getFrame().setTitle("Player 1"); //changes name of frame to player 1
                if (row > 11){
                    //get the panel of player 2's upper board
                    row -= 12;
                    JPanel myPanel = guiView.getPanel(row, col);
                    //if that gridspace hasn't been hit yet...
                    if (!p2hits[row][col]){
                        //if clicked cell is an enemy (hit), turn red. if not, turn grey
                        if (p2.isOccupied(row, col)){
                            cellPanel.setBackground(Color.RED);
                            p2hitCount++;
                            myPanel.setBackground(Color.RED);
                        }
                        else{
                            cellPanel.setBackground(Color.GRAY);
                            myPanel.setBackground(Color.GRAY);
                        }
                        p2hits[row][col] = true;
                        isP1 = false;
                    }
                }
            }
            else {
                guiView.getFrame().setTitle("Player 2");
                if (row > 11){
                    row -= 12;
                    JPanel myPanel = guiView2.getPanel(row, col);
                    if (!p1hits[row][col]){
                        if (p1.isOccupied(row, col)){
                            cellPanel.setBackground(Color.RED);
                            p1hitCount++;
                            myPanel.setBackground(Color.RED);
                        }
                        else{
                            cellPanel.setBackground(Color.GRAY);
                            myPanel.setBackground(Color.GRAY);
                        }
                        p1hits[row][col] = true;
                        isP1 = true;
                    }
                }
            }
            //creating a TimerTask object where updateView() is run inside run(), happens every .6 seconds
            timer.schedule(new TimerTask() {
                public void run() {
                    updateView();
                }
            }, 600);
            turnActive = false;
        }
    }

    private void placeShips(Player player) {
        for (int i = 0; i < ShipType.values().length; i++) {
            ShipType shipType = ShipType.values()[i];
            Ship ship = new Ship(shipType);
    
            // Place ships with different starting positions
            player.addShip(ship, i, i, true);
    
            // You might want to add logic to handle boundaries
            if (i + ship.getSize() >= 10) {
                break;  // Break if you've reached the board boundaries, adjust as needed
            }
        }
    
        // Update the view after placing all ships for the current player
        updateView();
    }
    
    

    public void startGame() {
        // Additional setup logic if needed

        placeShips(p1);
        placeShips(p2);
        playerTurn();
        //guiView.hide();

        // Main game loop, commenting out now to run the game through the click event
        /*while (!isGameOver()) {
            // Perform player actions (e.g., handle turns, get input, update game state)
            playerTurn();

            // Additional game logic or checks

            // Update the view to reflect the current game state
            updateView();
        }

        // Game over logic (e.g., display winner, final statistics)
        displayGameOver();*/
    }

    private void playerTurn() {
        // Implement logic for handling a player's turn
        // This might include getting input from the player, updating the game state, etc.
        turnActive = true;
        System.out.println("playerTurn() ran");
    }

    private void updateView() {
        if (iterations == 0){
            //display ships
            for (int row = 0; row < 10; row++){
                for (int col = 0; col < 10; col++){
                    if (p1.isOccupied(row, col)){
                        JPanel cellPanel = guiView2.getPanel(row, col);
                        if (cellPanel != null) cellPanel.setBackground(Color.BLUE);
                    }
                }
            }
            for (int row = 0; row < 10; row++){
                for (int col = 0; col < 10; col++){
                    if (p2.isOccupied(row, col)){
                        JPanel cellPanel = guiView.getPanel(row, col);
                        if (cellPanel != null) cellPanel.setBackground(Color.BLUE);
                    }
                }
            }
        }

        Player currentPlayer = isP1 ? p1 : p2;
        GUIView currentView = isP1 ? guiView : guiView2;
    
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if (currentPlayer.isOccupied(row, col) && !p1hits[row][col] && !p2hits[row][col]){
                    JPanel cellPanel = currentView.getPanel(row, col);
                    if (cellPanel != null) cellPanel.setBackground(Color.BLUE);
                }
            }
        }
        /*if (currentPlayer.isOccupied(row, col)){
                if (!p1hits[row][col] && !p2hits[row][col]){
                    JPanel cellPanel = currentView.getPanel(row, col);
                    if (cellPanel != null) cellPanel.setBackground(Color.BLUE);
                }
                else{
                    JPanel cellPanel = currentView.getPanel(row, col);
                    if (cellPanel != null) cellPanel.setBackground(Color.RED);
                }
                }*/
        // Update the view to reflect the current game state
        // This involves calling methods on the GameView interface
        System.out.println("turn switched");
        if (isGameOver()){
            timer.cancel();
            guiView.getFrame().dispose();
            guiView2.getFrame().dispose();
            if (p1hitCount == 17){
                //logic
                System.out.println("Player 2 wins");
            }
            else{
                //logic
                System.out.println("Player 1 wins");
            }
        }
        else if (isP1){
            guiView.hide();
            guiView2.show();
            playerTurn();
        }
        else if (!isP1){
            guiView2.hide();
            guiView.show();
            playerTurn();
        }
        iterations++;
        // You might also call other methods on gameView to update the board state, etc.
    }

    private boolean isGameOver() {
        if (p1hitCount == 17 || p2hitCount == 17){
            return true;
        }
        // Implement logic to check if the game is over
        // This might involve checking win conditions, reaching a certain turn limit, etc.
        return false; // Placeholder; replace with actual game-over logic
    }

    private void displayGameOver() {
        // Implement logic to display the game-over state
        // This might include showing the winner, final statistics, etc.
        guiView.displayMessage("Game over! Player X wins!"); // Example message
    }
}

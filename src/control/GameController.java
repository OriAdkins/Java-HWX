package control; // Adjust package as needed

import view.*;
import javax.swing.*;
import java.awt.Color;

public class GameController implements CellClickListener { //providing implementation for CellClickListener
    private GUIView guiView;
    Player p1;
    Player p2;
    boolean[][] p1hits = new boolean[10][10];  //represents hit spaces on player 1's board
    boolean[][] p2hits = new boolean[10][10];
    boolean isP1 = true;
    boolean turnActive = false;

    public GameController(GUIView guiView) {
        this.guiView = guiView; 
        guiView.setCellClickListener(this); //makes GameController a CellClickListener
    }
    //implementation
    @Override
    public void onCellClicked(int row, int col, JPanel cellPanel) {
        //only listen for clicks once the game has started
        if (turnActive){
            //panel has been clicked, if it's player 1's turn...
            if (isP1){
                //if that gridspace hasn't been hit yet...
                if (!p2hits[row][col]){
                //if clicked cell is an enemy (hit), turn red. if not, turn grey
                    if (p2.isOccupied(row, col)) cellPanel.setBackground(Color.RED);
                    else cellPanel.setBackground(Color.GRAY);
                    p2hits[row][col] = true;
                    isP1 = false;
                }
            }
            else {
                if (!p1hits[row][col]){
                    if (p1.isOccupied(row, col)) cellPanel.setBackground(Color.RED);
                    else cellPanel.setBackground(Color.GRAY);
                    p1hits[row][col] = true;
                    isP1 = true;
                }
            }
            turnActive = false;
            updateView();
            System.out.println("Turn is over");
        }
    }

    public void startGame() {
        // Additional setup logic if needed
        playerTurn();

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
        // Update the view to reflect the current game state
        // This involves calling methods on the GameView interface
        guiView.displayMessage("It's the next player's turn."); // Example message
        System.out.println("turn switched");
        // You might also call other methods on gameView to update the board state, etc.
        playerTurn();
    }

    private boolean isGameOver() {
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

class Player{
    boolean[][] ships;
    public Player(){
        ships = new boolean[10][10];
        //initializing ships, just for testing
        for (int i = 0; i < 10; i++){
            ships[i][i] = true;
        }
    }
    public boolean isOccupied(int x, int y){
        if (ships[x][y]) return true;
        return false;
    }
}
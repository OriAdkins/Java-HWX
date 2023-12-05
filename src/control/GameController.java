package control; // Adjust package as needed

import view.*;
import javax.swing.*;
import java.awt.Color;

public class GameController implements CellClickListener { //providing implementation for CellClickListener
    private GUIView guiView;
    boolean[][] p1ships = new boolean[10][10]; //player 1's ships
    boolean[][] p1hits = new boolean[10][10];  //represents hit spaces on player 1's board
    boolean[][] p2ships = new boolean[10][10];
    boolean[][] p2hits = new boolean[10][10];
    boolean isP1 = true;
    boolean turnStarted = false;

    public GameController(GUIView guiView) {
        this.guiView = guiView; 
        guiView.setCellClickListener(this); //makes GameController a CellClickListener
    }
    //implementation
    @Override
    public void onCellClicked(int row, int col, JPanel cellPanel) {
        //only listen for clicks once the game has started
        if (turnStarted){
            //panel has been clicked, if it's player 1's turn...
            if (isP1){
                //if that gridspace hasn't been hit yet...
                if (!p2hits[row][col]){
                //if clicked cell is an enemy (hit), turn red. if not, turn grey
                    if (p2ships[row][col]) cellPanel.setBackground(Color.RED);
                    else cellPanel.setBackground(Color.GRAY);
                    p2hits[row][col] = true;
                    isP1 = false;
                }
            }
            else {
                if (!p1hits[row][col]){
                    if (p1ships[row][col]) cellPanel.setBackground(Color.RED);
                    else cellPanel.setBackground(Color.GRAY);
                    p1hits[row][col] = true;
                    isP1 = true;
                }
            }
            turnStarted = false;
            System.out.println("Turn is over");
        }
    }

    public void startGame() {
        // Additional setup logic if needed
        //initializing ships, just for testing
        for (int i = 0; i < 10; i++){
            p1ships[i][i] = true;
        }
        for (int i = 0; i < 10; i++){
            if(i < 9) p2ships[i][i+1] = true; //like p1 but one gridspace to the right
            else p2ships[i][i] = true;
        }

        // Main game loop
        while (!isGameOver()) {
            // Perform player actions (e.g., handle turns, get input, update game state)
            playerTurn();

            // Additional game logic or checks

            // Update the view to reflect the current game state
            updateView();
        }

        // Game over logic (e.g., display winner, final statistics)
        displayGameOver();
    }

    private void playerTurn() {
        // Implement logic for handling a player's turn
        // This might include getting input from the player, updating the game state, etc.
        turnStarted = true;
        //System.out.println("playerTurn() ran");
    }

    private void updateView() {
        // Update the view to reflect the current game state
        // This involves calling methods on the GameView interface
        if (!turnStarted){
        guiView.displayMessage("It's the next player's turn."); // Example message
        System.out.println("turn switched");
        }
        // You might also call other methods on gameView to update the board state, etc.
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

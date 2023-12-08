package view;

import model.Ship;
import control.GameController;
import model.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class GUIView implements GameView {
    private static final int GRID_SIZE = 10; // Change this for a larger or smaller grid

    private JFrame frame;
    // private Ship selectedShip;
    // private Player currentPlayer;
    //should be 15 enemy spaces
    //array of the board, determines which panels are enemies
    boolean[][] enemies = new boolean[10][10];
    private CellClickListener cellClickListener;
    private JPanel[][] cellPanels = new JPanel[21][10]; //new code: holds reference to cellPanels so I can change the state of any cellPanel

    public void setCellClickListener(CellClickListener listener) {
        this.cellClickListener = listener;
    }

    public GUIView() {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Battleship Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            //rules feature
            JLabel rules = new JLabel("Click here to see the rules", SwingConstants.CENTER);
            //setting border
            int topThickness = 10;
            int bottomThickness = 10;
            boolean isBottom = false;
            rules.setBorder(BorderFactory.createMatteBorder(topThickness, 0, bottomThickness, 0, Color.BLACK));
            //when clicked, take the user to a rules page
            rules.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // Open the rules webpage when the jlabel rules is clicked
                openWebpage("https://www.hasbro.com/common/instruct/battleship.pdf");
            }
            });
            frame.add(rules, BorderLayout.NORTH);

            JPanel gridPanel = new JPanel(new GridLayout(21, GRID_SIZE));
            //creating the top board (player's board, shows your ships)
            for (int row = 0; row < 21; row++) {
                for (int col = 0; col < GRID_SIZE; col++) {
                    if (row != 10){
                        //row 10 is a blank set of JPanels used to divide the boards
                        JPanel cellPanel = new JPanel();
                        cellPanel.setPreferredSize(new Dimension(35, 25)); // Adjust panel size as needed
                        cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add a border for visibility
                        cellPanels[row][col] = cellPanel; //new code

                        // Add a label to make the panels visually distinct
                        if (row > 10){
                            row -= 11;
                            isBottom = true;
                        }
                        JLabel label = new JLabel(String.format("[%d, %d]", row, col), SwingConstants.CENTER);
                        cellPanel.add(label);
                        if (isBottom) row += 11;

                        cellPanel.addMouseListener(new CellMouseListener(row, col, this));
                        gridPanel.add(cellPanel);
                    }
                    else {
                        JPanel cellPanel = new JPanel();
                        cellPanel.setPreferredSize(new Dimension(35, 25)); // Adjust panel size as needed
                        cellPanel.addMouseListener(new CellMouseListener(row, col, this));
                        gridPanel.add(cellPanel);
                        cellPanels[row][col] = cellPanel; //new code
                    }
                }
            }

            frame.add(gridPanel, BorderLayout.CENTER);
            frame.pack();
            frame.setLocationRelativeTo(null); // Center the frame
            frame.setVisible(true);
        });
    }

    // Implement methods from GameView interface to update the display based on game state

    @Override
    public void displayMessage(String message) {
        // Update GUI to display a message (e.g., player's turn, game over)
    }

    @Override
    public void updateBoardState(int row, int col, boolean isHit) {
        // Update GUI to reflect the state of a specific cell on the game board
    }
    
    public void show(){
        if (frame != null) frame.setVisible(true);
    }
    public void hide(){
        if (frame != null) frame.setVisible(false);
    }

    public JPanel getPanel(int row, int col){
        return cellPanels[row][col];
    }

    // MouseListener for cell panels
    private class CellMouseListener extends MouseAdapter {
        private final int row;
        private final int col;
        private GUIView currView;

        public CellMouseListener(int row, int col, GUIView currView) {
            this.row = row;
            this.col = col;
            this.currView = currView;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (cellClickListener != null) {
                cellClickListener.CellClick(row, col, (JPanel) e.getSource(), currView);
            }
        }
    }
    //rules feature
    private void openWebpage(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url)); //opening URL (obtained from URI object) in default web browser on user's desktop
        } catch (IOException | URISyntaxException er) {
            er.printStackTrace(); //must be in a try catch block to be exucuted
        }
    }
}
package view;

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
    //should be 15 enemy spaces
    //array of the board, determines which panels are enemies
    boolean[][] enemies = new boolean[10][10];
    private CellClickListener cellClickListener;

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
                        JPanel cellPanel = new JPanel();
                        cellPanel.setPreferredSize(new Dimension(35, 25)); // Adjust panel size as needed
                        cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add a border for visibility

                        // Add a label to make the panels visually distinct
                        JLabel label = new JLabel(String.format("[%d, %d]", row, col), SwingConstants.CENTER);
                        cellPanel.add(label);

                        cellPanel.addMouseListener(new CellMouseListener(row, col, this));
                        gridPanel.add(cellPanel);
                    }
                    else {
                        JPanel cellPanel = new JPanel();
                        cellPanel.setPreferredSize(new Dimension(35, 25)); // Adjust panel size as needed
                        cellPanel.addMouseListener(new CellMouseListener(row, col, this));
                        gridPanel.add(cellPanel);
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
            /*for (int i = 0; i < 10; i++){
                enemies[i][i] = true;
            }
            //testing to show which cell was clicked on.
            System.out.println("Clicked on cell: " + row + ", " + col);

            //if clicked cell is an enemy (hit), turn red. if not, turn grey
            JPanel cellPanel = (JPanel) e.getSource();
            if (enemies[row][col]) cellPanel.setBackground(Color.RED);
            else cellPanel.setBackground(Color.GRAY);

            // disable this cell (it is out of play)
            cellPanel.removeMouseListener(this);*/
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
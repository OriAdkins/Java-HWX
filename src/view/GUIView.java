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
                // Execute code when the jlabel rules is clicked
                openWebpage("https://www.hasbro.com/common/instruct/battleship.pdf");
            }
            });
            frame.add(rules, BorderLayout.NORTH);

            JPanel gridPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));

            for (int row = 0; row < GRID_SIZE; row++) {
                for (int col = 0; col < GRID_SIZE; col++) {
                    JPanel cellPanel = new JPanel();
                    cellPanel.setPreferredSize(new Dimension(50, 50)); // Adjust panel size as needed
                    cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add a border for visibility

                    // Add a label to make the panels visually distinct
                    JLabel label = new JLabel(String.format("[%d, %d]", row, col), SwingConstants.CENTER);
                    cellPanel.add(label);

                    cellPanel.addMouseListener(new CellMouseListener(row, col));
                    gridPanel.add(cellPanel);
                }
            }

            frame.getContentPane().add(gridPanel, BorderLayout.CENTER);
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

    // MouseListener for cell panels
    private class CellMouseListener extends MouseAdapter {
        private final int row;
        private final int col;

        public CellMouseListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            //testing to show which cell was clicked on.
            System.out.println("Clicked on cell: " + row + ", " + col);

            //turn them to red (just to show it works)
            JPanel cellPanel = (JPanel) e.getSource();
            cellPanel.setBackground(Color.RED);

            // disable this cell (it is out of play)
            cellPanel.removeMouseListener(this);
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
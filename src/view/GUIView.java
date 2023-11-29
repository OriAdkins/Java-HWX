package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIView implements GameView {
    private static final int GRID_SIZE = 10; // Change this for a larger or smaller grid

    private JFrame frame;

    public GUIView() {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Battleship Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            JPanel gridPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));

            for (int row = 0; row < GRID_SIZE; row++) {
                for (int col = 0; col < GRID_SIZE; col++) {
                    JButton cellButton = new JButton();
                    cellButton.setPreferredSize(new Dimension(50, 50)); // Adjust button size as needed
                    cellButton.addActionListener(new CellClickListener(row, col));
                    gridPanel.add(cellButton);
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

    // ActionListener for cell buttons
    private class CellClickListener implements ActionListener {
        private final int row;
        private final int col;

        public CellClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Handle button click and update the game state (notify GameController)
        }
    }
}

// package view;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// public class BattleshipGrid extends JFrame {
//     private static final int GRID_SIZE = 10; // Change this for a larger or smaller grid

//     public BattleshipGrid() {
//         setTitle("Battleship Grid");
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//         JPanel gridPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));

//         // Create buttons for each cell on the grid
//         for (int row = 0; row < GRID_SIZE; row++) {
//             for (int col = 0; col < GRID_SIZE; col++) {
//                 JButton cellButton = new JButton();
//                 cellButton.setPreferredSize(new Dimension(50, 50)); // Adjust button size as needed
//                 cellButton.addActionListener(new CellClickListener(row, col));
//                 gridPanel.add(cellButton);
//             }
//         }

//         getContentPane().add(gridPanel);
//         pack();
//         setLocationRelativeTo(null); // Center the frame
//     }

//     // ActionListener for cell buttons
//     private class CellClickListener implements ActionListener {
//         private final int row;
//         private final int col;

//         public CellClickListener(int row, int col) {
//             this.row = row;
//             this.col = col;
//         }

//         @Override
//         public void actionPerformed(ActionEvent e) {
//             // Handle button click (you can update game state here)
//             System.out.println("Clicked on cell: " + row + ", " + col);
//         }
//     }

// }
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.Icon; // interface used to manipulate images
import javax.swing.ImageIcon; // loads images
import control.GameController;
public class StartScreen {
    private static final int GRID_SIZE = 8; // Change this for a larger or smaller grid
    private JFrame frame;

    public StartScreen(){
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Battleship Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel gridPanel = new JPanel(new GridLayout(GRID_SIZE, 10));

            for (int row = 0; row < GRID_SIZE; row++) {
                for (int col = 0; col < 10; col++) {
                    //add the play button
                    if (row == 5 && col == 4){
                        //Play
                        JButton cellButton = new JButton("Play");
                        cellButton.setPreferredSize(new Dimension(70, 70)); // Adjust button size as needed
                        cellButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                System.out.println("play button clicked");
                                //getting rid of start screen and making gameboard
                                frame.dispose();
                                GUIView guiView = new GUIView();
                                GameController gameController = new GameController(guiView);
                                //start the game loop
                                gameController.startGame();
                            }
                        });
                        gridPanel.add(cellButton);
                    }
                    else if (row == 5 && col == 5){
                        //Rules button
                        JButton cellButton = new JButton("Rules");
                        cellButton.setPreferredSize(new Dimension(70, 70)); // Adjust button size as needed
                        cellButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                openWebpage("https://www.hasbro.com/common/instruct/battleship.pdf");
                            }
                        });
                        gridPanel.add(cellButton);
                    }
                    else{
                        Icon water = new ImageIcon( getClass().getResource( "water.jpg" ) );
                        JLabel background = new JLabel(water);
                        background.setPreferredSize(new Dimension(70, 70));
                        gridPanel.add(background);
                    }
                }
            }

            frame.getContentPane().add(gridPanel, BorderLayout.CENTER);
            frame.pack();
            frame.setLocationRelativeTo(null); // Center the frame
            frame.setVisible(true);
        });
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
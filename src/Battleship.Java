/* Main Class of our Game (hopefully)
 * Published by Ori Adkins and Ryan Rowe
 */

 import control.GameController;
 import view.*;
 
 public class Battleship {
     public static void main(String[] args) {
         // Create an instance of GUIView (or any other implementation of GameView)
         GUIView guiView = new GUIView();
 
         // Create an instance of GameController and pass the view to it
         GameController gameController = new GameController(guiView);
 
         // Start the game loop
         gameController.startGame();
     }
 }
 
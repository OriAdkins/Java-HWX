package view; // Adjust package as needed

public interface GameView {
    void displayMessage(String message);

    void updateBoardState(int row, int col, boolean isHit);
}

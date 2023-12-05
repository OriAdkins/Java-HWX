//used to help GameController communicate with GUIView
package view;
import javax.swing.JPanel;

public interface CellClickListener {
    void onCellClicked(int row, int col, JPanel cellPanel); //GUIView calls this method and GameController defines it, cellPanel = panel clicked
}
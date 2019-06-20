import java.util.logging.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Tic Tac Toe Single Player version 1.0.2.
 */
public class Tic_Tac_Toe_Single_Player_Version_1_0_2 extends JFrame {

    private static final int NUMBER_OF_COLUMNS=3, NUMBER_OF_ROWS=3, DEFAULT_GRID_VALUE=0, NOUGHT_VALUE=1, CROSSES_VALUE=2; // The number of rows and columns can be adjusted to play Tic Tac Toe on a grid with more tiles.

    private int squaresFilled; // this variable is needed to mark an endgame state wherein all tiles are occupied but there is no winner.
    private JPanel mainPanel;
    private boolean gameWon;
    private Tic_Tac_Toe_Tile_Single_Player_Version_1_0_2[][] gameGrid;


    public Tic_Tac_Toe_Single_Player_Version_1_0_2(){
        createMainPanel();
        constructGameGrid();
        squaresFilled=0;
        gameWon=false;
        add(mainPanel);
        pack();
    }

    /**
     * This mouseAdapter registers mouse presses on individual Tic_Tac_Toe_Single_Player_Version_1_0_2 tiles.
     */
    class TicTacToeListener extends MouseAdapter {

        public void mousePressed(MouseEvent e){

            Tic_Tac_Toe_Tile_Single_Player_Version_1_0_2 tile=(Tic_Tac_Toe_Tile_Single_Player_Version_1_0_2) e.getSource();

            if(tile.isFilled()==false){
                if(Tic_Tac_Toe_Tile_Single_Player_Version_1_0_2.isFirstMove()){
                    Tic_Tac_Toe_Tile_Single_Player_Version_1_0_2.setNoughtsTurn(true);
                    Tic_Tac_Toe_Tile_Single_Player_Version_1_0_2.setFirstMove(false);
                }
                tile.repaint();
                if(Tic_Tac_Toe_Tile_Single_Player_Version_1_0_2.isNoughtsTurn()){
                    tile.setValue(NOUGHT_VALUE);
                }
                else{
                    tile.setValue(CROSSES_VALUE);
                }
                tile.setFilled(true);
                squaresFilled++;
                tile.repaint();
                checkEndGameConditions();
            }
        }
    }


    /**
     * This method constructs the main panel.
     */
    public void createMainPanel(){
        final int BORDER_THICKNESS=5;
        mainPanel=new JPanel();
        mainPanel.setLayout(new GridLayout(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS));
        mainPanel.setBorder(new TitledBorder(new LineBorder(Color.RED, BORDER_THICKNESS), "Tic Tac Toe Version 1.01", TitledBorder.CENTER, TitledBorder.BOTTOM, new Font("TimesRoman", Font.ITALIC, 10)));
    }

    /**
     * This method creates a 2D array of Tic Tac Toe tiles so that I have a way of accessing each individual tile. Each tile is added to the main panel.
     */
    private void constructGameGrid(){
        gameGrid=new Tic_Tac_Toe_Tile_Single_Player_Version_1_0_2[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
        for(int i=0;i<NUMBER_OF_ROWS;i++){
            for(int j=0;j<NUMBER_OF_COLUMNS;j++){
                Tic_Tac_Toe_Tile_Single_Player_Version_1_0_2 tile=new Tic_Tac_Toe_Tile_Single_Player_Version_1_0_2();
                tile.addMouseListener(new TicTacToeListener());
                gameGrid[i][j]=tile;
                mainPanel.add(tile);
            }
        }
    }

    /**
     * This method checks if an endgame condition has been met.
     * The endgame conditions are:
     * 1) Either Noughts or Crosses occupies an entire row, column, or diagonal.
     * 2) All tiles are occupied but condition 1 is not met.
     */
    public void checkEndGameConditions() {
        int value=Tic_Tac_Toe_Tile_Single_Player_Version_1_0_2.isNoughtsTurn()?NOUGHT_VALUE:CROSSES_VALUE;

        //Iterate over rows and check for win condition.
        for(int row=0;row<NUMBER_OF_ROWS;row++){
            for(int column=0;column<NUMBER_OF_COLUMNS;column++){
                if(gameGrid[row][column].getValue()!=value){
                    break;
                }
                else if(column==NUMBER_OF_COLUMNS-1){
                    gameWon=true;
                }
            }
        }

        // Iterate over columns and check for win condition.
        for(int column=0;column<NUMBER_OF_COLUMNS;column++){
            for(int row=0;row<NUMBER_OF_ROWS;row++){

                if(gameGrid[row][column].getValue()!=value){
                    break;
                }
                else if(row==NUMBER_OF_ROWS-1){
                    gameWon=true;
                }
            }
        }

        int row=0;
        for(int column=0;column<NUMBER_OF_COLUMNS;column++){


            if(gameGrid[row][column].getValue()!=value){
                break;
            }
            else if(column==NUMBER_OF_COLUMNS-1){
                gameWon=true;
            }
            row++;
        }

        // Iterate over anti-diagonal and check for win condition.
        row=0;
        for(int column=NUMBER_OF_COLUMNS-1;column>=0;column--){

            if(gameGrid[row][column].getValue()!=value){
                break;
            }
            else if(column==0){
                gameWon=true;
            }
            row++;
        }

        if(gameWon || Tic_Tac_Toe_Single_Player_Version_1_0_2.this.squaresFilled==NUMBER_OF_ROWS*NUMBER_OF_COLUMNS){

            displayEndOfGameMessage();
        }
    }

    /**
     * This method displays an endgame message. The message is dependent on the endgame state reached.
     */
    public void displayEndOfGameMessage(){
        String message="";
        String title="";
        if(gameWon) {
            String winner = Tic_Tac_Toe_Tile_Single_Player_Version_1_0_2.isNoughtsTurn() ? "Noughts" : "Crosses";
            message=String.format("%s wins! Play again?", winner);
            title="Victory!";
        }
        else if(!gameWon) {
            message = "Nobody wins. Play again?";
            title = "It's a draw!";
        }
        int option=JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
        if(option==0){
            this.restart();
        }
        else{
            JOptionPane.showMessageDialog(this, "Thanks for playing!", "Bye bye!", JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        }
    }

    /**
     * This method resets the game state.
     */
    public void restart(){
        for(int i=0;i<NUMBER_OF_ROWS;i++){
            for(int j=0;j<NUMBER_OF_COLUMNS;j++){
                gameGrid[i][j].setFilled(false);
                gameGrid[i][j].setValue(DEFAULT_GRID_VALUE);
            }
        }
        Tic_Tac_Toe_Tile_Single_Player_Version_1_0_2.setFirstMove(true);
        Tic_Tac_Toe_Tile_Single_Player_Version_1_0_2.resetGame();
        this.gameWon=false;
        this.squaresFilled=0;
        this.repaint();
    }

    public static void createAndShowGUI(){
        Tic_Tac_Toe_Single_Player_Version_1_0_2 frame=new Tic_Tac_Toe_Single_Player_Version_1_0_2();

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
        JOptionPane.showMessageDialog(frame, "Tic Tac Toe (Noughts & Crosses) \n Single Player Version \n Version 1.01 by Daan Pol 2019", "Tic Tac Toe", JOptionPane.PLAIN_MESSAGE);
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(()-> createAndShowGUI());
    }

}

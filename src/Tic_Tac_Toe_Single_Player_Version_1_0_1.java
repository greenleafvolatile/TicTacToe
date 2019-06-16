import java.util.logging.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * The Tic_Tac_Toe_Single_Player_Version_1_0_1 class builds a Tic Tac Toe game grid.
 */
public class Tic_Tac_Toe_Single_Player_Version_1_0_1 extends JFrame {

    private static final Dimension DIMENSION=new Dimension(400, 400);
    private static final int NUMBER_OF_COLUMNS=3, NUMBER_OF_ROWS=3, DEFAULT_GRID_VALUE=0; // The number of rows and columns can be adjusted to play Tic Tac Toe on a grid with more tiles.

    private final int NOUGHTS_VALUE=1, CROSSES_VALUE=2;
    private int[][] ticTacToeGrid;
    private int squaresFilled; // this variable is needed to mark an endgame state wherein all tiles are occupied but there is no winner.
    private JPanel mainPanel;
    private boolean gameWon;


    public Tic_Tac_Toe_Single_Player_Version_1_0_1(){
        Logger.getGlobal().setLevel(Level.OFF);
        this.ticTacToeGrid=new int[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
        this.squaresFilled=0;
        this.gameWon=false;
        this.createMainPanel();
        this.add(mainPanel);
        this.pack();
    }

    /**
     * This mouseAdapter registers mouse presses on individual Tic_Tac_Toe_Single_Player_Version_1_0_1 tiles.
     */
    class TicTacToeListener extends MouseAdapter {

        public void mousePressed(MouseEvent e){

            Tic_Tac_Toe_Tile tile=(Tic_Tac_Toe_Tile) e.getSource();
            System.out.print(tile.isFilled());
            if(!tile.isFilled()){
                if(Tic_Tac_Toe_Tile.isFirstMove()){
                    Tic_Tac_Toe_Tile.startGame();
                }
                tile.draw();
                Tic_Tac_Toe_Single_Player_Version_1_0_1.this.squaresFilled++;
                Tic_Tac_Toe_Single_Player_Version_1_0_1.this.markOnGrid(tile.getRow(),tile.getColumn());
                Tic_Tac_Toe_Single_Player_Version_1_0_1.this.checkEndGameConditions();
            }
        }
    }

    /**
     * This method constructs the UI game grid.
     */
    public void createMainPanel(){
        final int BORDER_THICKNESS=5;
        mainPanel=new JPanel();
        mainPanel.setPreferredSize(this.DIMENSION);
        mainPanel.setLayout(new GridLayout(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS));
        mainPanel.setBorder(new TitledBorder(new LineBorder(Color.RED, BORDER_THICKNESS), "Tic Tac Toe Version 1.01", TitledBorder.CENTER, TitledBorder.BOTTOM, new Font("TimesRoman", Font.ITALIC, 10)));
        this.addTiles();
    }

    /**
     * This method adds the Tic_Tac_Toe_Single_Player_Version_1_0_1 components/tiles to the UI game grid.
     */
    public void addTiles(){

        for(int i=0;i<NUMBER_OF_ROWS;i++){
            for(int j=0;j<NUMBER_OF_COLUMNS;j++){
                JComponent tile =new Tic_Tac_Toe_Tile(i, j);
                MouseListener listener=new TicTacToeListener();
                tile.addMouseListener(listener);
                mainPanel.add(tile);
            }
        }
    }

    /**
     * This method assigns a value to the element in the ticTacToeGrid that corresponds to the row and column values of the Tic_Tac_Toe_Single_Player_Version_1_0_1 component that generated a mousePressed() event.
     * If isNoughts returns true that element is assigned an integer value of 1, if it returns false an integer value of 2.
     * @param row an integer
     * @param column an integer
     */
    public void markOnGrid(int row, int column){
        if(Tic_Tac_Toe_Tile.isNoughts()){
            ticTacToeGrid[row][column]=NOUGHTS_VALUE;
        }
        else if(!Tic_Tac_Toe_Tile.isNoughts()){
            ticTacToeGrid[row][column]=CROSSES_VALUE;
        }
    }


    /**
     * This method checks if an endgame condition has been met.
     * The endgame conditions are:
     * 1) Either Noughts or Crosses occupies an entire row, column, or diagonal.
     * 2) All tiles are occupied but condition 1 is not met.
     */
    public void checkEndGameConditions() {
        int gridValue= Tic_Tac_Toe_Tile.isNoughts() ? NOUGHTS_VALUE : CROSSES_VALUE;

        // Iterate over rows and check for win condition.
        for(int row=0;row<NUMBER_OF_ROWS;row++){
            for(int column=0;column<NUMBER_OF_COLUMNS;column++){
                if(ticTacToeGrid[row][column]!=gridValue){
                    break;
                }
                if(column==NUMBER_OF_COLUMNS-1){
                   gameWon=true;
                }
            }
        }

        // Iterate over columns and check for win condition.
        for(int column=0;column<NUMBER_OF_COLUMNS;column++){
            for(int row=0;row<NUMBER_OF_ROWS;row++){
                if(ticTacToeGrid[row][column]!=gridValue){
                    break;
                }
                if(row==NUMBER_OF_ROWS-1){
                    gameWon=true;
                }
            }
        }

        // Iterate over diagonal and check for win condition.

        int row=0;
        for(int column=0;column<NUMBER_OF_COLUMNS;column++){
            if(ticTacToeGrid[column][row]!=gridValue){
                break;
            }
            if(column==NUMBER_OF_COLUMNS-1){
                gameWon=true;
            }
            row++;
        }

        // Iterate over anti-diagonal and check for win condition.
        row=0;
        for(int column=NUMBER_OF_COLUMNS-1;column>=0;column--){
            if(ticTacToeGrid[column][row]!=gridValue){
                break;
            }
            if(column==0){
                gameWon=true;
            }
            row++;
        }

        if(gameWon || Tic_Tac_Toe_Single_Player_Version_1_0_1.this.squaresFilled==NUMBER_OF_ROWS*NUMBER_OF_COLUMNS){

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
            String winner = Tic_Tac_Toe_Tile.isNoughts() ? "Noughts" : "Crosses";
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
        Tic_Tac_Toe_Tile.resetGame();
        this.gameWon=false;
        this.squaresFilled=0;
        this.resetGrid();
        this.repaint();
    }

    /**
     * This method resets the ticTacToeGrid by filling it with 0's.
     */
    public void resetGrid(){
        for(int i=0;i<this.ticTacToeGrid.length;i++){
            Arrays.fill(this.ticTacToeGrid[i], DEFAULT_GRID_VALUE);
        }
        Logger.getGlobal().info(Arrays.deepToString(this.ticTacToeGrid));
    }

    public static void createAndShowGUI(){
        Tic_Tac_Toe_Single_Player_Version_1_0_1 frame=new Tic_Tac_Toe_Single_Player_Version_1_0_1();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JOptionPane.showMessageDialog(frame, "Tic Tac Toe (Noughts & Crosses) \n Single Player Version \n Version 1.01 by Daan Pol 2019", "Tic Tac Toe", JOptionPane.PLAIN_MESSAGE);
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(()-> createAndShowGUI());
    }

}

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * A TicTacToe component constitutes one tile in a TicTacToe (Noughts & Crosses) game.
 */

public class Tic_Tac_Toe_Tile_Single_Player_Version_1_0_1 extends JComponent {


    private static final int STROKE_THICKNESS=5, BORDER_THICKNESS=2;

    private static boolean noughts=false, firstMove=true;

    private int row, column;
    private LineBorder border;
    private boolean isFilled;

    /**
     * The constructor
     * @param row an integer representing the objects row position in the game grid.
     * @param column an integer representing the objects column position in the game grid.
     */
    public Tic_Tac_Toe_Tile_Single_Player_Version_1_0_1(int row, int column){
        border=new LineBorder(Color.PINK, BORDER_THICKNESS);
        setBorder(border);
        this.row=row;
        this.column=column;
    }

    /**
     * This method sets a TicTacToe component's isFilled value to false when noughts is false and firstMove is false(i.e. at the beginning of a game).
     * If noughts is true and firstMove is false, this method draws a nought (circle).
     * If noughts if false and firstMove is false, this method draws a cross.
     * @param g a Graphics object.
     */
    @Override
    public void paintComponent(Graphics g){
        System.out.print(isFilled);
        Graphics2D g2d=(Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(STROKE_THICKNESS));
        if(firstMove){
            isFilled=false;
        }
        else if(noughts && !isFilled){
            drawNought(g2d);
            noughts=!noughts;
            isFilled=!isFilled;  //This statement is repeated below. I could also put in in draw(), in which case it does not need to be repeated.
        }
        else if(!noughts && !isFilled){
            drawCross(g2d);
            noughts=!noughts;
            isFilled=!isFilled;
        }
    }

    /**
     * This methods draw a nought on a TicTacToe component.
     * @param g2d a Graphics2D object.
     */
    private void drawNought(Graphics2D g2d){
        g2d.setColor(Color.RED);
        g2d.drawOval(border.getThickness(), border.getThickness(), this.getSize().width-border.getThickness()*2, this.getSize().height-border.getThickness()*2);
    }

    /**
     * This method draws a Cross on a TicTacToe component.
     * @param g2d a Graphics2D object.
     */
    private void drawCross(Graphics2D g2d){
        g2d.drawLine(border.getThickness(),border.getThickness(),  this.getSize().width-border.getThickness(), this.getSize().height-border.getThickness());
        g2d.drawLine(border.getThickness(), this.getSize().height-border.getThickness(), this.getSize().width-border.getThickness(), border.getThickness());
    }

    /**
     * This method redraws a TicTacToe component when it is the source of a mousePressed() event.
     */
    public void draw() {
        repaint();
    }

    /**
     * This method returns the row number of a component/tile corresponding to its place in the game grid (ticTacToeGrid).
     * @return an integer
     */
    public int getRow(){
        return row;
    }

    /**
     * This method returns the column number of a component/tile corresponding to its place in the game grid (ticTacToeGrid).
     * @return an integer
     */
    public int getColumn(){
        return column;
    }

    /**
     * This method returns the value of isNoughts, thereby indicating that either a cross or a nought will be drawn when repaint() is next invoked.
     * @return a boolean
     */
    public static boolean isNoughts() {
        return noughts;
    }

    /**
     * This methods returns the value of isFilled. If a component objects isFilled value returns true then that object will not be redrawn until after the game is reset.
     * @return a boolean.
     */
    public boolean isFilled(){
        return isFilled;
    }

    /**
     * This method returns the value of isFirstMove. If isFirstMove returns true this indicates the game has not yet begun, and all TicTacToe components/objects will be drawn blank.
     * @return a boolean
     */
    public static boolean isFirstMove() {
        return firstMove;
    }

    /**
     * This method initializes the prerequisite variables for a new game. Noughts always starts first.
     */
    public static void startGame(){
        noughts=true;
        firstMove=false;
    }

    /**
     * This method resets all the prerequisite variables for a new game.
     */
    public static void resetGame(){
        noughts=false;
        firstMove=true;
    }
}





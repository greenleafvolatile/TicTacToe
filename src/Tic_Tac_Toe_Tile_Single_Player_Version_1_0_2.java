import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * A Tic_Tac_Toe_Single_Player_Version_1_0_2 component constitutes one tile in a Tic_Tac_Toe_Single_Player_Version_1_0_2 (Noughts & Crosses) game.
 */

public class Tic_Tac_Toe_Tile_Single_Player_Version_1_0_2 extends JComponent {


    private static final int STROKE_THICKNESS=5, BORDER_THICKNESS=2, PREF_WIDTH=125, PREF_HEIGHT=125;

    private static boolean noughtsTurn=false, firstMove=true;

    private LineBorder border;
    private boolean isFilled;
    private int value;

    public Tic_Tac_Toe_Tile_Single_Player_Version_1_0_2(){
        border=new LineBorder(Color.PINK, BORDER_THICKNESS);
        setBorder(border);
        isFilled=false;
    }

    /**
     * paintComponent draws the noughts and crosses. It also has some turn-logic. I know that I should separate the turn logic from
     * the paint logic,  but when I moved all the turn logic from paintComponent to
     * the mouseListener I ran into an issue where the event dispatching thread did not execute repaint() immediately and the turn logic got messed up. I learned since then that
     * I should take a snapshot of the data before invoking paintComponent in the mouseListener but I have as yet not figured out how to do that.
     * @param g a Graphics object.
     */
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d=(Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(STROKE_THICKNESS));
        if(noughtsTurn){
            drawNought(g2d);
            noughtsTurn=!noughtsTurn;
        }
        else if(!noughtsTurn && !firstMove){
            drawCross(g2d);
            noughtsTurn=!noughtsTurn;
        }
    }

    /**
     * This methods draw a nought on a Tic_Tac_Toe_Single_Player_Version_1_0_2 component.
     * @param g2d a Graphics2D object.
     */
    private void drawNought(Graphics2D g2d){
        g2d.setColor(Color.RED);
        g2d.drawOval(border.getThickness(), border.getThickness(), this.getSize().width-border.getThickness()*2, this.getSize().height-border.getThickness()*2);
    }

    /**
     * This method draws a Cross on a Tic_Tac_Toe_Single_Player_Version_1_0_2 component.
     * @param g2d a Graphics2D object.
     */
    private void drawCross(Graphics2D g2d){
        g2d.drawLine(border.getThickness(),border.getThickness(),  this.getSize().width-border.getThickness(), this.getSize().height-border.getThickness());
        g2d.drawLine(border.getThickness(), this.getSize().height-border.getThickness(), this.getSize().width-border.getThickness(), border.getThickness());
    }


    /**
     * This method resets the turn logic (which causes a blank tile to be drawn).
     */
    public  static void resetGame(){
        noughtsTurn=false;
        firstMove=true;
    }

    public Dimension getPreferredSize(){
        return new Dimension(PREF_WIDTH, PREF_HEIGHT);
    }

    public static boolean isNoughtsTurn() {
        return noughtsTurn;
    }


    public static void setNoughtsTurn(boolean value){
        noughtsTurn=value;
    }

    public boolean isFilled(){
        return isFilled;
    }

    public void setFilled(boolean filled){
        isFilled=filled;
    }

    public static boolean isFirstMove() {
        return firstMove;
    }

    public static void setFirstMove(boolean value){
        firstMove=value;
    }

    public void setValue(int value){
        this.value=value;
    }

    public int getValue(){
        return value;
    }
}





import javax.swing.*;

public class Tic_Tac_Toe_Single_Player_Version_1_0_1 {

    public static void main(String[] args){

        TicTacToeFrame frame=new TicTacToeFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JOptionPane.showMessageDialog(frame, "Tic Tac Toe (Noughts & Crosses) \n Single Player Version \n Version 1.01 by Daan Pol 2019", "Tic Tac Toe", JOptionPane.PLAIN_MESSAGE);
    }

}

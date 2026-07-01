import javax.swing.*;
import java.awt.*;

public class DisplayGui {
   public static void main (String[] args) {

      JFrame frame = new JFrame();
      ImageIcon imageIcon = new ImageIcon("mosticonic.jpeg");
      int boardWidth = 660;
      int boardHeight = 660;



      frame.setTitle("Snake Recreate");
      frame.setSize(boardWidth,boardHeight);
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLocationRelativeTo(null);
      frame.getContentPane().setBackground(Color.ORANGE);
      frame.setIconImage(imageIcon.getImage());

      Snake mySnake = new Snake();
      frame.add(mySnake);

      frame.pack();
      frame.setVisible(true);
   }
}
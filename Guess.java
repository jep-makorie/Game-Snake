import java.util.Scanner;
import java.util.Random;

public class ComputahGuess{

  public static void main (String[] args) {
    Scanner input = new Scanner(System.in);
    Random rand = new Random();
    int number = rand.nextInt(10);
    
    System.out.println("Guess a number Human because it is obvious you are not as smart as a COMPUTAH 🥱:");
    int guessed = input.nextInt();
    int times = 10;
    boolean won = false;

    for (int i=0;i<times;i++) {
      if(number == guessed) {
       System.out.println("You got it bro, I guess you are smarter than I thought ");
       System.out.println("The number is: " + number);
       won = true;
       break;
      }
      else if (guessed < number) {
        System.out.println("I love your low IQ");
      }else {
        System.out.println("Now you are showing off, go lower bro");
      }

      guessed = input.nextInt();
    }
    
    if(won) {
      System.out.println("Game over! The number is: " + number + ". I knew you would never get it!");
    }


    input.close();
  }

   public void gameOver(Graphics g) {
        g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
        g.setFont(new Font("Comfortaa",Font.BOLD, 40 ));

        FontMetrics metrics = getFontMetrics(g.getFont());
        
        String over = "Game Over";
        String score = "Score: " + applesEaten;

        int overY = BOARD_HEIGHT/2;
        int scoreY = overY - getFont().getSize()- 30;

        g.drawString(over, (BOARD_WIDTH - metrics.stringWidth(over))/2, overY);
        g.drawString(score, (BOARD_WIDTH - metrics.stringWidth(score))/2, scoreY );

    }
}
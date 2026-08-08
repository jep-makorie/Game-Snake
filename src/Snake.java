import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.util.Random;


public class Snake extends JPanel implements ActionListener{
    static final int BOARD_WIDTH = 660;
    static final int BOARD_HEIGHT = 660;
    static final int COMP_SIZE = 20;
    static final int GAME_UNITS = (BOARD_WIDTH/COMP_SIZE)*(BOARD_HEIGHT/COMP_SIZE);
    final int[] x = new int[GAME_UNITS];
    final int[] y = new int[GAME_UNITS];
    int Delay = 200;
    Image background;
    Random random;
    int appleX;
    int appleY;
    int appleWidth;
    int appleHeight;
    int bodyParts = 8;
    int applesEaten;
    int applesCollected;
    char direction = 'R';
    Timer timer;
    boolean running = false;
    boolean paused = false;


    Snake() {
        this.setPreferredSize(new Dimension(BOARD_WIDTH,BOARD_HEIGHT));
        this.addKeyListener(new MyKeyAdapter());
        this.setFocusable(true);
        random = new Random();
        applesEaten = 0;
        apple();

        for (int i = 0; i < bodyParts; i++) {
        x[i] = (bodyParts - i) * COMP_SIZE;
        y[i] = 0;
        }

        timer = new Timer(Delay, this);
        running = true;
        timer.start();
    
    }

    public void pro() {

        if(applesCollected>0 && applesCollected%10==0) {
            Delay -= 50;
        
        
            if(Delay<30) {
                Delay = 30;
            }
            timer.setDelay(Delay);
        }
    }

    public void restartGame(){
       bodyParts =8;
       applesEaten = 0;
       applesCollected = 0;
       Delay = 200; 
       direction = 'R';
       running = true;
       paused = false;

        for (int i = 0; i < x.length; i++) {
            x[i] = 0;
            y[i] = 0;
        }

        for (int i = 0; i < bodyParts; i++) {
            x[i] = (bodyParts - i) * COMP_SIZE;
            y[i] = 0;
        }

        apple();

        timer.setDelay(Delay);
        timer.start();
        
        
    }
    public void resumeGame() {
        int choice = JOptionPane.showOptionDialog(null, "Continue?", "Paused", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

        if(choice == JOptionPane.YES_OPTION) {
            paused = false;
            running = true;
            timer.start();
        } else {
            paused = false;
            running = false;
            timer.stop();
            repaint();
        }
        
    }
    public void move() {
        for(int i=bodyParts;i>0;i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        switch(direction) {
            case 'U' :
                y[0] = y[0] - COMP_SIZE;
                break;
            case 'D' :
                y[0] =y[0] + COMP_SIZE;
                break;
            case 'R' :
                x[0] = x[0] + COMP_SIZE;
                break;
            case 'L' :
                x[0] = x[0] - COMP_SIZE;
                break;
            case 'S' :
        }
    }

    public void apple() {
        appleX = random.nextInt((int) BOARD_WIDTH/COMP_SIZE)*COMP_SIZE;
        appleY = random.nextInt((int) BOARD_HEIGHT/COMP_SIZE)*COMP_SIZE;
        appleWidth = (BOARD_WIDTH/COMP_SIZE)*COMP_SIZE;
        appleHeight = (BOARD_HEIGHT/COMP_SIZE)*COMP_SIZE;
        
    }
    
    public void checkApples() {
        if(x[0]==appleX && y[0] == appleY) {
            bodyParts++;
            applesCollected++;
        
            if (applesCollected>0 && applesCollected % 10 == 0) {
                applesEaten+=3;
                pro();
            } else {
                applesEaten++;
            }
            apple();
        }


    }
    public void checkCollisions(){
        for(int i=bodyParts;i>0;i--) {
            if(x[0]==x[i] && y[0]==y[i]) {
            running = false;
            }
        }

        if(x[0] <0) {
            running = false;
        }
        if(x[0] > BOARD_WIDTH) {
            running = false;
        }
         if(y[0] <0) {
            running = false;
        }
        if(y[0] > BOARD_HEIGHT) {
            running = false;
        }
        if(!running) {
            timer.stop();
        }

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);

    }
    public void draw(Graphics g) {
        if(running) {
            g.drawImage(background,0,0, BOARD_WIDTH,BOARD_HEIGHT,null);

            for (int i = 0; i < BOARD_WIDTH / COMP_SIZE; i++) {
            g.drawLine(i*COMP_SIZE,0,i*COMP_SIZE,BOARD_WIDTH);
            g.drawLine(0,i*COMP_SIZE,BOARD_HEIGHT,i*COMP_SIZE);
            }
            g.setColor(new Color(51,36,33));
            g.fillOval(appleX, appleY, COMP_SIZE, COMP_SIZE);

            if((applesEaten + 1) % 10 == 0) {
                g.setColor(new Color(57,255,20));
            } else {
                g.setColor(new Color(51,36,33));
            }
            g.fillOval(appleX, appleY, COMP_SIZE, COMP_SIZE);

            for (int i=0;i<bodyParts;i++) {
                if(i==0) {
                    g.setColor(Color.RED);
                    g.fillOval(x[i],y[i], COMP_SIZE, COMP_SIZE);
                }
                else {
                    g.setColor(new Color(150,205,50));
                    g.fillOval(x[i],y[i], COMP_SIZE, COMP_SIZE);
                }

            }
            g.setColor(Color.BLACK);
            g.setFont(new Font("Comfortaa",Font.BOLD, 30));
            g.drawString("Score: " + applesEaten, 10, g.getFont().getSize());

        } else{
            gameOver();
        }
        
        
    }
    public void gameOver() {
        JLabel gameOverLabel = new JLabel();
        gameOverLabel.setText("Game Over!\nYour score: " + applesEaten);
        gameOverLabel.setFont(new Font("Comfortaa", Font.BOLD, 20));
        gameOverLabel.setForeground(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));        
        JOptionPane.showMessageDialog(null, gameOverLabel, "Game Over", JOptionPane.INFORMATION_MESSAGE);
        restartGame();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(running && !paused){
            move();
            checkApples();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {

            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT :
                    if(direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT :
                    if(direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP :
                    if(direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN :
                    if(direction != 'U') {
                        direction = 'D';
                    }
                    break;
                case KeyEvent.VK_SPACE :
                    if(running && !paused) {
                        paused = true;
                        timer.stop();
                        resumeGame();
                    } else if(!running) {
                        restartGame();
                    }
                    break;
            }
        }

    }
}

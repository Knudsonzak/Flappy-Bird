import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener, KeyListener{
    private JFrame frame = new JFrame();
    private ImageIcon bg = new ImageIcon("FlappyBackDrop.jpeg");
    private Image background = bg.getImage();
    private int counter = 0;
    private Poles Apoles = new Poles(250);
    private Poles Bpoles = new Poles(500);
    private Poles Cpoles = new Poles(700);
    private Poles Dpoles = new Poles(900);
    private Poles Epoles = new Poles(1100);
    private Bird bird = new Bird();
    private Timer timer; 
    private Timer timer2;
    private boolean gameStarted;
    private JLabel label = new JLabel("");
    private boolean gameOver = false;

    public FlappyBird(){
        
        Container can = frame.getContentPane();
        can.add(this);
        can.add(label, BorderLayout.NORTH);
        frame.setTitle("Flappy Bird: Score " + counter);
        setPreferredSize(new Dimension(850, 460));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
        setFocusable(true);
        frame.pack();
        
        timer = new Timer(15, this);
        timer2 = new Timer(31000, this);
        
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this);
        Apoles.draw(g);
        Bpoles.draw(g);
        Cpoles.draw(g);
        Dpoles.draw(g);
        Epoles.draw(g);
        bird.drawBird(g, bird.getX(),bird.getY());
    } 
    public static void main(String[] args) {
        new FlappyBird();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        while(source == timer2){
            
        }

        if(gameStarted){
            bird.update();
        }
        
        Apoles.locX --;
        Bpoles.locX --;
        Cpoles.locX --;
        Dpoles.locX --;
        Epoles.locX --;
        
        if(Apoles.locX == -80){
            Apoles.locX = 1000;
        }
        if(Bpoles.locX == -80){
            Bpoles.locX = 1000;
        }
        if(Cpoles.locX == -80){
            Cpoles.locX = 1000;
        }
        if(Dpoles.locX == -80){
            Dpoles.locX = 1000;
        }
        if(Epoles.locX == -80){
            Epoles.locX = 1000;
        }
        repaint();  

        if(!gameOver){
            if (bird.getBounds().intersects(Apoles.getTopBounds()) || bird.getBounds().intersects(Apoles.getDownBounds()) ||
                bird.getBounds().intersects(Bpoles.getTopBounds()) || bird.getBounds().intersects(Bpoles.getDownBounds()) ||
                bird.getBounds().intersects(Cpoles.getTopBounds()) || bird.getBounds().intersects(Cpoles.getDownBounds()) ||
                bird.getBounds().intersects(Dpoles.getTopBounds()) || bird.getBounds().intersects(Dpoles.getDownBounds()) ||
                bird.getBounds().intersects(Epoles.getTopBounds()) || bird.getBounds().intersects(Epoles.getDownBounds()) ||
                bird.getY() > 405 || bird.getY() < 0){
               
                bird.die();
                timer.stop();
                gameOver = true;
                label.setText("Game Over: Press R to restart");
            }
        }

        if(!gameOver){
            if (Apoles.locX == 150 ||
                Bpoles.locX == 150 ||
                Cpoles.locX == 150 ||
                Dpoles.locX == 150 ||
                Epoles.locX == 150 ){
                    
               counter ++;
               frame.setTitle("Flappy Bird: Score " + counter);
            }
        }

    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            if (!gameStarted) {
                gameStarted = true;
                timer.start();   
            }
            bird.flap();
        }
        else if(e.getKeyCode() == KeyEvent.VK_R){
            label.setText("");
            bird.locY = 220;
            counter = 0;
            frame.setTitle("Flappy Bird: Score " + counter);
            Apoles = new Poles(250);
            Bpoles = new Poles(500);
            Cpoles = new Poles(700);
            Dpoles = new Poles(900);
            Epoles = new Poles(1100);

            gameOver = false;
            gameStarted = false;
            bird.isAlive = true;
            
               
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Bird extends JPanel implements ActionListener{

    private int diameter;
    private int locX;
    public int locY;
    private int speed;
    public boolean isAlive;
    private ImageIcon bg = new ImageIcon("bird3.jpeg");
    private Image background = bg.getImage();

    public Bird(){
        locX = 125;
        locY = 220;
        speed = 0;
        isAlive = true;
        diameter = 50;
        
    }

    public void flap(){
        if(isAlive){
            speed = - 10;
        }
    }

    public void update(){
        if(isAlive){
            speed = speed + 1;
            locY = locY + speed;
        }
    }

    public int getX(){
        return locX;
    }

    public int getY(){
        return locY;
    }
    public int getDiameter(){
        return diameter;
    }
    
    public void die(){
        isAlive = false;
    }
        
    
    public void drawBird(Graphics g, int x, int y){
        g.drawImage(background, locX, locY, diameter, diameter,this);
    }
    
    public Rectangle getBounds(){
        return new Rectangle(locX, locY, diameter - 2, diameter - 2);
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}

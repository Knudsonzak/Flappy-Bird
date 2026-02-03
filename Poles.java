import java.awt.*;
import javax.swing.*;

public class Poles extends JPanel{
    public int locX;
    public int diameter;
    private int gap;
    
    private int DownPoleHeight = (int)((250 - 100 + 1)*Math.random() + 100);
   
    public Poles(int x) {
        locX = x;
        gap = 150;
        
    }
    public int getDiameter(){
        return diameter;
    }

    public void draw(Graphics g){
        drawDownPole(g);
        drawUpPole(g);
    }

    public Rectangle getTopBounds(){
        return new Rectangle(locX, 0, 80, DownPoleHeight);
    }

    public Rectangle getDownBounds(){
        int upPoleHeight = 440 - (DownPoleHeight + gap);
        return new Rectangle(locX, DownPoleHeight + gap, 80, upPoleHeight);
    }

    public Rectangle getDistanceBounds(){
        return new Rectangle(locX + 40, DownPoleHeight, 1, gap);
    }

    public void drawDownPole(Graphics g){
        // 460 is the bottom of the screen
        //440 is the top of the grass
        g.setColor(new Color(42, 163, 42));
        g.fillRect(locX, 0, 80, DownPoleHeight);
        g.fillRect(locX - 10, DownPoleHeight, 100, 15);
        // g.setColor(new Color(255, 0, 0, 100));
        // g.fillRect(locX + 40, DownPoleHeight, 1, gap);
    }
    public void drawUpPole(Graphics g){
        // 460 is the bottom of the screen
        //440 is the top of the grass
        int upPoleHeight = 440 - (DownPoleHeight + gap);
        g.setColor(new Color(42, 163, 42));
        g.fillRect(locX, DownPoleHeight + gap, 80, upPoleHeight );
        g.fillRect(locX - 10, DownPoleHeight + gap, 100, 15);
    }
    
}

package hellotvxlet;

import java.awt.Color;
import java.awt.Graphics;
import org.havi.ui.HComponent;

/**
 *
 * @author student
 */
public class MijnComponent extends HComponent {
    public MijnComponent(int x, int y)
    {
        this.setBounds(x,y,100,100);//dit is de plaats en afmetingen van mijncomponent
    }
    
    public void paint(Graphics g)
    {
        g.setColor(Color.RED);
        g.drawLine(0,0,100,100);
        g.drawLine(0, 100, 100, 0);
        g.fillRoundRect(0, 0, 50, 50, 10, 10);//x,y,breedte,hoogte,r1,r2
    }
}
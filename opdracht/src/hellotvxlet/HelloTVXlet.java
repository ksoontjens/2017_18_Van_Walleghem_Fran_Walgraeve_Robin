package hellotvxlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.*;
import javax.tv.xlet.*;
import org.dvb.ui.DVBColor;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HStaticText;
import org.havi.ui.HTextButton;
import org.havi.ui.HVisible;
import org.havi.ui.event.HActionListener;


public class HelloTVXlet implements Xlet, HActionListener {

  private HScene scene;
  private HTextButton button1;
  private HTextButton button2;
  private HTextButton button3;
  private HTextButton button4;
  HStaticText label1;
  HStaticText label2;
  HStaticText label3;
  HStaticText label4;
  String[] liedjes = {"The Lion King - The Circle Of Life", "Moana - You're Welcome", 
  "Oliver and Company - Why Should I Worry", "Brother Bear - On My Way", "Cinderella - A Dream is a Wish Your Heart Makes", 
  "Peter Pan - You Can Fly", "Toy Story - You've Got A Friend In Me", "Tangled - When Will My Life Begin",
  "Tangled - I see The Light", "Cars - Our Town", "Dumbo - Baby Mine", "Alice In Wonderland - Painting The Roses Red",
  "One Hundred and One Dalmatians - Cruella Devil", "Jungle Book - Bear Necessities", "Aladdin - Whole New World",
  "The Little Mermaid - Under The Sea", "Beauty and the Beast - Be Our Guest", "Pocahontas - Colours of the Wind",
  "Mulan - I'll Make A Man Out Of You", "Hercules - I Won't Say I'm In Love", "Tarzan - Two Worlds",
  "Treasure Planet - I'm Still Here", "Princess and the Frog - Almost There"};
  int i = 0;
  List gekozenliedjes = new ArrayList();
  boolean PressedGo;
  boolean BackEnabled = true;
  
  
    public HelloTVXlet() {
        
    }

    public void initXlet(XletContext context) {
     scene=HSceneFactory.getInstance().getDefaultHScene();
     //SD D1     720 x 576
     label1=new HStaticText("GO OR GONE?",100,100,500,50);// x, y, lengte, breedte
     label1.setBackgroundMode(HVisible.BACKGROUND_FILL);
     label1.setBackground(new DVBColor(0,0,0,179));
     
     scene.add(label1);
     
     label2=new HStaticText(liedjes[0],100,150,500,50);// x, y, lengte, breedte
     label2.setBackgroundMode(HVisible.BACKGROUND_FILL);
     label2.setBackground(new DVBColor(0,0,0,179));
     
     scene.add(label2);
     
     button1=new HTextButton("Go!",40,300,200,50);
     button1.setBackgroundMode(HVisible.BACKGROUND_FILL);
     button1.setBackground(Color.GRAY);
     scene.add(button1);
     
     button2=new HTextButton("Gone!",260,300,200,50);
     button2.setBackgroundMode(HVisible.BACKGROUND_FILL);
     button2.setBackground(Color.GRAY);
     scene.add(button2);
     
     button3=new HTextButton("STOP",260,400,200,50);
     button3.setBackgroundMode(HVisible.BACKGROUND_FILL);
     button3.setBackground(Color.GRAY);
     scene.add(button3);
     
     button4=new HTextButton("Back",480,300,200,50);
     button4.setBackgroundMode(HVisible.BACKGROUND_FILL);
     button4.setBackground(Color.GRAY);
     scene.add(button4);
     
     button1.setFocusTraversal(null, button3, null, button2);//boven, onder, links, rechts
     button2.setFocusTraversal(null, button3, button1, button4);
     button3.setFocusTraversal(button2, null, null, null);
     button4.setFocusTraversal(null, button3, button2, null);
     button1.requestFocus();
     
     
     button1.setActionCommand("button1");
     button2.setActionCommand("button2");
     button3.setActionCommand("button3");
     button4.setActionCommand("button4");
     
     button1.addHActionListener(this);
     button2.addHActionListener(this);
     button3.addHActionListener(this);
     button4.addHActionListener(this);
     
     scene.validate(); scene.setVisible(true);
     
    }


    public void actionPerformed(ActionEvent arg0) {
        System.out.println(arg0.getActionCommand());
        
        // na 10 liedjes automatisch naar gemaakte afspeellijst gaan
        
        if(arg0.getActionCommand().equals("button1"))
        {
            //GO --> toevoegen aan lijst, liedje veranderen
            gekozenliedjes.add(liedjes[i]);
            
            PressedGo = true;
            
            System.out.println(gekozenliedjes);
            
            i++;
            label2.setTextContent(liedjes[i], HVisible.NORMAL_STATE);
            
            button4.setEnabled(BackEnabled);
            
            label2.repaint();
        }
        else if (arg0.getActionCommand().equals("button2"))
        {
            //GONE --> liedje veranderen
            PressedGo = false;
            
            System.out.println(gekozenliedjes);
            
            i++;
            label2.setTextContent(liedjes[i], HVisible.NORMAL_STATE);
            
            BackEnabled = true;
            button4.setEnabled(BackEnabled);
            
            label2.repaint();
        }
        else if (arg0.getActionCommand().equals("button3")) {
            scene.remove(button1);
            scene.remove(button2);
            scene.remove(button3);
            scene.remove(button4);
            scene.remove(label1);
            scene.remove(label2);
            
            label3=new HStaticText("",100,110,500,450);// x, y, breedte, lengte
            label3.setBackgroundMode(HVisible.BACKGROUND_FILL);
            label3.setBackground(new DVBColor(60,70,195,179));
            label3.setFont(new Font("Serif", Font.PLAIN, 16));
            
            label4=new HStaticText("YOUR PLAYLIST",100,50,500,50);// x, y, breedte, lengte
            label4.setBackgroundMode(HVisible.BACKGROUND_FILL);
            label4.setBackground(new DVBColor(0,0,0,179));
            
            scene.add(label4);
            scene.add(label3);
            
            String s="";
            for (int j=0;j<gekozenliedjes.size();j++)
            {
                s=s+"\n"+(String)gekozenliedjes.get(j);
            }
            
            System.out.println("TOTALE STRING="+s);
            
            label3.setTextContent(s, HVisible.NORMAL_STATE);
            scene.repaint(); 
        }
        else if (arg0.getActionCommand().equals("button4")) {
            if (BackEnabled) {
                if (PressedGo) {                
                    //gekozenliedjes.remove(gekozenliedjes.size()-1);
                    //System.out.println(gekozenliedjes.remove(gekozenliedjes.size()-1));

                    Object test = gekozenliedjes.remove(gekozenliedjes.size()-1);

                    i--;
                    label2.setTextContent(liedjes[i], HVisible.NORMAL_STATE);
                    label2.repaint();

                    BackEnabled = false;
                    //button1.requestFocus();
                    button4.setEnabled(BackEnabled);
                }
                else {
                    i--;
                    label2.setTextContent(liedjes[i], HVisible.NORMAL_STATE);
                    label2.repaint();

                    BackEnabled = false;
                    //button1.requestFocus();
                    button4.setEnabled(BackEnabled);
                }
            }
            
            System.out.println(gekozenliedjes);
            
            scene.repaint();
            
        }
    }
    
    public void startXlet() {
    
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
     
    }
}

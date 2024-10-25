package src.screen;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.MouseEvent;

import src.framework.State;
import src.window.Game;

public class HelpScreen extends AbstractScreen{

    public HelpScreen(Game game){
        super(game);
    }

    public void mousePressed(MouseEvent e){
        int mx= e.getX();
        int my= e.getY();

        if (mouseOver(mx, my, 500, 420, 200, 100)) {
                game.playSound(5);
                game.getScreenManager().setScreen(State.Menu);
                return;
            }
            if (mouseOver(mx, my, 600,20, 120, 100)) {
                game.playSound(5);
                audioOn = !isAudioOn();
                System.out.println(audioOn);

                if (!audioOn) {
                    Game.musicOn = false;
                    Game.soundEffectsOn = false;
                }else{
                    Game.musicOn = true;
                    Game.soundEffectsOn = true;  
                }  
                game.repaint();
            }
    }
    
    public void tick(){

    }
    public void mouseReleased(MouseEvent e){
        
    }

    public void render(Graphics g){
        g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("HELP", 260, 100);

            g.setFont(fnt3);
            g.drawString("Use space to change your gravity", 100, 300);

            g.setFont(fnt2);
            g.drawRect(500, 420, 200, 100);
            g.drawString("Back", 530, 490);

            g.drawRect(600, 20, 120, 100);
            if (audioOn) {
                g.drawString("ON", 620, 90);
            }else 
                g.drawString("OFF", 610, 90);
            
        
    }
}

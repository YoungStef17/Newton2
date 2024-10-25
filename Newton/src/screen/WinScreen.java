package src.screen;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.MouseEvent;

import src.framework.State;
import src.window.Game;

public class WinScreen extends AbstractScreen {
    int score = 0;
    public WinScreen(Game game){
        super(game);
    }
    public void mousePressed(MouseEvent e){
        int mx= e.getX();
        int my= e.getY();

        if (mouseOver(mx, my, 400, 420, 350, 100)) {
            game.playSound(5);
            game.getScreenManager().setScreen(State.Menu);
            return;
        }
    }
   
    public void tick(){

    }
    public void mouseReleased(MouseEvent e){
        
    }

    public void render(Graphics g){
        g.setColor(Color.white);

            g.setFont(fnt2);
            g.drawRect(400, 420, 350, 100);
            g.drawString("Back to menu", 410, 490);
    }
}

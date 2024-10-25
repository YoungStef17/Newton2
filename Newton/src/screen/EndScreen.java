package src.screen;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.MouseEvent;

import src.framework.State;
import src.window.Game;

public class EndScreen extends AbstractScreen {

    public EndScreen(Game game) {
        super(game);
    }
    public void mousePressed(MouseEvent e){
        int mx= e.getX();
        int my= e.getY();

        if (mouseOver(mx, my, 80, 420, 250, 100)) {
            game.playSound(5);
            game.getScreenManager().setScreen(State.Game);
        }
        if (mouseOver(mx, my, 400, 420, 350, 100)) {
            game.playSound(5);
            game.getScreenManager().setScreen(State.Menu);
            return;
        }
    }
    public void mouseReleased(MouseEvent e){
        
    }
    public void tick(){

    }

    public void render(Graphics g){
        g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Game Over", 200, 100);

            g.setFont(fnt3);
            g.drawString("You lost with a score of: "/* + game.getScore()*/, 100, 300);

            g.setFont(fnt2);
            g.drawRect(80, 420, 250, 100);
            g.drawString("Try Again", 90, 490);

            
            g.drawRect(400, 420, 350, 100);
            g.drawString("Back to menu", 410, 490);
    }
    
}

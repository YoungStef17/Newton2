package src.screen;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.MouseEvent;

import src.framework.State;
import src.window.Game;

public class SelectLevelScreen extends AbstractScreen{

    public SelectLevelScreen(Game game){
        super(game);
    }

    public void mousePressed(MouseEvent e){
        int mx= e.getX();
        int my= e.getY();

        if(mouseOver(mx, my, 80, 50, 600, 100)){
            game.getScreenManager().setScreen(State.Game);
            game.playSound(5);
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
        g.setFont(fnt3);
            g.setColor(Color.white);
            //g.drawString("Select difficulty", 200, 100);

            g.drawRect(80, 50, 600, 100);
            g.drawString("Easy", 110, 110);
            g.drawString("Highscore:"/*+ game.getHighscore()*/, 450, 110);

            g.drawRect(80, 200, 600, 100);
            g.drawString("Medium", 110, 260);

            //g.drawRect(520, 200, 200, 100);
            //g.drawString("Hard", 540, 270);

            g.drawRect(400, 420, 350, 100);
            g.drawString("Back to menu", 430, 480);
    }
}

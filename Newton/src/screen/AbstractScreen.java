package src.screen;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;


import src.framework.Sound;
import src.window.Game;

public abstract class AbstractScreen extends MouseAdapter {
    protected Game game;
    protected BufferedImage level ;
    protected Sound music = new Sound();
    protected Sound soundEffects = new Sound();
    protected boolean audioOn = true;
    protected Font fnt = new Font("aerial", 1, 100);
    protected Font fnt2 = new Font("aerial", 1, 50);
    protected Font fnt3 = new Font("aerial", 1, 20);

    public AbstractScreen(Game game){
        this.game = game;
        
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract void mousePressed(MouseEvent e);
    public abstract void mouseReleased(MouseEvent e);

    protected boolean mouseOver(int mx, int my, int x, int y, int width, int height){ // vedo se con il mouse sto sopra al pulsante
        if (mx > x && mx < x +width) {
            if (my > y && my < y+ height) {
                return true;
            } else
                return false;
        } else
            return false;

    }
    
    
    public boolean isAudioOn(){
        return audioOn;
    }
}

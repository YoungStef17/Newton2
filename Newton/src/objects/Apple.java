package src.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import src.window.Handler;

import src.framework.GameObject;
import src.framework.ObjectId;
import src.framework.Texture;
import src.screen.PlayingScreen;
import src.window.Animation;
import src.window.Game;


public class Apple extends GameObject {
    Texture tex = PlayingScreen.getInstance();
    private Animation appleRotating;

    
    public Apple(float x, float y, ObjectId id, Handler handler, PlayingScreen playingScreen){
        super(x, y, id, handler, playingScreen);    
        appleRotating = new Animation(5,tex.apple[0],tex.apple[2],tex.apple[3],tex.apple[4],tex.apple[5],tex.apple[6],tex.apple[7],tex.apple[8],tex.apple[9]);
    }
    public void tick(ArrayList<GameObject> object) {
        appleRotating.runAnimation();
    }

    public void render(Graphics g) {
        appleRotating.drawAnimation(g, (int) x, (int) y);

        /*g.setColor(Color.green);
        g.fillRect((int)x, (int)y, 32, 32);
        */
    }

    public  Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,32,32);
    }
    public  Rectangle getBoundsBot(){
        return new Rectangle();

    }
    public  Rectangle getBoundsTop(){
        return new Rectangle();

    }

 
}

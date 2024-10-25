package src.objects;

import java.util.ArrayList;

import src.framework.GameObject;
import src.framework.ObjectId;
import src.framework.Texture;
import src.screen.PlayingScreen;
import src.window.Handler;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Block extends GameObject{

    Texture tex = PlayingScreen.getInstance();
    public int type;
    
    public Block(float x, float y, int type, ObjectId id, Handler handler, PlayingScreen playingScreen){
        super(x, y, id, handler, playingScreen);
        this.type = type;
    }

    public  void tick(ArrayList <GameObject> object){

    }
        public  void render(Graphics g){
                if (type == 0) {
                g.drawImage(tex.block[0], (int) x, (int) y, null);
                }
                if (type == 1) {
                    g.drawImage(tex.block[1], (int) x, (int) y, null);
                }
                if (type == 2) {
                    g.drawImage(tex.block[2], (int) x, (int) y, null);
                }
                if (type == 3) {
                    g.drawImage(tex.block[3], (int) x, (int) y, null);
                }
                if (type == 4) {
                    g.drawImage(tex.block[4], (int) x, (int) y, null);
                }
                if (type == 5) {
                    g.drawImage(tex.block[5], (int) x, (int) y, null);
                }
                if (type == 6) {
                    g.drawImage(tex.block[6], (int) x, (int) y, null);
                }
                /*g.setColor(Color.white);
                g.drawRect((int)x,(int) y, 32, 32);
                Graphics2D g2d = (Graphics2D) g;
                g.setColor(Color.red);
                g2d.draw(getBoundsTop());/
                //g2d.draw(getBoundsBot());
                g2d.draw(getBounds());*/
    }

        public Rectangle getBoundsTop(){//top
            return new Rectangle((int) x, (int) y, 32,10);
        }
        public Rectangle getBounds(){
            return new Rectangle((int) x, (int) (y), 32, 32);
        }

        public Rectangle getBoundsBot(){
            return new Rectangle((int) x, (int) (y+22), 32,10);
        }
}

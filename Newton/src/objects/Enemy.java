package src.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import src.framework.GameObject;
import src.framework.ObjectId;
import src.framework.State;
import src.framework.Texture;
import src.screen.PlayingScreen;
import src.window.Animation;
import src.window.Handler;


public class Enemy extends GameObject{
    
    private float width=64 , height=64;
    private Queue<Action> actionQueue = new LinkedList<>();
    Texture tex = PlayingScreen.getInstance();
    private Animation enemyAttacking;


    private static class Action {
        long timestamp; // Time when the action was performed
        boolean gravityInverted; // Gravity state at the time of the action

        Action(long timestamp, boolean gravityInverted) {
            this.timestamp = timestamp;
            this.gravityInverted = gravityInverted;
        }
    }


    public Enemy(float x, float y,  ObjectId id,Handler handler, PlayingScreen playingScreen) {
        super(x, y, id, handler, playingScreen);
        
        enemyAttacking = new Animation(5,tex.enemy[0],tex.enemy[1],tex.enemy[2],tex.enemy[3],tex.enemy[4],tex.enemy[5],tex.enemy[6],tex.enemy[7],tex.enemy[8]);
    }
    
    public void tick(ArrayList<GameObject> object) {
        enemyAttacking.runAnimation();

        x+=velX;
        y+=velY;        
        
        if (!actionQueue.isEmpty()) {
            Action oldestAction = actionQueue.peek();
            if (System.currentTimeMillis() - oldestAction.timestamp >= 1000) {
                // 1 seconds have passed, perform the oldest action
                setGravityInverted(oldestAction.gravityInverted);
                actionQueue.poll(); // Remove the action from the queue
            }
        }

        if(gravityInverted){
            velY+= -gravity; //inverti gravità --> sale
        }else 
            velY+=gravity; //gravità normale --> scende

        if(velY> Player.MAX_SPEED){
            velY=Player.MAX_SPEED; //limito la velocità
        } else if (velY < -Player.MAX_SPEED) {
            velY = -Player.MAX_SPEED;
            }
        
        x+=3;
        Collision(object);
    }
    
    public void addAction(boolean gravityInverted) {
        long currentTime = System.currentTimeMillis();
        actionQueue.add(new Action(currentTime, gravityInverted));
    }
    
    private void Collision(ArrayList<GameObject> objects){

        for(int i=0; i< handler.object.size(); i++){
            GameObject tempObject= handler.object.get(i);
            if(tempObject.getId() == ObjectId.Block){
                Block block = (Block) tempObject;

                if (getBoundsTop().intersects(tempObject.getBoundsBot())) {
                    y=tempObject.getY() +32;
                    velY = 0;
                    canSwitchGravity=true;
                    if (block.type == 1) {
                        velX =(float) 0.7;
                        handler.cam.velX = (float) 0.7;
                    }
                    if (block.type == 2) {
                        velX = (float) -0.7;
                        //handler.cam.velX = (float) -0.2;
                    }
                    if (block.type == 3) {
                        velX=0;
                        handler.cam.velX=0;                    
                    }


                }

                if (getBoundsBot().intersects(tempObject.getBoundsTop())) { //intersects verifica se 2 rettangoli si intersecano
                    y=tempObject.getY() - height;
                    velY = 0;
                    canSwitchGravity=true;
                    if (block.type == 1) {
                        velX = (float) 0.7;
                        handler.cam.velX = (float) 0.7;
                    }
                    if (block.type == 2) {
                        velX = (float) -0.7;
                        //handler.cam.velX = (float) -0.2;
                    }
                    if (block.type == 3) {
                        velX=0;
                        handler.cam.velX=0;
                    }
                }
                
                //Right
                if (getBounds().intersects(tempObject.getBounds())) {
                    x=tempObject.getX() -64;
                }
                //left
                if (getBoundsLeft().intersects(tempObject.getBounds())) {
                    x=tempObject.getX() +64;
                }

                
            }else if (tempObject.getId() == ObjectId.Player) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    playingScreen.getGame().stopMusic();
                    playingScreen.getGame().playSound(7);
                    playingScreen.getGame().getScreenManager().setScreen(State.End);
                    playingScreen.resetGame();
                }
            }
        }


    }

    public void render(Graphics g) {

        //g.setColor(Color.red);  // Colore diverso per il nemico
        //g.fillRect((int)x, (int)y, 64, 64);

        enemyAttacking.drawAnimation(g, (int) x, (int) y);

        //g.drawImage(tex.enemy[0],(int)x ,(int) y, null);
        
        /*Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.black);
        g2d.draw(getBoundsBot());
        g2d.draw(getBoundsTop());
        g2d.draw(getBounds());
        g2d.draw(getBoundsLeft());
        */
    }
        
    public Rectangle getBoundsBot() {

            return new Rectangle((int)(x+(width/2)-(width/2)/2), (int) (y+(height/2)) ,(int)width/2, (int)height/2 );
        } 
        
    
        public Rectangle getBoundsTop() {
    
            return new Rectangle((int)(x+(width/2)-(width/2)/2), (int) y,(int)width/2, (int)height/2 );
        }
    
        public Rectangle getBounds() {//right
    
            return new Rectangle((int)(x+width-5), (int) y+5,(int) 5, (int)height-10 );
        }
    
        public Rectangle getBoundsLeft() {
    
            return new Rectangle((int)x, (int) y+5,(int) 5, (int)height-10 );
        }
}


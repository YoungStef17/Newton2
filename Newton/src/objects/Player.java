package src.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import src.window.Animation;
import src.window.Handler;
import src.framework.GameObject;
import src.framework.ObjectId;
import src.framework.State;
import src.framework.Texture;
import src.screen.PlayingScreen;

public class Player extends GameObject {

    private float width=32 , height=64;
    public static final float MAX_SPEED=10;
    //private Handler handler;

    Texture tex = PlayingScreen.getInstance();
    private Animation playerRun;
    private Animation playerRunInverted;
    


    public Player(float x, float y, ObjectId id, Handler handler, PlayingScreen playingScreen){
        super(x, y, id, handler, playingScreen);
        playerRun = new Animation(5,tex.player[1],tex.player[2],tex.player[3],tex.player[4],tex.player[5],tex.player[6],tex.player[7]);
        playerRunInverted = new Animation(5,tex.playerInverted[0],tex.playerInverted[1],tex.playerInverted[2],tex.playerInverted[3],tex.playerInverted[4],tex.playerInverted[5],tex.playerInverted[6],tex.playerInverted[7]);

    }


    public void tick(ArrayList<GameObject> object) {
        //Aggiorna la posizione del giocatore in base alla sua velocità.
        x+=velX;
        y+=velY;        

        
        if(gravityInverted){
            velY+= -gravity; //inverti gravità --> sale
        }else 
            velY+=gravity; //gravità normale --> scende

        if(velY> MAX_SPEED){
            velY=MAX_SPEED; //limito la velocità
        } else if (velY < -MAX_SPEED) {
            velY = -MAX_SPEED;
            }
        
        x+=4;
        Collision(object);
        if (gravityInverted) {
            playerRunInverted.runAnimation();

        }else
            playerRun.runAnimation();

        

    }

    private void Collision(ArrayList<GameObject> objects){

        for(int i=0; i< handler.object.size(); i++){
            GameObject tempObject= handler.object.get(i);
            //System.out.println("oggetti handler: "+ handler.object.size());
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
                        velX = (float) 0.8;
                        handler.cam.velX = (float) 0.8;
                    }
                    if (block.type == 2) {
                        velX = (float) -0.7;
                        //handler.cam.velX = (float) -0.2;
                    }
                    if (block.type == 3) {
                        velX=0;
                        handler.cam.velX=0;
                    }
                    if (block.type == 4) {
                        playingScreen.getGame().stopMusic();
                        playingScreen.getGame().playSound(7);
                        playingScreen.getGame().getScreenManager().setScreen(State.End);
                        playingScreen.resetGame();
                    }
                   
                }
                
                //Right
                if (getBounds().intersects(tempObject.getBounds())) {
                    x=tempObject.getX() -32;
                }
                //left
                if (getBoundsLeft().intersects(tempObject.getBounds())) {
                    x=tempObject.getX() +32;
                }
                
            }else if (tempObject.getId() == ObjectId.Apple) {
                if (getBounds().intersects(tempObject.getBounds())|| getBoundsBot().intersects(tempObject.getBounds()) || getBoundsTop().intersects(tempObject.getBounds())) {
                    
                    PlayingScreen.score +=1000;
                    PlayingScreen.nApple ++;
                    playingScreen.getGame().playSound(1);
                    handler.removeObject(tempObject);
                }
            }else if (tempObject.getId() == ObjectId.InvisibleBlock) {
                if (getBounds().intersects(tempObject.getBounds())|| getBoundsBot().intersects(tempObject.getBounds()) || getBoundsTop().intersects(tempObject.getBounds())) {
                    playingScreen.getGame().getScreenManager().setScreen(State.Win);
                    playingScreen.getGame().stopMusic();
                    playingScreen.getGame().playSound(4);
                    playingScreen.resetGame();
                }
            }
            
        }


    }

    public void render(Graphics g) { 
        if (gravityInverted) {
            playerRunInverted.drawAnimation(g, (int) x, (int) y);
        }else
        playerRun.drawAnimation(g, (int) x, (int) y);


        
        //g.fillRect((int)x, (int) y,(int)width, (int)height);
        /* 
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.red);
        g2d.draw(getBoundsBot());
        g2d.draw(getBoundsTop());
        g2d.draw(getBounds());
        g2d.draw(getBoundsLeft());
        */
        
    }


    //definisco i rettangoli di collisione in diverse direzioni (alto, basso, sinistra e destra) per il giocatore
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
    /*_________________
      |     |    |     |
      |--|  |    |  |--|
      |  |  |----|  |  |
      |--|  |    |  |--| 
      |_____|____|_____| 
      il rettangolo è il giocatore, tutti i piccoli rettangoli sono i vari rettangoli di collisione    
     */
}

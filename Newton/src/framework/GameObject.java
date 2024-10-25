package src.framework;

import java.util.ArrayList;
import src.window.Handler;
import src.screen.PlayingScreen;
import src.window.Game;

import java.awt.Graphics;
import java.awt.Rectangle;

//è una classe base per tutti gli oggetti del gioco
public abstract class GameObject{
    public float x,y;
    protected float velX=0,velY=0;
    protected ObjectId id; 
    protected boolean gravityInverted=true;
    protected boolean canSwitchGravity = false; 
    protected PlayingScreen playingScreen;
    protected Handler handler;
    protected float gravity = 0.1f;
    //protected: significa che solo chi estende la classe può usare le variabili

    public GameObject(float x, float y, ObjectId id,Handler handler, PlayingScreen playingScreen){
        this.x = x;
        this.y=y;
        this.id = id;
        this.handler = handler;
        this.playingScreen = playingScreen;
    }

    public abstract void tick(ArrayList <GameObject> object);
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();
    public abstract Rectangle getBoundsBot();
    public abstract Rectangle getBoundsTop();
    
    public  float getX(){
        return x;
    }
    public  float getY(){
        return y;
    }
    public  void setX(float x){
        this.x = x;
    }
    public  void setY(float y){
        this.y = y;
    }

    public  float getVelX(){
        return velX;
    }
    public  float getVelY(){
        return velY;
    }
    public  void setVelX(float velX){
        this.velX=velX;
    }
    public  void setVelY(float velY){
        this.velY=velY;
    }

    public boolean isGravityInverted(){
        return gravityInverted;
    }

    public void setGravityInverted(boolean gravityInverted){
        this.gravityInverted=gravityInverted;
    }

    public  ObjectId getId(){
        return id;
    }

    public PlayingScreen getPlayingScreen(){
        return playingScreen;
    }

}
package src.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import src.objects.Enemy;
import src.objects.Player;
import src.window.Handler;

public class KeyInput extends KeyAdapter{

    Handler handler;

    public KeyInput(Handler handler){
        this.handler=handler;
    }

    public void keyPressed(KeyEvent e){
        int key=e.getKeyCode(); //ogni comando ha una key
        for(int i =0 ; i<handler.object.size(); i++){
            GameObject tempObject= handler.object.get(i);

            if (tempObject.getId() == ObjectId.Player) {
                Player player = (Player) tempObject;

                if (key == KeyEvent.VK_SPACE && player.canSwitchGravity ) {
                    player.setGravityInverted(!tempObject.isGravityInverted());
                    player.canSwitchGravity = false;
                    tempObject.getPlayingScreen().getGame().playSound(6);
             
                    for (GameObject obj : handler.object) {
                        if (obj.getId() == ObjectId.Enemy) {
                            ((Enemy) obj).addAction(player.isGravityInverted());
                        }
                    }
                }
            }

            if(key== KeyEvent.VK_ESCAPE){
                System.exit(1);   
            }
            
        }
    }
}
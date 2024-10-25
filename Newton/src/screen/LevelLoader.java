package src.screen;

import java.awt.Color;
import java.awt.image.BufferedImage;

import src.framework.ObjectId;
import src.objects.Apple;
import src.objects.Block;
import src.objects.Enemy;
import src.objects.Player;
import src.window.Handler;

public class LevelLoader {

    private Handler handler;
    private PlayingScreen playingScreen;

    public LevelLoader(PlayingScreen playingScreen, Handler handler){
        this.playingScreen = playingScreen;
        this.handler = handler;
    }

    public void loadImageLevel(BufferedImage image){
        int w = image.getWidth();
            int h = image.getHeight();
    
            for(int xx =0; xx<w; xx++){
                for(int yy=0; yy < h; yy++){
                    int pixel = image.getRGB(xx, yy);
                    Color pixelColor = new Color(pixel);
    
                    if( pixelColor.equals(Color.white)){
                        handler.addObject(new Block(xx*32, yy*32,0, ObjectId.Block, handler,playingScreen)); //aggiunge blocchi basici
    
                    }
                    if( pixelColor.equals(Color.red)){
                        handler.addObject(new Block(xx*32, yy*32,1, ObjectId.Block, handler,playingScreen)); //aggiunge blocchi speedUp
    
                    }
                    if( pixelColor.equals(Color.blue)){
                        handler.addObject(new Block(xx*32, yy*32,2, ObjectId.Block, handler,playingScreen)); //aggiunge blocchi speedDown
                    }
                    if (pixelColor.equals(Color.yellow)) {
                        handler.addObject(new Block(xx*32, yy*32,3, ObjectId.Block, handler,playingScreen)); //aggiunge blocchi normalSpeed
                    }
                    if (pixelColor.equals(Color.gray)) {
                        handler.addObject(new Block(xx*32, yy*32,4, ObjectId.Block, handler,playingScreen)); //aggiunge blocchi gameOver
                    }
                    if (pixelColor.equals(Color.magenta)) {
                        handler.addObject(new Block(xx*32, yy*32,5, ObjectId.InvisibleBlock, handler,playingScreen)); //aggiunge blocchi win
                    }
                    if (pixelColor.equals(new Color(180,130,0))) {
                        handler.addObject(new Block(xx*32, yy*32,6, ObjectId.Block, handler ,playingScreen)); 
                    }
                    
                    
                    if( pixelColor.equals(Color.lightGray)){
                        Player player = new Player(xx*32, yy*32,ObjectId.Player, handler,playingScreen);
                        handler.addObject(player);
                        playingScreen.setPlayer(player);
                    }
                    if( pixelColor.equals(Color.green)){
                        handler.addObject(new Apple(xx*32, yy*32, ObjectId.Apple, handler,playingScreen));
                    }
                    if( pixelColor.equals(Color.CYAN)){
                        Enemy enemy = new Enemy(xx*32, yy*32, ObjectId.Enemy,handler,playingScreen);
                        handler.addObject(enemy);
                        playingScreen.setEnemy(enemy);

                    }
    //moltiplico per 32 perchè 1 pixel rappresenta un blocco 32x32
    
                }
            }
    }
}

package src.framework;

import java.awt.image.BufferedImage;
import src.window.BufferImageLoader;

public class Texture {
    
    
    SpriteSheet bs1, bs2, ps,es,as;

    private BufferedImage block_sheet1 = null;
    private BufferedImage block_sheet2 = null;
    private BufferedImage player_sheet = null;
    private BufferedImage enemy_sheet = null;
    private BufferedImage apple_sheet = null;



    public BufferedImage[] block = new BufferedImage[11];
    public BufferedImage[] player = new BufferedImage[8];
    public BufferedImage[] playerInverted = new BufferedImage[8];
    public BufferedImage[] enemy = new BufferedImage[9];
    public BufferedImage[] apple = new BufferedImage[10];



    public Texture(){
        BufferImageLoader loader = new BufferImageLoader();
        try {
            block_sheet1 = loader.loadImage("/src/res/spriteBlock1.png");
            block_sheet2 = loader.loadImage("/src/res/spriteBlock2.png");
            player_sheet = loader.loadImage("/src/res/spritePlayer.png");
            enemy_sheet = loader.loadImage("/src/res/spriteEnemy.png");
            apple_sheet = loader.loadImage("/src/res/spriteApple.png");

        } catch (Exception e) {
            e.printStackTrace();
       }

        bs1 = new SpriteSheet(block_sheet1);
        bs2 = new SpriteSheet(block_sheet2);
        ps = new SpriteSheet(player_sheet);
        es = new SpriteSheet(enemy_sheet);
        as = new SpriteSheet(apple_sheet);
        getTextures();
    }

    private void getTextures(){

        block[0] = bs1.grabImage(5, 10, 32, 32);//leaf block
        block[1] = bs1.grabImage(21, 9, 32, 32);//speed block
        block[2] = bs1.grabImage(13, 9, 32, 32);//slow block
        block[3] = bs1.grabImage(3, 4, 32, 32);//reset vel block
        block[4] = bs2.grabImage(9, 16, 32, 32);//die block
        block[5] = bs1.grabImage(5, 7, 32, 32);//win block
        block[6] = bs1.grabImage(17, 12, 32, 32);//bark block
        block[10] = bs1.grabImage(5, 9, 32, 32);//leaf2 block
        


        
        for(int i = 0; i <8 ; i++){
            player[i] = ps.grabImagePadding(1+i, 1, 64, 64);

        }
        for(int i = 0; i <8 ; i++){
            playerInverted[i] = ps.grabImagePadding(1+i, 2, 64, 64);
        }
        for(int j=0;j<3;j++){
            for(int i=0;i<3;i++){
               enemy[j*3+i] = es.grabImage(i+1, j+1, 64, 64);  
            }
        }

        for(int j=0;j<2;j++){
            for(int i=0;i<5;i++){
               apple[j*5+i] = as.grabImage(i+1, j+1, 32, 32);               
           }
        }
    
    }
}

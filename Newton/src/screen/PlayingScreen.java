package src.screen;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import src.framework.GameObject;
import src.framework.KeyInput;
import src.framework.ObjectId;
import src.framework.State;
import src.framework.Texture;
import src.objects.Enemy;
import src.objects.Player;
import src.window.BufferImageLoader;
import src.window.Camera;
import src.window.Game;
import src.window.Handler;
import src.window.HighScoreManager;
import src.window.UI;

public class PlayingScreen extends AbstractScreen{
    private final int FPS_SET = 140;
    private final int UPS_SET = 140;
    public static int score = 0;
    public static int nApple = 0;

        public static Texture tex ;
        public Handler handler;
        public Camera cam;
        private Player player;
        private Enemy enemy;
        
        public UI userInterface = new UI(this);
        public BufferedImage level = null, backgroundImg = null;
        


        public HighScoreManager highScoreManager;
        private Timer updateTimer;
        private Timer frameTimer;
    private LevelLoader levelLoader;

    public PlayingScreen(Game game){
        super(game);
        highScoreManager = new HighScoreManager("highscore.txt");
        init();

    }


    public void init(){
        BufferImageLoader loader = new BufferImageLoader();
        level = loader.loadImage("/src/res/level.png");
        backgroundImg = loader.loadImage("/src/res/background.png");
        tex = new Texture();
    
        cam = new Camera(0,0,0);
    
        handler = new Handler(cam);

        levelLoader = new LevelLoader(this, handler);
    
        game.addKeyListener(new KeyInput(handler));

    }

    public void startGameLoop(){
        game.setFocusable(true);
        game.requestFocus();
        double timePerUpdate = 100.0 / FPS_SET;  // Tempo per aggiornamento in millisecondi
        double timePerFrame = 100.0 / UPS_SET;   // Tempo per frame in millisecondi
        // Timer per gli aggiornamenti di logica di gioco (UPS)
        updateTimer = new Timer((int) timePerUpdate, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tick();  // Chiama il metodo per aggiornare lo stato del gioco
            }
        });
        // Timer per il rendering (FPS)
        frameTimer = new Timer((int) timePerFrame, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.repaint();
            }
        });
        updateTimer.start();
        frameTimer.start();
    }
    
    public void stopGameLoop(){
        updateTimer.stop();
        frameTimer.stop();
    }
    
    public void tick() {
        handler.tick();
    
        cam.tick();    
        score ++;
        
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
    
            if (tempObject.getId() == ObjectId.Player) {

                if (playerOutOfBounds()) {
                    gameOver();
                }
            }
            if (tempObject.getId() == ObjectId.Enemy) {
                if (enemyOutOfBounds() ) {
                    
                }   
            }
        }  
    }

    public void gameOver(){
        game.stopMusic();
        game.playSound(7);
        game.getScreenManager().setScreen(State.End);
        resetGame();
    }

    public void respawnEnemyIfOutOfBounds(){
        enemy.x=player.x-256;
        enemy.y = player.y;
        enemy.setVelX(player.getVelX());
    }

    public boolean playerOutOfBounds(){
        if (player.getY() > (cam.getY() + Game.HEIGHT+128) || player.getY() < cam.getY()-128 || player.getX() < (-1 *cam.getX()-64)) {
            return true;
        }else   
            return false;
    }
    public boolean enemyOutOfBounds(){
        if (enemy.getY() > (cam.getY() + Game.HEIGHT+128) || enemy.getY() < cam.getY()-128 || enemy.getX() < (-1 *cam.getX()-256)) {
            return true;
        }else   
            return false;
    }

    public void render(Graphics g) {
        Graphics2D g2d=(Graphics2D) g;//trasformo g in g2d perchè graphics2d fornisce funzioni + avanzate come la traslazione
        
        g2d.translate(cam.getX(), cam.getY());//begin of cam
        //translate() sposta l'intera area di disegno in base alle coordinate della telecamera.
        for(int xx = 0; xx < (backgroundImg.getWidth()*33) ; xx+=backgroundImg.getWidth())
            g.drawImage(backgroundImg, xx, 0 , game);
        
            handler.render(g);
        

        g2d.translate(-cam.getX(), -cam.getY());//end of cam
        //Se non usassi g2d.translate(-cam.getX(), -cam.getY()), la traslazione si accumulerebbe ad ogni ciclo di rendering, ciò che disegnerei nei cicli successivi sarebbe ulteriormente spostato di una certa quantità rispetto alla posizione corrente
        userInterface.draw(g2d);
        //g.dispose();
    }

    public void setPlayer(Player player){
        this.player = player;
    }
    public void setEnemy(Enemy enemy){
        this.enemy = enemy;
    }
    public void setUpGame(){
        score=0;
        nApple =0;

        levelLoader.loadImageLevel(level);

        game.playMusic(0);
        userInterface.showMessage("Use space to jump");

    }

    public void resetGame(){
        handler.clearObjects();

        cam.setX(0);
        cam.setY(0);
        cam.velX=0;

        if (highScoreManager.checkAndUpdateHighScore(score)) {
            System.out.println("nuovo highscore"+ highScoreManager.getHighscore());
        }
    }

    public int getHighscore(){
        return highScoreManager.getHighscore();
    }
        
    public static Texture getInstance(){
        return tex;
    }
    

    public int getScore(){
        return score;
    }
    
    public int getApple(){
        return nApple;
    }

    public Game getGame(){
        return game;
    }
    public Handler getHandler(){
        return handler;
    }
    public void mousePressed(MouseEvent e) {
        
    }
    
    public void mouseReleased(MouseEvent e) {
        
    }
}

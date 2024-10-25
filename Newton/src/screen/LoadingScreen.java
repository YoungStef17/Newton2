package src.screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import src.framework.State;
import src.window.BufferImageLoader;
import src.window.Game;

public class LoadingScreen extends AbstractScreen {
    
    private BufferedImage loadingImage;
    private long loadingStartTime = 0;
    private boolean isTimerStarted= false;

    
    public LoadingScreen(Game game) {
        super(game);
        // Carica l'immagine di caricamento
        BufferImageLoader loader = new BufferImageLoader();
        loadingImage = loader.loadImage("/src/res/icon/iconLogo.png"); // Percorso dell'immagine
    }

    public void mousePressed(MouseEvent e){

    }
    public void tick(){

    }
    
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        // Ottieni le dimensioni della finestra
        int windowWidth = Game.WIDTH;
        int windowHeight = Game.HEIGHT;
        
        // Disegna l'immagine ridimensionata per adattarsi alla finestra
        
        g2d.drawImage(loadingImage, 0, 0, windowWidth, windowHeight, null);
        g.setColor(Color.white);
        g2d.drawString("TEORIZZANDO LA GRAVITAZIONE...", 300,495 );
        timeLoading();
    }
    
    public void timeLoading(){
        
        if (!isTimerStarted) {
            // Inizia il timer solo una volta
            loadingStartTime = System.currentTimeMillis();
            isTimerStarted = true;
        }
    
        // Calcola il tempo trascorso
        long elapsedTime = System.currentTimeMillis() - loadingStartTime;
    
        if (elapsedTime > 5000) {  // Aspetta 5 secondi (5000 ms)
            // Una volta trascorsi i 5 secondi, cambia lo stato del gioco
            game.getScreenManager().setScreen(State.Menu);
            System.out.println("Tempo trascorso: vai al menu.");
        }
        game.repaint();
    }
    public void mouseReleased(MouseEvent e){
        
    }
    
}

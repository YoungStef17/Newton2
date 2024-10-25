package src.window;

import java.awt.Graphics;
import java.security.cert.CertPath;
import java.awt.Color;

import javax.swing.JPanel;

import src.framework.Sound;
import src.framework.State;

import src.screen.ScreenManager;
    
    
    public class Game extends JPanel{
        
        public static int HEIGHT,WIDTH;
        
        private ScreenManager screenManager;
    
        public State gameState = State.LoadingScreen;
        

        private State previousState;
        public  Sound music = new Sound();
        public static Sound soundEffects = new Sound();
        public static Boolean musicOn= true;
        public static Boolean soundEffectsOn = true;


        public Game(){
            setDoubleBuffered(true);
            screenManager = new ScreenManager(this);
            
        }
    
    
        //metodo che è responsabile dell'aggiornamento della logica del gioco (es. movimento dei personaggi, calcolo delle collisioni, ecc.)
        private void tick(){

            if (previousState != gameState) {
                screenManager.updateMouseListeners(); // Aggiorna i listener solo se lo stato è cambiato
                previousState = gameState; // Aggiorna lo stato precedente
            }
    
           
            screenManager.tick();
            
        }
    
        //metodo per disegnare sullo schermo usando una strategia di buffering per migliorare la fluidità e ridurre lo sfarfallio.
    
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            WIDTH= getWidth();
            HEIGHT = getHeight();
            g.setColor(Color.black);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            
            tick();  
            screenManager.render(g);   
    
        }

        
       
        public void playMusic(int ind) {
            if (musicOn) {
                music.setFile(ind);
                music.play();
                music.loop();
            }
        }
        public void stopMusic() {
            if (musicOn) {
                music.stop();
            }
        }
    
        public void playSound(int ind) {
            if (soundEffectsOn) {
                soundEffects.setFile(ind);
                soundEffects.play();
            }
        }
        public ScreenManager getScreenManager(){
            return screenManager;
        }

        public static void main(String[] args) {
            new Window(800, 600, "Newton",new Game());
        }
    }
    
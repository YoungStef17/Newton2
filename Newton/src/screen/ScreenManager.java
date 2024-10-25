package src.screen;

import java.awt.Graphics;
import java.awt.event.MouseListener;
import src.framework.State;
import src.window.Game;

public class ScreenManager {

    private Game game;

    private MenuScreen menusScreen;
    private SelectLevelScreen selectLevelScreen;
    private EndScreen endScreen;
    private WinScreen winScreen;
    private HelpScreen helpScreen;
    private LoadingScreen loadingScreen;
    private PlayingScreen playingScreen;


    // Costruttore per inizializzare le schermate
    public ScreenManager(Game game) {
        this.game = game;

        this.menusScreen = new MenuScreen(game);
        this.selectLevelScreen = new SelectLevelScreen(game);
        this.endScreen = new EndScreen(game);
        this.winScreen = new WinScreen(game);
        this.helpScreen = new HelpScreen(game);
        this.loadingScreen = new LoadingScreen(game);
        this.playingScreen = null;
    }

    public void tick(){

    }
    
    public void render(Graphics g){
            
        switch (game.gameState) {
            case Menu:
                menusScreen.render(g);
                break;
            case SelectLevel:
                selectLevelScreen.render(g);
                break;
            case End:
                endScreen.render(g);
                break;
            case Win:
                winScreen.render(g);
                break;
            case Help:
                helpScreen.render(g);
                break;
            case LoadingScreen:
                loadingScreen.render(g);
                break;
            case Game:
                playingScreen.render(g);
            default:
                break;
        }
        
    }
public void setScreen(State newState) {
    System.out.println("Cambiamento di schermata: da " + game.gameState + " a " + newState);

        // Gestisce il passaggio tra schermate
        if (game.gameState == State.Game && playingScreen != null) {
            System.out.println("Fermando il game loop nella PlayingScreen.");

            playingScreen.stopGameLoop();  // Ferma il game loop se si passa da PlayerScreen
        }

        game.gameState = newState;  // Aggiorna lo stato del gioco

        // Inizializza il nuovo stato e avvia il game loop se necessario
        switch (newState) {
            case Game:
                if (playingScreen == null) {
                    System.out.println("Creazione della PlayingScreen.");

                    playingScreen = new PlayingScreen(game);  // Crea la PlayerScreen se non esiste
                }
                System.out.println("Inizializzazione del gioco in PlayingScreen.");
                playingScreen.setUpGame();

                playingScreen.startGameLoop();  // Avvia il game loop
                break;
            default:
                game.repaint();
                System.out.println("ridipingi");
                break;
        }
        
    }
    public void updateMouseListeners() {
        // Rimuovi tutti i mouse listener esistenti
        for (MouseListener listener : game.getMouseListeners()) {
            game.removeMouseListener(listener);
        }

        // Aggiungi il listener appropriato in base allo stato del gioco
        switch (game.gameState) {
            case Menu:
                game.addMouseListener(menusScreen);
                break;
            case SelectLevel:
                game.addMouseListener(selectLevelScreen);
                break;
            case End:
                game.addMouseListener(endScreen);
                break;
            case Win:
                game.addMouseListener(winScreen);
                break;
            case Help:
                game.addMouseListener(helpScreen);
                break;
            case LoadingScreen:
                game.addMouseListener(loadingScreen);
                break;
            default:
                break;
        }
    }

    public PlayingScreen getPlayingScreen(){
        return playingScreen;
    }
}

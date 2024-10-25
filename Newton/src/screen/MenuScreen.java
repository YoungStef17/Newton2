package src.screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import src.window.BufferImageLoader;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import src.framework.State;
import src.window.Game;


public class MenuScreen extends AbstractScreen {

    private BufferedImage menuImage, buttonImage;


    public MenuScreen(Game game) {
        super(game);
        BufferImageLoader loader = new BufferImageLoader();
        menuImage = loader.loadImage("/src/res/icon/menuBackground.png"); // Percorso dell'immagine
        buttonImage = loader.loadImage("/src/res/icon/button.png");
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        // Play button
        if (mouseOver(mx, my, 280, 180, 200, 100)) {
            game.playSound(5);
            game.getScreenManager().setScreen(State.SelectLevel);
        }

        // Command button
        if (mouseOver(mx, my, 280, 300, 200, 100)) {
            game.playSound(5);
            game.getScreenManager().setScreen(State.Help);
        }

        // Quit button
        if (mouseOver(mx, my, 280, 420, 200, 100)) {
            game.playSound(5);
            System.exit(1);
        }
    }

    public void mouseReleased(MouseEvent e) {
        // Può essere lasciato vuoto se non necessario
    }

    public void tick() {
        // Aggiorna logica se necessario (vuoto per ora)
    }

    public void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        
        int windowWidth = Game.WIDTH;
        int windowHeight = Game.HEIGHT;

        g2d.drawImage(menuImage, 0, 0, windowWidth, windowHeight, null);
        
        if (menuImage == null) {
            System.out.println("L'immagine del menu non è stata caricata correttamente.");
        } else {
            System.out.println("L'immagine del menu è stata caricata correttamente.");
        }
        // Disegna titolo del menu
        
        g.setFont(fnt);
        g.setColor(Color.white);
        g.drawString("MENU", 240, 100);

        // Disegna i pulsanti
        drawButton(g, "PLAY", 200, 180, 360, 80);
        drawButton(g, "HELP", 200, 300, 360, 80);
        drawButton(g, "QUIT", 200, 420, 360, 80);
    }

    // Metodo per disegnare pulsanti riutilizzabile
    private void drawButton(Graphics g, String text, int x, int y, int width, int height) {

        Graphics2D g2d = (Graphics2D) g;

        // Colore di sfondo del pulsante

        g2d.drawImage(buttonImage, x, y, width, height, null);

        // Contorno del pulsante
        //g.setColor(Color.white);
        //g.drawRect(x, y, width, height);

        // Testo del pulsante
        g.setFont(fnt2);
        g.setColor(Color.white);
        g.drawString(text, x + (width/4) +15, y+ (height/2)+15);
    }
}
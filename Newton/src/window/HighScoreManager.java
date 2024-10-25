package src.window;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HighScoreManager {
    public int highScore;
    private String filePath;

    public HighScoreManager(String filePath){
        this.filePath = filePath;
        this.highScore = loadHighScore();
    }

    public void saveHighScore(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            
            writer.write(String.valueOf(highScore));
        }
        catch (IOException e){
            System.out.println("Errore durante il salvataggio");
        }
    }
    public int loadHighScore(){
        int loadedHighScore = 0;
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line = reader.readLine();
            if (line != null) {
                loadedHighScore = Integer.parseInt(line);
            }
        }catch(IOException | NumberFormatException e){
            System.out.println("Errore durante il caricamento");
        }
        return loadedHighScore;
    }

    public boolean checkAndUpdateHighScore(int currentScore){
        if (currentScore > highScore) {
            highScore = currentScore;
            saveHighScore();
            return true;
        }
        return false;
    }

    public int getHighscore(){
        return highScore;
    }
    public void resetHighscore(){   
        highScore = 0;
    }
}

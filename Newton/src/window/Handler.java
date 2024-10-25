package src.window;

import java.awt.Graphics;
import java.util.ArrayList;

import src.framework.GameObject;
import src.framework.ObjectId;


//classe Handler è un componente fondamentale per la gestione e l'aggiornamento di tutti gli oggetti del gioco
//è responsabile della gestione di tutti i GameObject nel gioco, aggiornandoli (tick) e renderizzandoli (render).
public class Handler {

    public ArrayList<GameObject> object = new ArrayList<GameObject>(); //contiene tutti gli oggetti di gioco attivi.

    public Camera cam;

    public Handler(Camera cam) {  // Passa la camera nel costruttore
        this.cam = cam;
    }
    /* 
    il metodo tick viene chiamato ad ogni ciclo di gioco per aggiornare lo stato di ciascun oggetto
    scorre la lista di tutti gli oggetti di gioco
    Per ogni oggetto, chiama il suo metodo tick() (che è definito nella classe concreta che estende GameObject), 
    */
    public void tick(){
        
        for(int i =0; i< object.size(); i++){
            GameObject tempObject = object.get(i);
            
            
            tempObject.tick(object);
        }
    }

    /*
    Scorre la lista degli oggetti, come nel metodo tick.
    Per ogni oggetto, chiama il suo metodo render(Graphics g) per disegnare l'oggetto
    */
    public void render (Graphics g){
        
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
            
            if ((tempObject.x > -cam.getX() + Game.WIDTH) || (tempObject.x < -cam.getX() - 32) || (tempObject.y > -cam.getY() + Game.HEIGHT) || (tempObject.y < -cam.getY() - 32)) {
                ;
            } else {
                // Controllo se l'oggetto è fuori dalla visuale della camera (e.g. a sinistra dello schermo)
                if (tempObject.getX() < (-cam.getX() - 100)) { 
                    if (tempObject.getId()!=ObjectId.Enemy) {
                        System.out.println("Rimuovo oggetto: " + tempObject.getId());

                        removeObject(tempObject); // Rimuove l'oggetto se è fuori dalla visuale
                        i--; // Decremento l'indice per evitare errori di salto durante la rimozione
                    }
                } else {
                    tempObject.render(g); // Renderizza l'oggetto se è ancora visibile
                }
        }
        }
    }

    public void addObject(GameObject object){
        this.object.add(object);
    }

    public void removeObject( GameObject object){
        this.object.remove(object);
    }

    public void clearObjects(){
        object.clear();
    }


}
/*
 *La classe Handler è un componente chiave che centralizza la gestione di tutti gli oggetti nel gioco. I suoi compiti principali includono:

Aggiornare ogni oggetto chiamando il loro metodo tick().
Renderizzare ogni oggetto chiamando il loro metodo render(Graphics g).
Gestire l'aggiunta e la rimozione di oggetti dal gioco, mantenendo aggiornata la lista degli oggetti attivi.
 */

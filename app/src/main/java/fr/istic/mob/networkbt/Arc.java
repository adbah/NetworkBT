package fr.istic.mob.networkbt;

import android.graphics.Color;
import android.graphics.Path;

import java.util.Collection;
import java.util.List;

public class Arc {
    private Node debut;
    private Node fin;
    private int color;

    public static int ARC_WIDTH= 20;


    public Arc(Node debut, Node fin) {
        this.color = Color.WHITE;
        this.debut = debut;
        this.fin = fin;
    }

    public Node getDebut() {
        return debut;
    }

    public Node getFin() {
        return fin;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setFin(Node fin) {
        this.fin = fin;
    }

    /**
     * *
     *
     * @param nwNode Remplace le debut de l'arc par nwNode
     */
    public void setDebut(Node nwNode) {
        debut = nwNode;
    }

    public String toString() {
        return debut.toString() + " -->  " + fin.toString();
    }

    /**
     * Le path de l'arc
     * @return
     */
    public Path getPath(){
        int x1 = this.getDebut().getX();
        int x2 = this.getFin().getX();
        int y1 = this.getDebut().getY();
        int y2 = this.getFin().getY();
        final Path path = new Path();
        path.moveTo(x1, y1);
        path.lineTo(x1, y1);
        path.lineTo(x2, y2);
        return path;
    }

}


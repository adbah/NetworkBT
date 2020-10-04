package fr.istic.mob.networkbt;

import android.graphics.Color;

import java.util.Random;

public class Node {
    //les attributs du noeud
    private int x;
    private int y;
    private int color;
    private String etiquette;
    private int width;

    //Parametres pas defaut du noeud
    public static int DEFAULT_COLOR=Color.RED;
    public static String DEFAULT_ETIQUETTE="";

    public static int DEFAULT_RADIUS=100;

    //Constructeurs
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.color = DEFAULT_COLOR;
        this.width = DEFAULT_RADIUS;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getEtiquette() {
        return this.etiquette;
    }

    /**
     * retourne l'etiquette du noeud si taille inferieur à 5, abrege l'etiquette sinon
     * @return String
     */

    public int getColor() {
        return this.color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getWidth() {
        return width;
    }

    /**
     * @param CoordMax
     * @return une coordonnée comprise entre 100 et max-100 de manière aléatoire
     */
    public static int getRandomCoord(int CoordMax) {
        Random nbAleatoire = new Random();
        return nbAleatoire.nextInt(CoordMax);
    }

    /**
     * Vérifie si un Node est trop proche du node courant(cas d'un chevauchement)
     *
     * @param recentNode
     * @return true si oui
     */
    public boolean isOntheCurrent(Node recentNode) {
        int margY = Math.abs(y - recentNode.getY());
        int margX = Math.abs(x - recentNode.getX());
        return (margX < (this.getWidth()) + (recentNode.getWidth())) && (margY < (this.getWidth()) + (recentNode.getWidth()));
    }
    /**
     * Vérifie s'il y a chevauchement entre deux noeuds
     *
     * @param node1
     * @param node2
     * @return true si oui
     */
    public static boolean overlap(Node node1, Node node2) {
        return node1.isOntheCurrent(node2);
    }

    /**
     * Met un Node à jour
     *
     * @param x
     * @param y
     */
    public void update(int x, int y) {
        this.x = x;
        this.y = y;
    }
}


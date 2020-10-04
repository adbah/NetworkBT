package fr.istic.mob.networkbt;

import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class Graph {

    public int MAX_X = 800;
    public int MAX_Y = 600;
    /*Les noeuds du graph
     * Les arcs qui relient les nodes
     * Les couleurs possibles des noeuds
     */
    private List<Node> nodes = new ArrayList<>();
    private List<Arc> arcs = new ArrayList<>();

    /**
     * Constructeur avec le nombre de nodes de depart
     *
     * @param nbNodes
     */
    public Graph(int nbNodes) {

    }
    /*
     * retourne une couleur  parmi celles qui exitent de facon aleatoire
     */

    /**
     * Initialise les couleurs disponibles pour les noeuds
     */


    /**
     * Ajoute un nouveau Node au graphe quand c'est possible
     *
     * @param node
     * @return true quand le Node est ajoutÃ©
     */
    public void addNode(Node node) {
        this.nodes.add(node);

    }


    /**
     * @return ArrayList de tous les noeuds
     */
    public List<Node> getNodes() {
        return this.nodes;
    }

    /**
     * @return ArrayList de tous les arcs
     */
    public List<Arc> getArcs() {
        return this.arcs;
    }

    /**
     * @return La liste de toutes les couleurs disponibles
     */

    /**
     * Ajoute un Arc
     *
     * @param arc
     */
   /* public void addArc(Arc arc) {

        this.arcs.add(arc);
    }*/
    /**
     * Ajoute un arc entre les nodes index1 et index2 de la liste de nodes
     *
     * @param index1
     * @param index2
     */
   public void addArc(int index1, int index2) {
        if (index1 != index2) {
            Node n1 = getNodes().get(index1);
            Node n2 = getNodes().get(index2);
            if (!Node.overlap(n1, n2)) {
                Log.d("XXXX", "add arc ");
                this.arcs.add(new Arc(n1, n2));
            }
        }

    }
    /**
     * Retourne le node selectionnÃ© ou null
     *
     * @param x
     * @param y
     * @return
     */
    public Node selectedNode(int x, int y) {
        Node node = new Node(x, y);
        Iterator<Node> it = nodes.iterator();
        while (it.hasNext()) {
            Node n = it.next();
            if (Node.overlap(n, node)) {
                return n;
            }
        }
        return null;
    }

    public void reset(){
        nodes.clear();
        arcs.clear();
    }

    public void addArc(Arc a) {
        this.arcs.add(a);
    }
}

package fr.istic.mob.networkbt;



import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //ContextMenuNode cont = new ContextMenuNode();



    DrawableGraph myDraw;
    ImageView supportView;
    MenuItem reset;
    MenuItem others;

    int ADD_NODE_MODE=1;
    int ADD_ARC_BOUCLE_MODE=2;
    int UPDATE_ARC_BOUCLE_MODE=3;

    //DEFAULT MODE
    int mode=ADD_NODE_MODE;

    int clicX = 0;
    int clicY = 0;

    int relacheX = 0;
    int relacheY = 0;

    long touchStartTime = 0;
    Arc tempArc=null;


    static {
        graph = new Graph(9);
    }

    protected static Graph graph;

    List<Integer> closestColorsList = new ArrayList<>();


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDraw = new DrawableGraph(graph);
        setContentView(R.layout.activity_main);
        supportView = (ImageView) findViewById(R.id.imageView);
        supportView.setImageDrawable(myDraw);
        reset=(MenuItem) findViewById(R.id.reset);
        others=(MenuItem) findViewById(R.id.others);
        this.registerForContextMenu(supportView);

        supportView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                Node  node1,node2;
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        clicX= (int) event.getX();
                        clicY = (int) event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        myDraw.setTempArcNull();
                        relacheX = (int) event.getX();
                        relacheY = (int) event.getY();
                        if(mode == ADD_NODE_MODE || mode == UPDATE_ARC_BOUCLE_MODE){
                            if ((Math.abs(relacheX - clicX) < 5) || (Math.abs(relacheY - clicY) < 5)) {
                                Node node = new Node(relacheX, relacheY);
                                graph.addNode(node);
                                supportView.invalidate();
                            }
                        }
                        node1 = graph.selectedNode(clicX, clicY);
                        node2 = graph.selectedNode(relacheX, relacheY);
                        if(mode == ADD_ARC_BOUCLE_MODE || mode == UPDATE_ARC_BOUCLE_MODE){
                            if ((node1 != null) && (node2 != null)) {
                                Arc a = new Arc(node1, node2);
                                graph.addArc(a);
                                supportView.invalidate();

                            }
                        }
                        if(node1 != null && node2 ==null){
                            if(mode == ADD_NODE_MODE ){
                                node1.update(relacheX, relacheY);
                            }
                            supportView.invalidate();
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int moveX = (int) event.getX();
                        int moveY = (int) event.getY();

                        Node n1 = graph.selectedNode(clicX,clicY);
                        Node tempNode = new Node (moveX,moveY);

                        if (n1 != null && (mode == ADD_ARC_BOUCLE_MODE)) {
                            tempArc = new Arc(n1, tempNode);
                            myDraw.setTempArc(tempArc);
                            supportView.invalidate();
                        }
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        break;
                }

                return true;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        menu.setGroupCheckable(1, true, true);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        Context context = getApplicationContext();
        CharSequence text = "";
        int duration = Toast.LENGTH_SHORT;

        switch(item.getItemId())
        {
            case R.id.reset:
                text = "reset graph !";
                graph.reset();
                mode=ADD_NODE_MODE;
                supportView.invalidate();
                break;
            case R.id.addArc:
                text = "mode add arc !";
                mode=ADD_ARC_BOUCLE_MODE;
                break;
            case R.id.addNode:
                text = "mode add node !";
                mode=ADD_NODE_MODE;
                break;
            case R.id.updateArcNode:
                text = "mode update all !";
                mode=UPDATE_ARC_BOUCLE_MODE;
                break;
            case R.id.selectMode:
                text = "select a mode !";
                mode=ADD_NODE_MODE;
                break;
            default:
                text = "...";

                //   return true;
        }
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        return super.onOptionsItemSelected(item);
    }


}
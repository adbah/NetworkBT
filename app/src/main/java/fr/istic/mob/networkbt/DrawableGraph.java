package fr.istic.mob.networkbt;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DrawableGraph extends Drawable {

    private static final int LABEL_TEXT_SIZE = 30;
    private Paint nodePaint;
    private Paint arcPaint;
    private Paint whitePaint;
    private Paint arcEtiqBackPaint;
    private Paint paint;
    private Paint nodeEtiqPaint;
    private Paint arcEtiqPaint;
    private Canvas canvas = new Canvas();

    private Path linePath = new Path();
    Graph graph;
    private Arc tempArc = null; // arc temporaire pour suivre les mouvements

    public Arc getTempArc() {
        return tempArc;
    }

    public void setTempArc(Arc tempArc) {
        this.tempArc = tempArc;
    }

    public void setTempArcNull() {
        this.tempArc = null;
    }

    public DrawableGraph(Graph graph) {
        this.graph = graph;

        nodePaint = new Paint();
        nodePaint.setColor(Node.DEFAULT_COLOR);
        nodePaint.setStrokeWidth(Node.DEFAULT_RADIUS);


        arcPaint = new Paint();
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(Arc.ARC_WIDTH);
        arcPaint.setColor(Color.RED);


        whitePaint = new Paint();
        whitePaint.setStyle(Paint.Style.STROKE);
        whitePaint.setStrokeWidth(Arc.ARC_WIDTH+5);


        arcEtiqPaint = new Paint();
        arcEtiqPaint.setColor(Color.WHITE);
        arcEtiqPaint.setTextSize(LABEL_TEXT_SIZE);


        paint = new Paint();
        paint.setColor(Color.RED);

        nodeEtiqPaint = new Paint();
        nodeEtiqPaint.setColor(Color.WHITE);
        nodeEtiqPaint.setTextSize(LABEL_TEXT_SIZE);



        arcEtiqBackPaint = new Paint();
        arcEtiqBackPaint.setColor(Color.GRAY);

    }

    public void drawNode(Node node) {

        nodePaint.setColor(node.getColor());

        int left = node.getX()-node.getWidth()/2;
        int right = node.getX()+node.getWidth()/2;
        int top = node.getY()-node.getWidth()/2;
        int botom = node.getY()+node.getWidth()/2;

        RectF r = new RectF(left,top,right,botom);
        canvas.drawRoundRect(r,left,right,nodePaint);
    }
    @Override
    public void draw(@NonNull Canvas canvas) {
        this.canvas = canvas;

        drawNodes();
        drawArcs();
    }
    private void drawNodes() {
        for (Node node : graph.getNodes()) {
            drawNode(node);
        }
    }

    //tous les arcs
    public void drawArcs() {
        for (Arc arc : graph.getArcs()) {
            drawArc(arc);
        }
        if (this.tempArc != null) {
            drawArc(tempArc);
        }
    }



    public void drawArc(Arc arc) {

        arcPaint.setColor(arc.getColor());
        canvas.drawPath(arc.getPath(), whitePaint);
        canvas.drawPath(arc.getPath(), arcPaint);

    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int i) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }



}


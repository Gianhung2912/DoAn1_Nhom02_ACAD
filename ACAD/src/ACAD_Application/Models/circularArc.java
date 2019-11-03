package ACAD_Application.Models;

import ACAD_Application.Models.Topology.Shape;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.awt.*;

public class circularArc extends Shape {
    protected Point2D centerPoint;
    protected double h;
    protected double w;
    protected double startAngle;
    protected double arcExtent;
    public int clickCount;

    public circularArc() {
        this.centerPoint = new Point2D(0, 0);
        this.h = 0;
        this.w = 0;
        this.startAngle = 0;
        this.arcExtent = 50;
        this.clickCount = 0;
    }

    public circularArc(Point2D centerpoint) {
        this.centerPoint = centerpoint;
        this.h = 0;
        this.w = 0;
        this.startAngle = 0;
        this.arcExtent = 50;
        this.clickCount = 1;
    }

    @Override
    public void setPosition(Point2D position)
    {
        super.setPosition(position);

        w = Math.sqrt(Math.pow(startPosition.getX() - centerPoint.getX(), 2) + Math.pow(startPosition.getY() - centerPoint.getY(), 2)) * 2;
        h = w;
        setTopLeft(calculateTopLeft());
        Point2D Test =  this.topLeft;
        calcStartAngle();
        clickCount++;
    }

    @Override
    public Point2D calculateTopLeft()
    {
        double x;
        double y;

        double quarterSquare_Cross = Math.sqrt(2 * w * w);

        x = centerPoint.getX() - w / 2;
        y = centerPoint.getY() - w / 2;
        return new Point2D(x, y);
    }

    @Override
    public void getArcExtent(String input)
    {
        this.arcExtent = Double.parseDouble(input);
    }

    @Override
    public void setEndPosition(Point2D position)
    {
        Point2D temp = new Point2D(position.getX(), Math.pow(w / 2, 2) - Math.pow(position.getX() - centerPoint.getX(), 2) + centerPoint.getY());
        this.endPosition = temp;
        calcArcExtent();
    }

    public void calcArcExtent()
    {
        Point2D temp = new Point2D(endPosition.getX(), centerPoint.getY());
        double oppsite = Math.sqrt(Math.pow(temp.getX() - endPosition.getX(), 2) + Math.pow(temp.getY() - endPosition.getY(), 2));
        arcExtent = 180 - Math.toDegrees(Math.sin(oppsite / (w / 2))) - startAngle;
    }

    public void calcStartAngle()
    {
        Point2D temp = new Point2D(startPosition.getX(), centerPoint.getY());
        double oppsite = Math.sqrt(Math.pow(temp.getX() - startPosition.getX(), 2) + Math.pow(temp.getY() - startPosition.getY(), 2));
        startAngle = Math.toDegrees(Math.sin(oppsite / (w / 2)));
    }

    @Override
    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.CYAN);
        gc.setFill(super.getFillColor());
        gc.strokeArc(topLeft.getX(), topLeft.getY(), w, h, startAngle, arcExtent, ArcType.OPEN);
        gc.fillArc(topLeft.getX(), topLeft.getY(), w, h, startAngle, arcExtent, ArcType.OPEN);
    }
}

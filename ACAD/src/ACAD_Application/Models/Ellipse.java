package ACAD_Application.Models;

import ACAD_Application.Models.Topology.Shape;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ellipse extends Shape
{
    protected double hRadius;
    protected double vRadius;

    public Ellipse()
    {

    }

    public Ellipse(Point2D point)
    {
        setPosition(point);
    }

    public void sethRadius(double hRadius)
    {
        this.hRadius = hRadius;
    }

    public void setvRadius(double vRadius)
    {
        this.vRadius = vRadius;
    }

    public double gethRadius() {
        return hRadius;
    }

    public double getvRadius() {
        return vRadius;
    }

    @Override
    public void setEndPosition(Point2D position)
    {
        this.endPosition = position;

        hRadius = Math.abs(getPosition().getX() - getEndPosition().getX())/2;
        vRadius = Math.abs(getPosition().getY() - getEndPosition().getY())/2;
    }

    @Override
    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(super.getColor());
        gc.strokeOval(super.getTopLeft().getX(), super.getTopLeft().getY(), hRadius*2, vRadius*2);
        //gc.setFill(super.getFillColor());
        //gc.fillOval(super.getTopLeft().getX(), super.getTopLeft().getY(), hRadius*2, vRadius*2);
    }
}

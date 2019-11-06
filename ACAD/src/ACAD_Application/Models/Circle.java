package ACAD_Application.Models;

import ACAD_Application.Models.Topology.Shape;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Ellipse
{
    @Override
    public void setEndPosition(Point2D position)
    {
        this.endPosition = position;
    }


    public Circle()
    {

    }

    public Circle(Point2D point)
    {
        setPosition(point);
        radius = 0;
    }

    @Override
    public void draw(Canvas canvas)
    {
        hRadius = Math.abs(getPosition().getX() - getEndPosition().getX())/2;
        vRadius = Math.abs(getPosition().getY() - getEndPosition().getY())/2;

        if(hRadius < vRadius)
            super.setvRadius(hRadius);
        else
            super.sethRadius(vRadius);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(super.getColor());
        gc.strokeOval(super.getTopLeft().getX(), super.getTopLeft().getY(), hRadius*2, vRadius*2);
        //gc.setFill(super.getFillColor());
        //gc.fillOval(super.getTopLeft().getX(), super.getTopLeft().getY(), hRadius*2, vRadius*2);
    }
}


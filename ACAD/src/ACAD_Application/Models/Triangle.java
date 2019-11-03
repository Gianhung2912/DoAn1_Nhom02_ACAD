package ACAD_Application.Models;

import ACAD_Application.Models.Topology.Shape;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle extends Shape
{
    protected Point2D thirdPoint;
    public Triangle()
    {

    }


    public  Triangle(Point2D start)
    {
        this.thirdPoint = start;
    }

    public Triangle(Point2D startPos, Point2D endPos, Color strockColor)
    {
        super(startPos, endPos, strockColor);
        double temp = Math.abs(startPos.getX() - endPos.getX());

        if(super.getPosition().getX()<super.getEndPosition().getX())
            thirdPoint = new Point2D(endPos.getX()-(temp*2), endPos.getY());
        else
            thirdPoint = new Point2D(endPos.getX()+(temp*2), endPos.getY());
    }

    public void draw(Canvas canvas)
    {
        double x1 = super.getPosition().getX();
        double y1 = super.getPosition().getY();
        double x2 = super.getEndPosition().getX();
        double y2 = super.getEndPosition().getY();
        double x3 = thirdPoint.getX();
        double y3 = thirdPoint.getY();
        double temp = Math.abs(getPosition().getX() - getEndPosition().getX());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(super.getColor());
        if(super.getPosition().getX()<super.getEndPosition().getX())
            thirdPoint = new Point2D(getEndPosition().getX()-(temp*2), getEndPosition().getY());
        else
            thirdPoint = new Point2D(getEndPosition().getX()+(temp*2), getEndPosition().getY());
        gc.strokePolygon(new double[]{x1,x2,x3}, new double[]{y1,y2,y3}, 3);
        gc.setFill(super.getFillColor());
        gc.fillPolygon(new double[]{x1,x2,x3}, new double[]{y1,y2,y3}, 3);
    }
}


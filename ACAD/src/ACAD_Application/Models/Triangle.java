package ACAD_Application.Models;

import ACAD_Application.Models.Topology.Shape;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle extends Shape
{
    protected Point2D secondPoint;
    public Triangle()
    {

    }

    public  Triangle(Point2D start)
    {
        setPosition(start);
    }

    public Triangle(Point2D startPos, Point2D endPos, Color strockColor)
    {
        super(startPos, endPos, strockColor);
        double temp = Math.abs(startPos.getX() - endPos.getX());

        if(super.getPosition().getX()<super.getEndPosition().getX())
            secondPoint = new Point2D(endPos.getX()-(temp * 2), endPos.getY());
        else
            secondPoint = new Point2D(endPos.getX()+(temp * 2), endPos.getY());
    }

    @Override
    public void AddPoint(Canvas maincanvas, Point2D point)
    {
        this.secondPoint = point;
        Line gui = new Line(getPosition());
        gui.setEndPosition(secondPoint);
        gui.setColor(getColor());
        gui.draw(maincanvas);
    }

    @Override
    public void setEndPosition(Point2D position)
    {
        this.endPosition = position;
    }



    public void draw(Canvas canvas)
    {
        double x1 = super.getPosition().getX();
        double y1 = super.getPosition().getY();
        double x2 = secondPoint.getX();
        double y2 = secondPoint.getY();
        double x3 = super.getEndPosition().getX();
        double y3 = super.getEndPosition().getY();

        double temp = Math.abs(getPosition().getX() - getEndPosition().getX());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(super.getColor());
        if(super.getPosition().getX()<super.getEndPosition().getX())
            setEndPosition(new Point2D(getEndPosition().getX()-(temp*2), getEndPosition().getY()));
        else
            setEndPosition(new Point2D(getEndPosition().getX()+(temp*2), getEndPosition().getY()));
        gc.strokePolygon(new double[]{x1,x2,x3}, new double[]{y1,y2,y3}, 3);
        //gc.setFill(super.getFillColor());
        //gc.fillPolygon(new double[]{x1,x2,x3}, new double[]{y1,y2,y3}, 3);
    }
}


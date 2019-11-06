package ACAD_Application.Models;

import ACAD_Application.Models.Topology.Shape;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Shape
{
    private double width;
    private double height;

    public Rectangle()
    {

    }

    public Rectangle(Point2D point)
    {
        setPosition(point);
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public void draw(Canvas canvas)
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(super.getColor());
        width = Math.abs(getPosition().getX() - getEndPosition().getX());
        height = Math.abs(getPosition().getY() - getEndPosition().getY());
        gc.strokeRect(super.getTopLeft().getX(), super.getTopLeft().getY(), width, height);
        //gc.setFill(super.getFillColor());
        //gc.fillRect(super.getTopLeft().getX(), super.getTopLeft().getY(), width, height);
    }
}

package ACAD_Application.Models.Topology;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

public abstract class Shape implements iShape, java.lang.Cloneable
{
    public enum ShapeType
    {
        Null,
        Line,
        Ellipse,
        Rectangle,
        Triange,
        Circle,
        Polygon,
        Arc,
        Polyline
    }

    protected Point2D startPosition;
    protected Point2D endPosition;
    protected Point2D topLeft;
    protected Color color;
    protected Color fillColor;

    public Shape(){
        //Variables will be set by the Properties map.
    }

    public Shape(Point2D startPos, Point2D endPos , Color strockColor){
        this.color = strockColor;
        this.startPosition = startPos;
        this.endPosition = endPos;
        this.fillColor = Color.TRANSPARENT;
        this.topLeft = calculateTopLeft();
    }

    /*public Shape(Point2D startPos, Point2D endPos, Color strockColor, Color fillColor){
        this.color = strockColor;
        this.startPosition = startPos;
        this.endPosition = endPos;
        this.fillColor = fillColor;
    }*/

    @Override
    public void setPosition(Point2D position)
    {
        this.startPosition = position;
    }

    public void setEndPosition(Point2D position)
    {
        this.endPosition = position;
    }

    public void getArcExtent(String input)
    {

    }

    @Override
    public Point2D getPosition()
    {
        return this.startPosition;
    }

    public Point2D getEndPosition() {
        return this.endPosition;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public void setFillColor(Color color) {
        this.fillColor = color;
    }

    @Override
    public Color getFillColor() {
        return this.fillColor;
    }

    @Override
    public void draw(Canvas canvas) {

    }



    @Override
    public Shape clone() throws CloneNotSupportedException{
        return cloneShape();
    }

    public Shape cloneShape() throws CloneNotSupportedException {
        Shape temp = null;
        temp = (Shape) super.clone();
        return temp;
    }

    public Point2D calculateTopLeft(){
        double x = Math.min(this.getPosition().getX(), this.getEndPosition().getX());
        double y = Math.min(this.getPosition().getY(), this.getEndPosition().getY());
        return new Point2D(x,y);
    }

    public Point2D getTopLeft(){
        return topLeft;
    }

    public void setTopLeft(Point2D pos){
        this.topLeft = pos;
    }

    public void clearShape()
    {
        startPosition = null;
        endPosition = null;
        topLeft = null;
        color = null;
        fillColor = null;
    }

}


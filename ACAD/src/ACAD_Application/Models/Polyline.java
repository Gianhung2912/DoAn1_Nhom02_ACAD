package ACAD_Application.Models;

import ACAD_Application.Models.Topology.Shape;
import com.sun.javafx.scene.paint.GradientUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class Polyline extends Shape
{
    public int nPoints;
    public List<Point2D> Point;

    public Polyline()
    {
        this.nPoints = 0;
        this.Point = new ArrayList<Point2D>();
    }

    public Polyline(Point2D point)
    {
        this.Point = new ArrayList<Point2D>();
        this.nPoints = 0;
        Point.add(point);
    }

    @Override
    public void AddPoint(Canvas maincanvas, Point2D point)
    {
        Point.add(point);
        nPoints++;
        if(Point.size() >= 2)
        {
            GraphicsContext gc = maincanvas.getGraphicsContext2D();
            gc.setStroke(super.getColor());
            Line gui = new Line(Point.get(Point.size() - 2));
            gui.setEndPosition(Point.get(Point.size() - 1));
            gui.setColor(getColor());
            gui.draw(maincanvas);
        }
    }

    @Override
    public void draw(Canvas canvas)
    {
        double[] xPoints = new double[nPoints + 1];
        double[] yPoints = new double[nPoints + 1];
        for(int i = 0; i < nPoints + 1; i++)
        {
            xPoints[i] = Point.get(i).getX();
            yPoints[i] = Point.get(i).getY();
        }
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(super.getColor());
        gc.strokePolyline(xPoints, yPoints, nPoints + 1);
    }
}

package ACAD_Application;

import ACAD_Application.Models.Line;
import ACAD_Application.Models.Rectangle;
import ACAD_Application.Models.Topology.Shape;
import com.sun.javafx.scene.control.CustomColorDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.*;


public class MainSence implements Initializable
{
    private Shape.ShapeType CurrentShape;
    private Shape DrawingShape;
    private ArrayList<Shape> ShapeList = new ArrayList<Shape>();
    private boolean isDrawing;
    private Point2D start;
    private Point2D end;

    @FXML
    private Canvas MainCanvas;
    @FXML
    private ColorPicker ColorPicker;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        CurrentShape = Shape.ShapeType.Null;
        isDrawing = false;
    }


    public void bnt_Line_Click(ActionEvent actionEvent)
    {
        CurrentShape = Shape.ShapeType.Line;
    }


    public void MainCanvas_MouseDown(MouseEvent mouseEvent)
    {
        if(!isDrawing) {
            start = new Point2D(mouseEvent.getX(), mouseEvent.getY());
            DrawingShape = createShape(CurrentShape, start, start, Color.WHITE);
            DrawingShape.setColor(ColorPicker.getValue());
            DrawingShape.setFillColor(ColorPicker.getValue());
            DrawingShape.draw(MainCanvas);
            isDrawing = true;
        }
        else
        {
            refresh(MainCanvas);
            DrawingShape.setEndPosition(new Point2D(mouseEvent.getX(), mouseEvent.getY()));
            DrawingShape.draw(MainCanvas);
            ShapeList.add(DrawingShape);
            isDrawing = false;
            //DrawingShape.clearShape();
        }
    }

    public void MainCanvas_MouseMove(MouseEvent mouseEvent)
    {
        if(isDrawing) {
            refresh(MainCanvas);
            DrawingShape.setEndPosition(new Point2D(mouseEvent.getX(), mouseEvent.getY()));
            DrawingShape.draw(MainCanvas);
        }
    }

    public void MainCanvas_MouseUp(MouseDragEvent mouseDragEvent)
    {

    }

    public Shape createShape(Shape.ShapeType Type, Point2D start, Point2D end, Color color){
        Shape temp = null;
        switch(Type){
            case Line:
                temp = new Line(start,end,color);
                break;
            case Rectangle:
                temp = new Rectangle(start,end,color);
                break;
        }
        return temp;
    }

    public void refresh(Object canvas)
    {
        redraw((Canvas) canvas);
    }

    public void redraw(Canvas canvas)
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, 980, 640);
        try{
            for(int i=0;i<ShapeList.size();i++)
            {
                ShapeList.get(i).draw(canvas);
            }
        }catch(Exception e){}
    }


    public void bnt_Rectagle_Click(ActionEvent actionEvent)
    {
        CurrentShape = Shape.ShapeType.Rectangle;
    }
}

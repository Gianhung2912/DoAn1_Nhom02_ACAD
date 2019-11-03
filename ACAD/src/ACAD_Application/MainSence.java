package ACAD_Application;

import ACAD_Application.Models.*;
import ACAD_Application.Models.Topology.Shape;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Window;

import javax.swing.*;
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
    private int ClickCount;

    @FXML
    private Canvas MainCanvas;
    @FXML
    private ColorPicker ColorPicker;
    @FXML
    private AnchorPane Window;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        CurrentShape = Shape.ShapeType.Null;
        isDrawing = false;
        ClickCount = 0;
    }


    public void bnt_Line_Click(ActionEvent actionEvent)
    {
        CurrentShape = Shape.ShapeType.Line;
    }
    public void bnt_Ellipse_Click(ActionEvent actionEvent) {CurrentShape = Shape.ShapeType.Ellipse; }
    public void bnt_Arc_Click(ActionEvent actionEvent) { CurrentShape = Shape.ShapeType.Arc; }
    public void bnt_Triangle(ActionEvent actionEvent){CurrentShape=Shape.ShapeType.Triange;}
    public void bnt_TypeTool(ActionEvent actionEvent){}

    public void MainCanvas_MouseDown(MouseEvent mouseEvent) {
        start = new Point2D(mouseEvent.getX(), mouseEvent.getY());

        if (!isDrawing)
        {
            switch (CurrentShape)
            {
                case Arc:
                    if(ClickCount == 0)
                        DrawingShape = new circularArc(start);
                    if(ClickCount == 1)
                    {
                        DrawingShape.setPosition(start);
                        TextField tx = new TextField("Nhập góc");
                        tx.setLayoutX(start.getX());
                        tx.setLayoutY(start.getY());
                        Window.getChildren().add(tx);
                        tx.setOnKeyReleased(event -> {
                            if (event.getCode() == KeyCode.ENTER){
                                DrawingShape.getArcExtent(tx.getText());
                                DrawingShape.draw(MainCanvas);
                                addShape(DrawingShape);
                                Window.getChildren().remove(tx);
                                ClickCount = 0;
                                return;
                            }
                        });
                    }
                    break;
                case Triange:
                    if(ClickCount == 0)
                        DrawingShape = new Triangle(start);
                    if(ClickCount == 1)
                    {
                        DrawingShape.setPosition(start);
                    }
                    if(ClickCount == 2)
                    {
                        DrawingShape.setEndPosition(start);
                        DrawingShape.setColor(ColorPicker.getValue());
                        DrawingShape.setFillColor(ColorPicker.getValue());
                        DrawingShape.draw(MainCanvas);
                        ClickCount = 0;
                        addShape(DrawingShape);
                        DrawingShape = null;
                        return;
                    }
                    break;
                default:
                    DrawingShape = createShape(CurrentShape, start, start, Color.WHITE);
                    DrawingShape.setColor(ColorPicker.getValue());
                    DrawingShape.setFillColor(ColorPicker.getValue());
                    DrawingShape.draw(MainCanvas);
                    isDrawing = true;
            }
            ClickCount++;
        }
        else
        {
            refresh(MainCanvas);
            DrawingShape.setEndPosition(new Point2D(mouseEvent.getX(), mouseEvent.getY()));
            DrawingShape.draw(MainCanvas);
            addShape(DrawingShape);
            isDrawing = false;
            ClickCount = 0;
        }
    }

    public void MainCanvas_MouseMove(MouseEvent mouseEvent)
    {
        if(CurrentShape == Shape.ShapeType.Arc && ClickCount == 2)
        //{
        //    refresh(MainCanvas);
        //    DrawingShape.setEndPosition(new Point2D(mouseEvent.getX(), mouseEvent.getY()));
        //    DrawingShape.draw(MainCanvas);
        //}

        if(isDrawing) {
            refresh(MainCanvas);
            DrawingShape.setEndPosition(new Point2D(mouseEvent.getX(), mouseEvent.getY()));
            DrawingShape.draw(MainCanvas);
        }
    }

    public void MainCanvas_MouseUp(MouseDragEvent mouseDragEvent)
    {

    }

    public Shape createShape(Shape.ShapeType Type, Point2D start, Point2D end, Color color)
    {
        Shape temp = null;
        switch(Type)
        {
            case Line:
                temp = new Line(start,end,color);
                break;
            case Rectangle:
                temp = new Rectangle(start,end,color);
                break;
            case Ellipse:
                temp = new Ellipse(start,end,color);
                break;
            case Circle:
                temp = new Circle(start,end,color);
                break;
            case Arc:
                temp = new circularArc();
                break;
            case Triange:
                temp=new Triangle(start,end,color);
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
    public void bnt_Circle_Click (ActionEvent actionEvent){ CurrentShape=Shape.ShapeType.Circle;}
    public void exitApp(ActionEvent actionEvent)
    {

    }

    public void addShape(Shape shape)
    {
        ShapeList.add(shape);
        refresh(MainCanvas);
    }

}

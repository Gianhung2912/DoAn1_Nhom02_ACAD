package ACAD_Application;

import ACAD_Application.Models.*;
import ACAD_Application.Models.Topology.Shape;
import com.sun.javafx.logging.PlatformLogger;
import com.sun.webkit.Timer;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainSence implements Initializable
{
    private Shape.ShapeType CurrentShape;
    private Shape DrawingShape;
    private ArrayList<Shape> ShapeList = new ArrayList<Shape>();
    private boolean isDrawing;
    private Point2D start;
    private Point2D end;
    private boolean DrawByCmd;
    private int ClickCount;
    private Image image;

    @FXML
    private Canvas MainCanvas;
    @FXML
    private ColorPicker ColorPicker;
    @FXML
    private AnchorPane Window;
    @FXML
    private TextField txt_Cmd;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        CurrentShape = Shape.ShapeType.Null;
        isDrawing = false;
        ClickCount = 0;
        DrawByCmd = false;
    }

    public void bnt_Line_Click(ActionEvent actionEvent)
    {
        CurrentShape = Shape.ShapeType.Line;
        ClickCount = 0;
    }
    public void bnt_Ellipse_Click(ActionEvent actionEvent)
    {
        CurrentShape = Shape.ShapeType.Ellipse;
        ClickCount = 0;
    }
    public void bnt_Arc_Click(ActionEvent actionEvent)
    {
        CurrentShape = Shape.ShapeType.Arc;
        ClickCount = 0;
    }
    public void bnt_Triangle(ActionEvent actionEvent)
    {
        CurrentShape= Shape.ShapeType.Triange;
        ClickCount = 0;
    }

    public void bnt_Rectagle_Click(ActionEvent actionEvent)
    {
        CurrentShape = Shape.ShapeType.Rectangle;
        ClickCount = 0;
    }
    public void bnt_Circle_Click (ActionEvent actionEvent)
    {
        CurrentShape=Shape.ShapeType.Circle;
        ClickCount = 0;
    }

    public void bnt_Polyline_Click(ActionEvent actionEvent)
    {
        CurrentShape = Shape.ShapeType.Polyline;
        ClickCount = 0;
    }

    public void MainCanvas_MouseDown(MouseEvent mouseEvent)
    {
        Point2D clickedPoint = new Point2D(mouseEvent.getX(), mouseEvent.getY());
        if(!DrawByCmd) {
            switch (CurrentShape) {
                // code cho ve line
                case Line:
                    if (ClickCount == 0) {
                        DrawingShape = new Line(clickedPoint);
                        DrawingShape.setColor(ColorPicker.getValue());
                    } else {
                        DrawingShape.setEndPosition(clickedPoint);
                        DrawingShape.draw(MainCanvas);
                        addShape(DrawingShape);
                        refresh(MainCanvas);
                        ClickCount = 0;
                        return;
                    }
                    break;

                // Code ve Ellipse
                case Ellipse:
                    if (ClickCount == 0) {
                        DrawingShape = new Ellipse(clickedPoint);
                        DrawingShape.setColor(ColorPicker.getValue());
                    } else {
                        DrawingShape.setEndPosition(clickedPoint);
                        DrawingShape.draw(MainCanvas);
                        addShape(DrawingShape);
                        refresh(MainCanvas);
                        ClickCount = 0;
                        return;
                    }
                    break;

                // Code ve hinh tron
                case Circle:
                    if (ClickCount == 0) {
                        DrawingShape = new Circle(clickedPoint);
                        DrawingShape.setColor(ColorPicker.getValue());
                    } else {
                        DrawingShape.setEndPosition(clickedPoint);
                        DrawingShape.draw(MainCanvas);
                        addShape(DrawingShape);
                        refresh(MainCanvas);
                        ClickCount = 0;
                        return;
                    }
                    break;

                // Code ve HCN
                case Rectangle:
                    if (ClickCount == 0) {
                        DrawingShape = new Rectangle(clickedPoint);
                        DrawingShape.setColor(ColorPicker.getValue());
                    } else {
                        DrawingShape.setEndPosition(clickedPoint);
                        DrawingShape.draw(MainCanvas);
                        addShape(DrawingShape);
                        refresh(MainCanvas);
                        ClickCount = 0;
                        return;
                    }
                    break;

                // Code ve tam giac
                case Triange:
                    if (ClickCount == 0) {
                        DrawingShape = new Triangle(clickedPoint);
                        DrawingShape.setColor(ColorPicker.getValue());
                    }

                    if (ClickCount == 1)
                        DrawingShape.AddPoint(MainCanvas, clickedPoint);

                    if (ClickCount == 2) {
                        DrawingShape.setEndPosition(clickedPoint);
                        DrawingShape.draw(MainCanvas);
                        addShape(DrawingShape);
                        refresh(MainCanvas);
                        ClickCount = 0;
                        return;
                    }
                    break;

                // Code ve polyline
                case Polyline:
                    if (ClickCount == 0) {
                        DrawingShape = new Polyline(clickedPoint);
                        DrawingShape.setColor(ColorPicker.getValue());
                    } else {
                        if (Main.isESCPressed == true)
                        {
                            Main.isESCPressed = false;
                            ClickCount = 1;

                            DrawingShape.draw(MainCanvas);
                            addShape(DrawingShape);

                            DrawingShape = new Null();
                            DrawingShape = new Polyline(clickedPoint);
                            DrawingShape.setColor(ColorPicker.getValue());
                            return;
                        } else
                            DrawingShape.AddPoint(MainCanvas, clickedPoint);
                    }
                    break;
            }
            ClickCount++;
        }
        else
        {
            switch (CurrentShape)
            {
                case Circle:
                    DrawingShape.setPosition(new Point2D(mouseEvent.getX() - Math.sqrt(2) * DrawingShape.radius / 2 , mouseEvent.getY() - Math.sqrt(2) * DrawingShape.radius / 2));
                    DrawingShape.setEndPosition(new Point2D( DrawingShape.getPosition().getX() + Math.sqrt(2) * DrawingShape.radius, DrawingShape.getPosition().getY() + Math.sqrt(2) * DrawingShape.radius));
                    refresh(MainCanvas);
                    DrawingShape.draw(MainCanvas);
                    addShape(DrawingShape);
                    DrawByCmd = false;
                    break;
            }

        }
    }

    public void MainCanvas_MouseMove(MouseEvent mouseEvent)
    {
        if(DrawByCmd)
        {
            DrawingShape.setPosition(new Point2D(mouseEvent.getX() - Math.sqrt(2) * DrawingShape.radius / 2 , mouseEvent.getY() - Math.sqrt(2) * DrawingShape.radius / 2));
            DrawingShape.setEndPosition(new Point2D( DrawingShape.getPosition().getX() + Math.sqrt(2) * DrawingShape.radius, DrawingShape.getPosition().getY() + Math.sqrt(2) * DrawingShape.radius));
            refresh(MainCanvas);
            DrawingShape.draw(MainCanvas);
        }
    }

    public void MainCanvas_MouseUp(MouseDragEvent mouseDragEvent)
    {

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
            gc.drawImage(image, 0, 0, MainCanvas.getWidth(), MainCanvas.getHeight());
            for(int i=0;i<ShapeList.size();i++)
            {
                ShapeList.get(i).draw(canvas);
            }
        }catch(Exception e){}
    }

    public void exitApp(ActionEvent actionEvent)
    {

    }

    public void addShape(Shape shape)
    {
        ShapeList.add(shape);
        refresh(MainCanvas);
    }

    public void MenuSave_Click(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("jpeg files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(Main.stage);

        if (file != null) {
            try {
                SnapshotParameters params = new SnapshotParameters();
                params.setFill(Color.BLACK);
                WritableImage writableImage = new WritableImage((int) MainCanvas.getWidth(), (int) MainCanvas.getHeight());
                WritableImage snapshot = MainCanvas.snapshot(params, writableImage);
                ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", file);
            } catch (IOException ex)
            {

            }
        }
    }

    public void bnt_CmdExec_Click(ActionEvent actionEvent)
    {
        CurrentShape = Shape.ShapeType.Polyline;
        if(txt_Cmd.getText().equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Cảnh báo");
            alert.setHeaderText("Không có lệnh nào để thực thi");
            alert.setContentText("Mời nhập lệnh!");
            alert.showAndWait();
        }
        else
        {
            String[] arrOfStr;
            String str = txt_Cmd.getText().replaceAll("\\s", "");
            arrOfStr = str.split("\\(|\\)|\\,");
            for (String a : arrOfStr)
                System.out.println(a);
            int a;
            if(arrOfStr.length > 0) {
                switch (arrOfStr[0])
                {
                    case "Circle":
                    case "circle":
                        if(arrOfStr.length == 2)
                        {
                            DrawByCmd = true;
                            CurrentShape = Shape.ShapeType.Circle;
                            DrawingShape = new Circle(new Point2D(0, 0));
                            DrawingShape.radius = Integer.valueOf(arrOfStr[1]);
                            DrawingShape.setColor(ColorPicker.getValue());
                            DrawingShape.setEndPosition(new Point2D(Math.sqrt(2) * DrawingShape.radius, Math.sqrt(2) * DrawingShape.radius));
                            DrawingShape.draw(MainCanvas);
                            // addShape(DrawingShape);
                        }
                        break;
                }
            }
        }
    }

    public void MenuOpen_Click(ActionEvent actionEvent)
    {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("jpeg files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(Main.stage);
        System.out.println(file.toString());

        if (file != null) {
            try
            {
                image = new Image(file.toURI().toString());
                refresh(MainCanvas);
            }
            catch (Exception ex)
            {

            }
        }
    }
}

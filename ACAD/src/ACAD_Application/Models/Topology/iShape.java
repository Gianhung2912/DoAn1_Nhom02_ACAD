package ACAD_Application.Models.Topology;

public interface iShape{

    public void setPosition(javafx.geometry.Point2D position);

    public javafx.geometry.Point2D getPosition();

    public void setColor(javafx.scene.paint.Color color);

    public javafx.scene.paint.Color getColor();

    public void setFillColor(javafx.scene.paint.Color color);

    public javafx.scene.paint.Color getFillColor();

    /* redraw the shape on the canvas,
    for swing, you will cast canvas to java.awt.Graphics */
    public void draw(javafx.scene.canvas.Canvas canvas);

    /* create a deep clone of the shape */
    public Object clone() throws CloneNotSupportedException;
}

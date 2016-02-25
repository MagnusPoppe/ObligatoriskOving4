package Oblig4.Mandelbrot;

import javafx.scene.paint.Color;

/**
 * Klassen brukes til å holde på fargen når den går
 * igjennom programmet. Brukes med "Mandelbrot"
 * klassen.
 *      - Magnus
 * Created by jooivind on 25.02.2016.
 */
public class Point implements ColoredPoint {
    double x,y;
    private Color color;
    public Point(double x, double y, int iterations)
    {
        this.x = x;
        this.y = y;
        color = Color.color(iterations, iterations, iterations);
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public Color getColor()
    {
        return color;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public void setY(double y)
    {
        this.y = y;
    }
}

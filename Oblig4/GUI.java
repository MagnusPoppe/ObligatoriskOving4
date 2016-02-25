package Oblig4;/**
 * Created by Magnu on 25.02.2016.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GUI extends Application
{
    //Globale elementer:
        static BorderPane root;
        final static double STAGEX = 800;
        final static double STAGEY = 480;
        Ctrl ctrl;

    //Grafiske elementer til top-menyen:
        static HBox menu;
        static Label mandelbrot, serpinski, tree;
        final static Color NOTSELECTED = Color.BLACK;
        final static Color SELECTED = Color.SKYBLUE;

    //Grafiske elementer til tegneområdet:
        static ImageView presenter;
        static WritableImage graph;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        //Starter kontrolleren:
            ctrl = new Ctrl();

        //Bestemmer stage/scene innstillinger:
            Scene scene = new Scene(root, STAGEX, STAGEY);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Obligatorisk øving #4");
            stage.show();

        //Lyttefunksjoner:

    }

    /**
     * Brukes til å bygge alle elementene som går inn i GUI.
     * Metoden er kun til for å slippe rot i startmetoden.
     */
    public static void build()
    {
        //Definerer menyen:
            menu = new HBox(15);
            mandelbrot = new Label("Mandelbrot");
            serpinski = new Label("Serpinski");
            tree = new Label("Tree");
            menu.getChildren().addAll(mandelbrot, serpinski, tree);

        //Definerer visningsområdet
            presenter = new ImageView();

        //Setter opp vinduet:
            root = new BorderPane();
            root.setTop(menu);
            root.setCenter(presenter);
    }
}

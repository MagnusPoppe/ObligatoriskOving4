package Oblig4.Scale;

import Oblig4.Mandelbrot.Logikk.ClickCoords;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Stack;

/**
 * Created by Jo Øivind Gjernes on 06.03.2016.
 *
 * ....
 */
public class Zoom
{
	private Coords from;
	private Coords to;
	private ZoomInterface parent;
	private Rectangle rectangle;
	private Stack<ClickCoords> click;
	private boolean flipped = false;

	/**
	 * Opprette et ZOOM objekt.
	 *
	 * OBS: Klassen som bruker zoom objekt MÅ være en subklasse av typen Pane, da denne klassen trenger tilgang til blant annet
	 * eventhandlere.
 	 * @param from koordinatsystem man skal regne fra. i.e. scene koordinater
	 * @param to koordinatsystem man skal til. i.e. mandelbrot koordinater
	 * @param parent klassen som implementerer zoom. dette MÅ være en subklasse av Pane!
	 */
	public Zoom(Coords from, Coords to, ZoomInterface parent)
	{
		click = new Stack<>();
		this.from = from;
		this.to = to;
		this.parent = parent;
		initRect();
		initEventHandlers();
	}

	public Zoom(Coords from, Coords to, ZoomInterface parent, boolean flipYAxis)
	{
		this(from,to,parent);
		flipped=true;
	}

	private void initRect()
	{
		rectangle = null;
		rectangle = new Rectangle(0,0, Color.TRANSPARENT);
		rectangle.setStroke(Color.BLACK);
	}


	/**
	 * Oppdater rektangel koordinater. Dersom musepekeren flyttes "bakover" trengs det litt logikk.
	 * @param currentMousePosX musens posisjon
	 * @param currentMousePosY musens posisjon
	 */
	private void updateRect(double currentMousePosX, double currentMousePosY)
	{
		double rectangleX = rectangle.getX();
		double rectangleY = rectangle.getY();

		if(currentMousePosX < rectangleX){
			rectangle.setTranslateX(currentMousePosX-rectangleX);
			rectangle.setWidth(rectangleX - currentMousePosX);
		} else {
			rectangle.setWidth(currentMousePosX - rectangleX);
		}
		if(currentMousePosY < rectangleY){
			rectangle.setTranslateY(currentMousePosY-rectangleY);
			rectangle.setHeight(rectangleY - currentMousePosY);
		} else {
			rectangle.setHeight(currentMousePosY - rectangleY);
		}
	}

	private void initEventHandlers()
	{
		if(parent instanceof Pane){
		Pane p = (Pane)(parent);
		p.setOnMousePressed(e->{
			rectangle.setX(e.getX());
			rectangle.setY(e.getY());
			click.push(new ClickCoords((int)(e.getSceneX()), (int)(flip(e.getY()))));
			p.getChildren().add(rectangle);
		});

		p.setOnMouseDragged(e2->{
			if(!click.isEmpty()) {
				updateRect(e2.getX(), e2.getY());
			}
		});

		p.setOnMouseReleased(e3->{
			p.getChildren().remove(rectangle);
			if(!click.isEmpty()){
				ClickCoords click1 = click.pop();
				to = getCoordsFromClick(click1, new ClickCoords((int)e3.getSceneX(),(int)flip(e3.getSceneY())));
				parent.zoom(to);
				p.getChildren().remove(rectangle);
				initRect();
			}

		});
		} else {
			throw new IllegalArgumentException("Illegal use of zoom class");
		}
	}

	/**
	 * Siden bildene starter med origo i øvre venstre hjørne, er det ønskelig å "omregne" dette dersom bildet er tegnet opp ned.
	 * Denne metoden gjør kun noe dersom "flipped" er satt til true.
	 * @param coordinate Y koordinat.
	 * @return reversert Y-koordinat.
	 */
	private double flip(double coordinate)
	{
		if(flipped){
			return from.getToY()-coordinate;
		}
		return coordinate;
	}

	private Coords getCoordsFromClick(ClickCoords click1, ClickCoords click2)
	{
		Coords coords = new Coords(Math.min(click1.x,click2.x),Math.max(click1.x,click2.x),Math.min(click1.y,click2.y),Math.max(click1.y,click2.y));
		Coords converted = ConvertCoordinates.convert(coords, from, to);
		converted.enforceProportions();
		return converted;
	}


}

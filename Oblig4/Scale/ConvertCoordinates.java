package Oblig4.Scale;

/**
 * Created by Jo Øivind Gjernes on 25.02.2016.
 *
 * Klasse for å konvertere fra ett koordinatsystem til et annnet
 *
 *
 * BESKRIVELSE:
 * Man initialiserer det med hvilket koordinat system man skal konvertere fra
 * og hvilket man skal konvertere til.
 * så kan man putte inn et koordinat og få tilsvarende koordinat ut i det andre systemet.
 */
public class ConvertCoordinates
{
	private Coords from;
	private Coords to;

	private double scalingFactorX;
	private double scalingFactorY;
	private double scalingConstantX;
	private double scalingConstantY;


	/**
	 * Opprette et konverterinngsobjekt dette kan konvertere et punkt fra et koordinatsystem til et annet (x,y)-planet
	 * @param from koordinatsystemet man konverterer fra
	 * @param to koordinatsystemet man konverterer til
	 */
	public ConvertCoordinates(Coords from, Coords to)
	{
		this.from = from;
		this.to = to;
		computeScalingFactors();
	}

	/**
	 * Beregne skalerningsfaktorer (slik at man slipper det ved hver operasjon)
	 */
	private void computeScalingFactors()
	{
		scalingFactorX = (to.getToX()-to.getFromX())/(from.getToX()-from.getFromX());
		scalingFactorY = (to.getToY()-to.getFromY())/(from.getToY()-from.getFromY());
		scalingConstantX = to.getFromX();
		scalingConstantY = to.getFromY();
	}

	public double computeIncrement()
	{
		return 1.0/scalingFactorX;
	}


}

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.Random;

import javax.swing.text.Segment;

public class Asteroid

{
	private int randomColor1 = new Random().nextInt(128);
	private int randomColor2 = new Random().nextInt(55);
	private int randomColor3 = new Random().nextInt(67);
	private int randomColor4 = new Random().nextInt(128);
	private int randomColor5 = new Random().nextInt(55);
	private int randomColor6 = new Random().nextInt(67);
	private int randomOutlineColor1 = new Random().nextInt(255);
	private int randomOutlineColor2 = new Random().nextInt(255);
	private int randomOutlineColor3 = new Random().nextInt(255);
	private int randomOutlineColor4 = new Random().nextInt(255);
	private int randomOutlineColor5 = new Random().nextInt(255);
	private int randomOutlineColor6 = new Random().nextInt(255);
	
	private int[] asteroidXPoints =
	{ sg(-48), sg(-36), sg(-13), sg(13), sg(36), sg(48), sg(48), sg(36), sg(13), sg(-13), sg(-36), sg(-48)};
	private int[] asteroidYPoints =
	{ sg(-12), sg(-35), sg(-48), sg(-48), sg(-35), sg(-12),  sg(12), sg(35), sg(48), sg(48), sg(35), sg(12)}; 
	
	public int asteroidXPos;
	public int asteroidYPos;
	private int deltaX;
	private int deltaY;
	private int directionOfAsteroid = 90; // degrees
	private double speedOfAsteroid = 1;
	private double scaleFactor = .1;
	private double rotationSpeed;
	private double accumulatedRotation = 0;
	public Polygon asteroidShape;
	public int asteroidNumber;
	public double asteroidSize = Math.random()*1.5 + .5;
	public int asteroidSpeedIndex;
	
	public Asteroid(int asteroidXPos, int asteroidYPos, int course, int speed, double scaleFactor, double rotationSpeed, boolean asteroidFragment) //asteroid constructor
	{
		this.asteroidXPos = asteroidXPos;
		this.asteroidYPos = asteroidYPos;
		this.speedOfAsteroid = speed;
		this.directionOfAsteroid = course;
		this.scaleFactor = scaleFactor;
		this.rotationSpeed = .025 - Math.random() * .05;//prevent asteroids from becoming too small and stop the fast asteroids from spawning immediately.
		this.asteroidShape = new Polygon(asteroidXPoints,asteroidYPoints, asteroidXPoints.length);
 		this.asteroidNumber = asteroidNumber++;
 		System.out.println(asteroidXPos + " " + asteroidYPos);
	}

	private int sg(double nominalSegmentLength)
	{
		double x = (nominalSegmentLength + nominalSegmentLength * 0.1) - Math.random()*(nominalSegmentLength * 0.5);
		return (int)x;
	}
	
	public void paintAsteroid(Graphics2D g2)
	{
		Utilities.convertCourseSpeedToDxDy(directionOfAsteroid, speedOfAsteroid);
		deltaX = Utilities.getDeltaX();
		deltaY = Utilities.getDeltaY();
		asteroidXPos = (int) (asteroidXPos + deltaX);
		asteroidYPos = (int) (asteroidYPos + deltaY);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0F));
		g2.translate(asteroidXPos, asteroidYPos);
		g2.rotate(Math.toRadians(-directionOfAsteroid + 90)); //asteroid course
		g2.scale(asteroidSize, asteroidSize);
		accumulatedRotation = accumulatedRotation + rotationSpeed;
		g2.rotate(accumulatedRotation);
		GradientPaint redtowhite = new GradientPaint(34, 63, new Color(randomColor1, randomColor2, randomColor3), 20, 67, new Color( randomColor4, randomColor5, randomColor6));
		g2.setPaint(redtowhite);
		g2.fill(asteroidShape);
		GradientPaint randomOutlines = new GradientPaint(34, 63, new Color(randomOutlineColor1, randomOutlineColor2, randomOutlineColor3), 20, 67, new Color( randomOutlineColor4, randomOutlineColor5, randomOutlineColor6));
		g2.setPaint(randomOutlines);
		g2.draw(asteroidShape);
		scaleFactor = scaleFactor * 0.01;
	}

	public void setSpeedOfAsteroid(double speedOfAsteroid)//How fast the ship is going
	{
		this.speedOfAsteroid = Math.random()*speedOfAsteroid;
	}
	public void setDirectionOfAsteroid(double direction)
	{
		directionOfAsteroid = (int) direction;
	}
	public void sizeOfAsteroid(double size){
		
	}
}
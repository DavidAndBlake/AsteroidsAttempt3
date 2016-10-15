import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

import javax.swing.text.Segment;

public class Asteroid extends Utilities
{
	private int[] asteroidXPoints =
	{ sg(-48), sg(-36), sg(-13), sg(13), sg(36), sg(48), sg(48), sg(36), sg(13), sg(-13), sg(-36), sg(-48)};
	private int[] asteroidYPoints =
	{ sg(-12), sg(-35), sg(-48), sg(-48), sg(-35), sg(-12),  sg(12), sg(35), sg(48), sg(48), sg(35), sg(12)}; 
	
	public int asteroidXPos;
	public int asteroidYPos;
	private int deltaX;
	private int deltaY;
	private int directionOfAsteroid = 90; // degrees
	private int speedOfAsteroid = 1;
	private double scaleFactor = 0.1;
	private double rotationSpeed;
	private double accumulatedRotation = 0;
	private Color asteroidColor;
	public Polygon asteroidShape;
	
	public Asteroid(int asteroidXPos, int asteroidYPos, int course, int speed, double scaleFactor, double rotationSpeed) //asteroid constructor
	{
		this.asteroidXPos = asteroidXPos;
		this.asteroidYPos = asteroidYPos;
		this.speedOfAsteroid = speed;
		this.directionOfAsteroid = course;
		this.scaleFactor = scaleFactor;
		this.rotationSpeed = (rotationSpeed/rotationDirectionRandomizer(100));
//		this.asteroidColor = asteroidColor;
		this.asteroidShape = new Polygon(asteroidXPoints,asteroidYPoints, asteroidXPoints.length);
		
	}

	private int sg(double nominalSegmentLength)
	{
		double x = (nominalSegmentLength + nominalSegmentLength * 0.1) - Math.random()*(nominalSegmentLength * 0.5);
		return (int)x;
	}
	private double rotationDirectionRandomizer(double rotationSpeedAndDirection)
	{
		double x = (rotationSpeedAndDirection) - Math.random()*(rotationSpeedAndDirection * 2);
		return (double)x;
	}
	
	public void paintAsteroid(Graphics2D g2)
	{
		convertCourseSpeedToDxDy(directionOfAsteroid, speedOfAsteroid);
		deltaX = getDeltaX();
		deltaY = getDeltaY();
		asteroidXPos = (int) (asteroidXPos + deltaX);
		asteroidYPos = (int) (asteroidYPos + deltaY);
		g2.translate(asteroidXPos, asteroidYPos);
		g2.rotate(Math.toRadians(-directionOfAsteroid + 90)); //asteroid course
		g2.scale(1.5, 1.5);
		accumulatedRotation = accumulatedRotation + rotationSpeed;
		g2.rotate(accumulatedRotation);
		g2.setColor(new Color(98,32,12));
		g2.fill(asteroidShape);
		scaleFactor = scaleFactor * 0.01;
	}

	public void setSpeedOfAsteroid(int speedOfAsteroid)//How fast the ship is going
	{
		this.speedOfAsteroid = speedOfAsteroid;
	}
	public void setDirectionOfAsteroid(double direction)
	{
		directionOfAsteroid = (int) direction;
	}
}

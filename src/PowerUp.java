import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import org.w3c.dom.css.Rect;

public class PowerUp
{
	private int powerUpXPos;
	private int powerUpYPos; // make the power up move to the right, and have
								// collision detection with the ship
	private int powerUpCourse;
	private double powerUpSpeed;
	private double powerUpRotation;
	Rectangle2D.Double powerUpShape;
	AffineTransform powerUpTransform = new AffineTransform(); // Identity Transform
																// (scale = 1,
																// rotation = 0,
																// x = 0, y = 0)
	private int deltaX;
	private int deltaY;
	private Area powerUpArea;
	private boolean isPowerUpOnScreen = true;

	public PowerUp(int powerUpXPos, int powerUpYPos, int course, double speed, double rotation, boolean isTouchingShip, boolean isTouchingLaser)
	{
		this.powerUpXPos = powerUpXPos;
		this.powerUpYPos = powerUpYPos;
		this.powerUpCourse = course;
		this.powerUpRotation = rotation;
		this.powerUpSpeed = speed;
		powerUpShape = new Rectangle2D.Double(0, 0, 40, 40);
		setPowerUpArea(new Area(powerUpShape));
	}

	public void paintPowerUp(Graphics2D g2)
	{
		 g2.setTransform(powerUpTransform); //resets g2 back to zero
		 Utilities.convertCourseSpeedToDxDy(powerUpCourse, powerUpSpeed);
		 deltaX = Utilities.getDeltaX();
		 deltaY = Utilities.getDeltaY();
		 powerUpXPos += deltaX;
		 powerUpYPos += deltaY;
		 g2.translate(powerUpXPos, powerUpYPos);
		 g2.setColor(Color.white);
		 g2.fill(powerUpShape);
		 getPowerUpArea().transform(powerUpTransform);
		 if (powerUpXPos > -10 && powerUpXPos < 2000 && powerUpYPos > -10 && powerUpYPos < 1000)
		 {
			 isPowerUpOnScreen = true;
		 }
		 else {
			isPowerUpOnScreen = false;
		}
	}
	public boolean getIsPowerUpOnScreen()
	{
		return isPowerUpOnScreen;
	}

	public Area getPowerUpArea()
	{
		return powerUpArea;
	}

	public void setPowerUpArea(Area powerUpArea)
	{
		this.powerUpArea = powerUpArea;
	}
}
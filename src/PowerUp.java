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
	AffineTransform powerUpTransform = new AffineTransform(); // Unity Transform
																// (scale = 1,
																// rotation = 0,
																// x = 0, y = 0)
	private int deltaX;
	private int deltaY;

	public PowerUp(int powerUpXPos, int powerUpYPos, int course, double speed, double rotation, boolean isTouchingShip, boolean isTouchingLaser)
	{
		this.powerUpXPos = powerUpXPos;
		this.powerUpYPos = powerUpYPos;
		this.powerUpCourse = course;
		this.powerUpRotation = rotation;
		this.powerUpSpeed = speed;
		powerUpShape = new Rectangle2D.Double(powerUpXPos, powerUpYPos, 40, 40);
	}
	public void paintPowerUp(Graphics2D g2)
	{
		 Utilities.convertCourseSpeedToDxDy(powerUpCourse, powerUpSpeed);
//		 g2.setTransform(powerUpTransform);
//		 powerUpTransform.translate(powerUpXPos, powerUpYPos);
//		 powerUpXPos ++;
//		 deltaX = Utilities.getDeltaX();
//		 deltaY = Utilities.getDeltaY();
//		 powerUpXPos = (int) (powerUpXPos + deltaX);
//		 powerUpYPos = (int) (powerUpXPos + deltaY);;
//		 g2.setColor(Color.white);
//		 g2.fill(powerUpShape);
//		 g2.translate(powerUpXPos, powerUpYPos);
	}
}
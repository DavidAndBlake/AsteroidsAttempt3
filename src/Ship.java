import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class Ship extends Utilities
{
	private Utilities util = new Utilities();
	private int screenWidth;
	private int screenHeight;
	private int[] leftSideShipXPoints =
	{  0, -16, -32, -16, -10, 0  };
	private int[] leftSideShipYPoints =
	{ -31, 9, 21, 18, 14, 14 };
	private int[] rightSideShipXPoints =
	{ 0, 10, 16, 32, 16, 0};
	private int[] rightSideShipYPoints =
	{ 14, 14, 18, 21, 9, -31};
	private int[] canopyXPoints =
	{ 7, 0, -7 };
	private int[] canopyYPoints =
	{ 6, -9, 6 };
	public int shipXPos;
	public int shipYPos;
	private int deltaX;
	private int deltaY;
	public int directionOfHeadOfShip = 90; // degrees
	public int colorChangeController;
	public int colorChangerRightSide;
	public int colorChangerLeftSide;
	private int speedOfShip = 0;
	private int speedLimitOfShip = 15;
	private Polygon shipLeftSide;
	private Polygon shipRightSide;


	public Ship(int shipXPos, int shipYPos) // ship constructor
	{
		this.shipXPos = shipXPos;
		this.shipYPos = shipYPos;
		this.shipLeftSide = new Polygon(leftSideShipXPoints, leftSideShipYPoints, leftSideShipXPoints.length);
		this.shipRightSide = new Polygon(rightSideShipXPoints, rightSideShipYPoints, rightSideShipXPoints.length);
	}

	public void paintShip(Graphics2D g2)
	{
//		TODO: Figure out how to change sun position to top right 
		colorChangerRightSide = Math.abs((int) directionOfHeadOfShip - 180);
		if (colorChangerRightSide > 180)
		{
			colorChangerRightSide = 360 - directionOfHeadOfShip;
		}
		colorChangerLeftSide = Math.abs((int) directionOfHeadOfShip);
		if (colorChangerLeftSide > 180)
		{
			colorChangerLeftSide = 360 - directionOfHeadOfShip;
		}
		convertCourseSpeedToDxDy(directionOfHeadOfShip, speedOfShip);
		deltaX = getDeltaX();
		deltaY = getDeltaY();
		shipXPos = (int) (shipXPos + deltaX);
		shipYPos = (int) (shipYPos + deltaY);
		g2.translate(shipXPos, shipYPos);
		g2.rotate(Math.toRadians(-directionOfHeadOfShip + 90));
		g2.setColor(new Color(Math.abs(colorChangerRightSide % 255),Math.abs(colorChangerRightSide % 255),Math.abs(colorChangerRightSide % 255)));
		g2.fill(shipLeftSide);
		g2.setColor(new Color(Math.abs(colorChangerLeftSide % 255),Math.abs(colorChangerLeftSide % 255),Math.abs(colorChangerLeftSide % 255)));
		g2.fill(shipRightSide);
		g2.setColor(Color.BLUE);
		g2.fillPolygon(canopyXPoints, canopyYPoints, canopyXPoints.length);
		g2.setColor(Color.black);
		if (shipYPos > screenHeight)
		{
			shipYPos = 0;
		}
		if (shipYPos < -20)
		{
			shipYPos = screenHeight;
		}
		if (shipXPos > screenWidth + 20)
		{
			shipXPos = -20;
		}
		if (shipXPos < -20)
		{
			shipXPos = screenWidth + 20;
		}
		
	}

	public void setScreenWidth(int screenWidth)
	{
		this.screenWidth = screenWidth;
	}
	public void setScreenHeight(int screenHeight)
	{
		this.screenHeight = screenHeight;
	}
	public void setSpeedOfShip(int speedOfShip)// How fast the ship is going
	{
		this.speedOfShip = speedOfShip;
	}
	public void setDirectionOfHeadOfShip(double direction)
	{
		directionOfHeadOfShip = (int) direction;
	}
	public int getSpeedOfShip()
	{
		return speedOfShip;
	}
	public int getSpeedLimitOfShip()
	{
		return speedLimitOfShip;
	}

}

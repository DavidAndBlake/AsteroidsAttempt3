
public class Utilities
{
	private int deltaX;
	private int deltaY;

	public void convertCourseSpeedToDxDy(int course, int speed)
	{
		double cosine = Math.cos(Math.toRadians(course));
		double sine = -Math.sin(Math.toRadians(course));
		deltaX = ((int) (cosine * speed));
		deltaY = ((int) (sine * speed));
	}
	public int getDeltaX()
	{
		return deltaX;
	}
	public int getDeltaY()
	{
		return deltaY;
	}
	public boolean isOffScreen(int xPos, int yPos, int screenWidth,
			int screenHeight)
	{
		if (xPos > screenWidth + 50 || xPos < -50 || yPos > screenHeight + 50
				|| yPos < -50)
		{
			return true;
		} else
		{
			return false;
		}
	}
	public void shipMovementRegulator(double rotationDegree, double directionOfHeadOfShip, boolean moveFaster, boolean turnRight, boolean turnLeft,
			boolean slowDown, int speedOfShip, int speedLimitOfShip, double colorChangeController, Ship arwing)
	{
		rotationDegree = Math.toRadians(directionOfHeadOfShip);

		rotationDegree = -directionOfHeadOfShip + 90;
		if (moveFaster)
		{
			speedOfShip = speedOfShip + 1;
		}
		if (turnRight)
		{
			directionOfHeadOfShip = directionOfHeadOfShip - 6;
		}
		if (turnLeft)
		{
			directionOfHeadOfShip = directionOfHeadOfShip + 6;
		}
		if (directionOfHeadOfShip > 360)
		{
			directionOfHeadOfShip = directionOfHeadOfShip - 360;
		}
		if (directionOfHeadOfShip < 0)
		{
			directionOfHeadOfShip = directionOfHeadOfShip + 360;
		}
		if (slowDown)
		{
			speedOfShip = speedOfShip - 1;
		}
		if (speedOfShip > speedLimitOfShip)
		{
			speedOfShip = speedOfShip - 1;
		}
		if (speedOfShip < 0)
		{
			speedOfShip = speedOfShip + 1;
		}
		if (rotationDegree > 180)
		{
			colorChangeController = 360 - directionOfHeadOfShip;
		}
//		System.out.println("rotationDegree = " + rotationDegree + "\ndirectionOfHeadOfShip = " + directionOfHeadOfShip + "\n" + moveFaster + "\n" + turnRight + "\n" + turnLeft + "\n" + slowDown + "\n"
//				+ speedOfShip + "\n" + speedLimitOfShip);
	}
}

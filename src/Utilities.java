
public class Utilities
{
	private int deltaX;
	private int deltaY;

	public void convertCourseSpeedToDxDy(int course, double speed)
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
	public Ship shipMovementRegulator(double rotationDegree, double directionOfHeadOfShip, boolean moveFaster, boolean turnRight, boolean turnLeft,
			boolean slowDown, int speedOfShip, int speedLimitOfShip, double colorChangeController, Ship arwing)
	{
		rotationDegree = Math.toRadians(directionOfHeadOfShip);

		rotationDegree = -directionOfHeadOfShip + 90;
		if (moveFaster)
		{
			arwing.setSpeedOfShip(arwing.getSpeedOfShip() + 1);
		}
		if (turnRight)
		{
			arwing.directionOfHeadOfShip = arwing.directionOfHeadOfShip - 6;
		}
		if (turnLeft)
		{
			arwing.directionOfHeadOfShip = arwing.directionOfHeadOfShip + 6;
			System.out.println(arwing.directionOfHeadOfShip);
		}
		if (arwing.directionOfHeadOfShip > 360)
		{
			arwing.directionOfHeadOfShip = arwing.directionOfHeadOfShip - 360;
		}
		if (arwing.directionOfHeadOfShip < 0)
		{
			arwing.directionOfHeadOfShip = arwing.directionOfHeadOfShip + 360;
		}
		if (slowDown)
		{
			arwing.setSpeedOfShip(arwing.getSpeedOfShip() - 1);
		}
		if (arwing.getSpeedOfShip() > arwing.getSpeedLimitOfShip())
		{
			arwing.setSpeedOfShip(arwing.getSpeedOfShip() - 1);
		}
		if (arwing.getSpeedOfShip() < 0)
		{
			arwing.setSpeedOfShip(arwing.getSpeedOfShip() + 1);
		}
		if (rotationDegree > 180)
		{
			colorChangeController = 360 - directionOfHeadOfShip;
		}
		return arwing;
	}
	
}

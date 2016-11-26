import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Utilities implements KeyListener
{
	private int deltaX;
	private int deltaY;
	private boolean moveFaster;
	private boolean turnLeft;
	private boolean turnRight;
	private boolean slowDown;
	private Ship arwing;
	public ArrayList<AsteroidDestroyingProjectile> projectileList;
	public Utilities(Ship arwing,  ArrayList<AsteroidDestroyingProjectile> projectileList)
	{
		this.arwing = arwing;
		this.projectileList = projectileList;
	}
	
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
	@Override
	public void keyTyped(KeyEvent e)
	{
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			turnLeft = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			moveFaster = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			slowDown = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			turnRight = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			projectileList.add(new AsteroidDestroyingProjectile(arwing.shipXPos, arwing.shipYPos, arwing.directionOfHeadOfShip, arwing.getSpeedOfShip()));
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			turnLeft = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			moveFaster = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			slowDown = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			turnRight = false;
		}
	}

}

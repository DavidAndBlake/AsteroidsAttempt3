import java.applet.AudioClip;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JApplet;

public class Utilities implements KeyListener
{
	private static int deltaX;
	private static int deltaY;
	private boolean moveFaster;
	private boolean turnLeft;
	private boolean turnRight;
	private boolean slowDown;
	private Ship arwing;
	private AsteroidGameController controller;
	public ArrayList<AsteroidDestroyingProjectile> projectileList;
	private KeyEvent e;
	public URL shotSoundURL;
	public URL gameMusicURL;
	public URL soundAddress;
	public AudioClip soundFile;

	public void playShotSound()
	{
		soundAddress = getClass()
				.getResource("270536__littlerobotsoundfactory__laser-09.wav");
		soundFile = JApplet.newAudioClip(soundAddress);
		soundFile.play();
	}

	public Utilities(Ship arwing,
			ArrayList<AsteroidDestroyingProjectile> projectileList)
	{
		this.arwing = arwing;
		this.projectileList = projectileList;
	}

	public static void convertCourseSpeedToDxDy(int course, double speed)
	{
		double cosine = Math.cos(Math.toRadians(course));
		double sine = -Math.sin(Math.toRadians(course));
		deltaX = ((int) (cosine * speed));
		deltaY = ((int) (sine * speed));
	}
	public static int getDeltaX()
	{
		return deltaX;
	}
	public static int getDeltaY()
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
	public Ship shipMovementRegulator(double rotationDegree,
			double directionOfHeadOfShip, int speedOfShip, int speedLimitOfShip,
			double colorChangeController)
	{
		rotationDegree = Math.toRadians(directionOfHeadOfShip);

		rotationDegree = -directionOfHeadOfShip + 90;
		if (this.moveFaster)
		{
			arwing.setSpeedOfShip(arwing.getSpeedOfShip() + 1);
		}
		if (this.turnRight)
		{
			arwing.directionOfHeadOfShip = arwing.directionOfHeadOfShip - 6;
		}
		if (this.turnLeft)
		{
			arwing.directionOfHeadOfShip = arwing.directionOfHeadOfShip + 6;
		}
		if (arwing.directionOfHeadOfShip > 360)
		{
			arwing.directionOfHeadOfShip = arwing.directionOfHeadOfShip - 360;
		}
		if (arwing.directionOfHeadOfShip < 0)
		{
			arwing.directionOfHeadOfShip = arwing.directionOfHeadOfShip + 360;
		}
		if (this.slowDown)
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
			this.turnLeft = true;
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
			 playShotSound();

			projectileList.add(new AsteroidDestroyingProjectile(
					arwing.getShipXPos(), arwing.getShipYPos(),
					arwing.directionOfHeadOfShip, arwing.getSpeedOfShip()));

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
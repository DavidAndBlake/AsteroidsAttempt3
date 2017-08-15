import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Delayed;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JApplet;
import javax.swing.Timer;

public class Utilities implements KeyListener, ActionListener
{
	private static int deltaX;
	private static int deltaY;
	private boolean moveFaster;
	private boolean turnLeft;
	private boolean turnRight;
	private boolean slowDown;
	private boolean shoot;
	private Ship spaceDrone;
	private AsteroidGameController controller;
	public ArrayList<Laser> projectileList;
	private boolean shipDestroyed;
	private int laserSpeed = 50;
	public Clip music;
	// public Timer shotRegulator = new Timer(400, e);
	public boolean gameComplete = false;

	public void playShotSound()
	{
		try
		{
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(getClass().getResource(
							"laser sound -10db.wav"
							));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex)
		{
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}
//	public void playMusic()
//	{
//		try
//		{
//			AudioInputStream audioInputStream = AudioSystem
//					.getAudioInputStream(getClass().getResource(
//							"Dublin.wav"));
//			music = AudioSystem.getClip();
//			music.open(audioInputStream);
//			music.loop(Clip.LOOP_CONTINUOUSLY);
//		} catch (Exception ex)
//		{
//			System.out.println("Error with playing sound.");
//			ex.printStackTrace();
//		}
//	}
//	public void stopMusic()
//	{
//		music.stop();
//	}
	public void playExplosionSound()
	{
		try
		{
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
					getClass().getResource("250712__aiwha__explosion.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			// FloatControl gainControl =
			// (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			// gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.
			clip.start();
		} catch (Exception ex)
		{
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}

	public Utilities(Ship spaceDrone, ArrayList<Laser> projectileList)
	{
		this.spaceDrone = spaceDrone;
		this.projectileList = projectileList;
		// this.projectileSpeed = projectileSpeed;
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

		if (!shipDestroyed)
		{
			rotationDegree = Math.toRadians(directionOfHeadOfShip);

			rotationDegree = -directionOfHeadOfShip + 90;
			if (this.moveFaster)
			{
				spaceDrone.setSpeedOfShip(spaceDrone.getSpeedOfShip() + 1);
			}
			if (this.turnRight)
			{
				spaceDrone.directionOfHeadOfShip = spaceDrone.directionOfHeadOfShip - 6;
			}
			if (this.turnLeft)
			{
				spaceDrone.directionOfHeadOfShip = spaceDrone.directionOfHeadOfShip + 6;
			}
			if (this.slowDown)
			{
				spaceDrone.setSpeedOfShip(spaceDrone.getSpeedOfShip() - 1);
			}

			if (spaceDrone.directionOfHeadOfShip > 360)
			{
				spaceDrone.directionOfHeadOfShip = spaceDrone.directionOfHeadOfShip
						- 360;
			}
			if (spaceDrone.directionOfHeadOfShip < 0)
			{
				spaceDrone.directionOfHeadOfShip = spaceDrone.directionOfHeadOfShip
						+ 360;
			}
			if (spaceDrone.getSpeedOfShip() > spaceDrone.getSpeedLimitOfShip())
			{
				spaceDrone.setSpeedOfShip(spaceDrone.getSpeedOfShip() - 1);
			}
			if (spaceDrone.getSpeedOfShip() < 0)
			{
				spaceDrone.setSpeedOfShip(spaceDrone.getSpeedOfShip() + 1);
			}
			if (rotationDegree > 180)
			{
				colorChangeController = 360 - directionOfHeadOfShip;
			}

		}
		if (shipDestroyed)
		{
			spaceDrone.setSpeedOfShip(0);
		}
		return spaceDrone;

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
			shoot = true;
		}
		if (gameComplete == true)
		{
			shoot = false;
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
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			shoot = false;
		}
	}
	public void setShipDestroyed(boolean shipDestroyed)
	{
		this.shipDestroyed = shipDestroyed;
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (this.shoot && !shipDestroyed)
		{
			playShotSound();
			projectileList.add(new Laser(spaceDrone.getShipXPos(),
					spaceDrone.getShipYPos(), spaceDrone.directionOfHeadOfShip,
					spaceDrone.getSpeedOfShip() + laserSpeed));

		}
	}
}
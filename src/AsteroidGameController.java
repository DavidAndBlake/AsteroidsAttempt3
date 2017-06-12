import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class AsteroidGameController extends JComponent implements ActionListener
{
	public JFrame space = new JFrame();
	private int widthOfScreen = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
	private int heightOfScreen = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
	private Image spaceImage = Toolkit.getDefaultToolkit().createImage(getClass().getResource("spacePicture.jpg"));
	public Timer ticker = new Timer(30, this);// THIS IS THE TIMER
	public int[] asteroid1XPoints =
	{ 21, 16, 20, 15, 0, -19, -17, -21, -15 };
	public int[] asteroid1YPoints =
	{ 24, 19, 18, 16, 17, 24, 20, 17, 18 };
	private int speedOfShip = 0;
	private int speedLimitOfShip = 10;
	private int middleScreenXPos = widthOfScreen / 2;
	private int middleScreenYPos = heightOfScreen / 2;
	private int directionOfHeadOfShip = 90; // degrees
	public int colorChangeController;
	public int colorChanger = (int) directionOfHeadOfShip - colorChangeController;
	public ArrayList<Asteroid> asteroidList = new ArrayList<>();
	public ArrayList<Laser> projectileList = new ArrayList<>();
	private Laser shot;
	public AffineTransform identity = new AffineTransform(); // identity
	public Random r = new Random();
	public int asteroidSpawnQuadrantPicker;
	public Ship spaceDrone = new Ship(middleScreenXPos, middleScreenYPos, widthOfScreen, heightOfScreen);
	public Utilities util = new Utilities(spaceDrone, projectileList);
	public Timer shotTicker = new Timer(90, util);
	private Timer endingDelayTicker = new Timer(300, null);
	private int score;
	public URL soundAddress;
	public AudioClip soundFile;
	public boolean shipDestroyed;
	private int asteroidSpeedLimit = 5;
	private int fastAsteroidSpeed = 15;
	private int asteroidDestroyedNumber = 1;
	private int fastAsteroidCounter;
	private int fastAsteroidInterval = 22;
	private int gameWinQuota = 100;
	private int asteroidLimit = 14;
	private double asteroidScaleFactor = 1.5;
	private double asteroidSpeed;

	public static void main(String[] args)
	{
		new AsteroidGameController().getGoing();
	}

	void getGoing()
	{
		/*********************************************************
		 * spawn asteroids
		 *********************************************************/
		JOptionPane.showMessageDialog(space,
				"Greetings drone pilot! Thank you for accepting this job!\n\nGenericFuturisticIndustries Inc. LTD needs your help in clearing the space around this quadrant of asteroids to prepare for space station construction");
		for (int j = 0; j < asteroidLimit; j++)
		{
			asteroidSpawner();
		}
		spaceDrone.setScreenHeight(heightOfScreen);
		spaceDrone.setScreenWidth(widthOfScreen);
		ticker.start();
		shotTicker.start();
		space.setSize(widthOfScreen, heightOfScreen);
		space.setVisible(true);
		space.setDefaultCloseOperation(space.EXIT_ON_CLOSE);
		space.add(this);
		space.setBackground(Color.BLACK);
		space.setTitle("HEY! GUESS WHAT? I'M A TITLE!");
		space.addKeyListener(util);
		util.playMusic();
	}

	public void asteroidSpawner() //Try to find a way to combine these commands into one in order to reduce space taken.
	{
		
			asteroidSpeed = (Math.random() * asteroidSpeedLimit) + 2;
		if (fastAsteroidCounter >= fastAsteroidInterval)
			{
				fastAsteroidCounter = 15;
				asteroidSpeed = (int) (Math.random() * fastAsteroidSpeed) + 6;
			}
		asteroidScaleFactor = (asteroidScaleFactor * Math.random()) + .8;
		asteroidSpawnQuadrantPicker = r.nextInt(4);
		if (asteroidSpawnQuadrantPicker == 0)// west
		{
			asteroidList.add(new Asteroid(-50, r.nextInt(heightOfScreen), r.nextInt(90) - 45, (int)asteroidSpeed, asteroidScaleFactor, Math.random(), true));
			// xpos, ypos, course, speed, scale factor, rotation speed, is asteroid a piece
		}
		if (asteroidSpawnQuadrantPicker == 1) // north
		{
			asteroidList.add(new Asteroid(r.nextInt(widthOfScreen), -50, r.nextInt(90) - 135, (int)asteroidSpeed, asteroidScaleFactor, Math.random(), true));
		}
		if (asteroidSpawnQuadrantPicker == 2) // east
		{
			asteroidList.add(new Asteroid(widthOfScreen + 50, r.nextInt(heightOfScreen), r.nextInt(90) - 225, (int)asteroidSpeed, asteroidScaleFactor, Math.random(), true));
		}
		if (asteroidSpawnQuadrantPicker == 3) // south
		{
			asteroidList.add(new Asteroid(r.nextInt(widthOfScreen), heightOfScreen + 50, r.nextInt(90) + 45, (int)asteroidSpeed, asteroidScaleFactor, Math.random(), true));
		}
	}

	public void asteroidPieceCreator(int asteroidXPos, int asteroidYPos, int course, double speed, double scaleFactor)
	{
		asteroidList.add(new Asteroid(asteroidXPos, asteroidYPos, r.nextInt(360) - 45, (int) (Math.random() * asteroidSpeedLimit) + 3, asteroidScaleFactor / 2, 0.001, false));
		// xpos, ypos, course, speed, scale factor, rotation speed, fragment
	}
//	public void fastAsteroidSpawner()
//	{
//		asteroidDestroyedNumber++;
//		asteroidSpawnQuadrantPicker = r.nextInt(4);
//		if (asteroidSpawnQuadrantPicker == 0)// west
//		{
//			asteroidList.add(new Asteroid(-50, r.nextInt(heightOfScreen), r.nextInt(90) - 45, (int) (Math.random() * fastAsteroidSpeed) + 6, asteroidScaleFactor, Math.random(), true));
//			// xpos, ypos, course, speed, scale factor, rotation speed, is a
//			// whole piece
//		}
//		if (asteroidSpawnQuadrantPicker == 1) // north
//		{
//			if (asteroidScaleFactor > 1)
//				asteroidList.add(new Asteroid(r.nextInt(widthOfScreen), -50, r.nextInt(90) - 135, (int) (Math.random() * fastAsteroidSpeed) + 6, asteroidScaleFactor, Math.random(), true));
//		}
//
//		if (asteroidSpawnQuadrantPicker == 2) // east
//		{
//			asteroidList.add(new Asteroid(widthOfScreen + 50, r.nextInt(heightOfScreen), r.nextInt(90) - 225, (int) (Math.random() * fastAsteroidSpeed) + 6, asteroidScaleFactor, Math.random(), true));
//		}
//		if (asteroidSpawnQuadrantPicker == 3) // south
//		{
//			asteroidList.add(new Asteroid(r.nextInt(widthOfScreen), heightOfScreen + 50, r.nextInt(90) + 45, (int) (Math.random() * fastAsteroidSpeed) + 6, asteroidScaleFactor, Math.random(), true));
//		}
//	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		double rotationDegree = Math.toRadians(directionOfHeadOfShip);
		spaceDrone = util.shipMovementRegulator(rotationDegree, directionOfHeadOfShip, speedOfShip, speedLimitOfShip, colorChangeController);
		repaint();
	}

	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setTransform(identity);
		g2.scale((double) widthOfScreen / spaceImage.getWidth(this), (double) heightOfScreen / spaceImage.getHeight(this));
		g2.drawImage(spaceImage, 0, 0, null, null);
		g2.setColor(Color.green);
		g2.drawRect(widthOfScreen / 2, heightOfScreen / 4 - 50, 80, 50);
		g2.setColor(Color.white);
		g2.drawString("Arrow keys to move\nHold space to shoot", widthOfScreen / 4, heightOfScreen / 3);
		g2.setFont(new Font("Sans", Font.PLAIN, 40));
		g2.drawString("" + score, (widthOfScreen / 2), heightOfScreen / 4);
		g2.setTransform(identity);
		spaceDrone.paintShip(g2);
		for (int i = 0; i < asteroidList.size(); i++)
		{
			g2.setTransform(identity); // cleans up screen
			asteroidList.get(i).paintAsteroid(g2);
			Asteroid asteroid = asteroidList.get(i);
			Area asteroidArea = new Area(asteroid.asteroidShape);
			AffineTransform asteroidAT = new AffineTransform();
			asteroidAT.setToTranslation(asteroidList.get(i).asteroidXPos, asteroidList.get(i).asteroidYPos);
			asteroidArea.transform(asteroidAT);
			if (util.isOffScreen(asteroid.asteroidXPos, asteroid.asteroidYPos, widthOfScreen, heightOfScreen))
			{
				asteroidList.remove(i);
				if (asteroidDestroyedNumber < gameWinQuota)
				{
					asteroidSpawner();
				} // fast asteroid spawner
			}
			for (int j = 0; j < projectileList.size(); j++) // checking all
															// bullets
			{
				shot = projectileList.get(j);
				Area shotArea = new Area(shot.shotShape);
				AffineTransform shotAT = new AffineTransform();
				shotAT.setToTranslation(shot.laserXPos, shot.laserYPos);
				shotArea.transform(shotAT);
				shotArea.intersect(asteroidArea);

				if (util.isOffScreen(shot.laserXPos, shot.laserYPos, widthOfScreen, heightOfScreen))
				{
					projectileList.remove(j);
				}
				if (!shotArea.isEmpty()) // asteroid Hit
				{
					int iXPos = asteroidList.get(i).asteroidXPos;
					int iYPos = asteroidList.get(i).asteroidYPos;
					asteroidList.remove(i);
					projectileList.remove(j);
					asteroidDestroyedNumber++;
					fastAsteroidCounter++;
					score = score + 1;
					if (asteroid.isAWholePiece)
					{
						for (int k = 0; k < 3; k++)
						{
							asteroidPieceCreator(iXPos, iYPos, 33, Math.random() * asteroidSpeedLimit, .3);
						}
					}
				}
			}
			Area leftShipArea = new Area(spaceDrone.shipLeftSide);
			Area rightShipArea = new Area(spaceDrone.shipRightSide);
			AffineTransform arwingAT = new AffineTransform();
			arwingAT.setToTranslation(spaceDrone.getShipXPos(), spaceDrone.getShipYPos());
			leftShipArea.transform(arwingAT);
			leftShipArea.intersect(asteroidArea);
			rightShipArea.intersect(asteroidArea);
			if (!leftShipArea.isEmpty())
			{
				spaceDrone.canopy.reset();
				spaceDrone.shipLeftSide.reset();
				spaceDrone.shipRightSide.reset();
				spaceDrone.setShipDestroyed(true);
				util.setShipDestroyed(true);
				util.playExplosionSound();
				endingDelayTicker.start();
				if (endingDelayTicker.isRunning()) // CHANGE THIS MECHANIC SO
													// THAT THE MESSAGE IS
													// DELAYED BY THE TIMER
				{
					JOptionPane.showMessageDialog(null, "You suck");
				}

			}
			if (asteroidList.size() < 1)
			{
				JOptionPane.showMessageDialog(null, "Congratulations! Your job is complete!\n\nYou win!");
			}
		}
		for (int j = 0; j < projectileList.size(); j++) // checking all bullets
		{
			Laser shot = projectileList.get(j);
			g2.setTransform(identity);
			shot.paintProjectile(g2);
		}
	}
}
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class AsteroidGameController extends JComponent
		implements ActionListener, KeyListener
{
	public JFrame space = new JFrame();
	int screenWidth = java.awt.Toolkit.getDefaultToolkit()
			.getScreenSize().width;
	int screenHeight = java.awt.Toolkit.getDefaultToolkit()
			.getScreenSize().height;
	public JPanel spaceImagePanel = new JPanel();
	private Image spaceImage = new ImageIcon(getClass().getResource("spacePicture.jpg")).getImage();// Image spaceImage;
	public Timer ticker = new Timer(30, this);
	public int[] asteroid1XPoints =
	{ 21, 16, 20, 15, 0, -19, -17, -21, -15 };
	public int[] asteroid1YPoints =
	{ 24, 19, 18, 16, 17, 24, 20, 17, 18 };
	public JLabel spaceLabel = new JLabel();
	private int speedOfShip = 0;
	private int speedLimitOfShip = 10;
	private int middleScreenXPos = screenWidth / 2;
	private int middleScreenYPos = screenHeight / 2;
	private int directionOfHeadOfShip = 90; // degrees
	public int colorChangeController;
	public int colorChanger = (int) directionOfHeadOfShip - colorChangeController;
	private boolean moveFaster;
	private boolean turnLeft;
	private boolean turnRight;
	private boolean slowDown;
	public Ship arwing;
	public AsteroidDestroyingProjectile shot;
	public ArrayList<Asteroid> asteroidList = new ArrayList<>();
	public ArrayList<AsteroidDestroyingProjectile> projectileList = new ArrayList<>();
	public AffineTransform identity = new AffineTransform(); // identity transform
	public Random r = new Random();
	public int asteroidSpawnQuadrantPicker;
	
	public static void main(String[] args)
	{
		new AsteroidGameController().getGoing();
	}

	void getGoing()
	{
		
		/*********************************************************
		 * spawn asteroids
		 *********************************************************/
		for (int j = 0; j < 6; j++)
		{
			asteroidSpawner();
		}
		/*********************************************************
		 * spawn projectiles
		 *********************************************************/
		for (int i = 0; i < projectileList.size(); i++)
		{
			AsteroidDestroyingProjectile shot = projectileList.get(i);
		}
		
		arwing = new Ship(middleScreenXPos, middleScreenYPos);
		arwing.setScreenHeight(screenHeight);
		arwing.setScreenWidth(screenWidth);
		ticker.start();
		spaceLabel.equals(spaceImage);
		space.setSize(screenWidth, screenHeight);
		space.setVisible(true);
		space.setDefaultCloseOperation(space.EXIT_ON_CLOSE);
		spaceImagePanel.add(spaceLabel);
		spaceImagePanel.setVisible(true);
		space.add(spaceImagePanel);
		space.add(this);
		space.setBackground(Color.BLACK);
		space.setTitle("HEY! GUESS WHAT? I'M A TITLE!");
		space.addKeyListener(this);
		arwing.setRotationDegree(0);
	}

	public void asteroidSpawner()
	{
		//try to figure out how to do collisions, and fix assimilating asteroid glitch
		asteroidSpawnQuadrantPicker = r.nextInt(4);
		if (asteroidSpawnQuadrantPicker == 0)// west
		{
			asteroidList.add(new Asteroid(-50, r.nextInt(screenHeight),
					r.nextInt(90) - 45, 3, Math.random() * 0.1, Math.random()));// xpos, ypos, course, speed, scale factor, rotation speed
		}
		if (asteroidSpawnQuadrantPicker == 1) // north
		{
			asteroidList.add(new Asteroid(r.nextInt(screenWidth), -50,
					r.nextInt(90) - 135, 3, Math.random() * 0.1, Math.random()));
		}
		if (asteroidSpawnQuadrantPicker == 2) // east
		{
			asteroidList.add(new Asteroid(screenWidth + 50,
					r.nextInt(screenHeight), r.nextInt(90) - 225, 3, Math.random() * 0.1, Math.random()));
		}
		if (asteroidSpawnQuadrantPicker == 3) // south
		{
			asteroidList.add(new Asteroid(r.nextInt(screenWidth),
					screenHeight + 50, r.nextInt(90) + 45, 3, Math.random() * 0.1, Math.random()));
		}
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		shipMovementRegulator();
		arwing.setDirectionOfHeadOfShip(directionOfHeadOfShip);
		arwing.setSpeedOfShip(speedOfShip);
		repaint();
	}

	public void shipMovementRegulator()
	{
		double rotationDegree = Math.toRadians(directionOfHeadOfShip);

		rotationDegree = -directionOfHeadOfShip + 90;
		if (moveFaster)
		{
			speedOfShip = speedOfShip + 1;
			arwing.setMoveFaster(moveFaster);
		}
		if (turnRight)
		{
			directionOfHeadOfShip = directionOfHeadOfShip - 6;
		}
		if (turnLeft)
		{
			directionOfHeadOfShip = directionOfHeadOfShip + 6;
			arwing.setTurnLeft(turnLeft);
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
	}
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setTransform(identity);
		g2.scale(1.25, 1);
		g2.drawImage(spaceImage, 0, 0, null);
		g2.setTransform(identity);
		arwing.paintShip(g2);
		for (int i = 0; i < asteroidList.size(); i++)
		{
			g2.setTransform(identity); // cleans up screen
			asteroidList.get(i).paintAsteroid(g2); 
			Asteroid asteroid = asteroidList.get(i);
			if (asteroid.asteroidXPos > screenWidth + 50
					|| asteroid.asteroidXPos < -50
					|| asteroid.asteroidYPos > screenHeight + 50
					|| asteroid.asteroidYPos < -50
					)
				{
					asteroidList.remove(i);
					asteroidSpawner();
				}
			for (int i = 0; i < projectileList.size(); i++)
			{
				AsteroidDestroyingProjectile bullet = projectileList.get(i);
				if(asteroid.asteroidShape.intersects(bullet.shotShape))
				{
					System.out.println("boom");
				}
			}
		}
		for (int i = 0; i < projectileList.size(); i++)
		{
			g2.setTransform(identity);
			projectileList.get(i).paintProjectile(g2);
		}
		
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
			projectileList.add(new AsteroidDestroyingProjectile(arwing.shipXPos, arwing.shipYPos, directionOfHeadOfShip, speedOfShip));
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

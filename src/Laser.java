import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

public class Laser implements ActionListener {
	public Ellipse2D.Double shotShape = new Ellipse2D.Double(-4, -80, 9, 100);
	private int laserCourse;
	private int laserSpeed;
	public int laserXPos;
	public int laserYPos;
	private int deltaX;
	private int deltaY;
	private int colorChanger1 = (int) (Math.random() * 255);
	private int colorChanger2 = (int) (Math.random() * 255);
	private int colorChanger3 = (int) (Math.random() * 255);

	public Laser(int xPos, int yPos, int projectileCourse, int projectileSpeed) {
		laserXPos = xPos;
		laserYPos = yPos;
		this.laserCourse = projectileCourse;
		this.laserSpeed = projectileSpeed;
	}

	public void paintProjectile(Graphics2D g2) {
		Utilities.convertCourseSpeedToDxDy(laserCourse, laserSpeed);
		deltaX = Utilities.getDeltaX();
		deltaY = Utilities.getDeltaY();
		laserXPos = (int) (laserXPos + deltaX);
		laserYPos = (int) (laserYPos + deltaY);
		g2.translate(laserXPos, laserYPos);
		g2.rotate(Math.toRadians(-laserCourse + 90));
		g2.setColor(new Color(colorChanger1, colorChanger2, colorChanger3));
		g2.fill(shotShape);	
	}

	public void setProjectileCourse(int laserCourse) {
		this.laserCourse = laserCourse;
	}

	public void setProjectileSpeed(int laserSpeed) {
		this.laserSpeed = laserSpeed;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		colorChanger1 = colorChanger1 + (int) (Math.random() * 100);
		colorChanger2 = colorChanger2 + (int) (Math.random() * 50);
		colorChanger3 = colorChanger3 + (int) (Math.random() * 25);
	}
}
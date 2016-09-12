import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class AsteroidDestroyingProjectile extends Utilities implements ActionListener 
{
	public Rectangle2D.Double shotShape = new Rectangle2D.Double(-4, -30, 9, 8);
	private int projectileCourse;
	private int projectileSpeed;
	public int projectileXPos;
	public int projectileYPos;
	private int deltaX;
	private int deltaY;
	private int colorChanger1 = (int)(Math.random() * 255);
	private int colorChanger2 = (int)(Math.random() * 255);
	private int colorChanger3 = (int)(Math.random() * 255);
	
	public AsteroidDestroyingProjectile(int xPos, int yPos, int projectileCourse, int projectileSpeed)
	{
		projectileXPos = xPos;
		projectileYPos = yPos;
		this.projectileCourse = projectileCourse;
		this.projectileSpeed = projectileSpeed;
	}

	public void paintProjectile(Graphics2D g2)
	{
		
		convertCourseSpeedToDxDy(projectileCourse, projectileSpeed + 10);
		deltaX = getDeltaX();
		deltaY = getDeltaY();
		projectileXPos = (int) (projectileXPos + deltaX);
		projectileYPos = (int) (projectileYPos + deltaY);
		g2.translate(projectileXPos, projectileYPos);
		g2.rotate(Math.toRadians(-projectileCourse + 90));
		g2.setColor(new Color(colorChanger1, colorChanger2, colorChanger3));
		g2.fill(shotShape);
		
	}

	public void setProjectileCourse(int projectileCourse)
	{
		this.projectileCourse = projectileCourse;
	}
	
	public void setProjectileSpeed(int projectileSpeed)
	{
		this.projectileSpeed = projectileSpeed;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		colorChanger1 = colorChanger1 + (int)(Math.random()*100);
		colorChanger2 = colorChanger2 + (int)(Math.random()*50);
		colorChanger3 = colorChanger3 + (int)(Math.random()*25);		
	}
	
}

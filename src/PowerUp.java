import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import org.w3c.dom.css.Rect;

public class PowerUp
{
	
	int powerUpXPos = (int)(Math.random()*500);
	int powerUpYPos = (int)(Math.random()*500); // make the power up move to the right, and have collision detection with the ship
	AffineTransform powerUpTransform = new AffineTransform(); //Affine Transform sets all the reference frames of the object to their default
	public void paintPowerUp(Graphics2D g2){
		g2.setTransform(powerUpTransform);
		g2.setColor(Color.white);
		Rectangle2D powerUpShape = new Rectangle2D (powerUpXPos, powerUpYPos, 50, 50);
		powerUpXPos = powerUpXPos+5;
	}
}

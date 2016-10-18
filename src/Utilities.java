
public class Utilities
{
	private int deltaX;
	private int deltaY;
	public Asteroid asteroid;
		
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
	public boolean isOffScreen(int xPos, int yPos, int screenWidth, int screenHeight)
	{
		if (asteroid.asteroidXPos > screenWidth + 50
				|| asteroid.asteroidXPos < -50
				|| asteroid.asteroidYPos > screenHeight + 50
				|| asteroid.asteroidYPos < -50);
		return true;
			}
	
}

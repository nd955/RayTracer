
public class AmbientLight {

	private double intensity;
	private double r,g,b;
	
	public AmbientLight(double i, double red, double green, double blue)
	{
		intensity = i;
		r = red;
		g = green;
		b = blue;
	}
	
	public double getR()
	{
		return r;
	}
	
	public double getG()
	{
		return g;
	}
	
	public double getB()
	{
		return b;
	}
}

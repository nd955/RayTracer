
public class PointLight {

	private double intensity;
	private double r,g,b;
	private Vertex location;
	
	public PointLight(double i, double red, double green, double blue, Vertex l)
	{
		intensity = i;
		r = red;
		g = green;
		b = blue;
		location = l;
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
	
	public Vertex getLocation()
	{
		return location;
	}
	
}


public class Sphere extends Surface {

	private double radius;
	private Vertex center;
	
	public Sphere(double rad, Vertex c, double red, double green, double blue, double ambient, double diffuse, double specularCo, double specularExp)
	{
		radius = rad;
		center = c;
		r = red;
		g = green;
		b = blue;
		ambientCoeff = ambient;
		diffuseCoeff = diffuse;
		specularCoeff = specularCo;
		specularExponent = specularExp;
	}
	
	public double getRadius()
	{
		return radius;
	}
	public void setRadius(double newR)
	{
		radius = newR;
	}
	
	public Vertex getCenter()
	{
		return center;
	}
	public void setCenter(Vertex newC)
	{
		center = newC;
	}

	@Override
	public char isA() {
		return 's';
	}
	
}

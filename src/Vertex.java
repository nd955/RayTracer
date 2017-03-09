
public class Vertex {
	
	public double x, y, z, h;

	public Vertex(double x1, double y1, double z1, double h1)
	{
		x = x1;
		y = y1;
		z = z1;
		h = h1;
	}
	public Vertex(double x1, double y1, double z1)
	{
		x = x1;
		y = y1;
		z = z1;
		h = 1.0;
	}
	
	public Vertex subtractVertices(Vertex v2)
	{
		Vertex newVertex;
		if(h != 1.0)
		{
			x = x / h;
			y = y / h;
			z = z / h;
			h = 1.0;
		}
		if(v2.h != 1.0)
		{
			v2.x = x / h;
			v2.y = y / h;
			v2.z = z / h;
			v2.h = 1.0;
		}
		
		newVertex = new Vertex(x - v2.x, y - v2.y, z - v2.z); //uses overloaded constructor => (h == 1.0)
		return newVertex;
	}
	public Vertex multiplyVertices(Vertex v2)
	{
		Vertex newVertex;
		if(h != 1.0)
		{
			x = x / h;
			y = y / h;
			z = z / h;
			h = 1.0;
		}
		if(v2.h != 1.0)
		{
			v2.x = x / h;
			v2.y = y / h;
			v2.z = z / h;
			v2.h = 1.0;
		}
		
		newVertex = new Vertex(x * v2.x, y * v2.y, z * v2.z); //uses overloaded constructor => (h == 1.0)
		return newVertex;
	}
	public Vector convertToVector()
	{
		return new Vector(x, y, z);
	}
	public String toString()
	{
		String sVertex = "[ " + x + ", " + y + ", " + z + ", " + h + " ]";
		return sVertex;
	}
}

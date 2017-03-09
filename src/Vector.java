
public class Vector {
	
	public double x, y, z;

	public Vector(double x1, double y1, double z1)
	{
		x = x1; 
		y = y1;
		z = z1;
	}
	
	public Vector crossProduct(Vector v2)
	{
		Vector cross = new Vector(y * v2.z - z * v2.y, z * v2.x - x * v2.z, x * v2.y - y * v2.x);
		return cross;
	}
	public double dotProduct(Vector v2)
	{
		double dot = x * v2.x + y * v2.y + z * v2.z;
		return dot;
	}
	public double magnitude()
	{
		double magnitude = x * x + y * y + z * z;
		magnitude = Math.sqrt(magnitude);
		return magnitude;
	}
	public void normalize()
	{
		double mag = this.magnitude();
		x = x / mag;
		y = y / mag;
		z = z / mag;
	}
	public Vector negate()
	{
		return new Vector(-x, -y, -z);
	}
	public Vector subtractVectors(Vector v2)
	{
		Vector newVector = new Vector(x - v2.x, y - v2.y, z - v2.z); 
		return newVector;
	}
	public String toString()
	{
		String sVector = "( " + x + ", " + y + ", " + z + " )";
		return sVector;
	}

}

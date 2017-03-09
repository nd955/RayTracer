public class Matrix {
	
	//[row][col]
	private double entries[][];
	
	public Matrix(double array[][])
	{
		entries = array;
	}

	public Matrix multiply(Matrix m2)
	{
		Matrix mat = new Matrix(new double[3][3]);
		//1st column
		mat.entries[0][0] = entries[0][0] * m2.entries[0][0] + entries[0][1] * m2.entries[1][0] + entries[0][2] * m2.entries[2][0] + entries[0][3] * m2.entries[3][0];
		mat.entries[1][0] = entries[1][0] * m2.entries[0][0] + entries[1][1] * m2.entries[1][0] + entries[1][2] * m2.entries[2][0] + entries[1][3] * m2.entries[3][0];
		mat.entries[2][0] = entries[2][0] * m2.entries[0][0] + entries[2][1] * m2.entries[1][0] + entries[2][2] * m2.entries[2][0] + entries[2][3] * m2.entries[3][0];
		mat.entries[3][0] = entries[3][0] * m2.entries[0][0] + entries[3][1] * m2.entries[1][0] + entries[3][2] * m2.entries[2][0] + entries[3][3] * m2.entries[3][0];
		//2nd column
		mat.entries[0][1] = entries[0][0] * m2.entries[0][1] + entries[0][1] * m2.entries[1][1] + entries[0][2] * m2.entries[2][1] + entries[0][3] * m2.entries[3][1];
		mat.entries[1][1] = entries[1][0] * m2.entries[0][1] + entries[1][1] * m2.entries[1][1] + entries[1][2] * m2.entries[2][1] + entries[1][3] * m2.entries[3][1];
		mat.entries[2][1] = entries[2][0] * m2.entries[0][1] + entries[2][1] * m2.entries[1][1] + entries[2][2] * m2.entries[2][1] + entries[2][3] * m2.entries[3][1];
		mat.entries[3][1] = entries[3][0] * m2.entries[0][1] + entries[3][1] * m2.entries[1][1] + entries[3][2] * m2.entries[2][1] + entries[3][3] * m2.entries[3][1];
		//3rd column
		mat.entries[0][2] = entries[0][0] * m2.entries[0][2] + entries[0][1] * m2.entries[1][2] + entries[0][2] * m2.entries[2][2] + entries[0][3] * m2.entries[3][2];
		mat.entries[1][2] = entries[1][0] * m2.entries[0][2] + entries[1][1] * m2.entries[1][2] + entries[1][2] * m2.entries[2][2] + entries[1][3] * m2.entries[3][2];
		mat.entries[2][2] = entries[2][0] * m2.entries[0][2] + entries[2][1] * m2.entries[1][2] + entries[2][2] * m2.entries[2][2] + entries[2][3] * m2.entries[3][2];
		mat.entries[3][2] = entries[3][0] * m2.entries[0][2] + entries[3][1] * m2.entries[1][2] + entries[3][2] * m2.entries[2][2] + entries[3][3] * m2.entries[3][2];
		//4th column
		mat.entries[0][3] = entries[0][0] * m2.entries[0][3] + entries[0][1] * m2.entries[1][3] + entries[0][2] * m2.entries[2][3] + entries[0][3] * m2.entries[3][3];
		mat.entries[1][3] = entries[1][0] * m2.entries[0][3] + entries[1][1] * m2.entries[1][3] + entries[1][2] * m2.entries[2][3] + entries[1][3] * m2.entries[3][3];
		mat.entries[2][3] = entries[2][0] * m2.entries[0][3] + entries[2][1] * m2.entries[1][3] + entries[2][2] * m2.entries[2][3] + entries[2][3] * m2.entries[3][3];
		mat.entries[3][3] = entries[3][0] * m2.entries[0][3] + entries[3][1] * m2.entries[1][3] + entries[3][2] * m2.entries[2][3] + entries[3][3] * m2.entries[3][3];
				
		return mat;
	}
	public Vertex multiply(Vertex u)
	{
		double x = entries[0][0] * u.x + entries[0][1] * u.y + entries[0][2] * u.z + entries[0][3] * u.h;
		double y = entries[1][0] * u.x + entries[1][1] * u.y + entries[1][2] * u.z + entries[1][3] * u.h;
		double z = entries[2][0] * u.x + entries[2][1] * u.y + entries[2][2] * u.z + entries[2][3] * u.h;
		double h = entries[3][0] * u.x + entries[3][1] * u.y + entries[3][2] * u.z + entries[3][3] * u.h;
		
		Vertex v = new Vertex(x, y, z, h);
		
		return v;
	}	
}

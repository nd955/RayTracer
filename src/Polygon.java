public class Polygon extends Surface
{

	private Vertex[] verts;

	public Polygon(Vertex[] vs, double red, double green, double blue, double ambient, double diffuse, double specularCo, double specularExp)
	{
		verts = vs;
		r = red;
		g = green;
		b = blue;
		ambientCoeff = ambient;
		diffuseCoeff = diffuse;
		specularCoeff = specularCo;
		specularExponent = specularExp;
	}
	
	public Vertex[] getVertexList()
	{
		return verts;
	}

	@Override
	public char isA() {
		return 'p';
	}

	
}

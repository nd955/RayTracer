
public abstract class Surface {

	protected double r,g,b;
	protected double ambientCoeff;
	protected double diffuseCoeff;
	protected double specularCoeff;
	protected double specularExponent;
	
	public void setAmbient(double ambient)
	{
		ambientCoeff = ambient;
	}
	
	public abstract char isA();
	
	public void setDiffuse(double diffuse)
	{
		diffuseCoeff = diffuse;
	}
	
	public void setSpecularCoeff(double specular)
	{
		specularCoeff = specular;
	}
	
	public void setSpecularExponent(double specular)
	{
		specularExponent = specular;
	}
	
	public double getAmbient()
	{
		return ambientCoeff;
	}
	
	public double getDiffuse()
	{
		return diffuseCoeff;
	}
	
	public double getSpecularCoeff()
	{
		return specularCoeff;
	}
	
	public double getSpecularExponent()
	{
		return specularExponent;
	}
	
	public void setRed(double red)
	{
		if (red >= 0)
		{
			if (red <= 1)
			{
				r = red;
			}
			else
			{
				r = 1.0;
			}

		}
		else
		{
			r = 0.0;
		}
	}

	public void setGreen(double green)
	{
		if (green >= 0)
		{
			if (green <= 1)
			{
				g = green;
			}
			else
			{
				g = 1.0;
			}

		}
		else
		{
			g = 0.0;
		}
	}

	public void setBlue(double blue)
	{
		if (blue >= 0)
		{
			if (blue <= 1)
			{
				b = blue;
			}
			else
			{
				b = 1.0;
			}

		}
		else
		{
			b = 0.0;
		}
	}
	
	public double getRed()
	{
		return r;
	}

	public double getGreen()
	{
		return g;
	}

	public double getBlue()
	{
		return b;
	}

	public String getColorInfo()
	{
		return "red: " + r + ", green: " + g + ", blue: " + b;
	}
	
}

/*
   RGBPixel 

   Instantiable class that stores a Red, Green and Blue value each
   in the range 0-255.

   Author: Michael Eckmann
   Skidmore College
   Spring 2016

*/

public class RGBPixel
{
	private int r;
	private int g;
	private int b;

	public RGBPixel(int r, int g, int b)
	{
		if(r > 255)
			this.r = 255;
		else if(r < 0)
			this.r = 0;
		else
			this.r = r;
		
		if(g > 255)
			this.g = 255;
		else if(g < 0)
			this.g = 0;
		else
			this.g = g;
		
		if(b > 255)
			this.b = 255;
		else if (b < 0)
			this.b = 0;
		else
			this.b = b;
	}

	public int getRed()
	{
		return r;
	}

	public int getGreen()
	{
		return g;
	}

	public int getBlue()
	{
		return b;
	}

}

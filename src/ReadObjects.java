import java.awt.Frame;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ReadObjects {

	private static String fileName = "src/environment.txt";
	
	public static Vertex v[];
	public static Polygon p[];
	public static Sphere s[];
	public static PointLight l[];
	
	public static AmbientLight ambient;

	public static void readFile()
	{
		BufferedReader objFileBR;
		String line, tempstr;
		StringTokenizer st;
		
		try 
		{
			objFileBR = new BufferedReader(new FileReader(fileName));
			
			line = objFileBR.readLine();
			st = new StringTokenizer(line, " ");
			tempstr = st.nextToken();
			if (tempstr.equals("VERTICES")) 
			{
				tempstr = st.nextToken();
				v = new Vertex[Integer.parseInt(tempstr)];
			}
			else
			{
				System.out.println("Expecting VERTICES line in file "
						+ fileName);
				System.exit(1);
			}
			
			line = objFileBR.readLine();
			st = new StringTokenizer(line, " ");
			tempstr = st.nextToken();
			if (tempstr.equals("POLYGONS")) {
				tempstr = st.nextToken();
				p = new Polygon[Integer.parseInt(tempstr)];
			} else {
				System.out.println("Expecting POLYGONS line in file "
						+ fileName);
				System.exit(1);
			}
			
			line = objFileBR.readLine();
			st = new StringTokenizer(line, " ");
			tempstr = st.nextToken();
			if (tempstr.equals("SPHERES")) {
				tempstr = st.nextToken();
				s = new Sphere[Integer.parseInt(tempstr)];
			} else {
				System.out.println("Expecting SPHERES line in file "
						+ fileName);
				System.exit(1);
			}
			
			line = objFileBR.readLine();
			st = new StringTokenizer(line, " ");
			tempstr = st.nextToken();
			if (tempstr.equals("LIGHTS")) 
			{
				tempstr = st.nextToken();
				l = new PointLight[Integer.parseInt(tempstr)];
			} 
			else
			{
				System.out.println("Expecting LIGHTS line in file "
						+ fileName);
				System.exit(1);
			}
			
			line = objFileBR.readLine(); 
			st = new StringTokenizer(line, " ");
			tempstr = st.nextToken();
			if (tempstr.equals("VERTEX")) {
				tempstr = st.nextToken();
				if (!tempstr.equals("LIST")) {
					System.out.println("Expecting VERTEX LIST line in file "
							+ fileName);
					System.exit(1);
				}
			} else {
				System.out.println("Expecting VERTEX LIST line in file "
						+ fileName);
				System.exit(1);
			}
			
			for (int i = 0; i < v.length; i++) {
				line = objFileBR.readLine();
				st = new StringTokenizer(line, " ");
				double x1=0, y1=0, z1=0;	
				tempstr = st.nextToken();
				x1 = Double.parseDouble(tempstr);
				tempstr = st.nextToken();
				y1 = Double.parseDouble(tempstr);
				tempstr = st.nextToken();
				z1 = Double.parseDouble(tempstr);
				v[i] = new Vertex(x1,y1,z1,1.0);
			}
			
			line = objFileBR.readLine(); 
			st = new StringTokenizer(line, " ");
			tempstr = st.nextToken();
			if (tempstr.equals("POLYGON")) {
				tempstr = st.nextToken();
				if (!tempstr.equals("LIST")) {
					System.out.println("Expecting POLYGON LIST line in file "
							+ fileName);
					System.exit(1);
				}
			} else {
				System.out.println("Expecting POLYGON LIST line in file "
						+ fileName);
				System.exit(1);
			}
			
			Vertex[] polyVerts = new Vertex[0];
			
			for (int i = 0; i < p.length; i++) 
			{
				line = objFileBR.readLine();
				st = new StringTokenizer(line, " ");
				if(st.nextToken().equals("COUNT")) 
				{
					tempstr = st.nextToken();
					polyVerts = new Vertex[Integer.parseInt(tempstr)];
				}
				else 
				{
					System.out.println("Expecting COUNT line in file "
							+ fileName);
					System.exit(1);
				}
				
				if(st.nextToken().equals("VERTICES"))
				{
					for(int j = 0; j < polyVerts.length; j++)
					{
						polyVerts[j] = v[Integer.parseInt((st.nextToken()))];
					}
				}
				else 
				{
					System.out.println("Expecting VERTICES line in file "
							+ fileName);
					System.exit(1);
				}
				
				double r = 0, g = 0, b = 0, amb = 0, diff = 0, specCo = 0, specExp = 0;
				
				if(st.nextToken().equals("COLOR"))
				{
					tempstr = st.nextToken();
					r = Double.parseDouble(tempstr);
					tempstr = st.nextToken();
					g = Double.parseDouble(tempstr);
					tempstr = st.nextToken();
					b = Double.parseDouble(tempstr);
				}
				else
				{
					System.out.println("Expecting COLOR line in file "
							+ fileName);
					System.exit(1);
				}
				if(st.nextToken().equals("AMBIENT"))
				{
					amb = Double.parseDouble(st.nextToken());
				}
				else
				{
					System.out.println("Expecting AMBIENT line in file "
							+ fileName);
					System.exit(1);
				}
				if(st.nextToken().equals("DIFFUSE"))
				{
					diff = Double.parseDouble(st.nextToken());
				}
				else
				{
					System.out.println("Expecting DIFFUSE line in file "
							+ fileName);
					System.exit(1);
				}
				if(st.nextToken().equals("SPECULARCOEFFICIENT"))
				{
					specCo = Double.parseDouble(st.nextToken());
				}
				else
				{
					System.out.println("Expecting SPECULARCOEFFICIENT line in file "
							+ fileName);
					System.exit(1);
				}
				if(st.nextToken().equals("SPECULAREXPONENT"))
				{
					specExp = Double.parseDouble(st.nextToken());
				}
				else
				{
					System.out.println("Expecting SPECULAREXPONENT line in file "
							+ fileName);
					System.exit(1);
				}
				
				p[i] = new Polygon(polyVerts, r, g, b, amb, diff, specCo, specExp);
			}
			
			line = objFileBR.readLine(); 
			st = new StringTokenizer(line, " ");
			tempstr = st.nextToken();
			if (tempstr.equals("SPHERE")) {
				tempstr = st.nextToken();
				if (!tempstr.equals("LIST")) {
					System.out.println("Expecting SPHERE LIST line in file "
							+ fileName);
					System.exit(1);
				}
			} 
			else
			{
				System.out.println("Expecting SPHERE LIST line in file "
						+ fileName);
				System.exit(1);
			}
			for(int i = 0; i < s.length; i++)
			{
				double rad = 0, r = 0, g = 0, b = 0, amb = 0, diff = 0, specCo = 0, specExp = 0;
				Vertex cen = new Vertex(0,0,0);
				line = objFileBR.readLine();
				st = new StringTokenizer(line, " ");
				if(st.nextToken().equals("RADIUS"))
				{
					tempstr = st.nextToken();
					rad = Double.parseDouble(tempstr);
				}
				else
				{
					System.out.println("Expecting RADIUS line in file "
							+ fileName);
					System.exit(1);
				}
				if(st.nextToken().equals("CENTER"))
				{
					double x, y, z;
					x = Double.parseDouble(st.nextToken());
					y = Double.parseDouble(st.nextToken());
					z = Double.parseDouble(st.nextToken());
					cen = new Vertex(x, y, z);
				}
				else
				{
					System.out.println("Expecting CENTER line in file "
							+ fileName);
					System.exit(1);
				}
				if(st.nextToken().equals("COLOR"))
				{
					tempstr = st.nextToken();
					r = Double.parseDouble(tempstr);
					tempstr = st.nextToken();
					g = Double.parseDouble(tempstr);
					tempstr = st.nextToken();
					b = Double.parseDouble(tempstr);
				}
				else
				{
					System.out.println("Expecting COLOR line in file "
							+ fileName);
					System.exit(1);
				}
				if(st.nextToken().equals("AMBIENT"))
				{
					amb = Double.parseDouble(st.nextToken());
				}
				else
				{
					System.out.println("Expecting AMBIENT line in file "
							+ fileName);
					System.exit(1);
				}
				if(st.nextToken().equals("DIFFUSE"))
				{
					diff = Double.parseDouble(st.nextToken());
				}
				else
				{
					System.out.println("Expecting DIFFUSE line in file "
							+ fileName);
					System.exit(1);
				}
				if(st.nextToken().equals("SPECULARCOEFFICIENT"))
				{
					specCo = Double.parseDouble(st.nextToken());
				}
				else
				{
					System.out.println("Expecting SPECULARCOEFFICIENT line in file "
							+ fileName);
					System.exit(1);
				}
				if(st.nextToken().equals("SPECULAREXPONENT"))
				{
					specExp = Double.parseDouble(st.nextToken());
				}
				else
				{
					System.out.println("Expecting SPECULAREXPONENT line in file "
							+ fileName);
					System.exit(1);
				}
				s[i] = new Sphere(rad, cen, r, g, b, amb, diff, specCo, specExp);
			}
			
			line = objFileBR.readLine(); 
			st = new StringTokenizer(line, " ");
			tempstr = st.nextToken();
			if (tempstr.equals("LIGHT")) {
				tempstr = st.nextToken();
				if (!tempstr.equals("LIST")) {
					System.out.println("Expecting LIGHT LIST line in file "
							+ fileName);
					System.exit(1);
				}
			} 
			else
			{
				System.out.println("Expecting LIGHT LIST line in file "
						+ fileName);
				System.exit(1);
			}
			for(int i = 0; i < l.length; i++)
			{
				double intensity = 0, r = 0, g = 0, b = 0, x = 0, y = 0, z = 0;
				line = objFileBR.readLine(); 
				st = new StringTokenizer(line, " ");
				if(st.nextToken().equals("INTENSITY"))
				{
					intensity = Double.parseDouble(st.nextToken());
				}
				else
				{
					System.out.println("Expecting INTENSITY line in file "
							+ fileName);
					System.exit(1);
				}
				if(st.nextToken().equals("COLOR"))
				{
					tempstr = st.nextToken();
					r = Double.parseDouble(tempstr);
					tempstr = st.nextToken();
					g = Double.parseDouble(tempstr);
					tempstr = st.nextToken();
					b = Double.parseDouble(tempstr);
				}
				else
				{
					System.out.println("Expecting COLOR line in file "
							+ fileName);
					System.exit(1);
				}
				if(st.nextToken().equals("LOCATION"))
				{
					tempstr = st.nextToken();
					x = Double.parseDouble(tempstr);
					tempstr = st.nextToken();
					y = Double.parseDouble(tempstr);
					tempstr = st.nextToken();
					z = Double.parseDouble(tempstr);
				}
				else
				{
					System.out.println("Expecting LOCATION line in file "
							+ fileName);
					System.exit(1);
				}
				l[i] = new PointLight(intensity, r, g, b, new Vertex(x, y, z));
			}
			
			line = objFileBR.readLine(); 
			st = new StringTokenizer(line, " ");
			tempstr = st.nextToken();
			if (tempstr.equals("AMBIENT")) {
				tempstr = st.nextToken();
				if (!tempstr.equals("LIGHT")) {
					System.out.println("Expecting AMBIENT LIGHT line in file "
							+ fileName);
					System.exit(1);
				}
				
			} 
			else
			{
				System.out.println("Expecting AMBIENT LIGHT line in file "
						+ fileName);
				System.exit(1);
			}
			
			double intensity=0, r=0, g=0, b=0;
			line = objFileBR.readLine(); 
			st = new StringTokenizer(line, " ");
			if(st.nextToken().equals("INTENSITY"))
			{
				intensity = Double.parseDouble(st.nextToken());
			}
			else
			{
				System.out.println("Expecting INTENSITY line in file "
						+ fileName);
				System.exit(1);
			}
			if(st.nextToken().equals("COLOR"))
			{
				r = Double.parseDouble(st.nextToken());
				g = Double.parseDouble(st.nextToken());
				b = Double.parseDouble(st.nextToken());
			}
			else
			{
				System.out.println("Expecting COLOR line in file "
						+ fileName);
				System.exit(1);
			}
			ambient = new AmbientLight(intensity,r,g,b);
		}
		catch (FileNotFoundException fnfe) 
		{
			System.out.println("File not found");
		} 
		catch (IOException ioe) 
		{
			System.out.println("couldn't read from file");
		}
	}
	public static void main(String[] args)
	{
		readFile();
		int resolution = 2000;
		RGBPixel jpgFile[][] = new RGBPixel[resolution][resolution];
		double step = 2.0/((double)resolution);
		Vertex scan = new Vertex(-1, -1, 0);
		Vertex eye = new Vertex(0,0,-1);
		
		for(int i = 0; i < resolution; i++)
		{
			for(int j = 0; j < resolution; j++)
			{
				Vector rayDirection = scan.subtractVertices(eye).convertToVector();
				rayDirection.normalize();
				Ray traceRay = new Ray(rayDirection, scan);
				jpgFile[i][j] = traceRay.rayTrace(6);
				
				scan.x += step;
			}
			scan.x = -1;
			scan.y += step;
		}
		try {
			JPGAndRGBPixelArray.writeImage(jpgFile, "RayTracedImage.jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

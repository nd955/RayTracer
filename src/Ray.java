import java.util.ArrayList;


public class Ray{
	
	private Vector direction;
	private Vertex startingPos;

	public Ray(Vector d, Vertex s)
	{
		direction = d;
		startingPos = s;
		
		direction.normalize();
	}
	
	public Ray negate()
	{
		return new Ray(direction.negate(), startingPos);
	}
	public boolean intersectsPolygon(Polygon poly)
	{
		boolean intersects = false;
		Vertex verts[] = poly.getVertexList();
		
		Vertex p2MinusP1 = verts[1].subtractVertices(verts[0]);
		Vertex p3MinusP1 = verts[2].subtractVertices(verts[0]);
		
		Vector normal = p2MinusP1.convertToVector().crossProduct(p3MinusP1.convertToVector());
		normal.normalize();
		if(normal.dotProduct(direction) > 0)
		{
			normal.negate();
		}
		
		//ray parallel to plane
		if(normal.dotProduct(direction) == 0)
			return false;
		
		double d = -(normal.x * verts[0].x + normal.y * verts[0].y + normal.z * verts[0].z);
		double s = -(normal.dotProduct(startingPos.convertToVector()) + d)/(normal.dotProduct(direction));
		
		//ray does not intersect plane
		if(s < 0)
			return false;
		
		Vertex intersectionPoint = new Vertex(direction.x * s + startingPos.x, direction.y * s + startingPos.y, direction.z * s + startingPos.z);
		
		char ignoredDim;
		if(normal.x > normal.y &&  normal.x > normal.z)
		{
			ignoredDim = 'x';
		}
		else if(normal.y > normal.x && normal.y > normal.z)
		{
			ignoredDim = 'y';
		}
		else
		{
			ignoredDim = 'z';
		}
		
		ArrayList<TwoDimensionalPoint> newPoints = new ArrayList<TwoDimensionalPoint>();
		
		for(Vertex polyVert : poly.getVertexList())
		{			
			if(ignoredDim == 'x')
			{
				newPoints.add(new TwoDimensionalPoint(polyVert.y - intersectionPoint.y, polyVert.z - intersectionPoint.z));
			}
			if(ignoredDim == 'y')
			{
				newPoints.add(new TwoDimensionalPoint(polyVert.x - intersectionPoint.x, polyVert.z - intersectionPoint.z));
			}
			if(ignoredDim == 'z')
			{
				newPoints.add(new TwoDimensionalPoint(polyVert.x - intersectionPoint.x, polyVert.y - intersectionPoint.y));
			}
		}
		
		ArrayList<Edge> edgeList = new ArrayList<Edge>();
		
		for(int i = 0; i < newPoints.size(); i++)
		{
			if(i == newPoints.size() - 1)
			{
				edgeList.add(new Edge(newPoints.get(i), newPoints.get(0)));
			}
			else
			{
				edgeList.add(new Edge(newPoints.get(i), newPoints.get(i + 1)));
			}
		}
		
		int count = 0;
		TwoDimensionalPoint origin = new TwoDimensionalPoint(0, 0);
		
		for(Edge e : edgeList)
		{
			if(e.horizontalCross(origin))
				count++;
		}
		
		if(count % 2 == 0)
			intersects = false;
		else
			intersects = true;
		
		return intersects;
	}
	public Ray computeNormalRayPolygon(Polygon poly)
	{
		Vertex verts[] = poly.getVertexList();
		Vertex p2MinusP1 = verts[1].subtractVertices(verts[0]);
		Vertex p3MinusP1 = verts[2].subtractVertices(verts[0]);
		
		Vector normal = p2MinusP1.convertToVector().crossProduct(p3MinusP1.convertToVector());
		normal.normalize();
		if(normal.dotProduct(direction) > 0)
		{
			normal.negate();
		}
		
		//ray parallel to plane
		if(normal.dotProduct(direction) == 0)
			return null;
		
		double d = -(normal.x * verts[0].x + normal.y * verts[0].y + normal.z * verts[0].z);
		double s = -(normal.dotProduct(startingPos.convertToVector()) + d)/(normal.dotProduct(direction));
		
		//ray does not intersect plane
		if(s < 0)
			return null;
		
		Vertex intersectionPoint = new Vertex(direction.x * s + startingPos.x, direction.y * s + startingPos.y, direction.z * s + startingPos.z);
		return new Ray(normal, intersectionPoint);
	}
	public double computeSPolygon(Polygon poly)
	{
		Vertex verts[] = poly.getVertexList();
		Vertex p2MinusP1 = verts[1].subtractVertices(verts[0]);
		Vertex p3MinusP1 = verts[2].subtractVertices(verts[0]);
		
		Vector normal = p2MinusP1.convertToVector().crossProduct(p3MinusP1.convertToVector());
		normal.normalize();
		if(normal.dotProduct(direction) > 0)
		{
			normal.negate();
		}
		
		double d = -(normal.x * verts[0].x + normal.y * verts[0].y + normal.z * verts[0].z);
		double s = -(normal.dotProduct(startingPos.convertToVector()) + d)/(normal.dotProduct(direction));
		
		return s;
	}
	public boolean intersectsSphere(Sphere sphere)
	{
		boolean intersects = false;
		Vertex pCMinusPO = sphere.getCenter().subtractVertices(startingPos);
		double c0 = (Math.pow(pCMinusPO.convertToVector().magnitude(), 2) - (sphere.getRadius() * sphere.getRadius()));
		double c1 = -2 * (direction.dotProduct(pCMinusPO.convertToVector()));
		//double c2 = 1;	=> don't need this in any of our calculations
		
		double discriminant = c1 * c1 - 4 * c0;
		
		if(discriminant < 0)
			intersects = false;
		else 
			intersects = true;
		
		return intersects;
	}
	public Ray computeNormalRaySphere(Sphere sphere)
	{
		Vertex pCMinusPO = sphere.getCenter().subtractVertices(startingPos);
		double c0 = (Math.pow(pCMinusPO.convertToVector().magnitude(), 2) - (sphere.getRadius() * sphere.getRadius()));
		double c1 = -2 * (direction.dotProduct(pCMinusPO.convertToVector()));
		//double c2 = 1;	=> don't need this in any of our calculations
		double s;
		
		double discriminant = c1 * c1 - 4 * c0;
		
		if(discriminant < 0)
			return null;
		else if(discriminant == 0)
			s = -c1 / 2;
		else
		{
			if((-c1 + Math.sqrt(c1 * c1 - 4 * c0))/2 >= 0)
			{
				if((-c1 - Math.sqrt(c1 * c1 - 4 * c0))/2 < 0)
				{
					s = (-c1 + Math.sqrt(c1 * c1 - 4 * c0))/2;
				}
				else if((-c1 + Math.sqrt(c1 * c1 - 4 * c0))/2 > (-c1 - Math.sqrt(c1 * c1 - 4 * c0))/2)
				{
					s = (-c1 - Math.sqrt(c1 * c1 - 4 * c0))/2;
				}
				else
				{
					s = (-c1 + Math.sqrt(c1 * c1 - 4 * c0))/2;
				}
			}
			else
			{
				s = (-c1 - Math.sqrt(c1 * c1 - 4 * c0))/2;
			}
		}
		
		double xIntersect, yIntersect, zIntersect;
		xIntersect = startingPos.x + direction.x * s;
		yIntersect = startingPos.y + direction.y * s;
		zIntersect = startingPos.z + direction.z * s;
		
		Vector normal = new Vector((xIntersect - sphere.getCenter().x)/sphere.getRadius(),(yIntersect - sphere.getCenter().y)/sphere.getRadius(),(zIntersect - sphere.getCenter().z)/sphere.getRadius());
		normal.normalize();
		return new Ray(normal, new Vertex(xIntersect, yIntersect, zIntersect));
	}
	public double computeSSphere(Sphere sphere)
	{
		Vertex pCMinusPO = sphere.getCenter().subtractVertices(startingPos);
		double c0 = (Math.pow(pCMinusPO.convertToVector().magnitude(), 2) - (sphere.getRadius() * sphere.getRadius()));
		double c1 = -2 * (direction.dotProduct(pCMinusPO.convertToVector()));
		//double c2 = 1;	=> don't need this in any of our calculations
		double s;
		
		double discriminant = c1 * c1 - 4 * c0;
		
		if(discriminant == 0)
			s = -c1 / 2;
		else if(discriminant < 0)
			s = Double.NEGATIVE_INFINITY;
		else
		{
			if((-c1 + Math.sqrt(c1 * c1 - 4 * c0))/2 >= 0)
			{
				if((-c1 - Math.sqrt(c1 * c1 - 4 * c0))/2 < 0)
				{
					s = (-c1 + Math.sqrt(c1 * c1 - 4 * c0))/2;
				}
				else if((-c1 + Math.sqrt(c1 * c1 - 4 * c0))/2 > (-c1 - Math.sqrt(c1 * c1 - 4 * c0))/2)
				{
					s = (-c1 - Math.sqrt(c1 * c1 - 4 * c0))/2;
				}
				else
				{
					s = (-c1 + Math.sqrt(c1 * c1 - 4 * c0))/2;
				}
			}
			else
			{
				s = (-c1 - Math.sqrt(c1 * c1 - 4 * c0))/2;
			}
		}
		return s;
	}
	public Surface findClosestObject()
	{
		Surface closest = null;
		double s = Double.POSITIVE_INFINITY;
		for(Polygon poly : ReadObjects.p)
		{
			if(this.intersectsPolygon(poly))
			{
				double sCandidate = this.computeSPolygon(poly);
				if(sCandidate < s && sCandidate != Double.NEGATIVE_INFINITY)
				{
					s = sCandidate;
					closest = poly;
				}
			}
		}
		for(Sphere sph : ReadObjects.s)
		{
			if(this.intersectsSphere(sph))
			{
				double sCandidate = this.computeSSphere(sph);
				if(sCandidate < s && sCandidate != Double.NEGATIVE_INFINITY)
				{
					s = sCandidate;
					closest = sph;
				}
			}
		}
		
		return closest;
	}
	public Vector getDirection()
	{
		return direction;
	}
	public Vertex getStartingPos()
	{
		return startingPos;
	}
	
	//assumes computing from a normal ray
	public Ray computeReflectedRay(Ray inputRay)
	{
		double coefficient = 2 * (inputRay.getDirection().dotProduct(direction));
		Vector reflectedVector = new Vector(coefficient * direction.x, coefficient * direction.y, coefficient * direction.z);
		reflectedVector = reflectedVector.subtractVectors(inputRay.getDirection());
		reflectedVector.normalize();
		Ray refRay = new Ray(reflectedVector, startingPos);
		return refRay;	
	}
	
	public boolean isUnobstructed(Object startingObj, PointLight light)
	{
			boolean unobstructed = false;
			Surface object = findClosestObject();
			Ray normalRay = computeNormalRay(object);
			if(object == null || object == startingObj)
				unobstructed = true;
			else if(normalRay != null)
			{
				double distance = light.getLocation().subtractVertices(startingPos).convertToVector().magnitude();
				double distanceToObject = computeNormalRay(object).getStartingPos().subtractVertices(startingPos).convertToVector().magnitude();
				if(distanceToObject > distance)
					unobstructed = true;
			}
			
			return unobstructed;
	}
	
	public Ray computeNormalRay(Surface obj)
	{
		if(obj != null)
		{
			if(obj.isA() == 's')
				return computeNormalRaySphere((Sphere) obj);
			else
				return computeNormalRayPolygon((Polygon) obj);
		}
		else
			return null;
	}
	public boolean intersects(Surface obj)
	{
		if(obj.isA() == 's')
			return intersectsSphere((Sphere) obj);
		else
			return intersectsPolygon((Polygon) obj);
	}
	public double computeS(Surface obj)
	{
		if(obj.isA() == 's')
			return computeSSphere((Sphere) obj);
		else
			return computeSPolygon((Polygon) obj);
	}
	
	public RGBPixel rayShade(Surface object, int depth)
	{
		Ray point = computeNormalRay(object);
		Vertex eye = new Vertex(0,0,-1);
		RGBPixel shade;
		double r=0,g=0,b=0;
		double pLSumR=ReadObjects.ambient.getR() * object.getAmbient() * object.getRed(), 
				pLSumG=ReadObjects.ambient.getG() * object.getAmbient() * object.getGreen(),
				pLSumB=ReadObjects.ambient.getB() * object.getAmbient() * object.getBlue();

		for(PointLight light : ReadObjects.l)
		{
			if(point != null)
			{
				Vector lightDirection = (light.getLocation().subtractVertices(point.getStartingPos())).convertToVector();
				lightDirection.normalize();
				Ray lightRay = new Ray(lightDirection, point.getStartingPos());
				double s = light.getLocation().subtractVertices(point.getStartingPos()).convertToVector().magnitude();
				double fradatten = (double)(1/(1 + s + 0.01 * s * s));
				if(point.direction.dotProduct(lightDirection) >= 0 && lightRay.isUnobstructed(object, light))
				{
					Vector pointToEye = eye.subtractVertices(point.getStartingPos()).convertToVector();
					pointToEye.normalize();
					pLSumR += fradatten * light.getR() * (object.getDiffuse() * object.getRed() * 
							lightDirection.dotProduct(point.getDirection()) + object.getSpecularCoeff() * 
							Math.pow(Math.abs(pointToEye.dotProduct(point.computeReflectedRay(lightRay).getDirection())), object.getSpecularExponent()));
					pLSumG += fradatten * light.getG() * (object.getDiffuse() * object.getGreen() * 
							lightDirection.dotProduct(point.getDirection()) + object.getSpecularCoeff() * 
							Math.pow(Math.abs(pointToEye.dotProduct(point.computeReflectedRay(lightRay).getDirection())), object.getSpecularExponent()));
					pLSumB += fradatten * light.getB() * (object.getDiffuse() * object.getBlue() * 
							lightDirection.dotProduct(point.getDirection()) + object.getSpecularCoeff() * 
							Math.pow(Math.abs(pointToEye.dotProduct(point.computeReflectedRay(lightRay).getDirection())), object.getSpecularExponent()));
					if(depth > 0)
					{
						RGBPixel newPixel = point.computeReflectedRay(lightRay).rayTrace(depth - 1);
						r += object.getSpecularCoeff() * newPixel.getRed();
						g += object.getSpecularCoeff() * newPixel.getGreen();
						b += object.getSpecularCoeff() * newPixel.getBlue();
					}
				}
			}
		}

		r += pLSumR;
		g += pLSumG;
		b += pLSumB;
		
		shade = new RGBPixel((int)(255*r),(int)(255*g),(int)(255*b));
		return shade;
	}
	
	public RGBPixel rayTrace(int depth)
	{
		if(depth > 0)
		{
			Surface object = findClosestObject();
			
			if(object != null)
			{
				return rayShade(object, depth);
			}
			else
				return new RGBPixel(0,0,0);
		}
		else
		{
			Surface object = findClosestObject();
			
			if(object != null)
			{
				return new RGBPixel((int)(255 * object.getRed()), (int)(255 * object.getBlue()), (int)(255 * object.getGreen()));
			}
			else
				return new RGBPixel(0,0,0);
		}
	}
}

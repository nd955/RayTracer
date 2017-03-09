public class Edge {
	
	TwoDimensionalPoint p1, p2;
	
	public Edge(TwoDimensionalPoint point1, TwoDimensionalPoint point2)
	{
		//sets the lower point as p1, higher as p2
		if(point1.getV() < point2.getV())
		{
			p1 = point1;
			p2 = point2;
		}
		else
		{
			p1 = point2;
			p2 = point1;
		}
	}
	
	public boolean horizontalCross(TwoDimensionalPoint p)
	{
		boolean crosses = false;
		
		if(p1.getU() > 0 || p2.getU() > 0) //only checks one side of the polygon
		{
			if(p.getV() > p1.getV() && p.getU() < p2.getV())
			{
				crosses = true;
			}
		}
		
		return crosses;
	}
}

package implementation;

import interfaces.Vertex;

import java.util.HashMap;

public class Edge 
{
	private Vertex source;
	private Vertex target;
	private HashMap<String, Integer> attributes;
	
	public Edge(Vertex v1, Vertex v2)
	{
		source = v1;
		target = v2;
		
		attributes = new HashMap<String, Integer>();
	}
	
	public void setAttribute(String name, int value)
	{
		attributes.put(name, value);
	}
	
	public Integer getAttributeValue(String name)
	{
		if(attributes.containsKey(name))
		{
			return attributes.get(name);
		}
		else
		{
			return null;
		}
	}
	
	public Vertex getSource()
	{
		return source;
	}
	
	public Vertex getTarget()
	{
		return target;
	}
}

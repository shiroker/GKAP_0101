package implementation;

import interfaces.Vertex;

import java.util.HashMap;

public class VertexImpl implements Vertex
{
	private String _name;
	private HashMap<String, Integer> attributes;
	
	private VertexImpl(String name)
	{
		_name = name;
		
		attributes = new HashMap<String, Integer>();
	}
	
	public static Vertex createV(String name)
	{
		return new VertexImpl(name);
	}
	
	public void setAttribute(String name, int value)
	{
		attributes.put(name, value);
	}
	
	@Override
	public boolean equals(Object o)
	{
		boolean result = false;
		
		if(o instanceof VertexImpl)
		{
			VertexImpl that = (VertexImpl) o;
			
			if(this.getName().equals(that.getName()))
			{
				result = true;
			}
		}
		
		return result;
	}
	
	@Override
	public String toString()
	{
		return this.getName();
	}
	
	public String getName()
	{
		return _name;
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
}
package interfaces;

import implementation.VertexImpl;

public interface Vertex 
{
	/*
	 * Erzeugt neue Ecke, dessen Name der Eingabestring ist. Ecke wird ohne externe
	 * Attribute erstellt.
	 */
	public static Vertex createV(String name)
	{
		return VertexImpl.createV(name);
	}
	
	
	/*
	 * 
	 */
	public Integer getAttributeValue(String name);
	
	/*
	 * 
	 */
	public String getName();
	
	/*
	 * 
	 */
	void setAttribute(String name, int value);
}

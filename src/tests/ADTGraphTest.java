package tests;

import interfaces.ADTGraph;
import interfaces.ADTMatrix;
import interfaces.Vertex;

import java.util.ArrayList;
import java.util.Collections;

import junit.framework.TestCase;

public class ADTGraphTest extends TestCase
{

	public void testCreateG()
	{
		Vertex v1 = Vertex.createV("v");
		ADTGraph g = ADTGraph.createG(v1);
		
		assertTrue(g.getVertexes().contains(v1) && g.getVertexes().size() == 1);
	}
	

	public void testAddVertex() 
	{
		Vertex v1 = Vertex.createV("v1");
		Vertex v2 = Vertex.createV("v2");
		ADTGraph g = ADTGraph.createG(v1);
		
		assertTrue(!g.getVertexes().contains(v2)); //Knoten darf noch nicht drin sein
		g.addVertex(v2);
		assertTrue(g.getVertexes().contains(v2)); //Knoten muss drin sein
		g.addVertex(v2);
		assertTrue(1 == Collections.frequency(g.getVertexes(), v2)); //Knoten darf nicht doppelt vorkommen
	}
	

	public void testDeleteVertex()
	{
		Vertex v = Vertex.createV("v");
		ADTGraph g = ADTGraph.createG(v);
		g.deleteVertex(v);
		assertTrue(g.getVertexes().contains(v)); //den letzten Knoten duerfen wir nicht loeschen
		
		Vertex v2 = Vertex.createV("v2");
		g.addVertex(v2);
		g.deleteVertex(v2);
		assertTrue(!g.getVertexes().contains(v2));
	}
	

	public void testAddEdge()
	{
		Vertex v1 = Vertex.createV("v1");
		Vertex v2 = Vertex.createV("v2");
		ADTGraph g = ADTGraph.createG(v1);
		
		g.addEdge(v1, v2);
		assertTrue(g.getEdges().get(0).equals(v1) && g.getEdges().get(1).equals(v2));
	}
	

	public void testDeleteEdge()
	{
		Vertex v1 = Vertex.createV("v1");
		Vertex v2 = Vertex.createV("v2");
		ADTGraph g = ADTGraph.createG(v1);
		
		g.addEdge(v1, v2);
		g.deleteEdge(v1, v2);
		assertTrue(g.getEdges().isEmpty());
	}
	

	public void testSetAtE()
	{
		Vertex v1 = Vertex.createV("v1");
		Vertex v2 = Vertex.createV("v2");
		ADTGraph g = ADTGraph.createG(v1);
		
		g.addEdge(v1, v2);
		g.setAtE(v1, v2, "cost", 50);
		assertTrue(50 == g.getValE(v1, v2, "cost"));
		g.setAtE(v1, v2, "cost", 25);
		assertTrue(25 == g.getValE(v1, v2, "cost"));
	}
	

	public void testSetAtV()
	{
		Vertex v1 = Vertex.createV("v1");
		ADTGraph g = ADTGraph.createG(v1);
		
		g.setAtV(v1, "hans", 10);
		assertTrue(10 == g.getValV(v1, "hans"));
		g.setAtV(v1, "hans", 30);
		assertTrue(30 == g.getValV(v1, "hans"));
	}
	

	public void testGetIncident()
	{
		Vertex v1 = Vertex.createV("v1");
		Vertex v2 = Vertex.createV("v2");
		Vertex v3 = Vertex.createV("v3");
		ADTGraph g = ADTGraph.createG(v1);
		
		g.addEdge(v1, v2);
		g.addEdge(v1, v3);
		g.addEdge(v3, v1);
		
		ArrayList<Vertex> incident = g.getIncident(v1);
		
		assertTrue(incident.get(0).equals(v1));
		assertTrue(incident.get(1).equals(v2));
		assertTrue(incident.get(2).equals(v1));
		assertTrue(incident.get(3).equals(v3));
		assertTrue(incident.get(4).equals(v3));
		assertTrue(incident.get(5).equals(v1));
	}
	

	public void testGetAdjacent()
	{
		Vertex v1 = Vertex.createV("v1");
		Vertex v2 = Vertex.createV("v2");
		Vertex v3 = Vertex.createV("v3");
		ADTGraph g = ADTGraph.createG(v1);
		
		g.addEdge(v1, v2);
		g.addEdge(v1, v3);
		
		assertTrue(g.getAdjacent(v1).get(0).equals(v2));
		assertTrue(g.getAdjacent(v1).get(1).equals(v3));
	}
	

	public void testGetTarget()
	{
		Vertex v1 = Vertex.createV("v1");
		Vertex v2 = Vertex.createV("v2");
		Vertex v3 = Vertex.createV("v3");
		Vertex v4 = Vertex.createV("v4");
		ADTGraph g = ADTGraph.createG(v1);
		
		g.addEdge(v1, v2);
		g.addEdge(v1, v3);
		g.addEdge(v4, v1);
		
		assertTrue(g.getTarget(v1).get(0).equals(v2));
		assertTrue(g.getTarget(v1).get(1).equals(v3));
		assertTrue(!g.getTarget(v1).contains(v4));
	}
	

	public void testGetSource()
	{
		Vertex v1 = Vertex.createV("v1");
		Vertex v2 = Vertex.createV("v2");
		Vertex v3 = Vertex.createV("v3");
		Vertex v4 = Vertex.createV("v4");
		ADTGraph g = ADTGraph.createG(v1);
		
		g.addEdge(v1, v2);
		g.addEdge(v1, v3);
		g.addEdge(v4, v1);
		
		assertTrue(!g.getSource(v1).contains(v2));
		assertTrue(!g.getSource(v1).contains(v3));
		assertTrue(g.getSource(v1).get(0).equals(v4));
	}
	

	public void testGetEdges()
	{
		Vertex v1 = Vertex.createV("v1");
		Vertex v7 = Vertex.createV("v7");
		Vertex v3 = Vertex.createV("v3");
		Vertex v4 = Vertex.createV("v4");
		ADTGraph g = ADTGraph.createG(v1);
		
		g.addEdge(v1, v7);
		g.addEdge(v1, v3);
		g.addEdge(v4, v1);
		
		assertTrue(g.getEdges().get(0).equals(v1));
		assertTrue(g.getEdges().get(1).equals(v7));
		assertTrue(g.getEdges().get(2).equals(v1));
		assertTrue(g.getEdges().get(3).equals(v3));
		assertTrue(g.getEdges().get(4).equals(v4));
		assertTrue(g.getEdges().get(5).equals(v1));
	}
	

	public void testGetVertexes()
	{
		Vertex v1 = Vertex.createV("v1");
		Vertex v2 = Vertex.createV("v2");
		ADTGraph g = ADTGraph.createG(v1);
		g.addVertex(v2);
		
		assertTrue(g.getVertexes().contains(v1));
		assertTrue(g.getVertexes().contains(v2));
	}
	

	public void testGetValE()
	{
		Vertex v1 = Vertex.createV("v1");
		Vertex v2 = Vertex.createV("v2");
		ADTGraph g = ADTGraph.createG(v1);
		
		g.addEdge(v1, v2);
		assertTrue(g.getValE(v1, v2, "cost") == null);
		g.setAtE(v1, v2, "cost", 10);
		assertTrue(g.getValE(v1, v2, "cost") == 10);
	}
	

	public void testGetValV()
	{
		Vertex v1 = Vertex.createV("v1");
		ADTGraph g = ADTGraph.createG(v1);
		
		assertTrue(g.getValV(v1, "cost") == null);
		g.setAtV(v1, "cost", 5);
		assertTrue(g.getValV(v1, "cost") == 5);
		g.setAtV(v1, "cost", 10);
		assertTrue(g.getValV(v1, "cost") == 10);
		g.setAtV(v1, "lachs", 15);
		assertTrue(g.getValV(v1, "lachs") == 15);
	}

	
	public void testEqual()
	{
		// TODO
		Vertex v1 = Vertex.createV("v1");
		Vertex v2 = Vertex.createV("v2");
		Vertex v3 = Vertex.createV("v3");
		Vertex v4 = Vertex.createV("v4");
		ADTGraph g = ADTGraph.createG(v1);
		
		g.addEdge(v1, v2);
		g.addEdge(v1, v3);
		g.addEdge(v4, v1);
		
		Vertex v5 = Vertex.createV("v1");
		Vertex v6 = Vertex.createV("v2");
		Vertex v7 = Vertex.createV("v3");
		Vertex v8 = Vertex.createV("v4");
		ADTGraph g2 = ADTGraph.createG(v5);
		
		g2.addEdge(v5, v7);
		g2.addEdge(v8, v5);
		g2.addEdge(v5, v6);
		
		assertTrue(g.equal(g2));
	}
	
	
	public void testMartix(){
		Vertex v1 = Vertex.createV("v1");
		Vertex v2 = Vertex.createV("v2");
		Vertex v3 = Vertex.createV("v3");
		Vertex v4 = Vertex.createV("v4");
		ADTGraph g = ADTGraph.createG(v2);
		
		g.addEdge(v1, v2);
		g.addEdge(v1, v3);
		g.addEdge(v4, v1);
		
		ADTMatrix mat = g.createM();
		mat.setValM(1, 2, 56);
		assertTrue(mat.indTOvert(0).equals(v2));
		assertTrue(mat.getValM(1, 2) ==(56));
		
	}
}

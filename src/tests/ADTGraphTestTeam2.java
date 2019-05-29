package tests;

import interfaces.ADTGraph;
import interfaces.Vertex;
import junit.framework.TestCase;


public class ADTGraphTestTeam2 extends TestCase
{


	public void testingCreateV() 
	{

		// testingCreateV() �berpr�ft die ordentliche Anlage von Vertexes
		// Herk�mmliche Vertexes erzeugen....
		Vertex V1 = Vertex.createV("A");
		Vertex V2 = Vertex.createV("A");
		Vertex V3 = Vertex.createV("B");
		assertNotNull(V1);
		assertNotNull(V2);
		assertNotNull(V3);
		assertEquals(V1, V2);
		//assertNotEquals(V1, V3);

		// Vertexes mit Leerzeichen im Namen erzeugen
		// Gleichheit wie oben
		V1 = Vertex.createV("A 2");
		V2 = Vertex.createV("A 2");
		V3 = Vertex.createV("B 2");
		assertNotNull(V1);
		assertNotNull(V2);
		assertNotNull(V3);
		assertEquals(V1, V2);
		//assertNotEquals(V1, V3);

		// Vertexes mit Sonderzeichen im Namen
		V1 = Vertex.createV("A,2");
		V2 = Vertex.createV("A,2");
		V3 = Vertex.createV("B,2");
		assertNotNull(V1);
		assertNotNull(V2);
		assertNotNull(V3);
		assertEquals(V1, V2);
		//assertNotEquals(V1, V3);

		// - leerer String ""
		V1 = Vertex.createV("");
		V2 = Vertex.createV("");
		V3 = Vertex.createV("B,2");
		assertNotNull(V1);
		assertNotNull(V2);
		assertNotNull(V3);
		assertEquals(V1, V2);
		//assertNotEquals(V1, V3);

		// - Vertexname aus Sonderzeichen
		V1 = Vertex.createV(",.#");
		V2 = Vertex.createV(",.#");
		V3 = Vertex.createV("+#+*");
		assertNotNull(V1);
		assertNotNull(V2);
		assertNotNull(V3);
		assertEquals(V1, V2);
		//assertNotEquals(V1, V3);
	}


	public void testingCreateG() 
	{
		// Erzeugung von Graphen, mit herk�mmlicher Ecke
		Vertex V1 = Vertex.createV("aName");
		ADTGraph G1 = ADTGraph.createG(V1);
		assertNotNull(G1);
		assertEquals(1, G1.getVertexes().size());
		assertEquals(V1, G1.getVertexes().get(0));

		// Erzeugung von Graphen, mit Sonderzeichen in Eckennamen
		Vertex VS = Vertex.createV("Y@XXB%");
		ADTGraph G2 = ADTGraph.createG(VS);
		assertNotNull(G2);
		assertEquals(1, G2.getVertexes().size());
		assertEquals(VS, G2.getVertexes().get(0));

		// Erzeugung von Graphen, mit leeren Eckennamen
		Vertex VL = Vertex.createV("");
		ADTGraph G3 = ADTGraph.createG(VL);
		assertNotNull(G3);
		assertEquals(1, G3.getVertexes().size());
		assertEquals(VL, G3.getVertexes().get(0));
	}


	public void testingAddVertex() {
		// Erzeugnung von Graphen, mit herk�mmlichen Ecken
		// Test, ob Ecken ordentlich in Graphen gespeichert werden
		Vertex V1 = Vertex.createV("A");
		Vertex V2 = Vertex.createV("B");
		ADTGraph G1 = ADTGraph.createG(V1);
		assertNotNull(G1);
		assertEquals(1, G1.getVertexes().size());
		assertEquals(true, G1.getVertexes().contains(V1));
		G1.addVertex(V2);
		assertEquals(2, G1.getVertexes().size());
		assertEquals(true, G1.getVertexes().contains(V1));
		assertEquals(true, G1.getVertexes().contains(V2));

		// Erzeugung von Graphen, mit Sonderzeichen in Eckennamen
		Vertex VS1 = Vertex.createV("Y@B%");
		Vertex VS2 = Vertex.createV("Y@C%B");
		ADTGraph G2 = ADTGraph.createG(VS1);
		assertNotNull(G2);
		assertEquals(1, G2.getVertexes().size());
		assertEquals(true, G2.getVertexes().contains(VS1));
		G2.addVertex(VS2);
		assertEquals(2, G2.getVertexes().size());
		assertEquals(true, G2.getVertexes().contains(VS1));
		assertEquals(true, G2.getVertexes().contains(VS2));

		// Erzeugung von Graphen, mit leeren Eckennamen
		Vertex VL1 = Vertex.createV("");
		Vertex VL2 = Vertex.createV("_");
		ADTGraph G3 = ADTGraph.createG(VL1);
		assertNotNull(G3);
		assertEquals(1, G3.getVertexes().size());
		assertEquals(true, G3.getVertexes().contains(VL1));
		G3.addVertex(VL2);
		assertEquals(2, G1.getVertexes().size());
		assertEquals(true, G3.getVertexes().contains(VL1));
		assertEquals(true, G3.getVertexes().contains(VL2));
	}


	public void testingDeleteVertex() {
		// Erzegung eines korrekten Graphen
		Vertex V1 = Vertex.createV("A");
		Vertex V2 = Vertex.createV("B");
		ADTGraph G1 = ADTGraph.createG(V1);
		G1.addVertex(V2);
		// �berpr�fung, ob beide Ecken im Graphen enthalten sind
		assertEquals(2, G1.getVertexes().size());
		assertEquals(true, G1.getVertexes().contains(V1));
		assertEquals(true, G1.getVertexes().contains(V2));
		// Nach L�schen von einer Ecke, muss die "enthalten_in-Abfrage" false
		// ergeben
		G1.deleteVertex(V1);
		assertEquals(1, G1.getVertexes().size());
		assertEquals(false, G1.getVertexes().contains(V1));
		assertEquals(true, G1.getVertexes().contains(V2));
		// gel�schte Ecke wieder hinzugef�gt, um V2 zu l�schen, erneute
		// �berpr�fung des nicht-/ oder enthalten.
		G1.addVertex(V1);
		G1.deleteVertex(V2);
		assertEquals(1, G1.getVertexes().size());
		assertEquals(true, G1.getVertexes().contains(V1));
		assertEquals(false, G1.getVertexes().contains(V2));

		// Nicht vorhandene Ecke wird gel�scht, erneute Pr�fung des nicht-/ oder
		// enthalten
		G1.deleteVertex(Vertex.createV("nichtenthalten"));
		assertEquals(1, G1.getVertexes().size());
		assertEquals(true, G1.getVertexes().contains(V1));
		assertEquals(false, G1.getVertexes().contains(V2));

	}


	public void testingAddEdge() {
//Anlage einer Kante zwischen zwei Ecken
		Vertex V1 = Vertex.createV("A");
		Vertex V2 = Vertex.createV("B");
		ADTGraph G1 = ADTGraph.createG(V1);
		G1.addVertex(V2);
//Anlage der Kante zwischen V1 und V2
		G1.addEdge(V1, V2);
//getEdges liefert die Kombination V1-V2 zur�ck	
		assertEquals(2, G1.getEdges().size());
		assertEquals(V1, G1.getEdges().get(0));
		assertEquals(V2, G1.getEdges().get(1));
		
	}



	 public void testingDeleteEdge() {
	 Vertex V1=Vertex.createV("A");
	 Vertex V2=Vertex.createV("B");
	 ADTGraph G1=ADTGraph.createG(V1);
	 G1.addVertex(V2);
	
	 G1.addEdge(V1, V2);
	 G1.addEdge(V2, V1);
	
	
	 assertEquals(4,G1.getEdges().size());
	 assertEquals(V1,G1.getEdges().get(0));
	 assertEquals(V2,G1.getEdges().get(1));
	 assertEquals(V2,G1.getEdges().get(2));
	 assertEquals(V1,G1.getEdges().get(3));
	
	 G1.deleteEdge(V2, V1);
	 assertEquals(2,G1.getEdges().size());
	 assertEquals(V1,G1.getEdges().get(0));
	 assertEquals(V2,G1.getEdges().get(1));
	
	 G1.deleteEdge(V1, V2);
	 assertEquals(0,G1.getEdges().size());
	
	 }


	 public void testingSetAtE() {
		 // Test ob bestimmte Key-Value-Paare korrekt ge und �berschrieben
	 Vertex V1=Vertex.createV("A");
	 Vertex V2=Vertex.createV("B");
	 ADTGraph G1=ADTGraph.createG(V1);
	 G1.addVertex(V2);
	
	 G1.addEdge(V1, V2);
	
	
	 G1.setAtE(V1, V2, "key", 22);
	 G1.setAtE(V1, V2, "key@,?X", 42);
	
	 G1.setAtE(V2, V1, "key", 22);
	 G1.setAtE(V2, V1, "key@,?X", 42);
	
	 G1.setAtE(V1, V2, "key", 21);
	
	
	 assertEquals(21, (int)G1.getValE(V1, V2, "key"));
	 assertEquals(42, (int)G1.getValE(V1, V2, "key@,?X"));
	// Key nicht vorhanden, Returnwert soll null sein.
	 assertNull(G1.getValE(V1, V2, "Keynichtenthalten"));
	
	 }


	 public void testingSetAtV() {
		 // Test ob bestimmte Key-Value-Paare korrekt ge und �berschrieben
	 Vertex V1=Vertex.createV("A");
	 Vertex V2=Vertex.createV("B");
	 ADTGraph G1=ADTGraph.createG(V1);
	
	 G1.setAtV(V1, "key", 22);
	 G1.setAtV(V1, "key@,?X", 42);
	 G1.setAtV(V2, "key", 42);
	 G1.setAtV(V1, "key", 21);
	
	 assertEquals(21, (int) G1.getValV(V1, "key"));
	 assertEquals(42, (int) G1.getValV(V1, "key@,?X"));
		// Key nicht enthalten, Returnwert soll null sein.
	 assertNull(G1.getValV(V1, "nichtenthalten"));
	 }



	public void testingExportG() {
		// "ExportG noch nicht implementiert"
	}

	public void testingImportG() {
		// ImportG Testfallschreiben"
	}
	
	public void testingGetIncident() {
		// �berpr�fung der Funktion getIncident.
		// erstellt Graphen mit vertex V1, V2, V3
		// zus�tzlich wird eine Kante eingef�gt und anschlie�end �berpr�ft
		Vertex V1 = Vertex.createV("A");
		Vertex V2 = Vertex.createV("B");
		Vertex V3 = Vertex.createV("C");
		ADTGraph G1 = ADTGraph.createG(V1);
		G1.addVertex(V2);
		G1.addVertex(V3);

		G1.addEdge(V1, V2);

		// da es sich um einen ungerichteten Graphen handelt, erwarten wir
		// Kanten mit V1,V2 und V2,V1
		assertEquals(2, G1.getIncident(V1).size());
		assertEquals(V1, G1.getIncident(V1).get(0));
		assertEquals(V2, G1.getIncident(V1).get(1));

		assertEquals(2, G1.getIncident(V2).size());
		assertEquals(V1, G1.getIncident(V2).get(0));
		assertEquals(V2, G1.getIncident(V2).get(1));

		assertEquals(0, G1.getIncident(V3).size());

	}

	public void testingGetAdjacent() {
		// �berpr�fung der Funktion getAdjacent.
		// erstellt Graphen mit vertex V1, V2, V3
		// zus�tzlich wird eine Kante eingef�gt und anschlie�end �berpr�ft
		// Alle benachbarten Ecken sollen einfach geliefert werden.
		Vertex V1 = Vertex.createV("A");
		Vertex V2 = Vertex.createV("B");
		Vertex V3 = Vertex.createV("C");
		ADTGraph G1 = ADTGraph.createG(V1);
		G1.addVertex(V2);
		G1.addVertex(V3);

		G1.addEdge(V1, V2);

		// Die Ecken V1 und V2 haben jeweils einen Nachbarn.
		assertEquals(1, G1.getAdjacent(V1).size());
		assertEquals(V2, G1.getAdjacent(V1).get(0));

		assertEquals(1, G1.getAdjacent(V2).size());
		assertEquals(V1, G1.getAdjacent(V2).get(0));
		// Dritte Ecke, hat keine Nachbarn
		assertEquals(0, G1.getAdjacent(V3).size());

		// Kante zwischen V1, V3 hinzuf�gen
		// Sollte f�r V3 einen Nachbarn ergeben, V1
		G1.addEdge(V1, V3);
		assertEquals(1, G1.getAdjacent(V3).size());
		assertEquals(V1, G1.getAdjacent(V3).get(0));

		//
	}


	 public void testingGetTarget() {
		 // Test f�r Ecken die an einer Kante einen korrekten Nachfolger erhalten sollen
	 Vertex V1=Vertex.createV("A");
	 Vertex V2=Vertex.createV("B");
	 Vertex V3=Vertex.createV("C");
	 ADTGraph G1=ADTGraph.createG(V1);
	 G1.addVertex(V2);
	
	 G1.addEdge(V1, V2);
	
	
	 assertEquals(1, G1.getTarget(V1).size());
	 assertEquals(V2,G1.getTarget(V1).get(0));
	
	 assertEquals(0, G1.getTarget(V2).size());
		// Kein Nachfolger enthalten. Returnwert soll null sein.

     assertTrue(G1.getTarget(V3).isEmpty());
	 }



	 public void testingGetSource() {
		 // Test f�r Ecken die an einer Kante einen korrekten Vorg�nger erhalten sollen
	 Vertex V1=Vertex.createV("A");
	 Vertex V2=Vertex.createV("B");
	 Vertex V3=Vertex.createV("C");
	 ADTGraph G1=ADTGraph.createG(V1);
	 G1.addVertex(V2);
	
	 G1.addEdge(V1, V2);
	
	
	 assertEquals(1, G1.getSource(V2).size());
	 assertEquals(V1,G1.getSource(V2).get(0));
	
	 assertEquals(0, G1.getSource(V1).size());
	// Kein Vorg�nger enthalten. Returnwert soll null sein.
     assertTrue(G1.getSource(V3).isEmpty());
	
	 }


	public void testingGetEdges() {
		// Fortf�hrender Test f�r GetEdges()-Methode
		Vertex V1 = Vertex.createV("A");
		Vertex V2 = Vertex.createV("B");
		Vertex V3 = Vertex.createV("C");
		ADTGraph G1 = ADTGraph.createG(V1);
		ADTGraph G2 = ADTGraph.createG(V1);
		G1.addVertex(V2);
		G2.addVertex(V2);

		G1.addEdge(V1, V2);

		assertEquals(2, G1.getEdges().size());
		assertEquals(true, G1.getEdges().contains(V1));
		assertEquals(true, G1.getEdges().contains(V2));
		// Tests f�r nicht enthaltene Ecke in Kante 
		assertEquals(false, G1.getEdges().contains(V3));
		// Graph ohne Kanten, liefer mit size() 0 zur�ck.
		assertEquals(0, G2.getEdges().size());

	}

	public void testingGetVertexes() {
		// Fortf�hrender Test f�r GetVertexes()-Methode
		Vertex V1 = Vertex.createV("A");
		Vertex V2 = Vertex.createV("B");
		Vertex V3 = Vertex.createV("C");
		ADTGraph G1 = ADTGraph.createG(V1);
		G1.addVertex(V2);

		assertEquals(2, G1.getVertexes().size());
		assertEquals(true, G1.getVertexes().contains(V1));
		assertEquals(true, G1.getVertexes().contains(V2));
		// Test f�r nicht vorhanden sein.
		assertEquals(false,G1.getVertexes().contains(V3));
	}


	 public void testingGetValE() {
		 // wie bei testingSetValE
	 Vertex V1=Vertex.createV("A");
	 Vertex V2=Vertex.createV("B");
	 ADTGraph G1=ADTGraph.createG(V1);
	 G1.addVertex(V2);
	
	 G1.addEdge(V1, V2);
	
	
	 G1.setAtE(V1, V2, "key", 22);
	 G1.setAtE(V1, V2, "key@,?X", 42);
	
	 G1.setAtE(V2, V1, "key", 22);
	 G1.setAtE(V2, V1, "key@,?X", 42);
	
	 G1.setAtE(V1, V2, "key", 21);
	

	 assertEquals(21, (int) G1.getValE(V1, V2, "key"));
	 assertEquals(42, (int) G1.getValE(V1, V2, "key@,?X"));
	 assertNull(G1.getValE(V1, V2, "Keynichtenthalten"));
	 }

	 public void testingGetValV() {
		// wie bei testingSetValV
	 Vertex V1=Vertex.createV("A");
	 Vertex V2=Vertex.createV("B");
	
	 ADTGraph G1=ADTGraph.createG(V1);
	 
	 G1.setAtV(V1, "key", 22);
	 G1.setAtV(V1, "key@,?X", 42);
	 G1.setAtV(V2, "key", 42);
	 G1.setAtV(V1, "key", 21);
	
	 assertEquals(21,(int) G1.getValV(V1, "key"));
	 assertEquals(42,(int) G1.getValV(V1, "key@,?X"));
	 assertNull(G1.getValV(V1, "nichtenthalten"));
	 assertNull(G1.getValV(V2, "nichtenthalten"));
	 }
}

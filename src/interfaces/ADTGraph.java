package interfaces;

import implementation.ADTGraphImpl;

import java.util.ArrayList;

public interface ADTGraph 
{
	/*
	 * Erzeugt einen neuen Graphen, der nur die Eingabeecke besitzt.
	 */
	public static ADTGraph createG(Vertex string)
	{
		return ADTGraphImpl.createG(string);
	}
	
	/*
	 * Fügt dem Graphen die Eingabeecke hinzu. Ist die Ecke bereits vorhanden, bleibt der Graph unverändert
	 */
	void addVertex(Vertex v11);
	
	/*
	 * Entfernt die Eingabeecke und alle ihre Kanten aus dem Graphen. Ist die Ecke
	 * nicht vorhanden oder wird die letzte Ecke gelöscht, bleibt der Graph unverändert.
	 */
	void deleteVertex(Vertex v);
	
	/*
	 * Fügt dem Graphen die Kante zwischen den beiden Eingabeecken hinzu. Die
	 * Reihenfolge der Ecken ist wichtig, bei ungerichteten Graphen werden
	 * automatisch Kanten in beide Richtungen hinzugefügt. Ist die Kante bereits
	 * vorhanden, bleibt der Graph unverändert. Sind Eingabeecken noch nicht im
	 * Graphen vorhanden, werden sie automatisch erzeugt.
	 */
	void addEdge(Vertex v1, Vertex v2);
	
	/*
	 * Entfernt die Kante zwischen den beiden Eingabeecken aus dem Graphen. Die
	 * Reihenfolge der Ecken ist wichtig, bei ungerichteten Graphen werden
	 * automatisch beide Kanten entfernt. Ist die Kante nicht vorhanden, bleibt der
	 * Graph unverändert.
	 */
	void deleteEdge(Vertex v1, Vertex v2);
	
	/*
	 * Fügt der Kante zwischen den Eingabeecken ein Attributspaar hinzu, bestehend
	 * aus Name und Wert. Besteht ein einzutragendes Attribut bereits an der Stelle im
	 * Graphen, wird der Wert überschrieben. Sind Eingabeecken oder die Kante nicht
	 * vorhanden, bleibt der Graph unverändert.
	 */
	void setAtE(Vertex v1, Vertex v2, String name, int value);
	
	/*
	 * Fügt der Eingabeecke ein Attributspaar hinzu, bestehend aus Name und Wert.
	 * Besteht ein einzutragendes Attribut bereits an der Stelle im Graphen, wird der
	 * Wert überschrieben. Ist die Ecke nicht vorhanden, bleibt der Graph unverändert.
	 */
	void setAtV(Vertex v1, String name, int value);
	
	/*
	 * Gibt einen auf der Eingabedatei basierenden Graphen aus. Die Datei muss die
	 * unter #Technische Vorgaben definierte Form haben.
	 */
	public static ADTGraph importG(String filename)
	{
		return ADTGraphImpl.importG(filename);
	}
	
	/*
	 * Erstellt eine auf dem Eingabeadtgraphen basierende Datei. Die Datei hat die
	 * unter #Technische Vorgaben definierte Form.
	 */
	void exportG(String filename);
	
	/*
	 * Gibt eine Vertexliste aller Kanten zurück, die von der Eingabeecke ausgehen. Ist
	 * die Eingabeecke im Graphen nicht vorhanden, wird leere Vertexliste
	 * zurückgegeben.
	 */
	ArrayList<Vertex> getIncident(Vertex v);
	
	/*
	 * Gibt eine Vertexliste aller Ecken zurück, die eine Kante hin oder zurück von der
	 * Eingabeecke besitzen (Gilt also nur für ungerichtete Graphen!). Ist die
	 * Eingabeecke im Graphen nicht vorhanden, wird leere Vertexliste zurückgegeben.
	 */
	ArrayList<Vertex> getAdjacent(Vertex v);
	
	/*
	 * Gibt eine Vertexliste aller Ecken zurück, die direkt von der Eingabeecke (die
	 * source) erreichbar sind (Richtung beachten!). Sind keine vorhanden, wird leere
	 * Vertexliste zurückgegeben.
	 */
	ArrayList<Vertex> getTarget(Vertex v);
	
	/*
	 * Gibt eine Vertexliste aller Ecken zurück, die die Eingabeecke (das target) erreichen
	 * (Richtung beachten!). Sind keine vorhanden, wird leere Vertexliste
	 * zurückgegeben
	 */
	ArrayList<Vertex> getSource(Vertex v);
	
	/*
	 * Gibt eine Vertexliste aller Kanten des Graphen zurück. Hat der Graph keine
	 * Kanten, wird eine leere Liste zurückgegeben
	 */
	ArrayList<Vertex> getEdges();
	
	/*
	 * Gibt eine Liste aller Ecken des Graphen zurück
	 */
	ArrayList<Vertex> getVertexes();
	
	/*
	 * Gibt den zum Eingabenamen gehörigen Attributswert zurück, der an der durch die
	 * Ecken bezeichneten Kante steht. Ist die Kante oder das Attribut nicht vorhanden,
	 * wird NULL zurückgegeben
	 */
	Integer getValE(Vertex v1, Vertex v2, String name);
	
	/*
	 * Gibt den zum Eingabenamen gehörigen Attributswert zurück, der an der
	 * Eingabeecke steht. Ist die Ecke oder das Attribut nicht vorhanden, wird NULL
	 * zurückgegeben.
	 */
	Integer getValV(Vertex v, Vertex v2);
		/*
	 * Liefert true wenn die beiden Graphen strukturgleich sind, sonst false. Denkbar
	 * wäre eine Implementation auf Basis der Vertexlisten.
	 */
	boolean equal(ADTGraph g);
	
	/*
	 * Erstellt am angegebenen Pfad eine dot-Datei des Eingabegraphen.
	 */
	void print(String filename);
	
	/*
	 * Gibt eine Matrixversion des Eingabeadtgraphen aus.
	 * Die Matrix soll genau ein Attribut darstellen.
	 * Welches der möglichen Attribute dargestellt wird, hängt von der jeweiligen
	 * Aufgabenstellung ab. Zunächst reicht es nur die Kosten darzustellen
	 */
	ADTMatrix createM();

	void addVertex(String string);

	void setValEdge(Vertex v1, Vertex v2, int i);

	int getValEdge(Vertex v1, Vertex v2);

	Integer getValV(Vertex v, String name);
}

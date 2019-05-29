package implementation;

import interfaces.ADTGraph;
import interfaces.ADTMatrix;
import interfaces.Vertex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ADTGraphImpl implements ADTGraph
{
	// FIELDS
	private ArrayList<ArrayList<HashMap<String, Integer>>> matrix;
	private ArrayList<Vertex> vertices;

	private ArrayList<HashMap<String, Integer>> vertexAttributes;
	
	// CONSTRUCTOR
	private ADTGraphImpl(Vertex dummy)
	{
		//Initializing		
		matrix = new ArrayList<ArrayList<HashMap<String, Integer>>>();
		vertices = new ArrayList<Vertex>();
		vertexAttributes = new ArrayList<HashMap<String, Integer>>();
	
		//add first vertex to graph
		addVertex(dummy);
	}
	
	// METHODS
	public static ADTGraph createG(Vertex string)
	{
		return new ADTGraphImpl(string);
	}
	@Override
	public void addVertex(Vertex string)
	{
		if(!vertices.contains(string))
		{
			vertices.add(string); //neuen Knoten merken
			matrix.add(new ArrayList<HashMap<String, Integer>>()); //fuer neuen Knoten eine neue Zeile in der Matrix erstellen
			
			int index = vertices.indexOf(string);
			ArrayList<HashMap<String, Integer>> newLine = matrix.get(index); //Zeile dieses Knotens
			for(int i = 0; i < matrix.size()-1; i++) //wir laufen über alle Zeilen außer die letzte
			{
				matrix.get(i).add(null); //letzte Spalte jeder ALTEN Reihe, Beziehung zum neuen Knoten, gibt noch keine Kante, also null
				newLine.add(null); //das ist die neue Zeile, sie muss komplett mit null gefüllt werden (kann noch keine Kanten geben)
			}
			newLine.add(null); //und noch einmal, weil noch die Beziehung zu sich selbst passieren muss
			
			vertexAttributes.add(new HashMap<String, Integer>()); //wir merken uns eine HashMap für diesen Knoten
		}
	}
	@Override
	public void deleteVertex(Vertex v)
	{
		if(!(vertices.size() == 1) && vertices.contains(v)) //one vertex must live
		{
			int index = vertices.indexOf(v);
			for(ArrayList<HashMap<String, Integer>> list : matrix)
			{
				list.remove(index); //wir löschen den Eintrag in jeder Zeile für den zu löschenden Knoten
			}
			matrix.remove(index); //wir löschen die Zeile dieses Knotens
			vertexAttributes.remove(index); //wir löschen die Attribute
			vertices.remove(v); //wir löschen den Knoten
		}
	}
	@Override
	public void addEdge(Vertex v1, Vertex v2)
	{
		addVertex(v1); //falls Knoten noch nicht existiert, soll er erstellt werden (addVertex prüft ob es ihn schon gibt)
		addVertex(v2);
		
		matrix.get(vertices.indexOf(v1)).set(vertices.indexOf(v2), new HashMap<String, Integer>()); // wenn eine HashMap existiert, dann gibt es eine Kante
	}
	@Override
	public void deleteEdge(Vertex v1, Vertex v2)
	{
		matrix.get(vertices.indexOf(v1)).set(vertices.indexOf(v2), null);
	}
	@Override
	public void setAtE(Vertex v1, Vertex v2, String name, int value)
	{
		HashMap<String, Integer> edge = matrix.get(vertices.indexOf(v1)).get(vertices.indexOf(v2));
		
		if(!(edge == null))
		{
			edge.put(name, value); //das Attribut wird in die HashMap gepackt
		}
	}
	@Override
	public void setAtV(Vertex v1, String name, int value)
	{
		if(vertices.contains(v1))
		{
			vertexAttributes.get(vertices.indexOf(v1)).put(name, value);
		}
	}
	
	public static ADTGraph importG(String filename)
	{
		Vertex dummy = Vertex.createV("dummy");
		ADTGraph output = new ADTGraphImpl(dummy);
		
		boolean gerichtet = false;
		
		BufferedReader reader ;

		try 
		{
		    File file = new File(filename);
		    reader = new BufferedReader(new FileReader(file));
		    String line;
		    
		    if((line = reader.readLine()) != null) //erste Zeile lesen
		    {
		    	if(line.toLowerCase().equals("#gerichtet"))
		    	{
		    		gerichtet = true;
		    	}
		    	else if(line.toLowerCase().equals("#ungerichtet"))
		    	{
		    		gerichtet = false;
		    	}
		    }
		    Pattern gruppen=Pattern.compile("(.*)\\s+-(.)\\s+(.*\\d*)\\s+(:)*(.*);");
			Matcher matcher;
		    
		
				
				
			
				
			
		    while ((line = reader.readLine()) != null) //alle weiteren Zeilen lesen
		    {    
		    	matcher = gruppen.matcher(line);
		    	
		    	System.out.println(line);
		    	
		    
		        //String[] newline = line.split("\\s");
		        System.out.println(Pattern.matches("((.*)\\s+-(.)\\s+(.*\\d*)\\s+(:)*(.*));",line));
		      
		        Vertex v1 = Vertex.createV(matcher.group(2));
		        Vertex v2 = Vertex.createV(matcher.group(4));
		        
		        output.addEdge(v1, v2); //addEdge erzeugt die Knoten wenn sie nicht existieren
		        if(!gerichtet)
		        {
		        	output.addEdge(v2, v1);
		        	output.setAtE(v2, v1, "cost", Integer.parseInt(matcher.group(5)));
		        }
		        output.setAtE(v1, v2, "cost", Integer.parseInt(matcher.group(5)));
		        
		        output.deleteVertex(dummy);
		    }

		    }
		catch (IOException e) 
		{
		    e.printStackTrace();
		} 
		

		return output;
	}
	
	public void exportG(String filename)
	{
		try 
		{
			File file = new File(filename);

			//guck ob es die Datei schon gibt
			if (file.exists()) 
			{
				file = new File(file.getName() + "neu");
			}
			file.createNewFile();

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter writer = new BufferedWriter(fw);
			
			ArrayList<Vertex> edges = this.getEdges();
			
			//Schreiben
			writer.write("#gerichtet");
			writer.newLine();
			
			String line;
			for(int i = 0; i < edges.size(); i = i+2)
			{
				line = edges.get(i).getName() + "," + edges.get(i+1) + "," + this.getValE(edges.get(i), edges.get(i+1), "cost");
				writer.write(line);
				writer.newLine();
			}
			writer.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	@Override
	public ArrayList<Vertex> getIncident(Vertex v)
	{		
		ArrayList<Vertex> output = new ArrayList<Vertex>();
		
		ArrayList<HashMap<String, Integer>> vertexRow = matrix.get(vertices.indexOf(v));

		int i = 0;
		
		//hier testen wir nur die beziehung von v SOURCE ist
		for(HashMap<String, Integer> edge : vertexRow)
		{
			// wir schauen uns alle Eintragen für diesen Knoten an. "nicht null" heißt es gibt eine Kante.
			// danach müssen wir gucken was der Index für diesen Eintrag ist, um zu wissen welcher Vertex das ist.
			// dieser kommt dann in die output als Target, der Parameter Vertex kommt rein als Source
			if(!(edge == null))
			{
				output.add(Vertex.createV(v.getName()));
				output.add(Vertex.createV(vertices.get(i).getName())); //wer ist der Knoten mit dem Index auf der Y Achse?
			}
			i++;
		}
		
		i = 0;
		//wir müssen aber noch testen wo v target ist
		for(ArrayList<HashMap<String, Integer>> row : matrix)
		{
			if(!(row.get(vertices.indexOf(v)) == null))
			{
				output.add(Vertex.createV(vertices.get(i).getName())); //Hier müssen wir den Index auf der X Achse angucken und wer der Knoten ist
				output.add(Vertex.createV(v.getName()));
			}
			i++;
		}
		
		return output;
	}
	@Override
	public ArrayList<Vertex> getAdjacent(Vertex v)
	{
		ArrayList<Vertex> output = new ArrayList<Vertex>();
		
		ArrayList<HashMap<String, Integer>> vertexRow = matrix.get(vertices.indexOf(v));
		
		int i = 0;
		
		for(HashMap<String, Integer> edge : vertexRow)
		{
			//siehe getIncident(), hier wollen wir aber nur den adjazenten Knoten natürlich.
			if(!(edge == null))
			{
				output.add(Vertex.createV(vertices.get(i).getName())); 
			}
			i++;
		}
		
		i = 0;
		//wir müssen aber noch testen wo v target ist
		for(ArrayList<HashMap<String, Integer>> row : matrix)
		{
			if(!(row.get(vertices.indexOf(v)) == null))
			{
				output.add(Vertex.createV(vertices.get(i).getName())); //Hier müssen wir den Index auf der X Achse angucken und wer der Knoten ist
			}
		}
		i++;
		
		return output;
	}
	@Override
	public ArrayList<Vertex> getTarget(Vertex v)
	{
		ArrayList<Vertex> output = new ArrayList<Vertex>();
		
		if(vertices.contains(v))
		{
			ArrayList<HashMap<String, Integer>> vertexRow = matrix.get(vertices.indexOf(v));
			
			int i = 0;
			
			for(HashMap<String, Integer> edge : vertexRow)
			{
				//siehe getIncident(), hier wollen wir aber nur den TARGETS natürlich.
				if(!(edge == null))
				{
					output.add(Vertex.createV(vertices.get(i).getName())); 
				}
				i++;
			}
		}
		
		return output;
	}
	@Override
	public ArrayList<Vertex> getSource(Vertex v)
	{
		ArrayList<Vertex> output = new ArrayList<Vertex>();
		
		if(vertices.contains(v))
		{
			int i = 0;
			//wir gucken uns die Sources an, wer hat v als Target?
			for(ArrayList<HashMap<String, Integer>> row : matrix)
			{
				if(!(row.get(vertices.indexOf(v)) == null))
				{
					output.add(Vertex.createV(vertices.get(i).getName())); //Hier müssen wir den Index auf der X Achse angucken und wer der Knoten ist
				}
				i++;
			}
		}
		
		return output;
	}
	@Override
	public ArrayList<Vertex> getEdges()
	{
		ArrayList<Vertex> output = new ArrayList<Vertex>();
		int x = 0;
		int y = 0;
		
		for(ArrayList<HashMap<String, Integer>> row : matrix)
		{
			for(HashMap<String, Integer> edge : row)
			{
				if(!(edge == null))
				{
					output.add(Vertex.createV(vertices.get(x).getName())); //wir gucken wer ist der Knoten auf der X Achse, seinen Namen wollen wir
					output.add(Vertex.createV(vertices.get(y).getName())); //das gleiche für Y Achse, bzw dies ist TARGET
					
				}
				y++;
			}
			y = 0;
			x++;
		}
		
		return output;
	}
	@Override
	public ArrayList<Vertex> getVertexes()
	{
		ArrayList<Vertex> output = new ArrayList<Vertex>();
		
		for(Vertex v : vertices)
		{
			output.add(Vertex.createV(v.getName())); //Namen der Kanten kommen in den output list
		}
		
		return output;
	}
	@Override
	public Integer getValE(Vertex v1, Vertex v2, String name)
	{
		Integer result = null;
		
		HashMap<String, Integer> edge = matrix.get(vertices.indexOf(v1)).get(vertices.indexOf(v2));
		
		if(!(edge.get(name) == null)) //wenn es eine HashMap gibt, dann ist es eine Kante
		{
			result = edge.get(name);
		}
		else{
			return null;
		}
		
		return result;
	}
	@Override
	public Integer getValV(Vertex v, String name)
	{		
		Integer result = null;
		
		if(vertices.contains(v))
		{
			result = vertexAttributes.get(vertices.indexOf(v)).get(name);
		}
		
		return result;
	}
	@Override
	public boolean equal(ADTGraph g)
	{
		boolean b = false;
		
		ArrayList<Vertex> thisVertices = this.getVertexes();
		ArrayList<Vertex> thatVertices = g.getVertexes();
		
		//compare number of vertices
		if(!(thisVertices.size() == thatVertices.size()))
		{
			return false;
		}
		
		//compare all vertexes
		for (Vertex v1 : thisVertices)
		{
			b = false;
			
			for (Vertex v2 : thatVertices)
			{
				if(v1.equals(v2))
				{
					b = true;
					break;
				}
			}
			if (b == false)
			{
				return false;
			}
		}

		ArrayList<Vertex> thisEdges = this.getEdges();
		ArrayList<Vertex> thatEdges = g.getEdges();
		
		//compare number edges
		if(!(thisEdges.size() == thatEdges.size()))
		{
			return false;
		}
		
		//compare all edges		
		for (int i = 0; i < thisEdges.size(); i++)
		{
			b = false;
			
			if(!(i % 2 == 0)) // wir vergleichen nur Sources, und gucken dann ob Targets gleich sind
			{
				continue;
			}
			
			for (int j = 0; j < thatEdges.size(); j++)
			{
				if(!(j % 2 == 0))
				{
					continue;
				}
				
				if(thisEdges.get(i).equals(thatEdges.get(j)))
				{
					if(thisEdges.get(i+1).equals(thatEdges.get(j+1)))
					{
						b = true;
						break;
					}
				}
			}
			
			if(b == false)
			{
				return false;
			}
		}
		
		return true;
	}
	@Override
	public void print(String filename)
	{
		try 
		{
			File file = new File(filename);

			//guck ob es die Datei schon gibt
			if (file.exists()) 
			{
				file = new File(file.getName() + "neu");
			}
			file.createNewFile();

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter writer = new BufferedWriter(fw);
			
			ArrayList<Vertex> edges = this.getEdges();
			
			//Schreiben
			writer.write("digraph tree");
			writer.newLine();
			writer.write("{");
			writer.newLine();
			
			String line;
			for(int i = 0; i < edges.size(); i = i+2)
			{
				line = edges.get(i).getName() + " -> " + edges.get(i+1) + " [label = " + this.getValE(edges.get(i), edges.get(i+1), "cost") + "]";
				writer.write(line);
				writer.newLine();
			}
			writer.write("}");
			writer.newLine();
			
			writer.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	@Override
	public ADTMatrix createM()
	{
		int[][] matrixNeu = new int[vertices.size()][vertices.size()];
		
		for(int iter=0; iter < vertices.size(); iter++){
			matrixNeu[iter][iter] = 0;
		}
		
		
		return new ADTMatrixImpl(matrixNeu, this.getVertexes()); 
	}
	

	
	@Override
	public String toString()
	{
		return matrix.toString();
	}

	

	@Override
	public void setValEdge(Vertex v1, Vertex v2, int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getValEdge(Vertex v1, Vertex v2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addVertex(String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getValV(Vertex v, Vertex v2) {
		// TODO Auto-generated method stub
		return null;
	}

	
}

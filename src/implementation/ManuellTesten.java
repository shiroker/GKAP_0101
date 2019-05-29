package implementation;

import interfaces.ADTGraph;
import interfaces.ADTMatrix;
import interfaces.Vertex;








import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ManuellTesten {

	
	public static void main(String[] args) {
	
		Vertex v1 = Vertex.createV("v1");
		Vertex v2 = Vertex.createV("v2");
		Vertex v3 = Vertex.createV("v3");
		Vertex v4 = Vertex.createV("v4");
		Vertex v11 = Vertex.createV("v11");
		ADTGraph g = ADTGraph.createG(v1);
		
		g.addEdge(v1, v2);
		g.addEdge(v1, v3);
		g.addEdge(v4, v1);
		g.addVertex(v11);
	
		Vertex v5 = Vertex.createV("v1");
		Vertex v6 = Vertex.createV("v2");
		Vertex v7 = Vertex.createV("v3");
		Vertex v8 = Vertex.createV("v4");
		ADTGraph g2 = ADTGraph.createG(v5);
		
		g2.addEdge(v5, v6);
		g2.addEdge(v5, v7);
		g2.addEdge(v8, v5);
		System.out.println(g.getEdges());
		
		ADTMatrix mat = g2.createM();
		mat.setValM(1, 2, 56);
		mat.setValM(1, 3, 55);
		System.out.println(mat.indTOvert(1).equals(v2));
		System.out.println(mat.getValM(1, 3));
		
		System.out.println(g.getTarget(v1).get(1).getName());
		
		
		System.out.println("-----------------------------------------------------------------------");
		
		String[][]matr = new String[5][6];
		String kop = "kaka";
		matr[3][4] = kop;
		System.out.println(matr[3][4]);
		System.out.println(matr[0][3]);
		
		String[] index = new String[6];
		String tat = "tata";
		index[4] = tat;
		for(int it=0; it < index.length; it++){
			if(index[it]== tat){
				System.out.println(it);
				System.out.println(index[it]);
				System.out.println(Arrays.asList(index).contains(tat));
			}
		}
		
		System.out.println("-------------------------------------------------------------------");
		
		
		HashMap<String, Integer> map = new HashMap<String,Integer>();
		
		map.put("kol",67);
		map.put("but",78);
		System.out.println(map.get("kol"));
		System.out.println(map.isEmpty());
		
		ArrayList<String> liste = new ArrayList<String>();
		liste.add("kap");
		liste.add("kop");
		liste.add("kup");
		System.out.println(liste.indexOf("p"));
		System.out.println(liste.size());
		
		ArrayList<HashMap> misal = new ArrayList<>(); 
		misal.add(map);
		System.out.println(misal.get(0).get("but"));
		
		System.out.println("--------------------------------------------------------------------------------------");
		
			
		
		ADTGraph gr = ADTGraph.createG(v2);
		
		gr.addEdge(v1, v2);
		gr.addEdge(v1, v3);
		gr.addEdge(v4, v1);
		
		ADTMatrix matrix = g.createM();
		matrix.setValM(1, 2, 56);
		System.out.println(matrix.indTOvert(0).equals(v2));
		System.out.println(matrix.getValM(1, 2) ==(56));
		System.out.println("-------------Test---alle-----------");
	
		//Vertex vert = VertexImpl.createV("a");
		ADTGraph aa = ADTGraph.importG("src/tests/graph03.gka");
		System.out.println(aa);
		
		
		
	
	}
	



}

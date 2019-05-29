package implementation;

import interfaces.ADTMatrix;
import interfaces.Vertex;

import java.util.ArrayList;

public class ADTMatrixImpl implements ADTMatrix
{
	int[][] matrix;
	ArrayList<Vertex> vertices;
	
	public ADTMatrixImpl(int[][] matrixNeu, ArrayList<Vertex> vertexlist)
	{
		matrix = matrixNeu;
		vertices = vertexlist;
	}
	
	public int vertTOind(Vertex v)
	{
		return vertices.indexOf(v);
	}
	
	public Vertex indTOvert(int i)
	{
		return vertices.get(i);
	}
	
	public int getValM(int x, int y)
	{
		return matrix[x][y];
	}
	
	public void setValM(int x, int y, int value)
	{
		matrix[x][y] = value;
		
	}
}

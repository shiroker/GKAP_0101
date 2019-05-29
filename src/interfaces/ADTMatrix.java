package interfaces;

public interface ADTMatrix 
{
	/*
	 * Gibt den Index der Eingabeecke in der Matrix zur�ck. Ist die Ecke nicht
	 * vorhanden, wird NULL zur�ckgegeben.
	 */
	int vertTOind(Vertex v);
	
	/*
	 * Gibt die Ecke zur�ck, die in der Matrix dem Eingabeindex zugeordnet ist. Existiert
	 * der Index nicht, wird NULL zur�ckgegeben.
	 */
	Vertex indTOvert(int index);
	
	/*
	 * Gibt den Wert zur�ck, der in der Matrix an den Eingabeindizes steht. Gibt es
	 * einen der Indizes nicht, wird NULL zur�ckgegeben.
	 */
	int getValM(int xIndex, int yIndex);
	
	/*
	 * �ndert den Wert, der durch die Eingabeindizes bezeichnet ist, auf den
	 * Eingabevalue. Indizes m�ssen innerhalb der Matrixgrenzen liegen, ansonsten
	 * bleibt Matrix unver�ndert.
	 */
	void setValM(int xIndex, int yIndex, int value);
}

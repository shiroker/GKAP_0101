package interfaces;

public interface ADTMatrix 
{
	/*
	 * Gibt den Index der Eingabeecke in der Matrix zurück. Ist die Ecke nicht
	 * vorhanden, wird NULL zurückgegeben.
	 */
	int vertTOind(Vertex v);
	
	/*
	 * Gibt die Ecke zurück, die in der Matrix dem Eingabeindex zugeordnet ist. Existiert
	 * der Index nicht, wird NULL zurückgegeben.
	 */
	Vertex indTOvert(int index);
	
	/*
	 * Gibt den Wert zurück, der in der Matrix an den Eingabeindizes steht. Gibt es
	 * einen der Indizes nicht, wird NULL zurückgegeben.
	 */
	int getValM(int xIndex, int yIndex);
	
	/*
	 * Ändert den Wert, der durch die Eingabeindizes bezeichnet ist, auf den
	 * Eingabevalue. Indizes müssen innerhalb der Matrixgrenzen liegen, ansonsten
	 * bleibt Matrix unverändert.
	 */
	void setValM(int xIndex, int yIndex, int value);
}

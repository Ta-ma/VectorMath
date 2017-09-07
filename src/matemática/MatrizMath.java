package matemática;

import java.util.Arrays;

public class MatrizMath 
{
	private double[][] coordenadas;
	private int filas, columnas;
	
	public MatrizMath (double[][] mat)
	{
		this.coordenadas = mat;
		this.filas = mat.length;
		this.columnas = mat[0].length;
	}
	
	public VectorMath getColumna (int indice)
	{
		double[] col = new double[this.filas];
		
		for (int i = 0; i < col.length; i++)
			col[i] = this.coordenadas[i][indice];
		
		VectorMath vec = new VectorMath(col);
		return vec;
	}
	
	public MatrizMath producto (MatrizMath mat) throws DimException
	{
		if (this.columnas != mat.filas)
			throw new DimException("las dimensiones no coinciden");
		
		double[][] coords = new double[this.filas][mat.columnas];
		
		for (int i = 0; i < this.filas; i++)
		{
			for (int j = 0; j < mat.columnas; j++)		
			{
				coords[i][j] = this.getFila(i).producto(mat.getColumna(j));
			}
				
		}
		
		return new MatrizMath(coords);
	}
	
	public VectorMath getFila (int indice)
	{
		return new VectorMath(this.coordenadas[indice]);
	}

	@Override
	public String toString() {
		return "MatrizMath [coordenadas=" + Arrays.toString(coordenadas) + "]";
	}
 public void mostrar()
 {
	 for (int i=0;i<this.filas;i++)
	 {
		 for(int j=0;j<this.columnas;j++)
			 System.out.print(this.coordenadas[i][j]+"\t");
	 System.out.println("\n");
	 }
		 
 }
 
 
	public int getFilas() {
		return filas;
	}

	public void setFilas(int filas) {
		this.filas = filas;
	}

	public int getColumnas() {
		return columnas;
	}

	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}
	
	
}

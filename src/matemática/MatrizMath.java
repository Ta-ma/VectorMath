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
	
	public MatrizMath (VectorMath[] columnas) throws MathException // asume que todos tienen misma dimensión
	{
		int dim = columnas[0].getDim(); // tomo la dim de la primera como referencia
		double[][] coords = new double[dim][columnas.length];
		
		for (int i = 0; i < dim; i++)
		{
			double[] coords_col = columnas[i].getCoordenadas();
			
			for (int j = 0; j < columnas.length; j++)
			{
				if (columnas[i].getDim() != dim) // una de las columnas tiene dim distinta
					throw new MathException ("el arreglo de vectores columnas no es uniforme");
				
				coords[j][i] = coords_col[j];
			}
			
		}
		
		this.coordenadas = coords;
		this.filas = dim;
		this.columnas = columnas.length;
	}

	public VectorMath getColumna (int indice)
	{
		double[] col = new double[this.filas];

		for (int i = 0; i < col.length; i++)
			col[i] = this.coordenadas[i][indice];

		VectorMath vec = new VectorMath(col);
		return vec;
	}

	public MatrizMath producto (MatrizMath mat) throws MathException
	{
		if (this.columnas != mat.filas)
			throw new MathException("las dimensiones no coinciden");

		double[][] coords = new double[this.filas][mat.columnas];

		for (int i = 0; i < this.filas; i++)
		{
			for (int j = 0; j < mat.columnas; j++)
				coords[i][j] = this.getFila(i).producto(mat.getColumna(j));
		}

		return new MatrizMath(coords);
	}
	
	public VectorMath producto (VectorMath vec) throws MathException
	{
		if (this.columnas != vec.getDim())
			throw new MathException("las dimensiones no coinciden");

		double[] coords = new double[this.filas];

		for (int i = 0; i < this.filas; i++)
			coords[i] = this.getFila(i).producto(vec);

		return new VectorMath(coords);
	}
	
	public MatrizMath traspuesta ()
	{
		double[][] coords = new double [this.columnas][this.filas];
		
		for (int i = 0; i < this.columnas; i++)
		{
			for (int j = 0; j < this.filas; j++)
				coords[i][j] = coordenadas[j][i];
		}
		
		return new MatrizMath(coords);
	}
	
	public VectorMath resolver (VectorMath vec) throws MathException
	{
		int filasCoef = this.getFilas();
		int columnasCoef = this.getColumnas();
		
		if (filasCoef != columnasCoef) // || columnasCoef != vec.getDim()
			throw new MathException("no voy a resolver un sistema que no tiene solución hijo de puta");
		
		// Generar Q
		VectorMath[] u = new VectorMath[columnasCoef];
		
		for (int i = 0; i < columnasCoef; i++)
		{
			VectorMath a = this.getColumna(i);
			u[i] = a;
			for (int j = 0; j < i; j++)
			{
				VectorMath e = u[j].normalizado();
				VectorMath proj = e.producto(e.producto(a) / e.producto(e));
				u[i] = u[i].resta(proj);
			}
			u[i] = u[i].normalizado();
		}
		
		MatrizMath q = new MatrizMath(u);
		//q.mostrar();
		
		// Generar R
		MatrizMath qT = q.traspuesta();
		MatrizMath r = qT.producto(this);
		//r.mostrar();
		
		// Resuelvo
		VectorMath qTvec = qT.producto(vec);
		double[] resultados = new double[vec.getDim()];
		
		for (int i = vec.getDim() - 1; i >= 0; i--)
		{
			resultados[i] = qTvec.getCoordenadas()[i];
			for (int j = qTvec.getDim() - 1; j > i; j--)
				resultados[i] -= r.getFila(i).getCoordenadas()[j] * resultados[j];
			resultados[i] /= r.getFila(i).getCoordenadas()[i];
		}
		
		return new VectorMath(resultados);
	}

	public VectorMath getFila (int indice)
	{
		return new VectorMath(this.coordenadas[indice]);
	}

	@Override
	public String toString ()
	{
		return "MatrizMath [coordenadas=" + Arrays.toString(coordenadas) + "]";
	}

	public void mostrar ()
	{
		for (int i = 0; i < this.filas; i++)
		{
			for (int j = 0; j < this.columnas; j++)
				System.out.print(this.coordenadas[i][j] + "\t");

			System.out.println("\n");
		}
	}

	public int getFilas ()
	{
		return filas;
	}

	public void setFilas (int filas)
	{
		this.filas = filas;
	}

	public int getColumnas ()
	{
		return columnas;
	}

	public void setColumnas (int columnas)
	{
		this.columnas = columnas;
	}

}

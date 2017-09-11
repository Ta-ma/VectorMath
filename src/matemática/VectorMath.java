package matemática;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class VectorMath
{
	private double[] coordenadas;
	private int dim;
	
	public VectorMath (double[] coordenadas)
	{
		this.dim = coordenadas.length;
		this.coordenadas = new double[dim];
		this.coordenadas = coordenadas;
	}
	
	public VectorMath (File archivo) throws IOException
	{
		Scanner sc = new Scanner(archivo);
		coordenadas = new double[sc.nextInt()];
		
		for (int i = 0; i < coordenadas.length; i++)
			coordenadas[i] = sc.nextDouble();
		
		sc.close();
	}
	
	public VectorMath (VectorMath vec)
	{
		this.dim = vec.dim;
		coordenadas = vec.coordenadas;
	}
	
	public VectorMath suma (VectorMath vec) throws MathException
	{
		if (this.dim != vec.dim)
		{
			throw new MathException("las dimensiones no son iguales");
		}
		
		double[] coords = new double[this.dim];
		
		for(int i = 0; i < this.dim; i++)
			coords[i] = this.coordenadas[i] + vec.coordenadas[i];
		
		VectorMath res = new VectorMath(coords);
		return res;
	}
	
	public VectorMath resta (VectorMath vec) throws MathException
	{
		if (this.dim != vec.dim)
		{
			throw new MathException("las dimensiones no son iguales");
		}
		
		double[] coords = new double[this.dim];
		
		for(int i = 0; i < this.dim; i++)
			coords[i] = this.coordenadas[i] - vec.coordenadas[i];
		
		VectorMath res = new VectorMath(coords);
		return res;
	}
	
	public VectorMath producto (double escalar)
	{
		double[] coords = this.coordenadas;
		
		for (int i = 0; i < coords.length; i++)
			coords[i] *= escalar;
		
		return new VectorMath(coords);
	}
	
	public double producto (VectorMath vec)
	{
		if (this.dim == vec.dim)
		{
			double res = 0;
			
			for(int i = 0; i < this.dim; i++)
				res += this.coordenadas[i] * vec.coordenadas[i];
			
			return res;
		}
		
		return 0;
	}
	
	public VectorMath producto (MatrizMath mat) throws MathException
	{
		if (this.dim != mat.getColumnas())
			throw new MathException("la dimensión del vector no es igual a la altura de la matriz");
		
		double[] coords = new double[this.dim];
			
		for(int i = 0; i < this.dim - 1; i++)
		{
			coords[i] = this.producto(mat.getColumna(i));
		}
			
		return new VectorMath(coords);
	}
	
	public double[] getCoordenadas()
	{
		return coordenadas;
	}

	public void setCoordenadas(double[] coordenadas)
	{
		this.coordenadas = coordenadas;
	}

	public int getDim()
	{
		return dim;
	}

	public void setDim(int dim)
	{
		this.dim = dim;
	}

	public double norma ()
	{
		double norm = 0;
		
		for (int i = 0; i < this.dim; i++)
			norm += Math.pow(coordenadas[i], 2);
		
		return Math.sqrt(norm);
	}
	
	public VectorMath normalizado () throws MathException
	{
		double norma = this.norma();
		if (dim == 0 || norma == 0)
			throw new MathException("intento de normalizar un vector nulo");
		
		double[] coords = new double[this.dim];
		
		for(int i = 0; i < this.dim; i++)
			coords[i] = coordenadas[i] / norma;
		
		return new VectorMath(coords);
	}
	
	@Override
	public String toString() 
	{
		return "VectorMath [coordenadas=" + Arrays.toString(coordenadas) + ", dim=" + dim + "]";
	}
	
}

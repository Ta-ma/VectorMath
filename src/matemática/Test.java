package matemática;

public class Test {

	public static void main(String[] args) throws DimException 
	{
		double[] cvec1 = {2, 4};
		double[] cvec2 = {1, 2};
		double[][] cmat1 = {{1, 2},
							{3, 4},
							{3, 2}};
		
		double[][] cmat2 = {{1, 2, 3},
							{3, 2, 1}};

		VectorMath vec1 = new VectorMath(cvec1);
		VectorMath vec2 = new VectorMath(cvec2);
		MatrizMath mat1 = new MatrizMath(cmat1);
		MatrizMath mat2 = new MatrizMath(cmat2);
		
		/*System.out.println(vec1.suma(vec2));
		
		System.out.println(vec1.toString());
		System.out.println(vec1.producto(vec2));
		System.out.println(mat1.getColumna(1));*/
		//System.out.println(mat1.producto(mat2));
		(mat1.producto(mat2)).mostrar();
		
	}

}


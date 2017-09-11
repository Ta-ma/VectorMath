package matemática;

public class Test {

	public static void main(String[] args) throws MathException 
	{
		double[] cvec1 = {2, 4};
		double[] cvec2 = {1, 2};
		double[] cvec3 = {4, -6, 7};
		double[][] cmat1 = {{1, 2},
							{3, 4},
							{3, 2}};
		
		double[][] cmat2 = {{1, 2, 3},
							{3, 2, 1}};
		
		double[][] cmat3 =  {{1, 1, -1},
							 {1, -2, 3},
							 {2, 3, 1}};

		VectorMath vec1 = new VectorMath(cvec1);
		VectorMath vec2 = new VectorMath(cvec2);
		VectorMath vec3 = new VectorMath(cvec3);
		VectorMath[] cols = new VectorMath[2];
		cols[0] = vec1;
		cols[1] = vec2;
		MatrizMath sup = new MatrizMath(cols);
		MatrizMath mat1 = new MatrizMath(cmat1);
		MatrizMath mat2 = new MatrizMath(cmat2);
		MatrizMath mat3 = new MatrizMath(cmat3);
		System.out.println(mat3.resolver(vec3)); // tiene que dar 1, 2, -1
		
		//System.out.println(vec1.norma());
		//System.out.println(vec2.norma());
		
		//System.out.println(vec1.normalizado());
		//System.out.println(vec2.normalizado());
		/*System.out.println(vec1.suma(vec2));
		
		System.out.println(vec1.toString());
		System.out.println(vec1.producto(vec2));
		System.out.println(mat1.getColumna(1));*/
		//System.out.println(mat1.producto(mat2));
		//mat1.traspuesta().mostrar();
		//mat2.traspuesta().mostrar();
		
	}

}


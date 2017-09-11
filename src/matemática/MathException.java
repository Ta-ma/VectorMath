package matemática;

public class MathException extends Exception 
{
	public MathException (String mensaje)
	{
		super(mensaje);
	}

	@Override
	public String toString() 
	{
		return "ERROR: " + super.getMessage();
	}
}

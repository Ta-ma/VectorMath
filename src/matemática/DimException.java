package matemática;

public class DimException extends Exception 
{
	public DimException (String mensaje)
	{
		super(mensaje);
	}

	@Override
	public String toString() 
	{
		return "ERROR: " + super.getMessage();
	}
}

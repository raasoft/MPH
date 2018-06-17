package exception;

public class ProfessoreEsistenteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2694073135120405663L;
	
	public ProfessoreEsistenteException(){};
	
	public ProfessoreEsistenteException(String s)
	{
		super(s);
	}

}


package ast;

import java.util.Hashtable;
import java.util.Set;

public class Declaration extends SchemeExpression 
{
	private Variable fVariable;
	private SchemeExpression fExp;
	
	public Variable getVariable()
	{
		return fVariable;
	}
	
	public SchemeExpression getExp()
	{
		return fExp;
	}
	
	public Declaration( Variable aVariable, SchemeExpression aExp )
	{
		fVariable = aVariable;
		fExp = aExp;
	}

    public Set<Variable> freeNames()
    {
    	return fExp.freeNames();
    }
    
    public SchemeExpression substitute( Variable x, SchemeExpression e )
    {
    	return new Declaration( getVariable(), fExp.substitute( x, e ) );
    }

    public SchemeExpression reduce( Hashtable<Variable,SchemeExpression> aSymTable )
	{
		if (aSymTable.containsKey(fVariable))
		{
			aSymTable.remove(fVariable);
			aSymTable.put(fVariable, fExp.reduce(aSymTable));
		}
		else
		{
			aSymTable.put(fVariable, fExp.reduce(aSymTable));
		}
		
		return fExp.reduce(aSymTable);
	}
    
    public String toString()
    {
		return (new StringBuilder())
			.append( "(define " )
			.append( fVariable.toString() )
			.append( " " )
			.append( fExp.toString() )
			.append( ")" )
			.toString();
    }

}
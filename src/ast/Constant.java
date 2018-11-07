package ast;

import java.util.Set;
import java.util.HashSet;
import java.util.Hashtable;

public class Constant extends SchemeExpression
{
    private Double fValue;

    public Double getValue()
    {
    	return fValue;
    }
    
    public Constant( String aNumber )
    {
    	fValue = Double.parseDouble( aNumber );
    }

    public Set<Variable> freeNames()
    {
    	return new HashSet<Variable>();
    }
    
    public SchemeExpression substitute( Variable x, SchemeExpression e )
    {
    	return this;
    }
	
	public SchemeExpression reduce( Hashtable<Variable,SchemeExpression> aSymTable )
    {
    	return this;
    }

    public String toString()
    {
    	return fValue.toString();
    }
}
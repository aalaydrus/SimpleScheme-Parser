package ast;

import java.util.Set;
import java.util.HashSet;
import java.util.Hashtable;

public class Variable extends SchemeExpression
{
    private String fValue;

    public String getValue()
    {
    	return fValue;
    }

    public Variable( String aIdentifier )
    {
    	fValue = aIdentifier;
    }

    public boolean equals( Object aOther )
    {
    	if ( aOther instanceof Variable )
    	{
    		return ((Variable)aOther).getValue().equals( fValue );
    	}
    	
    	return false;
    }
    
    public int hashCode()
    {
    	return fValue.hashCode();
    }
    
    public Set<Variable> freeNames()
    {
    	Set<Variable> Result = new HashSet<Variable>();

    	Result.add( this );

    	return Result;
    }

	public Variable getFreshVariable( Set<Variable> aSetOfFreeNames )
	{
		// build free name
		Variable lFresh = new Variable( fValue + "%" );
		while ( aSetOfFreeNames.contains( lFresh ) )
			lFresh.fValue += "%";
		
		return lFresh;
	}
	
    public SchemeExpression substitute( Variable x, SchemeExpression e )
    {
    	if ( equals( x ) )
    		// [e/x]x
    		return e;
    	else
    		// [e/x]y
    		return this;
    }
	
	public SchemeExpression reduce( Hashtable<Variable,SchemeExpression> aSymTable )
	{
		if( aSymTable.containsKey(this))
		{
			return aSymTable.get(this); //not sure if -return this- would give out the same value
		}
		else
		{
			throw new RuntimeException("Error! variable name is not free ");
		}
	}

    public String toString()
    {
    	return fValue;
    }

	
}

























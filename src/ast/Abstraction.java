
package ast;

import java.util.Hashtable;
import java.util.Set;

public class Abstraction extends SchemeExpression
{
    private Variable fVariable;

    public Variable getVariable()
    {
    	return fVariable;
    }

    private SchemeExpression fBody;

    public SchemeExpression getBody()
    {
    	return fBody;
    }

    public Abstraction( Variable aVariable, SchemeExpression aBody )
    {
    	fVariable = aVariable;
        fBody = aBody;
    }

    public Set<Variable> freeNames()
    {
    	Set<Variable> Result = fBody.freeNames();

    	Result.remove( getVariable() );

    	return Result;
    }

    public SchemeExpression substitute( Variable x, SchemeExpression e )
    {
    	if ( getVariable().equals( x ) )
    		// [e/x](lambda x . e1)
    		return this;
    	else
    	{
    		Set<Variable> lFrees = e.freeNames();
    		Variable y = getVariable();
    		
    		// rule 6
    		if ( lFrees.contains( y ) )
    		{
    			lFrees.addAll( getBody().freeNames() );

    			Variable z = y.getFreshVariable( lFrees );
    			
    			// [e/x][z/y]
    			SchemeExpression lNewBody = fBody.substitute( y, z );
    			lNewBody = lNewBody.substitute( x, e );
    			return new Abstraction( z, lNewBody );
    		}
    		else
    		{
    			// rule 5
    			return new Abstraction( y, fBody.substitute( x, e ) );
    		}
    	}
    }
	
	public SchemeExpression reduce( Hashtable<Variable,SchemeExpression> aSymTable )
    {
    	return this;
    }
	
    public String toString()
    {
		return (new StringBuilder())
			.append( "(lambda " )
			.append( fVariable.toString() )
			.append( " . " )
			.append( fBody.toString() )
			.append( ")" )
			.toString();
    }

}
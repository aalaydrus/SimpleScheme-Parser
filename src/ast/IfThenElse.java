package ast;

import java.util.Hashtable;
import java.util.Set;

public class IfThenElse extends SchemeExpression
{
	private SchemeExpression fCondition;
	
	public SchemeExpression getCondition()
	{
		return fCondition;
	}
	
	private SchemeExpression fThen;
	
	public SchemeExpression getThen()
	{
		return fThen;
	}
	
	private SchemeExpression fElse;
	
	public SchemeExpression getElse()
	{
		return fElse;
	}
	
	public IfThenElse( SchemeExpression aCondition, SchemeExpression aThen, SchemeExpression aElse )
	{
		fCondition = aCondition;
		fThen = aThen;
		fElse = aElse;
	}
	
	public Set<Variable> freeNames() 
	{
    	Set<Variable> Result = fCondition.freeNames();

    	Result.addAll( fThen.freeNames() );
        Result.addAll(  fElse.freeNames() );

        return Result;
	}

	public SchemeExpression substitute( Variable x, SchemeExpression e ) 
	{		
		return new IfThenElse ( fCondition.substitute( x, e ),
				                fThen.substitute( x, e ),
				                fElse.substitute( x, e ) );
	}
	

	public SchemeExpression reduce(Hashtable<Variable, SchemeExpression> aSymTable) 
	{
		SchemeExpression condition = fCondition.reduce(aSymTable);
		
		if(condition instanceof Constant)
		{
			Constant constCond = (Constant) condition;
			
			if(constCond.getValue() == 0)
			{
				return fThen.reduce(aSymTable);
			}
			else
			{
				return fElse.reduce(aSymTable);
			}
		}
		else 
		{
			throw new RuntimeException("Error! Condition does not evaluate to a Constant ");
		}
	}

	public String toString() 
	{
		return (new StringBuilder())
			.append( "(if " )
			.append( fCondition.toString() )
			.append( " " )
			.append( fThen.toString() )
			.append( " " )
			.append( fElse.toString() )
			.append( ")" )
			.toString();
	}

}
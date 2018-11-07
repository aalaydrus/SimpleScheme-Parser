package ast.builtin;

import ast.Constant;
import ast.SchemeExpression;
import ast.Variable;

public class IncrementAbstraction extends BuiltInAbstraction
{
	// we privately encapsulate the corresponding built-in in the built-in abstraction
	private static class Increment extends BuiltIn
	{
		public Increment( Variable aVariable )
		{
			super( aVariable );
		}
		public SchemeExpression substitute( Variable x, SchemeExpression e )
		{
			if ( getVariable().equals( x ) )
			{
				if ( e instanceof Constant )
				{
					Double lNumber = ((Constant)e).getValue() + 1.0;
					return new Constant( lNumber.toString() );
				}
				else
					throw new ArithmeticException( "Illegal argument: " + e );
			}
			else
				return this;
		}
		public String toString()
		{
			return (new StringBuilder())
					.append( "incr(" )
					.append( getVariable().toString() )
					.append( ")" )
					.toString();
		}
	}
	public IncrementAbstraction()
	{
		super( new Increment( new Variable( "x" ) ) );
	}
}
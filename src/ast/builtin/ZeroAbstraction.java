package ast.builtin;

import ast.Constant;
import ast.SchemeExpression;
import ast.Variable;

public class ZeroAbstraction extends BuiltInAbstraction
{
	// we privately encapsulate the corresponding built-in in the built-in abstraction
	private static class Zero extends BuiltIn
	{
		public Zero( Variable aVariable )
		{
			super( aVariable );
		}
		public SchemeExpression substitute( Variable x, SchemeExpression e )
		{
			if ( getVariable().equals( x ) )
			{
				if ( e instanceof Constant )
				{
					Double lNumValue = ((Constant)e).getValue();
					
					if(lNumValue == 0.0) 
					{
						return new Constant( "0.0" );
					}
					else
					{
						return new Constant("1.0");
					}
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
					.append( "zero(" )
					.append( getVariable().toString() )
					.append( ")" )
					.toString();
		}
	}
	public ZeroAbstraction()
	{
		super( new Zero( new Variable( "x" ) ) );
	}
}
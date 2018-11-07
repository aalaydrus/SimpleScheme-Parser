package ast.builtin;

import java.util.HashSet;
import java.util.Set;
import ast.Abstraction;
import ast.SchemeExpression;
import ast.Variable;

public abstract class BuiltInAbstraction extends Abstraction
{
	public BuiltInAbstraction( BuiltIn aBody )
	{
		// the built-in abstraction's variable is defined by built-in body
		super( null, aBody );
	}
	public Variable getVariable()
	{
		// the built-in abstraction's variable is defined by built-in body
		return ((BuiltIn)getBody()).getVariable();
	}
	public Set<Variable> freeNames()
	{
		// built-in abstraction has no free variables
		return new HashSet<Variable>();
	}
	public SchemeExpression substitute( Variable x, SchemeExpression e )
	{
		// built-in abstraction has not free variables
		return this;
	}
	public String toString()
	{
		// the built-in abstraction's representation is defined by built-in body
		return getBody().toString();
	}
}
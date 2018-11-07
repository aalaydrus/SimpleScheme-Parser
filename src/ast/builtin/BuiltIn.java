package ast.builtin;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import ast.SchemeExpression;
import ast.Variable;

public abstract class BuiltIn extends SchemeExpression
{
	private Variable fVariable;
	public Variable getVariable()
	{
		return fVariable;
	}
	public BuiltIn( Variable aVariable )
	{
		fVariable = aVariable;
	}
	public Set<Variable> freeNames()
	{
		return new HashSet<Variable>();
	}

	public SchemeExpression reduce( Hashtable<Variable,SchemeExpression> aSymTable )
	{
		return this;
	}
}
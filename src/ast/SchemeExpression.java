package ast;

import java.util.Set;
import java.util.Hashtable;

public abstract class SchemeExpression 
{
	public abstract Set<Variable> freeNames();
	
	public abstract SchemeExpression substitute( Variable x, SchemeExpression e );
	
	public abstract SchemeExpression reduce( Hashtable<Variable,SchemeExpression> aSymTable );
	
	public abstract String toString();
}
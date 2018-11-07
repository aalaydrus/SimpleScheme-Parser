package ast;

import java.util.Hashtable;
import java.util.Set;

public class Application extends SchemeExpression {
	private SchemeExpression fFtn;

	public SchemeExpression getFunction() {
		return fFtn;
	}

	private SchemeExpression fArg;

	public SchemeExpression getArgument() {
		return fArg;
	}

	public Application(SchemeExpression aFtn, SchemeExpression aArg) {
		fFtn = aFtn;
		fArg = aArg;
	}

	public Set<Variable> freeNames() {
		Set<Variable> Result = fFtn.freeNames();

		Result.addAll(fArg.freeNames());

		return Result;
	}

	public SchemeExpression substitute(Variable x, SchemeExpression e) {
		return new Application(getFunction().substitute(x, e), getArgument().substitute(x, e));
	}

	public SchemeExpression reduce(Hashtable<Variable, SchemeExpression> aSymTable) {
		SchemeExpression function = fFtn.reduce(aSymTable);
		SchemeExpression argument = fArg.reduce(aSymTable);

		if (function instanceof Abstraction) {
			Abstraction absFunc = (Abstraction) function;

			// [e2/x]e1
			SchemeExpression lNewBody = absFunc.getBody().substitute(absFunc.getVariable(), argument); // getBody to get the inner expression
			lNewBody = lNewBody.reduce(aSymTable);
			return lNewBody;
		} 
		else 
		{
			return new Application(function, argument);
		}
	}

	public String toString() {
		return (new StringBuilder()).append("(").append(fFtn.toString()).append(" ").append(fArg.toString()).append(")")
				.toString();
	}

}
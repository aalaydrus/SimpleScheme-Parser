package ast;

import java.util.Set;

import parser.SimpleSchemeParser;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

public class Load extends SchemeExpression 
{
	private String fUnitName;

	public String getUnitName()
	{
		return fUnitName;
	}

	public Load( String aUnitName )
	{
		fUnitName = aUnitName;

		if ( !fUnitName.endsWith( ".ssu" ) )
			fUnitName += ".ssu";
	}

	public Set<Variable> freeNames()
	{
		return new HashSet<Variable>();
	}

	public SchemeExpression substitute( Variable x, SchemeExpression e )
	{
		return this;
	}

	public SchemeExpression reduce(Hashtable<Variable, SchemeExpression> aSymTable) 
	{
		try
		{
			if ( !fUnitName.endsWith( ".ssu" ) )
				fUnitName += ".ssu";

			SimpleSchemeParser lParser = new SimpleSchemeParser( new FileInputStream( fUnitName ) );

			ArrayList<SchemeExpression> lExpressions = lParser.SimpleSchemeUnit();

			SchemeExpression Result = null;

			for ( SchemeExpression e : lExpressions )
			{
				Result = e.reduce( aSymTable );
			}

			return Result;
		}
		catch (Exception e)
		{
			throw new RuntimeException("Error! Caught an exception in Load ");
		}
	}

	public String toString()
	{
		return (new StringBuilder())
				.append( "(load \"" )
				.append( fUnitName )
				.append( "\")" )
				.toString();
	}

}
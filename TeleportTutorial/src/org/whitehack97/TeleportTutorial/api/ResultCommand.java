package org.whitehack97.TeleportTutorial.api;

public class ResultCommand
{
	public enum Type
	{
		CONSOLE,
		OPERATOR,
		PLAYER,
		MONEY,
		UNKNOWN,
	}

	private Type senderType = Type.UNKNOWN;
	private String command;
	
	public Type ValueOf(String s)
	{
		if(s.equalsIgnoreCase("CONSOLE")) return Type.CONSOLE;
		else if(s.equalsIgnoreCase("OP")) return Type.OPERATOR;
		else if(s.equalsIgnoreCase("MONEY")) return Type.MONEY;
		else return Type.PLAYER;
	}
	
	public void RunCommand()
	{
		
	}
	
	public void setType(Type sender)
	{
		this.senderType  = sender;
	}

	public void setCommand(String command)
	{
		this.command  = command;
	}
	
	public Type getSenderType()
	{
		return this.senderType;
	}
	
	public String getCommand()
	{
		return this.command;
	}
}

package com.unn.serverNetwork.model;

/*
 * Message received from client.
 *
 * @Author Jay Sridhar
 */
public class Message
{
    private String from;
    private int text;

    public Message() {}

    public Message(String from, int text)
    {
	this.from = from;
	this.text = text;
    }

    public String getFrom()
    {
        return from;
    }

    public void setFrom(String from)
    {
        this.from = from;
    }

    public int getText()
    {
        return text;
    }

    public void setText(int text)
    {
        this.text = text;
    }
}

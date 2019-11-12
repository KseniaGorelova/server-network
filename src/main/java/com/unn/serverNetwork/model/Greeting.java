package com.unn.serverNetwork.model;


import java.util.Date;

public class Greeting {

    private String from;
    private int message;
    private String topic;
    private Date time = new Date();

    public Greeting() {}

    public Greeting(String from, int message, String topic)
    {
        this.from = from;
        this.message = message;
        this.topic = topic;
    }

    public String getFrom()
    {
        return from;
    }

    public void setFrom(String from)
    {
        this.from = from;
    }

    public int getMessage()
    {
        return message;
    }

    public void setMessage(int message)
    {
        this.message = message;
    }

    public String getTopic()
    {
        return topic;
    }

    public void setTopic(String topic)
    {
        this.topic = topic;
    }

    public Date getTime()
    {
        return time;
    }

    public String toString()
    {

        return String
                .format("{\"from\":\" %1$-10s\",\"topic\": \"%2$-10s\" \"time\":\" %4$-15d\" \"mesg\": %3$s}",
                        getFrom(), getTopic(),
                        getMessage(), getTime().getTime());
    }

}

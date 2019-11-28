package com.bijay.pdfconverter.Model;


public class Data
{
    private String created_on;

    private String name;

    private String id;

    public String getCreated_on ()
    {
        return created_on;
    }

    public void setCreated_on (String created_on)
    {
        this.created_on = created_on;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [created_on = "+created_on+", name = "+name+", id = "+id+"]";
    }
}


package com.motadatabank.server.database;

public interface Database
{

    void add(Object obj);

    Object get(Object obj);

    void remove(Object obj);

}

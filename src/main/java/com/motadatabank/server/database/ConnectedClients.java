package com.motadatabank.server.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class ConnectedClients implements Database
{

    private static ConnectedClients instance = null;


    public static ConnectedClients getInstance()
    {

        if (instance == null)
        {
            instance = new ConnectedClients();
        }

        return instance;
    }

    private final Vector<String> clients = new Vector<>();


    @Override
    public void add(Object socket)
    {

        clients.add(String.valueOf(socket));

    }

    @Override
    public List<String> get(Object obj)
    {

        return new ArrayList<>(clients);

    }

    @Override
    public void remove(Object socket)
    {

        clients.remove(String.valueOf(socket));

    }

}

package com.motadatabank.server.database;

import java.time.LocalTime;
import java.util.HashMap;


public class ActiveCustomers implements Database
{

    private ActiveCustomers()
    {

    }

    private static ActiveCustomers instance = null;

    public static ActiveCustomers getInstance()
    {

        if (instance == null)
        {

            instance = new ActiveCustomers();

        }

        return instance;

    }

    private final HashMap<Integer, LocalTime> ACTIVE_CUSTOMERS = new HashMap<>();

    public boolean existingLogin(Integer customerId)
    {

        if (ACTIVE_CUSTOMERS.containsKey(customerId))
        {

            if (LocalTime.now().isAfter(get(customerId)))
            {

                ACTIVE_CUSTOMERS.remove(customerId);

                return false;

            }
            else
            {

                add(customerId);

                return true;

            }

        }
        else
        {

            return false;

        }

    }

    @Override
    public void add(Object obj)
    {

        ACTIVE_CUSTOMERS.put(Integer.parseInt(String.valueOf(obj)), LocalTime.now().plusMinutes(2));

    }

    @Override
    public LocalTime get(Object obj)
    {

        return ACTIVE_CUSTOMERS.get(Integer.parseInt(String.valueOf(obj)));

    }

    @Override
    public void remove(Object obj)
    {

        ACTIVE_CUSTOMERS.remove(Integer.parseInt(String.valueOf(obj)));

    }

}

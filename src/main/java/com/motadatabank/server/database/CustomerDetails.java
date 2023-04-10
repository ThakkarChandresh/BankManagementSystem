package com.motadatabank.server.database;

import com.motadatabank.server.model.Customer;

import java.util.HashMap;


public class CustomerDetails implements Database
{

    private CustomerDetails()
    {

    }

    private final HashMap<Integer, Customer> CUSTOMER_DETAILS = new HashMap<>();

    private static CustomerDetails instance = null;

    public static CustomerDetails getInstance()
    {

        if (instance == null)
        {

            instance = new CustomerDetails();

        }

        return instance;
    }

    @Override
    public void add(Object obj)
    {

        Customer customer = (Customer) obj;

        CUSTOMER_DETAILS.put(customer.getAccount().getCustomerId(), customer);
    }

    @Override
    public Customer get(Object obj)
    {

        return CUSTOMER_DETAILS.get(Integer.parseInt(String.valueOf(obj)));

    }

    @Override
    public void remove(Object obj)
    {

        CUSTOMER_DETAILS.remove(Integer.parseInt(String.valueOf(obj)));

    }

}

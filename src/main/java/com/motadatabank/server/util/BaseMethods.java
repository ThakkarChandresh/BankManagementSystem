package com.motadatabank.server.util;


import com.motadatabank.server.database.CustomerDetails;
import com.motadatabank.server.model.Account;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


public class BaseMethods
{

    private static final AtomicInteger ID = new AtomicInteger(1000);

    public static boolean validateCredentials(int customerId, String password)
    {

        Account account = CustomerDetails.getInstance().get(customerId).getAccount();

        return account.getCustomerId() == customerId && account.getPassword().equals(password);

    }

    public static int getUniqueCustomerId()
    {

        return ID.getAndIncrement();

    }


    public static long getAccountNumber()
    {

        return new Random().nextLong(1000000000L, 9999999999L);

    }


}
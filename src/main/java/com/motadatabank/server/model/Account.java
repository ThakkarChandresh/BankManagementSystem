package com.motadatabank.server.model;

public class Account
{

    private final int customerId;

    private final long accountNumber;

    private String password;

    private long amount;

    protected Account(int customerId, long accountNumber)
    {

        this.customerId = customerId;

        this.accountNumber = accountNumber;

    }

    public int getCustomerId()
    {

        return customerId;
    }

    public String getPassword()
    {

        return password;
    }

    public void setPassword(String password)
    {

        this.password = password;

    }

    public long getAmount()
    {

        return amount;

    }

    public void depositAmount(long amount)
    {

        this.amount += amount;

    }

    public void withdrawAmount(long amount)
    {

        this.amount -= amount;

    }

    public long getAccountNumber()
    {

        return accountNumber;

    }


}

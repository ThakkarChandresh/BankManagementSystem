package com.motadatabank.server.model;


import com.motadatabank.server.util.BaseMethods;


public class Customer
{

    private String firstName;

    private String lastName;

    private String emailId;

    private long contactNumber;

    private final Account account = new Account(BaseMethods.getUniqueCustomerId(), BaseMethods.getAccountNumber());

    public Customer()
    {

    }

    public Customer(String firstName, String lastName, String emailId, long contactNumber)
    {

        this.firstName = firstName;

        this.lastName = lastName;

        this.emailId = emailId;

        this.contactNumber = contactNumber;

    }

    public String getFirstName()
    {

        return firstName;
    }

    public void setFirstName(String firstName)
    {

        this.firstName = firstName;
    }

    public String getLastName()
    {

        return lastName;
    }

    public void setLastName(String lastName)
    {

        this.lastName = lastName;
    }

    public String getEmailId()
    {

        return emailId;
    }

    public void setEmailId(String emailId)
    {

        this.emailId = emailId;
    }

    public long getContactNumber()
    {

        return contactNumber;
    }

    public void setContactNumber(long contactNumber)
    {

        this.contactNumber = contactNumber;
    }

    public Account getAccount()
    {

        return account;
    }

    @Override
    public String toString()
    {

        return "Customer{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", emailId='" + emailId + '\'' + ", contactNumber=" + contactNumber + '}';

    }


}

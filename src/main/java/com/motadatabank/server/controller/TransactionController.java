package com.motadatabank.server.controller;

import com.motadatabank.server.database.ActiveCustomers;
import com.motadatabank.server.database.CustomerDetails;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TransactionController
{

    private final Integer customerId;

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public TransactionController(int customerId)
    {

        this.customerId = customerId;

    }

    public Integer getCustomerId()
    {

        return this.customerId;
    }

    private long getBalance()
    {

        return CustomerDetails.getInstance().get(customerId).getAccount().getAmount();

    }

    public void getChoice(DataOutputStream writer, DataInputStream reader) throws IOException
    {

        while (ActiveCustomers.getInstance().existingLogin(customerId))
        {

            int choice = Integer.parseInt(reader.readUTF());

            switch (choice)
            {
                case 1 -> deposit(writer, reader);
                case 2 -> withdraw(writer, reader);
                case 3 -> checkBalance(writer);
                case 4 ->
                {
                    writer.writeUTF("\nLogged Out Successfully");
                    writer.flush();
                    LOGGER.log(Level.INFO, "Customer With Customer ID: " + customerId + " Logged Out Successfully");
                    ActiveCustomers.getInstance().remove(customerId);
                }
            }
        }

    }

    public void deposit(DataOutputStream writer, DataInputStream reader) throws IOException
    {

        if (ActiveCustomers.getInstance().existingLogin(customerId))
        {

            writer.writeUTF("Session Active");

            writer.flush();

            long depositAmount = Long.parseLong(reader.readUTF());

            CustomerDetails.getInstance().get(customerId).getAccount().depositAmount(depositAmount);

            writer.writeUTF("\n₹" + depositAmount + " Deposited Successfully");

            writer.flush();

            LOGGER.log(Level.INFO, "Customer With Customer ID: " + customerId + " Deposited ₹" + depositAmount + " Successfully");

        }
        else
        {

            writer.writeUTF("\nSession Expired");

            writer.flush();

        }

    }

    public void checkBalance(DataOutputStream writer) throws IOException
    {

        if (ActiveCustomers.getInstance().existingLogin(customerId))
        {

            writer.writeUTF("Session Active");

            writer.flush();

            writer.writeUTF("\nYour Account Balance Is: ₹" + getBalance());

            writer.flush();

        }
        else
        {

            writer.writeUTF("\nSession Expired");

            writer.flush();

        }

    }

    public void withdraw(DataOutputStream writer, DataInputStream reader) throws IOException
    {

        if (ActiveCustomers.getInstance().existingLogin(customerId))
        {

            writer.writeUTF("Session Active");

            writer.flush();

            long withdrawAmount = Long.parseLong(reader.readUTF());

            if (withdrawAmount > getBalance())
            {

                writer.writeUTF("\nInsufficient Balance");

                writer.flush();

                LOGGER.log(Level.INFO, "Withdraw of ₹" + withdrawAmount + " for Customer ID:" + customerId + " Is Not Successful Due To Insufficient Balance");

            }
            else
            {

                CustomerDetails.getInstance().get(customerId).getAccount().withdrawAmount(withdrawAmount);

                writer.writeUTF("\n₹" + withdrawAmount + " Withdraw Successfully");

                writer.flush();

                LOGGER.log(Level.INFO, "Customer With Customer ID: " + customerId + " Withdraw ₹" + withdrawAmount + " Successfully");

            }

        }
        else
        {

            writer.writeUTF("\nSession Expired");

            writer.flush();

        }
    }

}

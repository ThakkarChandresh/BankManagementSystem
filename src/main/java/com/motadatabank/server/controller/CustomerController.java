package com.motadatabank.server.controller;

import com.motadatabank.server.database.ActiveCustomers;
import com.motadatabank.server.database.ConnectedClients;
import com.motadatabank.server.database.CustomerDetails;
import com.motadatabank.server.model.Customer;
import com.motadatabank.server.util.BaseMethods;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CustomerController
{

    private Integer customerId;

    public Integer getCustomerId()
    {

        return this.customerId;

    }

    public CustomerController(String socket)
    {

        ConnectedClients.getInstance().add(socket);

    }

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public void login(DataOutputStream writer, DataInputStream reader) throws IOException
    {

        int customerId = Integer.parseInt(reader.readUTF());

        String password = reader.readUTF();

        if (BaseMethods.validateCredentials(customerId, password))
        {

            if (ActiveCustomers.getInstance().existingLogin(customerId))
            {
                LOGGER.log(Level.INFO, "Customer With Customer ID:" + customerId + "Attempted To Re Login");

                writer.writeUTF("\nAnother session is active please close that session to continue ");

                writer.flush();

            }
            else
            {

                ActiveCustomers.getInstance().add(customerId);

                this.customerId = customerId;

                writer.writeUTF("\nLogged In Successfully");

                writer.flush();

                LOGGER.log(Level.INFO, "Customer With Customer ID:" + customerId + " Logged In");

                TransactionController controller = new TransactionController(customerId);

                controller.getChoice(writer, reader);

            }
        }
        else
        {

            writer.writeUTF("\nCustomer ID or Username incorrect");

            writer.flush();

        }
    }


    public void register(DataOutputStream writer, DataInputStream reader) throws IOException
    {

        String firstName = reader.readUTF();

        String lastName = reader.readUTF();

        String email = reader.readUTF();

        long contactNumber = Long.parseLong(reader.readUTF());

        String password = reader.readUTF();

        Customer newCustomer = new Customer(firstName, lastName, email, contactNumber);

        newCustomer.getAccount().setPassword(password);

        CustomerDetails.getInstance().add(newCustomer);

        writer.writeUTF("\n* * * * * * Account Created Successfully * * * * * *\n\nWelcome Dear " + firstName + " " + lastName + "\nYour Customer Id Is:" + newCustomer.getAccount().getCustomerId() + "\nYour Account Number Is:" + newCustomer.getAccount().getAccountNumber());

        writer.flush();

        LOGGER.log(Level.INFO, "New Customer Account Is Created With Customer ID:" + newCustomer.getAccount().getCustomerId());
    }

}

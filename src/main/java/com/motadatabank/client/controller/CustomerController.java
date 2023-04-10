package com.motadatabank.client.controller;

import com.motadatabank.client.util.BaseMethods;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class CustomerController
{

    private static String message;

    public static void login(DataOutputStream writer, BufferedReader reader, DataInputStream serverReader) throws IOException
    {

        String input;

        String customerId;

        String password;

        System.out.println("\n* * * * * * Login * * * * * *");

        System.out.print("\nPlease Enter Your Customer ID: ");

        if ((input = BaseMethods.validate(reader.readLine(), BaseMethods::isNumber, reader, "Invalid Customer ID Enter A Valid Customer ID: ", 2)) != null)
        {

            customerId = input;

        }
        else
        {

            return;

        }

        System.out.print("\nPlease Enter Your Password: ");

        if ((input = BaseMethods.validate(reader.readLine(), BaseMethods::isValidPassword, reader, "Invalid Password!\nPlease Enter Correct Password: ", 2)) != null)
        {

            password = input;

        }
        else
        {

            return;

        }

        writer.writeUTF("1");

        writer.flush();

        writer.writeUTF(customerId);

        writer.flush();

        writer.writeUTF(password);

        writer.flush();

        message = serverReader.readUTF();

        System.out.println(message);

        if (BaseMethods.checkSuccessfulLogin(message))
        {

            TransactionController controller = new TransactionController();

            controller.showMainMenu(writer, reader, serverReader);

        }

    }

    public static void register(DataOutputStream writer, BufferedReader reader, DataInputStream serverReader) throws IOException
    {

        String input;

        String firstName;

        String lastName;

        String emailID;

        String contactNumber;

        String password;

        System.out.println("\n* * * * * * Register * * * * * *");

        System.out.print("\nPlease Enter Your First Name: ");

        if ((input = BaseMethods.validate(reader.readLine(), BaseMethods::isValidName, reader, "Please Enter A Valid First Name: ", 2)) != null)
        {

            firstName = input;

        }
        else
        {

            return;

        }

        System.out.print("\nPlease Enter Your Last Name: ");

        if ((input = BaseMethods.validate(reader.readLine(), BaseMethods::isValidName, reader, "Please Enter A Valid Last Name: ", 2)) != null)
        {

            lastName = input;

        }
        else
        {

            return;

        }

        System.out.print("\nPlease Enter Your E-mail Id: ");

        if ((input = BaseMethods.validate(reader.readLine(), BaseMethods::isValidEmail, reader, "Please Enter A Valid Email ID: ", 2)) != null)
        {

            emailID = input;

        }
        else
        {

            return;

        }

        System.out.print("\nPlease Enter Your Contact Number: ");

        if ((input = BaseMethods.validate(reader.readLine(), BaseMethods::isValidContactNumber, reader, "Please Enter A Contact Number: ", 2)) != null)
        {

            contactNumber = input;

        }
        else
        {

            return;

        }

        System.out.print("\nPlease Enter Password For Your Account: ");

        if ((input = BaseMethods.validate(reader.readLine(), BaseMethods::isValidPassword, reader, "Please Enter A Strong Password: ", 2)) != null)
        {

            password = input;

        }
        else
        {

            return;

        }

        writer.writeUTF("2");

        writer.flush();

        writer.writeUTF(firstName);

        writer.flush();

        writer.writeUTF(lastName);

        writer.flush();

        writer.writeUTF(emailID);

        writer.flush();

        writer.writeUTF(contactNumber);

        writer.flush();

        writer.writeUTF(password);

        writer.flush();

        message = serverReader.readUTF();

        System.out.println(message);
    }

}

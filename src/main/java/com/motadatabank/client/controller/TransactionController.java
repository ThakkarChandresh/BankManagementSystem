package com.motadatabank.client.controller;

import com.motadatabank.client.util.BaseMethods;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class TransactionController
{

    public void showMainMenu(DataOutputStream writer, BufferedReader reader, DataInputStream serverReader) throws IOException
    {

        mainMenu:
        while (true)
        {

            System.out.println("\nSelect Appropriate Choice\n1.Deposit\n2.Withdraw\n3.Check Balance\n4.Logout\nEnter Your Choice: ");

            String choice = reader.readLine();

            switch (choice)
            {

                case "1":

                    if (deposit(writer, reader, serverReader))
                    {

                        break;

                    }
                    else
                    {

                        break mainMenu;

                    }

                case "2":

                    if (withdraw(writer, reader, serverReader))
                    {

                        break;

                    }
                    else
                    {

                        break mainMenu;

                    }

                case "3":

                    if (checkBalance(serverReader, writer))
                    {

                        break;

                    }
                    else
                    {

                        break mainMenu;

                    }

                case "4":

                    writer.writeUTF(choice);

                    writer.flush();

                    System.out.println(serverReader.readUTF());

                    break mainMenu;

                default:

                    System.out.println("Invalid Choice");

            }
        }

    }

    public boolean deposit(DataOutputStream writer, BufferedReader reader, DataInputStream serverReader) throws IOException
    {

        String amount;

        System.out.print("Enter Deposit Amount: ");

        if ((amount = BaseMethods.validate(reader.readLine(), BaseMethods::isValidAmount, reader, "Please Enter A Valid Amount:", 2)) == null)
        {

            return true;

        }

        writer.writeUTF("1");

        writer.flush();

        String message = serverReader.readUTF();

        if (BaseMethods.isActive(message))
        {

            writer.writeUTF(amount);

            writer.flush();

            message = serverReader.readUTF();

            System.out.println(message);

            return true;

        }
        else
        {

            System.out.println(message);

            return false;

        }

    }

    public boolean checkBalance(DataInputStream serverReader, DataOutputStream writer) throws IOException
    {

        writer.writeUTF("3");

        writer.flush();

        String message = serverReader.readUTF();

        if (BaseMethods.isActive(message))
        {
            message = serverReader.readUTF();

            System.out.println(message);

            return true;
        }
        else
        {

            System.out.println(message);

            return false;

        }
    }

    public boolean withdraw(DataOutputStream writer, BufferedReader reader, DataInputStream serverReader) throws IOException
    {

        String amount;

        System.out.print("Enter Amount To Withdraw: ");

        if ((amount = BaseMethods.validate(reader.readLine(), BaseMethods::isValidAmount, reader, "Please Enter A Valid Amount:", 2)) == null)
        {

            return true;

        }

        writer.writeUTF("2");

        writer.flush();

        String message = serverReader.readUTF();

        if (BaseMethods.isActive(message))
        {

            writer.writeUTF(amount);

            writer.flush();

            message = serverReader.readUTF();

            System.out.println(message);

            return true;

        }
        else
        {

            System.out.println(message);

            return false;

        }
    }


}

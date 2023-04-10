package com.motadatabank.client;

import com.motadatabank.client.controller.CustomerController;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;


public class BankClient
{

    public static void main(String[] args)
    {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); Socket socket = new Socket(InetAddress.getLocalHost(), 6565); DataOutputStream writer = new DataOutputStream(socket.getOutputStream()); DataInputStream serverReader = new DataInputStream(socket.getInputStream()))
        {

            System.out.println("* * * * * * Welcome To Motadata Bank * * * * * *");

            while (!Thread.currentThread().isInterrupted())
            {

                System.out.print("\nSelect Appropriate Choice\n1.Existing User? Login\n2.New User? Register\n3.Exit\nEnter Your Choice: ");

                String choice = reader.readLine();

                switch (choice)
                {
                    case "1" -> CustomerController.login(writer, reader, serverReader);
                    case "2" -> CustomerController.register(writer, reader, serverReader);
                    case "3" ->
                    {
                        System.out.println("\n* * * * * * Thank You! * * * * * *");
                        Thread.currentThread().interrupt();
                    }
                    default -> System.out.println("\nInvalid Choice Please Enter A Valid Choice!");
                }
            }
        }
        catch (Exception exception)
        {

            System.out.println("\nBank Server Is Temporarily Down! Please Try Again After Some Time!");

        }

    }

}

package com.motadatabank.server;

import com.motadatabank.server.controller.CustomerController;
import com.motadatabank.server.controller.TransactionController;
import com.motadatabank.server.database.ActiveCustomers;
import com.motadatabank.server.database.ConnectedClients;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BankServer
{

    private static final ExecutorService THREAD_POOL = Executors.newFixedThreadPool(21);

    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    public static void main(String[] args)
    {

        try (ServerSocket serverSocket = new ServerSocket(6565))
        {
            /*System.out.println("Press 1 To Get Connected Clients: ");

            Runnable inputReader = () -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)))
                {

                    while (serverSocket.isBound())
                    {

                        if (reader.readLine().equals("1"))
                        {
                            ConnectedClients.getConnectedClients();
                        }

                    }
                }
                catch (Exception exception)
                {

                    System.out.println("Some Error Occurred!");

                }
            };

            THREAD_POOL.submit(inputReader);*/

            while (serverSocket.isBound())
            {

                Socket socket = serverSocket.accept();

                Runnable customerJob = () -> {

                    CustomerController controller = null;

                    LOGGER.log(Level.INFO, "New Client Connected With Socket Address:" + socket.getRemoteSocketAddress());

                    try (DataOutputStream writer = new DataOutputStream(socket.getOutputStream()); DataInputStream reader = new DataInputStream(socket.getInputStream()))
                    {

                        controller = new CustomerController(socket.getRemoteSocketAddress().toString());

                        int choice;

                        while (socket.isConnected())
                        {
                            choice = Integer.parseInt(reader.readUTF());

                            if (choice == 1)
                            {
                                controller.login(writer, reader);


                            }
                            else if (choice == 2)
                            {

                                controller.register(writer, reader);

                            }
                        }

                    }
                    catch (Exception exception)
                    {

                        ConnectedClients.getInstance().remove(socket.getRemoteSocketAddress().toString());

                        if (controller != null && controller.getCustomerId() != null)
                        {

                            ActiveCustomers.getInstance().remove(controller.getCustomerId());

                        }

                        LOGGER.log(Level.INFO, "Client Disconnected With Socket Address:" + socket.getRemoteSocketAddress());

                    }
                };

                THREAD_POOL.execute(customerJob);

            }

        }
        catch (Exception exception)
        {

            LOGGER.log(Level.WARNING, exception.getMessage());

        }

    }

}

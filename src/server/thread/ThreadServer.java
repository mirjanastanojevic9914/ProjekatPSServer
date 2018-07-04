/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import server.Server;

/**
 *
 * @author Stefana
 */
public class ThreadServer extends Thread {

    ServerSocket serverSocket;
    boolean serverActive = true;

    public ThreadServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        serverActive = true;
        listenForClients();
    }

    public void closeThreadServer() throws IOException {
        for (int i = 0; i < Server.getClients().size(); i++) {
            ThreadClient client = Server.getClients().get(i);
            System.out.println(client);
            client.closeThreadClient();
            client.interrupt();
        }
        Server.getClients().clear();
        serverActive = false;
    }

    private void listenForClients() {
        try {
            while (serverActive) {
                System.out.println("Server is listening for clients.");
                Socket socket = serverSocket.accept();
                System.out.println("Client has connected");
                ThreadClient threadClient = new ThreadClient(socket);
                Server.getClients().add(threadClient);
                threadClient.start();
            }
        } catch (IOException ex) {
//            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Server has stopped running.");
    }
}

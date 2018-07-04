/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.thread;

import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.Server;

/**
 *
 * @author Stefana
 */
public class ThreadClient extends Thread {

    private Socket socket;
    boolean klijentActive = true;

    public ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        serveClient();
    }

    public void closeThreadClient() throws IOException {
        klijentActive = false;
        socket.close();
        System.out.println("Client socket closed: " + socket.isClosed());
    }

    private void serveClient() {
        while (klijentActive) {
            try {
                try {
                    Server.getInstance().processClient(socket);
                } catch (SocketException | EOFException ex) {
                    System.out.println("Client left\nClosing client");
                    //socket.close();
                    //klijentActive = false;
                    closeThreadClient();
                    Server.getClients().remove(this);
                    //System.out.println("Client is closed");
                }
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error happended while closing client socket.");
                klijentActive= false;
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }
}

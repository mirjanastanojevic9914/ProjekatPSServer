/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.client;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import server.thread.ThreadClient;

/**
 *
 * @author Mira
 */
public class ClientTableModel extends AbstractTableModel{

   
    private List<ThreadClient> clients;
    private String[] columns = {"Thread:", "Socket:"};

    public ClientTableModel(List<ThreadClient> clients) {
        this.clients = clients;
    }

    
    
    @Override
    public int getRowCount() {
        if (clients == null) {
            return 0;
        }
        return clients.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ThreadClient client = clients.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return client.toString();
            case 1:
                return client.getSocket().toString();
            default:
                return "N/A";
        }
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }
    
    public ThreadClient returnClient(int rowIndex) {
        return clients.get(rowIndex);
    }
}

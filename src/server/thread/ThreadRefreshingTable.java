/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.thread;

import form.server.ServerForm;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mira
 */
public class ThreadRefreshingTable extends Thread{
     ServerForm sf;

    public ThreadRefreshingTable(ServerForm sf) {
        this.sf = sf;
    }

    @Override
    public void run() {

        while (true) {            
            sf.arrangeTable();
            
            try {
                sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadRefreshingTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

package org.apache.commons.pool2.me.tcp;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by guilin on 2016/9/18.
 */
public class Client {

    public static void main(String[] args) {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(2);
        ConnectionPoolFactory poolFactory = new ConnectionPoolFactory(config, "127.0.0.1", 8081);
        for(int i=0;i<10;i++){

            Socket socket = null;
            try {
                socket = poolFactory.getConnection();
                System.out.println(config);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (socket != null) {
                    poolFactory.releaseConnection(socket);
                }
            }
        }
    }

    @Test
    public void test1() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8081);
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println(socket);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

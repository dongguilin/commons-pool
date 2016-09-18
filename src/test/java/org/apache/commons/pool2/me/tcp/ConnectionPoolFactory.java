package org.apache.commons.pool2.me.tcp;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by guilin on 2016/9/18.
 */
public class ConnectionPoolFactory {

    private GenericObjectPool pool;

    public ConnectionPoolFactory(GenericObjectPoolConfig config, String ip, int port) {
        ConnectionFactory factory = new ConnectionFactory(ip, port);
        pool = new GenericObjectPool(factory, config);
    }

    public Socket getConnection() throws Exception {
        return (Socket) pool.borrowObject();
    }

    public void releaseConnection(Socket socket) {
        try {
            pool.returnObject(socket);
        } catch (Exception e) {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e1) {
                    socket = null;
                }
            }
        }
    }
}

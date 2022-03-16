package com.story.storyadmin.framework.tcp.demo2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

public class BIOClient implements Client {
    private final Socket socket;
    private final BufferedOutputStream out;
    private final BufferedInputStream in;
    private final ReciveRegister reciveRegister;
    private final SendRegister sendRegister;

    public BIOClient(String host, int port, ReciveRegister reciveRegister, SendRegister sendRegister) throws IOException {
        socket = new Socket("127.0.0.1", 80);
        out = new BufferedOutputStream(socket.getOutputStream());
        in = new BufferedInputStream(socket.getInputStream());
        this.reciveRegister = reciveRegister;
        this.sendRegister = sendRegister;
    }

    @Override
    public Socket getSocket() {
        return this.socket;
    }

    @Override
    public byte[] send(Object data) {
        byte[] re = null;
        try {
            re = sendRegister.send(socket, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return re;
    }

    @Override
    public void close() {
        try {
            if (out != null) {
                out.flush();
                out.close();
            }
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
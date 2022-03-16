package com.story.storyadmin.framework.tcp.demo2;

import java.io.IOException;

public class ClientFactory {
    public static Client getClient(String host, int port, SocketType socketType, DelimitType reciveType, DelimitType sendType) {
        ReciveRegister reciveRegister = null;
        SendRegister sendRegister = null;
        Client client = null;
        switch (reciveType) {
            case LengthFlag:
                reciveRegister = new LengthFlagReciveRegister();
                break;
            case commonFlag:
                break;
            case fixedLength:
                break;
        }
        switch (sendType) {
            case LengthFlag:
                sendRegister = new LengthFlagSendRegister(reciveRegister);
                break;
            case fixedLength:
                break;
            case commonFlag:
                break;
        }
        switch (socketType) {
            case BIO:
                try {
                    client = new BIOClient(host, port, reciveRegister, sendRegister);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case NIO:
                break;
        }
        return client;
    }
}
package com.story.storyadmin.basic.net.websocket;

/**
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
 */

/**
 * WebSocket 的简单用例 https://www.cnblogs.com/niuyourou/p/14070380.html
 * WebSoket 相较于 HTTP ，有以下优点：
 *
 * 　　1、包头更短。在建立长连接等需要轮询发送请求确认连接状态的情况下，包头的减小使得服务端压力更小，节省服务端资源。在不包含扩展的情况下，对于服务器到客户端的内容，此头部大小只有2至10字节（和数据包长度有关）；对于客户端到服务器的内容，此头部还需要加上额外的4字节的掩码。相对于HTTP请求每次都要携带完整的头部，此项开销显著减少了。
 *
 * 　　2、全双工。虽然 HTTP 本身便是基于 TCP 协议的，但是 HTTP 的 请求-应答 机制先天限制了服务端主动向客户端发送数据。
 *
 * 　　3、有状态连接。与HTTP不同的是，Websocket需要先创建连接，这就使得其成为一种有状态的协议，之后通信时可以省略部分状态信息。而HTTP请求可能需要在每个请求都携带状态信息。　　
 *
 * 　　4、更好的二进制支持。Websocket定义了二进制帧，相对HTTP，可以更轻松地处理二进制内容。
 *
 * 　　5、可以支持扩展。Websocket定义了扩展，用户可以扩展协议、实现部分自定义的子协议。如部分浏览器支持压缩等。
 *
 * 　　6、更好的压缩效果。相对于HTTP压缩，Websocket在适当的扩展支持下，可以沿用之前内容的上下文，在传递类似的数据时，可以显著地提高压缩率。
 */
/**
public class MyWebSocketServer extends WebSocketServer { // Java服务端
    public MyWebSocketServer(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
        System.out.println("webSocketServer started at port: " + port);
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        System.out.println("new connect with : " + webSocket.getRemoteSocketAddress().getAddress());
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        System.out.println("connect closed : " + webSocket.getRemoteSocketAddress().getAddress());
    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        System.out.println("new Message : " + s);
        webSocket.send(" server has recived message : " + s);
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {

    }

    @Override
    public void onStart() {

    }

    public static void main(String[] args) throws Exception {
        new MyWebSocketServer(9000).start();
    }
}

 **/


/**
 * JS 写一个客户端：
 *function WebSocketTest(msg) {
 *     if ("WebSocket" in window) {
 *         var ws = new WebSocket("ws://localhost:9000");
 *         ws.onopen = function () {
 *             ws.send(msg);
 *         };
 *         ws.onmessage = function (evt) {
 *             alert("recive message : " + evt.data);
 *         };
 *         ws.onclose = function () {
 *             alert("connection closed");
 *         };
 *         return ws;
 *     } else {
 *         alert("您的浏览器不支持 WebSocket!");
 *     }
 * }
 *
 * var ws = WebSocketTest('build connect');
 * if (ws.readyState == 1) {
 *     ws.send("斯巴达！");
 * } else {
 *     setTimeout('ws.send("斯巴达！")', 500);
 * }
 *
 *
 *
 *
 *
 *
 *
 *
 * **/
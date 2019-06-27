package com.iot.base.socket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.util.HashMap;

/**
 * @author zhengnaishan
 * @date 2019/4/22 0022
 * @describe :
 */
public class SocketTest {
    public static void main(String[] args) {
        try { // 这里用的binance的socket接口，国内调用需要VPN，使用换成你的就行 //
            //     String url = "wss://stream.binance.com:9443/ws/ethbtc@ticker";
            //            String url = "wss://stream.binance.com:9443/ws/ethbtc@depth20";
            String url = "ws://localhost:8085/hello/1231";
            URI uri = new URI(url);
            WebSocketClient mWs = new WebSocketClient(uri){
                @Override
                public void onOpen(ServerHandshake serverHandshake) {

                }

                @Override
                public void onMessage(String s) {
                    System.out.println(s);
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    System.out.println("断开了");
                }

                @Override
                public void onError(Exception e) {
                    e.printStackTrace();
                }
            };
            System.out.println(mWs.isOpen());
            mWs.connect();
            Thread.sleep(3000);
            System.out.println(mWs.isOpen());
          //  mWs.send("非常成功".getBytes("utf-8"));



            byte[] bytes = null;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                SocketEntity socketEntity =new SocketEntity();
                socketEntity.setName("测试名");
                socketEntity.setId("12312");
             /*   HashMap map = new HashMap<>();
                map.put(socketEntity.getId(),socketEntity);*/
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(socketEntity);
                oos.flush();
                bytes = bos.toByteArray ();
                oos.close();
                bos.close();
                mWs.send(bytes);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

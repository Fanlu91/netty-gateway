package com.flhai.gateway;


import com.flhai.gateway.inbound.HttpInboundServer;

import java.util.Arrays;

public class NettyServerApplication {
    
    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "3.0.0";
    
    public static void main(String[] args) {

        String proxyPort = System.getProperty("BackendPort","8888");
        // 这是多个后端url走随机路由的例子
//        String backendServer = System.getProperty("backendServer","http://localhost:8801,http://localhost:8802");
        String backendServer = System.getProperty("backendServer","http://localhost:8088");

        int port = Integer.parseInt(proxyPort);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" starting...");

        HttpInboundServer server = new HttpInboundServer(port, Arrays.asList(backendServer.split(",")));
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" started at http://localhost:" + port + " for com.flhai.server:" + server.toString());
        try {
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

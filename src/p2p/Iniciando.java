/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2p;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 *
 * @author kevin
 */
public class Iniciando {
    String MyName,netIntWilan,netIntEth;
    InetAddress ip;
    int port;        
    HashMap<String,String> map;
    ExecutorService ex ;

    public Iniciando(String MyName, int port) {
        this.MyName = MyName;
        this.port = port;
        map = new HashMap<>();
        ex = Executors.newCachedThreadPool();
    }        
    
    void TurnUp() throws SocketException{        
        netIntWilan = "wlp7s0";        
        netIntEth = "enp9s0";
        NetworkInterface net = NetworkInterface.getByName(netIntWilan);
        for (InterfaceAddress IntAddr : net.getInterfaceAddresses()) {
            InetAddress Addr = IntAddr.getBroadcast();
            if (Addr==null) {                
            }
            else
                ip=Addr;
        }        
        System.out.println("Ip de BroadCast\t"+ip.getHostAddress());    
        ex.submit(new Cliente(ip, port, MyName,map));
        ex.submit(new Server(ip, port,MyName,map));   
        
    }
    
}

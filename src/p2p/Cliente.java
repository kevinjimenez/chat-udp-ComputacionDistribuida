/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2p;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;



/**
 *
 * @author kevin
 */
public class Cliente extends Thread{

    String nombre,readLine;
    InetAddress AddrBrodcast;
    DatagramSocket ds;
    DatagramPacket dp;
    int port;    
    byte [] buffer;
    BufferedReader in;
    private HashMap<String,String> map;
    
    public Cliente(InetAddress ipBroadcast,int port,String name,HashMap<String,String> map) throws SocketException{
        this.nombre=name;
        this.AddrBrodcast=ipBroadcast;
        this.port=port;        
        this.map=map;
        this.ds=new DatagramSocket();
        
    }
    @Override
    public void run() {
     //enviar();
        buffer = new byte[256];
        while (true) {            
            try {                
                readLine=null;
                in = new BufferedReader(new InputStreamReader(System.in));
                readLine = in.readLine();
                if (readLine.compareTo("--listar")==0) {
                    map.clear();
                }
                buffer=readLine.getBytes();
                dp = new DatagramPacket(buffer,buffer.length,AddrBrodcast,port);                 
                ds.send(dp);            
                if (readLine.compareTo("--listar")==0) {                
                        Thread.sleep(1000);
                        for (Map.Entry<String, String> entry : map.entrySet()) {
                            String key = entry.getKey();
                            String value = entry.getValue();
                            System.out.println("Usuario Connectado: "+key+"\t"+value);
                        }               
                }                
            } catch (Exception e) {
            }
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2p;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
public class Server extends Thread{

    private InetAddress addrBrodcast;
    private DatagramSocket ds;
    private String nombre,msjRecive;
    private int port;    
    byte [] buffer;
    private HashMap<String,String> map;
    DatagramPacket dp;
   
    public Server(InetAddress addrBrodcast,int port,String name,HashMap<String,String> map) throws SocketException{
        this.addrBrodcast = addrBrodcast;
        this.nombre=name;
        this.port=port;        
        this.map=map;
        this.ds = new DatagramSocket(port);
        
        
    }
    @Override
    public void run() {
     Listener();
    }
    
    private void Listener(){
        
        buffer = new byte[256];
        while(true){            
            try {              
               dp = new DatagramPacket(buffer, buffer.length);
               ds.receive(dp);                
               msjRecive = new String(dp.getData(), 0, dp.getLength());              
               String parser=parsear(msjRecive,dp.getAddress().getHostAddress());
               if(parser!=null){
                System.out.println(parser);   
               }
                
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    private String parsear(String msj, String hostAddress) throws IOException{
       
        if(msj.equals("--listar")){
            buffer = new byte[256];
            String reporte="report@"+nombre;
            buffer = reporte.getBytes();
            dp = new DatagramPacket(buffer,buffer.length,addrBrodcast,port);     
            ds.send(dp);                      
        return null;
        }
        StringTokenizer tokens = new StringTokenizer(msj,"@");
        String str=tokens.nextToken();
        if(str.equals(nombre)||str.equals("global")){
            if(str.equals("global")){                 
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    if (value.equals(hostAddress)) {
                        System.out.println(key+" desde "+hostAddress+" les dice a todos:");              
                        break;
                    }
                    
                }
            }
              
            if(str.equals(nombre)){
                  for (Map.Entry<String, String> entry : map.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                      if (value.equals(hostAddress)) {
                          System.out.println(key+" desde "+hostAddress+" dice:");              
                          break;
                      }
                      
                    
                }
               
                 
            }
            return tokens.nextToken(); 
        }
           
        if(str.equals("report")){
            String nodo =tokens.nextToken();
            map.put(nodo, hostAddress);
                return null;
            }                  
        return null;
        
        
        
    }
    
    
}

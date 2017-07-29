/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2p;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;

/**
 *
 * @author kevin
 */
public class PeerToPeer {

    
    static String MyName;   
    static int port;    
    static Frases frase;
    static Iniciando inicio;
    static HashMap<String,String> map;
    
    public static void main(String[] args) throws UnknownHostException, IOException {
        //frase = new Frases();        
        MyName= "kevin";
        System.out.println(MyName);
        port=5000;          
        inicio = new Iniciando(MyName, port);
        inicio.TurnUp();          
    }    
}

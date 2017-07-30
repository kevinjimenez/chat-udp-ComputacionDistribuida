/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2p;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 *
 * @author kevin
 */
public class Frases {

    Stack < Integer > listRandom;
    HashMap<Integer, String> listaPalabrar; 
    File texto ;
    BufferedReader in;
    StringTokenizer tokens;
    String Line;
    int lend;
    int llave;

    public Frases() throws FileNotFoundException {
        listaPalabrar = new HashMap<>();
        texto = new File("src/frases.txt");
        in = new BufferedReader(new FileReader(texto));
    }
    

    public void Palabras() throws IOException {                
        Line = in.readLine();
        tokens = new StringTokenizer(Line," ");        
        int i = 1;
        while (tokens.hasMoreTokens()) {
            String palabra = tokens.nextToken().replaceAll("[,|.|:|;]", "");
            listaPalabrar.put(i, palabra);            
            i++;
        }
        for (Map.Entry<Integer, String> entry : listaPalabrar.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key+" : "+value);
            
        }
        
        System.out.println("Núm. aleatorios sin repetición:");        
        //System.out.println(reparto(listaPalabrar.size()).toString());
        for (Integer word : reparto(listaPalabrar.size())) {
            System.out.println(word+" = "+listaPalabrar.get(word));
        }
    }
    
    private Stack<Integer> reparto(int tamano){
        listRandom = new Stack<Integer>();
        for (int i = 0; i < tamano; i++) {
            llave=(int) Math.floor(Math.random()*tamano);
            while (listRandom.contains(llave)) {                
                llave = (int) Math.floor(Math.random()*tamano);
            }
            listRandom.push(llave);
        }        
        return listRandom;
    }                  
}

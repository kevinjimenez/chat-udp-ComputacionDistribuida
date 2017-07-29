/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2p;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 *
 * @author kevin
 */
public class Frases {

    //ArrayList<String> listaPalabrar;
    TreeMap<Integer, String> listaPalabrar = new TreeMap<>();
    Random rnd = new Random();

    public void Palabras() throws IOException {
        File f = new File("src/frases.txt");
        BufferedReader b = new BufferedReader(new FileReader(f));
        String Line = b.readLine();
        StringTokenizer tokens = new StringTokenizer(Line," ");
        
        int i = 1;
        while (tokens.hasMoreTokens()) {
            String palabra = tokens.nextToken().replaceAll("[,|.|:|;]", "");
            listaPalabrar.put(i, palabra);
            //System.out.println(tokens.countTokens());
            i++;
        }

        for (Map.Entry<Integer, String> entry : listaPalabrar.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + " " + value);
            
        }
        

    }
    
   
}

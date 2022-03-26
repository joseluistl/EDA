/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arboles;

/**
 *
 * @author Jose Luis
 */
public class Main {
    
    public static void main (String[] args){
        
        BinaryTreeAVL<Integer> arbol = new BinaryTreeAVL <Integer>();
        
        for (int i = 0; i <10000000; i ++)
            arbol.addElement(i);
        
        System.out.println(arbol.toString());
        
    }
}

/**
 * DUPLA: Filipe Augusto Parreira Almeida, 2320622
 *        Mabylly Kauany Neres da Silva, 2368013
 * 
 *  - Item 1) Foi adicionado o PEERNovo que no qual seu comando getNome() retorna a String 
 *      "PEERNovo 
 */
package com.lista3.lab5;
public enum PeerLista {
    
    PEER1 {
        @Override
        public String getNome() {
            return "PEER1";
        }        
    },
    PEER2 {
        @Override
        public String getNome() {
            return "PEER2";
        }        
    },
    PEER3 {
        @Override
        public String getNome() {
            return "PEER3";
        }        
    },
    //************** INICIO CÓDIGO ALTERADO ************************
    PEERNovo {
        @Override
        public String getNome(){
            return "PEERNovo";
        }
    };
    //************** FIM CÓDIGO ALTERADO ****************************
    public String getNome(){
        return "NULO";
    }    
}

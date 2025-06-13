/**
 * DUPLA: Filipe Augusto Parreira Almeida, 2320622
 *        Mabylly Kauany Neres da Silva, 2368013
 * 
 * - Item 4) Sobrecarga do método iniciar() para que fosse passado como parâmetro 
 *  o nome do peer a ser iniciado. Para isso foi alterado o trecho de código referente
 *  a escolha aleatória de um peer.
 */
package com.lista3.lab5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import com.lista3.lab5.InterfaceGraficaFrame;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class Peer implements IMensagem {

    ArrayList<PeerLista> alocados;

    public Peer() {
        alocados = new ArrayList<>();
    }

    //Cliente: invoca o metodo remoto 'enviar'
    //Servidor: invoca o metodo local 'enviar'
    @Override
    public Mensagem enviar(Mensagem mensagem) throws RemoteException {
        Mensagem resposta;
        try {
            System.out.println("Mensagem recebida: " + mensagem.getMensagem());
            resposta = new Mensagem(parserJSON(mensagem.getMensagem()));
        } catch (Exception e) {
            e.printStackTrace();
            resposta = new Mensagem("{\n" + "\"result\": false\n" + "}");
        }
        return resposta;
    }

    public String parserJSON(String json) {
        String result = "false";

        String fortune = "-1";

        String[] v = json.split(":");
        System.out.println(">>>" + v[1]);
        String[] v1 = v[1].split("\"");
        System.out.println(">>>" + v1[1]);
        if (v1[1].equals("write")) {
            String[] p = json.split("\\[");
            System.out.println(p[1]);
            String[] p1 = p[1].split("]");
            System.out.println(p1[0]);
            String[] p2 = p1[0].split("\"");
            System.out.println(p2[1]);
            fortune = p2[1];

            // Write in file
            Principal pv2 = new Principal();
            pv2.write(fortune);
        } else if (v1[1].equals("read")) {
            // Read file
            Principal pv2 = new Principal();
            fortune = pv2.read();
        }

        result = "{\n" + "\"result\": \"" + fortune + "\"" + "}";
        System.out.println(result);

        return result;
    }

    public void iniciar() {

        try {
            //Adquire aleatoriamente um ID do PeerList
            List<PeerLista> listaPeers = new ArrayList<>();
            for (PeerLista peer : PeerLista.values()) {
                listaPeers.add(peer);
            }

            Registry servidorRegistro;
            try {
                servidorRegistro = LocateRegistry.createRegistry(1099);
            } catch (java.rmi.server.ExportException e) { //Registro jah iniciado 
                System.out.print("Registro jah iniciado. Usar o ativo.\n");
            }
            servidorRegistro = LocateRegistry.getRegistry(); //Registro eh unico para todos os peers
            String[] listaAlocados = servidorRegistro.list();
            for (int i = 0; i < listaAlocados.length; i++) {
                System.out.println(listaAlocados[i] + " ativo.");
            }

            SecureRandom sr = new SecureRandom();
            PeerLista peer = listaPeers.get(sr.nextInt(listaPeers.size()));

            int tentativas = 0;
            boolean repetido = true;
            boolean cheio = false;
            while (repetido && !cheio) {
                repetido = false;
                peer = listaPeers.get(sr.nextInt(listaPeers.size()));
                for (int i = 0; i < listaAlocados.length && !repetido; i++) {

                    if (listaAlocados[i].equals(peer.getNome())) {
                        System.out.println(peer.getNome() + " ativo. Tentando proximo...");
                        repetido = true;
                        tentativas = i + 1;
                    }

                }
                //System.out.println(tentativas+" "+listaAlocados.length);

                //Verifica se o registro estah cheio (todos alocados)
                if (listaAlocados.length > 0
                        && //Para o caso inicial em que nao ha servidor alocado,
                        //caso contrario, o teste abaixo sempre serah true
                        tentativas == listaPeers.size()) {
                    cheio = true;
                }
            }

            if (cheio) {
                System.out.println("Sistema cheio. Tente mais tarde.");
                System.exit(1);
            }

            IMensagem skeleton = (IMensagem) UnicastRemoteObject.exportObject(this, 0); //0: sistema operacional indica a porta (porta anonima)            

            servidorRegistro.rebind(peer.getNome(), skeleton);
            System.out.print(peer.getNome() + " Servidor RMI: Aguardando conexoes...");

            //---Cliente RMI
            new ClienteRMI().iniciarCliente();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //**************************** INICIO CÓDIGO ALTERADO *****************************
    public void iniciar(String nomePeer) {
        try {
            //Adquire aleatoriamente um ID do PeerList
            List<PeerLista> listaPeers = new ArrayList<>();
            for (PeerLista peer : PeerLista.values()) {
                listaPeers.add(peer);
            }

            Registry servidorRegistro;
            try {
                servidorRegistro = LocateRegistry.createRegistry(1099);
            } catch (java.rmi.server.ExportException e) { //Registro jah iniciado 
                System.out.print("Registro jah iniciado. Usar o ativo.\n");
            }
            servidorRegistro = LocateRegistry.getRegistry(); //Registro eh unico para todos os peers
            ArrayList<String> listaAlocados = new ArrayList<>(Arrays.asList(servidorRegistro.list()));
            for (int i = 0; i < listaAlocados.size(); i++) {
                System.out.println(listaAlocados.get(i) + " ativo.");
            }

            if (listaAlocados.contains(nomePeer)) {
                JOptionPane.showMessageDialog(null, "Erro ao conectar no Peer: " + nomePeer);
            } else {
                boolean cheio = (listaAlocados.size() >= listaPeers.size());

                if (cheio) {
                    System.out.println("Sistema cheio. Tente mais tarde.");
                    System.exit(1);
                }
                
                IMensagem skeleton = (IMensagem) UnicastRemoteObject.exportObject(this, 0); //0: sistema operacional indica a porta (porta anonima)            

                servidorRegistro.rebind(nomePeer, skeleton);
                System.out.print(nomePeer + " Servidor RMI: Aguardando conexoes...");
                JOptionPane.showMessageDialog(null, "Peer Conectado com sucesso: " + nomePeer);

                //---Cliente RMI
                new ClienteRMI().iniciarCliente();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //*************************** FIM CÓDIGO ALTERADO *********************************
}

package com.lista3.lab5;

import javax.swing.SwingWorker;

/**
 *
* DUPLA: Filipe Augusto Parreira Almeida, 2320622
 *        Mabylly Kauany Neres da Silva, 2368013
 * 
 * - Item 4) Essa classe foi criada para modificar o funcionamento do SwingWorker,
 *  pois é necessário que se passe como parâmetro para a chamada iniciar do objeto
 *  Peer o nome do Peer a ser iniciado.
 */
public class PeerWorkerEspec extends SwingWorker<Void, Void> {

    String nomePeerEspec = new String();

    public PeerWorkerEspec(String nomePeerEspec) {
        this.nomePeerEspec = nomePeerEspec;
    }

    @Override
    protected Void doInBackground() throws Exception {
        Peer servidor = new Peer();
        servidor.iniciar(this.nomePeerEspec); // O método que bloqueia a execução
        return null;
    }

}

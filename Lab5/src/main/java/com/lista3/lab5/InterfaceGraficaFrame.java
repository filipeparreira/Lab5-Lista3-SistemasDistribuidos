package com.lista3.lab5;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * DUPLA: Filipe Augusto Parreira Almeida, 2320622 Mabylly Kauany Neres da
 * Silva, 2368013
 *
 * - Item 2) Manipulado no evento de apertar o botão (btnListarActionPerformed)
 * utiliza-se a tabela (tabelaPeers) definida dentro do JFrame, adicionando
 * linhas contendo o retorno do método getNome() de cada peer dentro de
 * PeerLista.
 *
 * - Foi adicioando um botão de criar Peer (linha [colocar número da linha]),
 * ele foi feito utilizando o conceito de threads, para que a interface gráfica
 * não travasse com a execução do Peer. Para que isso fosse possível utilizou-se
 * a classe SwingWorker do javax, meio mais simples de implementar Threads
 * encontrado. Como base, foi utilizado os seguintes links de referencia:
 * Documentação Oracle Java SE 8:
 * https://docs-oracle-com.translate.goog/javase/8/docs/api/javax/swing/SwingWorker.html?_x_tr_sl=en&_x_tr_tl=pt&_x_tr_hl=pt&_x_tr_pto=tc
 * Artigo do Devmedia:
 * https://www.devmedia.com.br/trabalhando-com-swingworker-em-java/29331
 *
 * - Item 3) Para realizar o unbind de um Peer.java do Registry, utilizou-se um
 * botão (btnRemoverPeer). Para remover um peer, é necessário clicar sobre seu
 * nome na lista e então clicar no botão. Irá aparecer uma mensagem de sucesso
 * ou falha após fazer isso. O trecho de código é o do método
 * btnRemoverPeerActionPerformed que utiliza da variável global peerSelecionado.
 *
 * - Item 4) Para selecionar um peer para se conectar é utilizado também uma
 * tabela, nela é adotado exatamente a mesma lógica para tabela de remover peers
 * ativos. Ao clicar no botão para iniciar um peer específico é utilizado um
 * objeto da classe PeerWorkerEspec (verifica arquivo da classe para mais
 * detalhes), e então é instaciado uma thread para executar o peer específico. O
 * trecho de código correspondente é o do método
 * btnInitPeerEspecActionPerformed.
 *
 */
public class InterfaceGraficaFrame extends javax.swing.JFrame {

    private Registry servidorRegistro;
    private String peerSelecionado = new String();

    public InterfaceGraficaFrame() throws RemoteException {
        initComponents();
        //*****************INICIO CÓDIGO ALTERADO**********************
        // Inicia servidorRegistro para verificar quais peers ativos
        try {
            this.servidorRegistro = LocateRegistry.createRegistry(1099);
        } catch (java.rmi.server.ExportException e) {
            System.out.print("Registro jah iniciado. Usar o ativo.\n");
        }
        this.listarPeers();
        //******************FIM CÓDIGO ALTERADO *************************
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnListar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaPeersAtivos = new javax.swing.JTable();
        btnCriarPeer = new javax.swing.JButton();
        btnRemoverPeer = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaPeers = new javax.swing.JTable();
        btnInitPeerEspec = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnListar.setText("Listar Peers");
        btnListar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });

        tabelaPeersAtivos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Lista de Peers ativos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaPeersAtivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaPeersAtivosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaPeersAtivos);

        btnCriarPeer.setText("Iniciar Peer");
        btnCriarPeer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCriarPeer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCriarPeerActionPerformed(evt);
            }
        });

        btnRemoverPeer.setText("Remover Peer");
        btnRemoverPeer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRemoverPeer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverPeerActionPerformed(evt);
            }
        });

        tabelaPeers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Lista de Peers"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaPeers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaPeersMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelaPeers);

        btnInitPeerEspec.setText("Iniciar Peer Específico");
        btnInitPeerEspec.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnInitPeerEspec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInitPeerEspecActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemoverPeer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCriarPeer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnListar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnInitPeerEspec))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnListar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCriarPeer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemoverPeer)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInitPeerEspec))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//*************************** INICIO CÓDIGO ALTERADO ***********************************

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        // Pega o modelo para poder manipular as linhas 
        DefaultTableModel modelo = (DefaultTableModel) tabelaPeersAtivos.getModel();
        modelo.setRowCount(0);
        try {
            // Para todo item da lista de peers eu adiciono na tabela
            for (String reg : this.servidorRegistro.list()) {
                modelo.addRow(new Object[]{reg});
            }
        } catch (RemoteException ex) {
            Logger.getLogger(InterfaceGraficaFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnListarActionPerformed

    private void btnCriarPeerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriarPeerActionPerformed

        SwingWorker<Void, Void> worker;
        worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {                
                Peer servidor = new Peer();
                servidor.iniciar();
                return null;
            }
        };
        worker.execute();
    }//GEN-LAST:event_btnCriarPeerActionPerformed

    private void tabelaPeersAtivosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaPeersAtivosMouseClicked
        // Obtém a linha e a coluna do ponto clicado
        int row = this.tabelaPeersAtivos.rowAtPoint(evt.getPoint());
        int col = this.tabelaPeersAtivos.columnAtPoint(evt.getPoint());

        // Verifica se o clique foi dentro de uma célula válida
        if (row >= 0 && col >= 0) {
            // Obtém o conteúdo da célula
            Object value = this.tabelaPeersAtivos.getValueAt(row, col);
            this.peerSelecionado = value.toString().trim();
        }
    }//GEN-LAST:event_tabelaPeersAtivosMouseClicked

    private void btnRemoverPeerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverPeerActionPerformed
        if (!this.peerSelecionado.isEmpty()) {
            try {
                Registry registry = LocateRegistry.getRegistry();
                registry.unbind(this.peerSelecionado);
                JOptionPane.showMessageDialog(null, "o Peer: " + this.peerSelecionado + " foi removido com sucesso!!");
                this.peerSelecionado = new String();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro ao desconectar o Peer: " + this.peerSelecionado);
            }
        }
    }//GEN-LAST:event_btnRemoverPeerActionPerformed

    private void btnInitPeerEspecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInitPeerEspecActionPerformed
        if (!this.peerSelecionado.isEmpty()) {
            PeerWorkerEspec worker = new PeerWorkerEspec(this.peerSelecionado);
            this.peerSelecionado = new String();
            worker.execute();
        }
    }//GEN-LAST:event_btnInitPeerEspecActionPerformed

    private void tabelaPeersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaPeersMouseClicked
        // Obtém a linha e a coluna do ponto clicado
        int row = this.tabelaPeers.rowAtPoint(evt.getPoint());
        int col = this.tabelaPeers.columnAtPoint(evt.getPoint());

        // Verifica se o clique foi dentro de uma célula válida
        if (row >= 0 && col >= 0) {
            // Obtém o conteúdo da célula
            Object value = this.tabelaPeers.getValueAt(row, col);
            this.peerSelecionado = value.toString().trim();
        }
    }//GEN-LAST:event_tabelaPeersMouseClicked

    private void listarPeers() {
        DefaultTableModel modelo = (DefaultTableModel) this.tabelaPeers.getModel();
        modelo.setRowCount(0);

        for (PeerLista peer : PeerLista.values()) {
            modelo.addRow(new Object[]{peer.getNome()});
        }
    }

    public static void main(String[] args) throws RemoteException {
        new InterfaceGraficaFrame().setVisible(true);
    }
    //************************** FIM CÓDIGO ALTERADO **********************************

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCriarPeer;
    private javax.swing.JButton btnInitPeerEspec;
    private javax.swing.JButton btnListar;
    private javax.swing.JButton btnRemoverPeer;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabelaPeers;
    private javax.swing.JTable tabelaPeersAtivos;
    // End of variables declaration//GEN-END:variables
}

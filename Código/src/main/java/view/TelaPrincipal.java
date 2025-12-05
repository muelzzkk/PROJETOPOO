package Projeto_POO.src.main.java.view;

import Projeto_POO.src.main.java.Controller.ProgramaFidelidadeController;
import java.awt.Image;
import javax.swing.ImageIcon;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Samuel
 */
public class TelaPrincipal extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaPrincipal.class.getName());
    private final ProgramaFidelidadeController programaController;
    
    public TelaPrincipal() {
        this.programaController = new ProgramaFidelidadeController();
        initComponents();
        setLocationRelativeTo(null);
        Image icon = new ImageIcon(getClass().getResource("/imagens/icon.png")).getImage();
        setIconImage(icon);
     
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        botaocadastro = new javax.swing.JButton();
        botaoregistrar = new javax.swing.JButton();
        botaopontos = new javax.swing.JButton();
        resgata_bonus = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Programa Cartão Fidelidade");
        setBounds(new java.awt.Rectangle(0, 0, 800, 600));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cartão Fidelidade");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        botaocadastro.setText("Cadastrar Cliente");
        botaocadastro.addActionListener(this::botaocadastroActionPerformed);

        botaoregistrar.setText("Registrar Compra");
        botaoregistrar.addActionListener(this::botaoregistrarActionPerformed);

        botaopontos.setText("Consultar Pontos");
        botaopontos.addActionListener(this::botaopontosActionPerformed);

        resgata_bonus.setText("Resgatar Bônus");
        resgata_bonus.addActionListener(this::resgata_bonusActionPerformed);

        jLabel2.setText("@Direitos Reservados Samuel Freitas,Marco Antonio e Diogo Benites");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(botaoregistrar)
                            .addComponent(botaocadastro)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaopontos)
                            .addComponent(resgata_bonus, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(238, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(56, 56, 56)
                .addComponent(botaocadastro)
                .addGap(18, 18, 18)
                .addComponent(botaoregistrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaopontos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(resgata_bonus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                .addComponent(jLabel2))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

   
    private void botaocadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaocadastroActionPerformed
        new TelaCadastro(programaController.getClienteController()).setVisible(true);
        
    }//GEN-LAST:event_botaocadastroActionPerformed

    private void botaoregistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoregistrarActionPerformed
        new TelaCompra(programaController.getTransacaoController()).setVisible(true);
    }//GEN-LAST:event_botaoregistrarActionPerformed
   
    private void resgata_bonusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resgata_bonusActionPerformed
        new TelaBonus(programaController.getTransacaoController()).setVisible(true);
    }//GEN-LAST:event_resgata_bonusActionPerformed

    private void botaopontosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaopontosActionPerformed
        new TelaPontos(programaController).setVisible(true);
    }//GEN-LAST:event_botaopontosActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and fee
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaocadastro;
    private javax.swing.JButton botaopontos;
    private javax.swing.JButton botaoregistrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton resgata_bonus;
    // End of variables declaration//GEN-END:variables
}

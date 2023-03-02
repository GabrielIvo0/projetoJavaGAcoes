package com.ivo.ui;

import javax.swing.JOptionPane;

public class JAcoes extends javax.swing.JFrame {

    public JAcoes() {
        initComponents();
        this.setSize(850, 550);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPInterno = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        jmArquivo = new javax.swing.JMenu();
        jmiConfiguracoes = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmiSair = new javax.swing.JMenuItem();
        jmGraficos = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jmSobre = new javax.swing.JMenu();
        jInfo = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JAcoes");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setSize(new java.awt.Dimension(300, 300));

        javax.swing.GroupLayout jPInternoLayout = new javax.swing.GroupLayout(jPInterno);
        jPInterno.setLayout(jPInternoLayout);
        jPInternoLayout.setHorizontalGroup(
            jPInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 401, Short.MAX_VALUE)
        );
        jPInternoLayout.setVerticalGroup(
            jPInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 184, Short.MAX_VALUE)
        );

        menuBar.setBorder(null);

        jmArquivo.setMnemonic('a');
        jmArquivo.setText("Arquivo");

        jmiConfiguracoes.setMnemonic('c');
        jmiConfiguracoes.setText("Configurações");
        jmiConfiguracoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiConfiguracoesActionPerformed(evt);
            }
        });
        jmArquivo.add(jmiConfiguracoes);
        jmArquivo.add(jSeparator1);

        jmiSair.setMnemonic('r');
        jmiSair.setText("Sair");
        jmiSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSairActionPerformed(evt);
            }
        });
        jmArquivo.add(jmiSair);

        menuBar.add(jmArquivo);

        jmGraficos.setMnemonic('g');
        jmGraficos.setText("Gráficos");
        jmGraficos.setOpaque(false);
        jmGraficos.setRequestFocusEnabled(false);
        jmGraficos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmGraficosActionPerformed(evt);
            }
        });

        jMenuItem1.setText("Executar Gráfico");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jmGraficos.add(jMenuItem1);

        menuBar.add(jmGraficos);

        jmSobre.setText("Sobre");
        jmSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmSobreActionPerformed(evt);
            }
        });

        jInfo.setText("Info");
        jInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jInfoActionPerformed(evt);
            }
        });
        jmSobre.add(jInfo);

        menuBar.add(jmSobre);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPInterno)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPInterno)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmGraficosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmGraficosActionPerformed
        if (jConfiguracoes == null || jConfiguracoes.getDiretorioAtual() == null) {
            JOptionPane.showMessageDialog(this,
                    "Não foi informado o diretório em que estão os arquivos .csv",
                    "Erro ao acessar base de ativos", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JGrafico jg = new JGrafico();
        jg.setDiretorio(jConfiguracoes.getDiretorioAtual());
        jPInterno.add(jg);
        jg.setVisible(true);
    }//GEN-LAST:event_jmGraficosActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if (jConfiguracoes == null || jConfiguracoes.getDiretorioAtual() == null) {
            JOptionPane.showMessageDialog(this,
                    "Não foi informado o diretório em que estão os aquivos .csv .",
                    "Erro ao acessar base de ativos", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JGrafico jg = new JGrafico();
        jg.setDiretorio(jConfiguracoes.getDiretorioAtual());
        jPInterno.add(jg);
        jg.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jmiSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jmiSairActionPerformed

    private void jmiConfiguracoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiConfiguracoesActionPerformed
        if (jConfiguracoes == null) {
            jConfiguracoes = new JConfiguracoes();
            jPInterno.add(jConfiguracoes);
        }
        jConfiguracoes.setVisible(true);
    }//GEN-LAST:event_jmiConfiguracoesActionPerformed

    private void jmSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmSobreActionPerformed

    }//GEN-LAST:event_jmSobreActionPerformed

    private void jInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jInfoActionPerformed
        jPInterno.add(jSobre);
        jSobre.setVisible(true);
    }//GEN-LAST:event_jInfoActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JAcoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JAcoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JAcoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JAcoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JAcoes().setVisible(true);
            }
        });
    }

    // Variaveis
    private JConfiguracoes jConfiguracoes;
    private JSobre jSobre = new JSobre();
    ;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem jInfo;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JDesktopPane jPInterno;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenu jmArquivo;
    private javax.swing.JMenu jmGraficos;
    private javax.swing.JMenu jmSobre;
    private javax.swing.JMenuItem jmiConfiguracoes;
    private javax.swing.JMenuItem jmiSair;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables

}

package com.game;

import javax.swing.JFrame;

/*
 * OnlineGUI creates the user interface for hosting and joining online games and
 * also connects OnlineConnection with Chess.
 */
public class OnlineGUI extends JFrame {

    private OnlineConnection connection;    //OnlineConnection

    /*
     * Constructor
     */
    public OnlineGUI() {
        initComponents();
        setTitle("Online Game");
        setResizable(false);
        setVisible(true);
    }

    public void setConnectionStatus(String s) {
        status.setText("Status: " + s);

        if (s.equals("Connected")) {
            host.setEnabled(false);
            join.setEnabled(false);
            end.setEnabled(true);
        } else {
            host.setEnabled(true);
            join.setEnabled(true);
            end.setEnabled(false);
        }
    }

    public void setOpponent(String s) {
        opponent.setText("Opponent: " + s);
    }

    /*
     * Starts a new OnlineConnection in a new thread.
     */
    private void startConnection(boolean host) {
        connection = new OnlineConnection(host);
        (new Thread(connection)).start();
    }

    public void sendMove(Location from, Location to) {
        connection.sendMove(from, to);
    }

    public Location[] getLastMove() {
        return connection.getLastMove();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        host = new javax.swing.JButton();
        join = new javax.swing.JButton();
        status = new javax.swing.JLabel();
        opponent = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        end = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("I would like to:");

        host.setText("Host");
        host.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hostActionPerformed(evt);
            }
        });

        join.setText("Join");
        join.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinActionPerformed(evt);
            }
        });

        status.setText("Status: Not Connected");

        opponent.setText("Opponent: None");

        end.setText("End Connection");
        end.setEnabled(false);
        end.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(status)
                            .addComponent(opponent)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(host)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(join))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(end)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(host)
                    .addComponent(join))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(status)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(opponent)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(end)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void hostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hostActionPerformed
        startConnection(true);
    }//GEN-LAST:event_hostActionPerformed

    private void joinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinActionPerformed
        startConnection(false);
    }//GEN-LAST:event_joinActionPerformed

    private void endActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endActionPerformed
        connection.exit();
    }//GEN-LAST:event_endActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton end;
    private javax.swing.JButton host;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton join;
    private javax.swing.JLabel opponent;
    private javax.swing.JLabel status;
    // End of variables declaration//GEN-END:variables
}
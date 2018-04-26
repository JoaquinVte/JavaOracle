/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaoracle;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.BoxLayout;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import oracle.jdbc.OracleTypes;
/**
 *
 * @author PC-ORACLE
 */
public class BuscadorPartidos extends javax.swing.JFrame {

    /**
     * Creates new form BuscadorPartidos
     */
    public BuscadorPartidos(String mundial, String local, String visitante, String estadio) {
        
        initComponents();
        Buscar(mundial, local, visitante, estadio);
        jLabel3.setText("BUSCADOR DE PARTIDOS POR MUNDIAL: "+mundial+" LOCAL: "+local+" VISITANTE: "+visitante+" ESTADO: "+estadio);
    }
    
    private void Buscar(String mundial, String local, String visitante, String estadio)
    {
        //MigLayout layout = new MigLayout("wrap 7");
        JLabel[] l = new JLabel[32000];
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.orange);
        JScrollPane scroller = new JScrollPane(panel);

        setMinimumSize(new java.awt.Dimension(300, 500));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(300, 500));
        setLayout(null);

        panel.setPreferredSize(new java.awt.Dimension(300, 2000));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        scroller.setViewportView(panel);

        add(scroller);
        scroller.setBounds(450, 275, 325, 500);        
        
        showMessageDialog(null,"DESCOMENTA TODO EL CÓDIGO Y AQUÍ TENDRÁS QUE PONER LA CONEXIÓN A ORACLE Y LOS PARÁMETROS");
        int j = 0;
        

//        try {
            //int cantidad = .........
            //rs = (ResultSet)......;   // Nuestro cursor, convertido en ResultSet
  /*
            Border border = LineBorder.createGrayLineBorder();
            while (rs.next()) 
            {
                if(cantidad<4)
                {
                    if ("PARTIDO".equals(rs.getString("origen_datos"))) 
                    {
                        l[j] = new JLabel(rs.getString("c3") +" - "+rs.getString("c1")+" "+rs.getString("n1") +" - "+rs.getString("c2")+" "+rs.getString("n2"), SwingConstants.LEFT );
                        l[j].setBorder(border);
                        l[j].setOpaque(true);
                        l[j].setBackground(Color.GRAY);
                        l[j].setForeground(Color.white);
                        panel.add(l[j]);
                        j++;
                    }
                    else
                    {
                        if (!" ".equals(rs.getString("c6"))) 
                        {
                            l[j] = new JLabel(rs.getString("c6"), SwingConstants.CENTER );
                            l[j].setOpaque(true);
                            l[j].setBackground(Color.GREEN);
                            panel.add(l[j]);
                            j++;                            
                        }
                        l[j] = new JLabel(rs.getString("n3") +"-"+rs.getString("c4")+"-"+rs.getString("n4") +"  "+rs.getString("c5"), SwingConstants.LEFT );
                        l[j].setOpaque(true);
                        l[j].setBorder(border);
                        l[j].setBackground(Color.CYAN);
                        panel.add(l[j]);
                        j++;                            

                    }                    
                    
                }
                else if (cantidad<11)
                {
                    if ("PARTIDO".equals(rs.getString("origen_datos"))) 
                    {
                        l[j] = new JLabel(rs.getString("c3") +" - "+rs.getString("c1")+" "+rs.getString("n1") +" - "+rs.getString("c2")+" "+rs.getString("n2"), SwingConstants.LEFT );
                        l[j].setBorder(border);
                        l[j].setOpaque(true);
                        l[j].setBackground(Color.GRAY);
                        l[j].setForeground(Color.white);
                        panel.add(l[j]);
                        j++;
                    }
                    else
                    {
                            System.out.println(rs.getString("n4") +"-"+rs.getString("c6"));
                            l[j] = new JLabel(rs.getString("n4") +"-"+rs.getString("c6")+" ("+rs.getString("c7")+")", SwingConstants.LEFT );
                            l[j].setOpaque(true);
                            l[j].setBorder(border);
                            l[j].setBackground(Color.CYAN);
                            panel.add(l[j]);
                            j++;
                    }                    
                }
                else
                {
                        l[j] = new JLabel(rs.getString("FECHA") +" - "+rs.getString("EQUIPO_L")+" "+rs.getString("RESULTADO_L") +" - "+rs.getString("EQUIPO_V")+" "+rs.getString("RESULTADO_V"), SwingConstants.LEFT );
                        l[j].setBorder(border);
                        l[j].setOpaque(true);
                        l[j].setBackground(Color.GRAY);
                        panel.add(l[j]);
                        j++;                    
                }
              
            }
            setVisible(true);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        } 
        finally {
            SqlTools.close(rs, cs, con);
        }       
*/             
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("AUTOR: PABLO MATÍAS GARRAMONE RAMÍREZ");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setBackground(new java.awt.Color(255, 204, 0));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("PROYECTO MUNDIAL");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel2.setOpaque(true);

        jLabel3.setBackground(new java.awt.Color(255, 204, 0));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("jLabel3");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel3.setOpaque(true);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("<html><u>VOLVER ATRÁS</u></html>");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(139, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 846, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(193, 193, 193))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(323, 323, 323))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 390, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        NewJFrame fr = new NewJFrame();

        fr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel4MouseClicked



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}

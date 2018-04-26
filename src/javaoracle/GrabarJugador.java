/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaoracle;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleResultSet;
import static oracle.jdbc.OracleTypes.BLOB;
import static oracle.jdbc.driver.Representation.BLOB;

/**
 *
 * @author PC-ORACLE
 */
public class GrabarJugador extends javax.swing.JFrame {

    private FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de imagen","jpg");
    private byte[] foto = null;
    Blob fotoBLOB;
    String ruta="";
    File f;
    String nombreJugadorGlobal;
    
    
    	private static void copyFileUsingFileStreams(File source, File dest)
	        throws IOException {
	    InputStream input = null;
	    OutputStream output = null;
	    try {
	        input = new FileInputStream(source);
	        output = new FileOutputStream(dest);
	        byte[] buf = new byte[1024];
	        int bytesRead;
	        while ((bytesRead = input.read(buf)) > 0) {
	            output.write(buf, 0, bytesRead);
	        }
	    } finally {
	        input.close();
	        output.close();
	    }
	}
    /**
     * Creates new form GrabarJugador
     */
    public GrabarJugador(String nombreJugador) {
        initComponents();
        ruta="";
        jLabel3.setText("JUGADOR "+nombreJugador);
        obtenerJugador(nombreJugador);
        nombreJugadorGlobal=nombreJugador;
    }
    
    private void obtenerJugador(String nombreJugador)
    {
        Connection con = null;
        ResultSet rs = null;
        OracleCallableStatement cs = null;   
        try
        {
            JavaOracle obconeccion=new JavaOracle();
            con=obconeccion.Conectar();
            
            obconeccion.llenarDesplegable("EQUIPOS", desplEquipos,1, null, null);

            String sql ="BEGIN prueba.obtener_jugador2 (?,?,?); END;";
            cs = (OracleCallableStatement)con.prepareCall(sql);

            int pos = 0;

            // Cargamos los parametros de entrada IN
            cs.setString(++pos, nombreJugador);
            
            // Registramos los parametro de salida OUT
            cs.registerIndexTableOutParameter(++pos, 5, OracleTypes.VARCHAR, 100);
            cs.registerOutParameter(++pos, java.sql.Types.BLOB);

            // Ejecutamos
            cs.execute();
            

            String[] resultArray = (String[])cs.getPlsqlIndexTable(2);
            ruta="";
            foto = cs.getBytes(3);
            fotoBLOB = cs.getBlob (3);
            
           
            /////// GESTIÓN DEL BLOB
            if(foto!=null)
            {
                ////////
                // Lo grabo en un fichero temporal

                BufferedOutputStream os;
                f=new File("C:\\TEMP\\"+nombreJugador);
                os = new BufferedOutputStream(new FileOutputStream(f));
                os.write(fotoBLOB.getBytes(1, (int) fotoBLOB.length()), 0, (int) fotoBLOB.length());
                os.flush();
                os.close();

                Image imagenFoto = Toolkit.getDefaultToolkit().createImage(foto);
                ImageIcon Icono = new ImageIcon (imagenFoto);
                
                Image fotoRedimensionada = Icono.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH);
                ImageIcon nuevoIcono = new ImageIcon (fotoRedimensionada);
                imagenJugador.setIcon(nuevoIcono);
                imagenJugador.setSize(155,175);            
            }
            else
            {
             
                // METO AVATAR

                String path = "/imagenes/avatar.png";  
                ruta=path;
                URL url = this.getClass().getResource(path);  
                ImageIcon icon = new ImageIcon(url);  

                //redimensionamos la imagen
                Image image = icon.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH);
                icon = new ImageIcon(image, "Avatar");                
                
                imagenJugador.setIcon(icon); 
                imagenJugador.setSize(100,100);            
                
            }
            ///////
            
            //byte[] imagenFoto = null;
            //imagenFoto = cs.getBytes(3);
            
            textNombre.setText(resultArray[0]);
            textDireccion.setText(resultArray[1]);
            textFechaNac.setText(resultArray[3]);
            
            if("AR".equals(resultArray[2]))
                jRadioPortero.setSelected(true);
            else if("DF".equals(resultArray[2]))
                jRadioDefensa.setSelected(true);
            else if("MC".equals(resultArray[2]))
                jRadioCentrocampista.setSelected(true);
            else if("DL".equals(resultArray[2]))
                jRadioDelantero.setSelected(true);
            
            desplEquipos.setSelectedItem(resultArray[4]);
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        } 
        finally {
            SqlTools.close(rs, cs, con);
        }          

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoPuesto = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        desplEquipos = new javax.swing.JComboBox<>();
        jRadioPortero = new javax.swing.JRadioButton();
        jRadioDefensa = new javax.swing.JRadioButton();
        jRadioCentrocampista = new javax.swing.JRadioButton();
        jRadioDelantero = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        textNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        textDireccion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        imagenJugador = new javax.swing.JLabel();
        rutaFoto = new javax.swing.JTextField();
        grabarJugador = new javax.swing.JButton();
        textFechaNac = new javax.swing.JTextField();
        LinkAtras = new javax.swing.JLabel();

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
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel3.setText("jLabel3");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel3.setOpaque(true);

        grupoPuesto.add(jRadioPortero);
        jRadioPortero.setText("Portero");

        grupoPuesto.add(jRadioDefensa);
        jRadioDefensa.setText("Defensa");

        grupoPuesto.add(jRadioCentrocampista);
        jRadioCentrocampista.setText("Centrocampista");

        grupoPuesto.add(jRadioDelantero);
        jRadioDelantero.setText("Delantero");

        jLabel4.setText("Nombre: ");

        jLabel5.setText("Dirección");

        jLabel6.setText("Fecha Nacimiento:");

        jLabel7.setText("Puesto:");

        jLabel8.setText("Foto:");

        jButton1.setText("Subir Foto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        grabarJugador.setText("Grabar Jugador");
        grabarJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grabarJugadorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jRadioDelantero)
                    .addComponent(jRadioCentrocampista)
                    .addComponent(jRadioDefensa)
                    .addComponent(desplEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5))
                            .addComponent(jRadioPortero))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textDireccion)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1))
                            .addComponent(rutaFoto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(imagenJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(grabarJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(227, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(desplEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(textDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(textFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioPortero)
                            .addComponent(jLabel8)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioDefensa)
                            .addComponent(rutaFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioCentrocampista)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioDelantero))
                    .addComponent(imagenJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(grabarJugador)
                .addGap(34, 34, 34))
        );

        LinkAtras.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        LinkAtras.setText("<html><u>VOLVER ATRÁS</u></html>");
        LinkAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LinkAtrasMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(75, 75, 75)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(271, 271, 271)
                                .addComponent(LinkAtras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(LinkAtras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 920, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(31, 31, 31)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(32, 32, 32)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Creamos el objeto JFileChooser
        JFileChooser fch = new JFileChooser();
        
        fch.setFileFilter(filter);
        // Abri ventana de dialog
        int opcion = fch.showOpenDialog(this);
        
        // Si hacemos click 
        if (opcion==JFileChooser.APPROVE_OPTION)
        {
            //Obtenenemos el nombre del fichero seleccionado
            String nomFichero=fch.getSelectedFile().getPath();
            //Obtener donde se guardará la imagen
            String urlFichero=fch.getSelectedFile().toString();
            File origen=new File(urlFichero);
            File destino=new File("C:\\TEMP\\"+nombreJugadorGlobal);
            try {
                copyFileUsingFileStreams(origen, destino);
            } catch (IOException ex) {
                Logger.getLogger(GrabarJugador.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            imagenJugador.setIcon(new ImageIcon(nomFichero));
            
            //Modificamos la imagen
            ImageIcon icono = new ImageIcon(nomFichero);
            //Extraer la imagen del Icono
            Image img = icono.getImage();
            //Cambiamos tamaño a nuestra imagen
            Image nuevaImagen = img.getScaledInstance(155, 175, java.awt.Image.SCALE_SMOOTH);
            //Generamos el nuevo icono
            ImageIcon nuevoIcono = new ImageIcon (nuevaImagen);
            imagenJugador.setIcon(nuevoIcono);
            imagenJugador.setSize(155,175);
            
            rutaFoto.setText(urlFichero);
            
            ruta=urlFichero;
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void LinkAtrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LinkAtrasMouseClicked
        NewJFrame fr = new NewJFrame();

        fr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_LinkAtrasMouseClicked

    private void grabarJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grabarJugadorActionPerformed
        Connection con = null;
        ResultSet rs = null;
        OracleCallableStatement cs = null;   
        try
        {

            JavaOracle obconeccion=new JavaOracle();
            con=obconeccion.Conectar();
            
            String sql ="call prueba.grabarJugador(?,?,?,?,?,?)";
            cs = (OracleCallableStatement)con.prepareCall(sql);

            int pos = 0;

            // Cargamos los parametros de entrada IN
            cs.setString(++pos, textNombre.getText());
            cs.setString(++pos, (String) desplEquipos.getSelectedItem());
            cs.setString(++pos, textDireccion.getText());
            if (jRadioPortero.isSelected())
                cs.setString(++pos, "AR");
            else if (jRadioDefensa.isSelected())
                cs.setString(++pos, "DF");
            else if (jRadioCentrocampista.isSelected())
                cs.setString(++pos, "MC");
            else if (jRadioDelantero.isSelected())
                cs.setString(++pos, "DL");

            String fecha=textFechaNac.getText();
            cs.setString(++pos, fecha);
            //cs.registerOutParameter(++pos, java.sql.Types.BLOB);
            String nombreFichero=textNombre.getText();
            String nombreFicheroRuta="C:\\TEMP\\"+textNombre.getText();
            
            
            File fichero = new File(nombreFicheroRuta);
            
            if (fichero.exists())
                cs.setString(++pos,nombreFichero);
            else
                cs.setString(++pos,"Sin Fichero");

            //doUpdateBlob(textNombre.getText(), nombreFichero);//haciendo el trabajo
            cs.execute();

            //f.delete();            
            String equipoSeleccionado = (String) desplEquipos.getSelectedItem();
            JugadoreEquipo jframe = new JugadoreEquipo(equipoSeleccionado);
        
            jframe.setVisible(true);
            this.dispose();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        } 
        finally {
            SqlTools.close(rs, cs, con);
        }          
           
    }//GEN-LAST:event_grabarJugadorActionPerformed

    
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LinkAtras;
    private javax.swing.JComboBox<String> desplEquipos;
    private javax.swing.JButton grabarJugador;
    private javax.swing.ButtonGroup grupoPuesto;
    private javax.swing.JLabel imagenJugador;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public javax.swing.JRadioButton jRadioCentrocampista;
    public javax.swing.JRadioButton jRadioDefensa;
    public javax.swing.JRadioButton jRadioDelantero;
    public javax.swing.JRadioButton jRadioPortero;
    private javax.swing.JTextField rutaFoto;
    public javax.swing.JTextField textDireccion;
    private javax.swing.JTextField textFechaNac;
    public javax.swing.JTextField textNombre;
    // End of variables declaration//GEN-END:variables
}

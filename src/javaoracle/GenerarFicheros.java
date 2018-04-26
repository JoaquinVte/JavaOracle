/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaoracle;

import java.awt.Desktop;
import java.net.URI;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author PC-ORACLE
 */
public class GenerarFicheros {

    public void generarXML()
    {

        showMessageDialog(null,"DESARROLLA LA LLAMADA A LA FUNCIÓN DE GENERAR FICHERO XML y TXT, Ejercicio 4.7 \n Debes devolver en la función el nombre el fichero y con su URL en la variable $fichero.Debe quedar algo así: C:/Users/PC-ORACLE/Desktop/prueba.xml");
        
        if(java.awt.Desktop.isDesktopSupported())
        {
            try{
                Desktop dk = Desktop.getDesktop();
                dk.browse(new URI("file:///C:/Users/PC-ORACLE/Desktop/prueba.xml"));
            }catch(Exception e){
                System.out.println ("Error al abrir URL: "+e.getMessage());
            }
        }

    }
}

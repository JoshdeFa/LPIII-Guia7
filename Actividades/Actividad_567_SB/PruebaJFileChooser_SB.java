package Actividad_567_SB;

import java.io.IOException;
import javax.swing.JFrame;

public class PruebaJFileChooser_SB {
    public static void main(String[] args) throws IOException {
        DemoJFileChooser_SB aplicacion = new DemoJFileChooser_SB();
        aplicacion.setSize(500, 400); 
        aplicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aplicacion.setVisible(true);
    }
}
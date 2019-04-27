/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.run;

import frontend.gui.FramePrincipal;
import java.util.HashMap;

/**
 *
 * @author jesfrin
 */
public class Run {

    public static HashMap<String, Integer> variablesPrograma = new HashMap<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //variablesPrograma.replace(key, Integer.MIN_VALUE);
        FramePrincipal miFrame = new FramePrincipal();
        miFrame.setVisible(true);

//        System.out.println(variables.get("variable1"));//Devuelve nul si no la encuentra,devuelve el valor si la encuentra
    }
}

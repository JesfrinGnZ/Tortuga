/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculosTortuga;

import backend.analizadorInstrucciones.AnalizadorInstrucciones;
import backend.analizadorInstrucciones.parser;
import backend.run.Run;
import frontend.gui.FramePrincipal;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jesfrin
 */
public class ManejadorDeRepeat {

    private int cantidadDeRepeticiones;
    private ArrayList<String> instrucciones;
    private Tortuga tortuga;
    private HashMap<String, Integer> variablesDePrograma;
    private FramePrincipal frame;

    public ManejadorDeRepeat(int cantidadDeRepeticiones, ArrayList<String> instrucciones, Tortuga tortuga, HashMap<String, Integer> variablesDePrograma, FramePrincipal frame) {
        this.cantidadDeRepeticiones = cantidadDeRepeticiones;
        this.instrucciones = instrucciones;
        this.tortuga = tortuga;
        this.variablesDePrograma = variablesDePrograma;
        this.frame = frame;
    }

    public void analizarExpresion(String instruccion) {
        AnalizadorInstrucciones lex = new AnalizadorInstrucciones(new BufferedReader(new StringReader(instruccion)));
        lex.iniciarFrame(this.frame);
        parser sintactico = new parser(lex, this.frame, this.tortuga, this.variablesDePrograma);
        try {
            sintactico.parse();
        } catch (Exception ex) {
            Logger.getLogger(Run.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void trabajarLasInstrucciones() {
        for (int i = 0; i < cantidadDeRepeticiones; i++) {
            for (String instruccion : instrucciones) {
                analizarExpresion(instruccion);
            }
        }
    }
}

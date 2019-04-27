/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculosTortuga;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author jesfrin
 */
public class ImagenTortuga {

    private final int hipotenusa = 20;
    private Point puntoInicial;
    private Point puntoFinal;
    private Graphics g;

    public ImagenTortuga(Graphics g) {
        this.g = g;
    }

    public void borrarTortuga(Color color) {
        g.setColor(Color.white);
        this.g.drawLine(puntoInicial.x, puntoInicial.y, puntoFinal.x, puntoFinal.y);
        this.g.drawRect(puntoFinal.x, puntoFinal.y, 15, 15);
        g.setColor(color);
    }

    public void dibujarTortuga(int direccion, Point puntoInicial, Color colorActual, boolean debeDibujarse) {
        this.puntoInicial = puntoInicial;
        CalculosTrigonometricos calculo = new CalculosTrigonometricos(hipotenusa);
        calculo.print(Math.toRadians(direccion));
        //Se le suma a la posicion en el tablero
        Double posX = this.puntoInicial.getX() + calculo.getX();
        Double posY = this.puntoInicial.getY() - calculo.getY();
        int posicionX = (int) Math.round(posX);
        int posicionY = (int) Math.round(posY);
        //Se le asigna el nuevo punto
        this.puntoFinal = new Point(posicionX, posicionY);
        if (debeDibujarse) {
            g.setColor(Color.green);
            this.g.drawLine(this.puntoInicial.x, this.puntoInicial.y, puntoFinal.x, puntoFinal.y);
            this.g.drawRect(puntoFinal.x, puntoFinal.y, 15, 15);
            g.setColor(colorActual);
        }
    }

    public Point getPuntoInicial() {
        return puntoInicial;
    }

    public void setPuntoInicial(Point puntoInicial) {
        this.puntoInicial = puntoInicial;
    }

    public Point getPuntoFinal() {
        return puntoFinal;
    }

    public void setPuntoFinal(Point puntoFinal) {
        this.puntoFinal = puntoFinal;
    }

    public Graphics getG() {
        return g;
    }

    public void setG(Graphics g) {
        this.g = g;
    }

}

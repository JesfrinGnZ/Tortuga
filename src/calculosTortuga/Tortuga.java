/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculosTortuga;

import frontend.gui.FramePrincipal;
import frontend.gui.LienzoInternalF;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JInternalFrame;

/**
 *
 * @author jesfrin
 */
public class Tortuga {

    //Linezo
    private LienzoInternalF lienzo;
    private final Graphics graphic;
    private FramePrincipal miFrame;
    //Hacia atras a la direccion se le suma 180
    private Point puntoActual;
    private Point puntoSiguiente;
    // private int distanciaA_Recorrer;
    private int direccion;
    private boolean debeDejarRastro;//Si se debe dibujar = true
    private boolean esVisibleLaImagen;//Si se deve ver la tortuga =true
    private boolean deboBorrarTrazos;//Si se debe pintar de blanco = true
    private ImagenTortuga imagen;
    private Color colorAnterior;

    public Tortuga(Point puntoActual, int direccion, LienzoInternalF lienzoInternalFrame, FramePrincipal miFrame) {
        this.puntoActual = puntoActual;
        this.puntoSiguiente = puntoActual;
        this.direccion = direccion;
        //Valores por defecto
        this.debeDejarRastro = true;
        this.esVisibleLaImagen = true;
        this.deboBorrarTrazos = false;
        this.graphic = lienzoInternalFrame.getGraphics();
        //Se inicia la tortuga
        this.imagen = new ImagenTortuga(this.graphic);
        //this.imagen.dibujarTortuga(direccion);
        this.lienzo = lienzoInternalFrame;
        this.miFrame = miFrame;

    }
//-----------------------------------------------------Operacion matematica--------------------------------------

    private void calcularPuntoSiguiente(Integer distancia) {
        //Haciendo calculos
        CalculosTrigonometricos calculo = new CalculosTrigonometricos(distancia);
        calculo.print(Math.toRadians(this.direccion));
        //Se le suma a la posicion en el tablero
        Double posX = this.puntoActual.getX() + calculo.getX();
        Double posY = this.puntoActual.getY() - calculo.getY();
        //Redondeando
        int posicionX = (int) Math.round(posX);
        System.out.println("PosicionX:" + posicionX);
        int posicionY = (int) Math.round(posY);
        System.out.println("PosicionY:" + posicionY);
        //Se le asigna el nuevo punto
        this.puntoSiguiente = new Point(posicionX, posicionY);
    }

//------------------------------------------------Actualiza la posicion actual con la nueva-----------------------------------
    private void actualizarPosicionActual() {
        this.puntoActual = this.puntoSiguiente;
    }

//------------------------------------------------Dibuja la linea y mueve a la tortuga al frente--------------------------------    
    public void dibujarLinea(Integer distancia) {
        calcularPuntoSiguiente(distancia);
        this.imagen.borrarTortuga(this.graphic.getColor());
        if (debeDejarRastro) {
            graphic.drawLine(puntoActual.x, puntoActual.y, puntoSiguiente.x, puntoSiguiente.y);
        }
        this.imagen.dibujarTortuga(this.direccion, this.puntoSiguiente, this.graphic.getColor(), this.esVisibleLaImagen);
        actualizarPosicionActual();
        //Se mueve la tortuga
        //this.miFrame.moverTortuga(this.puntoActual, this.esVisible);
    }

//------------------------------------------------Dibuja la linea y mueve a la tortuga hacia atras--------------------------------    
    public void dibujarLineaDeReversa(Integer distancia) {
        int direccionCopia = this.direccion;
        this.direccion += 180;//Se agrega a la direccion 180 para que se voltee
        calcularPuntoSiguiente(distancia);
        this.imagen.borrarTortuga(this.graphic.getColor());
        this.puntoActual = this.imagen.getPuntoFinal();
        if (debeDejarRastro) {
            graphic.drawLine(puntoActual.x, puntoActual.y, puntoSiguiente.x, puntoSiguiente.y);
        }
        this.imagen.dibujarTortuga(direccionCopia, this.puntoSiguiente, this.graphic.getColor(), this.esVisibleLaImagen);//La misma direccion anterior
        this.direccion = direccionCopia;
        actualizarPosicionActual();
    }

//--------------------------------------------------Manejo de direcciones-------------------------------------------    
    public void setDireccionRight(int direccion) {
        this.direccion -= direccion;
        //Borrando la tortuga actual
        this.imagen.borrarTortuga(this.graphic.getColor());
        //Dibujando la tortuga en su nueva direccion
        this.imagen.dibujarTortuga(this.direccion, this.puntoSiguiente, this.graphic.getColor(), this.esVisibleLaImagen);//La misma direccion anterior

    }

    public void setDireccionLeft(int direccion) {
        this.direccion += direccion;
        //Borrando la tortuga actual
        this.imagen.borrarTortuga(this.graphic.getColor());
        //Dibujando la tortuga en una nueva direccion
        this.imagen.dibujarTortuga(this.direccion, this.puntoSiguiente, this.graphic.getColor(), this.esVisibleLaImagen);//La misma direccion anterior

    }

//--------------------------------------------------CLEARS-------------------------------------------    
    public void clearLienzo() {
        this.lienzo.limpiarLienzo(Color.RED);
        this.puntoActual = new Point(350, 200);
        this.puntoSiguiente = puntoActual;
        this.direccion = 90;
        this.imagen.dibujarTortuga(direccion, puntoActual, this.graphic.getColor(), debeDejarRastro);
    }

//--------------------------------------------------PenUp-PenDown-------------------------------------------    
    public void setDebeDejarRastro(boolean debeDejarRastro) {
        this.debeDejarRastro = debeDejarRastro;
    }

//--------------------------------------------------To-Center-------------------------------------------    
    public void moverTortugaAlCentro() {
        this.puntoSiguiente = new Point(350, 200);//El centro
        this.imagen.borrarTortuga(this.graphic.getColor());
        if (debeDejarRastro) {
            graphic.drawLine(puntoActual.x, puntoActual.y, puntoSiguiente.x, puntoSiguiente.y);
        }
        this.imagen.dibujarTortuga(this.direccion, this.puntoSiguiente, this.graphic.getColor(), this.esVisibleLaImagen);
        actualizarPosicionActual();
    }

//--------------------------------------------------Color-------------------------------------------    
    public void setColorNumero(String numero) {
        switch (numero) {
            case "1":
                if (this.deboBorrarTrazos) {
                    this.colorAnterior = Color.BLACK;
                } else {
                    this.graphic.setColor(Color.BLACK);
                }
                break;
            case "2":
                if (deboBorrarTrazos) {
                    this.colorAnterior = Color.BLUE;

                } else {
                    this.graphic.setColor(Color.BLUE);
                }
                break;
            case "3":
                if (deboBorrarTrazos) {
                    this.colorAnterior = Color.CYAN;
                } else {
                    this.graphic.setColor(Color.CYAN);
                }
                break;
            case "4":
                if (deboBorrarTrazos) {
                    this.colorAnterior = Color.MAGENTA;
                } else {
                    this.graphic.setColor(Color.MAGENTA);
                }
                break;
            case "5":
                if (deboBorrarTrazos) {
                    this.colorAnterior = Color.ORANGE;
                } else {
                    this.graphic.setColor(Color.ORANGE);
                }
                break;
            case "6":
                if (deboBorrarTrazos) {
                    this.colorAnterior = Color.RED;
                } else {
                    this.graphic.setColor(Color.RED);
                }
                break;
            case "7":
                if (deboBorrarTrazos) {
                    this.colorAnterior = Color.YELLOW;
                } else {
                    this.graphic.setColor(Color.YELLOW);
                }
                break;
            case "8":
                if (deboBorrarTrazos) {
                    this.colorAnterior = Color.DARK_GRAY;
                } else {
                    this.graphic.setColor(Color.DARK_GRAY);
                }
                break;
            case "9":
                if (deboBorrarTrazos) {
                    this.colorAnterior = Color.pink;
                } else {
                    this.graphic.setColor(Color.PINK);
                }
                break;
            default:
                //this.miFrame.escribirMensaje("Error semantico, el numero de color debe estar entre 0-9\n");
                break;
        }
    }

    public void setColorHexadecimal(String hex) {
        hex = hex.substring(1, hex.length() - 1);
        Color nuevoColor = Color.decode(hex);
        if (deboBorrarTrazos) {
            this.colorAnterior = nuevoColor;
        } else {
            this.graphic.setColor(nuevoColor);
        }
    }

//--------------------------------------------------PositionXY-------------------------------------------    
    public void nuevoPuntoFinalXY(String x, String y) {//350, 200
        this.puntoSiguiente = new Point(Integer.parseInt(x), Integer.parseInt(y));//El centro
        this.imagen.borrarTortuga(this.graphic.getColor());
        if (debeDejarRastro) {
            graphic.drawLine(puntoActual.x, puntoActual.y, puntoSiguiente.x, puntoSiguiente.y);
        }
        this.imagen.dibujarTortuga(this.direccion, this.puntoSiguiente, this.graphic.getColor(), this.esVisibleLaImagen);
        actualizarPosicionActual();
    }

//--------------------------------------------------PositionX-------------------------------------------    
    public void nuevoPuntoFinalX(String x) {
        this.puntoSiguiente = new Point(Integer.parseInt(x), this.puntoActual.y);//El centro
        this.imagen.borrarTortuga(this.graphic.getColor());
        if (debeDejarRastro) {
            graphic.drawLine(puntoActual.x, puntoActual.y, puntoSiguiente.x, puntoSiguiente.y);
        }
        this.imagen.dibujarTortuga(this.direccion, this.puntoSiguiente, this.graphic.getColor(), this.esVisibleLaImagen);
        actualizarPosicionActual();
    }
//--------------------------------------------------PositionX-------------------------------------------    

    public void nuevoPuntoFinaly(String y) {
        this.puntoSiguiente = new Point(this.puntoActual.x, Integer.parseInt(y));//El centro
        this.imagen.borrarTortuga(this.graphic.getColor());
        if (debeDejarRastro) {
            graphic.drawLine(puntoActual.x, puntoActual.y, puntoSiguiente.x, puntoSiguiente.y);
        }
        this.imagen.dibujarTortuga(this.direccion, this.puntoSiguiente, this.graphic.getColor(), this.esVisibleLaImagen);
        actualizarPosicionActual();
    }

    //--------------------------------------------------Hideturtle-------------------------------------------    
    public void ocultarTortuga() {
        this.esVisibleLaImagen = false;
        //Ahora procedemos a ocultar la tortuga
        this.imagen.borrarTortuga(this.graphic.getColor());
    }

//------------------------------------------------------Show turtle-----------------------------------------
    public void mostrarTortuga() {
        this.esVisibleLaImagen = true;
        //Ahora procedemos a ocultar la tortuga
        this.imagen.dibujarTortuga(this.direccion, this.puntoSiguiente, this.graphic.getColor(), this.esVisibleLaImagen);

    }

    //--------------------------------------------------To erase-------------------------------------------    
    public void activarBorrador() {
        this.colorAnterior = this.graphic.getColor();
        this.graphic.setColor(Color.white);
        this.debeDejarRastro = true;
        this.deboBorrarTrazos = true;
    }

    //--------------------------------------------------To Draw-------------------------------------------    
    public void desactivarBorrador() {
        this.graphic.setColor(this.colorAnterior);
        this.deboBorrarTrazos = false;
    }

    //--------------------------------------------------Getter and setter-------------------------------------
    public Graphics getGraphic() {
        return graphic;
    }

    public Point getPuntoActual() {
        return puntoActual;
    }

    public int getDireccion() {
        return direccion;
    }

    public ImagenTortuga getImagen() {
        return imagen;
    }

}

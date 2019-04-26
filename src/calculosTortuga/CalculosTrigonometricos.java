/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculosTortuga;

/**
 *
 * @author jesfrin
 */
public class CalculosTrigonometricos {
    private double x;
    private double y;
    
    public CalculosTrigonometricos(double hipotenusa)
    {
        this.x=hipotenusa;
        this.y=hipotenusa;
    }
    public void print(double theta)
    {
        if( ((Math.toDegrees(theta) / 90) % 2) == 1)
        {
            x = x*0;
            y = y*Math.sin(theta);
        }
        else if( ((Math.toDegrees(theta) / 90) % 2) == 0)
        {
            x = x*Math.cos(theta);
            y = y*0; 
        }
        else
        {
           x = x*Math.cos(theta);
           y = y*Math.sin(theta); 
        }
        System.out.println("x: "+x);
        System.out.println("y: "+y);
    }

    public double getX() {
        return x;
    }


    public double getY() {
        return y;
    }

}

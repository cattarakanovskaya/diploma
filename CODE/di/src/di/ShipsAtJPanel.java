/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di;
import java.lang.Math;
import static java.lang.Math.sin;

public class ShipsAtJPanel {
    int x;
    int y;
    int xm;
    int ym;
    
    
    ShipsAtJPanel(float w, float l){
        y=(int) (311+((MapMap.centrwidth-w)/0.0001)*0.06);
        x=(int) (446-((MapMap.centrlongitude-l)/0.001)*0.4); 
        long r=6371000;
        long ym1=(long) (r*Math.toRadians(w));
        long xm1=(long) (r*sin(Math.toRadians(90-w))*Math.toRadians(l));
       /* System.out.print('\n');
        System.out.print(xm1);
        System.out.print('\n');
        System.out.print(ym1);*/
        long ymc=(long) (r*Math.toRadians(MapMap.centrwidth));
        long xmc=(long) (r*sin(Math.toRadians(90-MapMap.centrwidth))*Math.toRadians(MapMap.centrlongitude));
        xm = (int) (446-(xmc-xm1)*0.00374); 
        ym=(int) (311+(ymc-ym1)*0.004);
        
    }
    
}

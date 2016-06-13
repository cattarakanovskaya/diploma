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
        y=(int) (311+((MapMap.centrwidth-w)/0.0001)*0.2);
        x=(int) (447-((MapMap.centrlongitude-l)/0.001)*1.4); 
        long r=6371000;
        long ym1=(long) (r*w*Math.PI/180);
        long xm1=(long) (r*sin(90-w)*l*Math.PI/180);
       /* System.out.print('\n');
        System.out.print(xm1);
        System.out.print('\n');
        System.out.print(ym1);*/
        long ymc=(long) (r*MapMap.centrwidth*Math.PI/180);
        long xmc=(long) (r*sin(90-MapMap.centrwidth)*MapMap.centrlongitude*Math.PI/180);
        xm = (int) (447-(xmc-xm1)*0.024); 
        ym=(int) (311+(ymc-ym1)*0.003);
        
    }
    
}

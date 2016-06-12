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
    long xm;
    long ym;
    
    
    ShipsAtJPanel(float w, float l){
        y=(int) (311+((MapMap.centrwidth-w)/0.0001)*0.2);
        x=(int) (447-((MapMap.centrlongitude-l)/0.001)*1.4); 
        long r=6371000;
        ym=(long) (r*w*Math.PI/180);
        xm=(long) (r*sin(90-w)*l*Math.PI/180);
    }
    
}

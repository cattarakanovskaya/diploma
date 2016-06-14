/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di;

import static java.lang.Math.sin;

/**
 *
 * @author Kate
 */
public class ImaginaryShip {
    float width;
    float longitude;
    ImaginaryShip(int w, int l){
        width = (float) (MapMap.centrwidth+((311-w)*1000/4)*180/(6371000*Math.PI));
        longitude = (float) (MapMap.centrlongitude-((447-l)*100000/375)*180/(6371000*sin(Math.toRadians(90-width)*Math.PI)));
        System.out.print('\n');
        System.out.print(width);
        System.out.print('\n');
        System.out.print(longitude);
    }
}

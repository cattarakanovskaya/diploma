/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di;

/**
 *
 * @author Kate
 */
public class ImaginaryShip {
    float width;
    float longitude;
    ImaginaryShip(int w, int l){
        width = (float) (MapMap.centrwidth+(311-w)*0.0005);
        longitude = (float) (MapMap.centrlongitude-(447-l)*0.007);
        System.out.print('\n');
        System.out.print(width);
        System.out.print('\n');
        System.out.print(longitude);
    }
}

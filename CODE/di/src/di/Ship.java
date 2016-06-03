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
public class Ship {
    int ID;
    float width;
    float longitude;
    float speed;
    int course;
    Ship(int id, float w, float l, float s, int c){
        ID = id;
        width = w;
        longitude = l;
        speed = s;
        course = c;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di;
import java.lang.String;

/**
 *
 * @author Kate
 */
public class Line {
    int ID;
    float width;
    float longitude;
    float speed;
    int course;
    long date;

    
    Line(int id, float w, float l, float s, int c, long d){
        ID = id;
        width = w;
        longitude = l;
        speed = s;
        course = c;
        date = d;
    }
}

package di;


public class MapMap {
    static float centrwidth; //широта центра
    static float centrlongitude; //долгота центра
    
    
    MapMap(float w, float l){
        centrwidth = w;
        centrlongitude = l;
    }
    
    
    void Centr(float mw, float lw, float ml, float ll){
       // System.out.print(mw);
        //System.out.print('\n');
        centrwidth=(float) (((mw+0.001)-(lw-0.001))/2);
        centrlongitude=(float) (((ml+0.001)-(ll-0.001))/2);
        //System.out.print(centrwidth);
        //System.out.print('\n');
        //System.out.print(centrlongitude);
    }
}

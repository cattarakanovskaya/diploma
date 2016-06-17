package di;

import static java.lang.Math.sin;

public class ImaginaryShip {
    
    public float width;
    public float longitude;
    
    private Map map;
    
    ImaginaryShip(int w, int l, Map map){
        width = (float) (map.centerSize.x+((311-w)*1000/4)*180/(6371000*Math.PI));
        longitude = (float) (map.centerSize.y-((447-l)*100000/375)*180/(6371000*sin(Math.toRadians(90-width)*Math.PI)));
        /*System.out.print('\n');
        System.out.print(width);
        System.out.print('\n');
        System.out.print(longitude);*/
    }
}

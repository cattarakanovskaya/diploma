package di;

import static java.lang.Math.sin;

public class ImaginaryShip {

    public float width;
    public float longitude;

    private Map map;

    ImaginaryShip(int w, int l, Map map) {
        int r = 6371000;
        long ymc=(long) (r*Math.toRadians(map.centerSize.x));
        int scalew=451;
        int scalel=43;
        if(map.getZoom()>8){
            scalew=scalew*2*(map.getZoom()-8);
            scalel=scalel*2*(map.getZoom()-8);
        }
        if(map.getZoom()<8){
            scalew=scalew/2*(8-map.getZoom());
            scalel=scalel/2*(8-map.getZoom());
        }
        if(map.getZoom()==11){
            scalew= scalew+88;
            scalel= scalel+86;
        }

        //if (map.getZoom() == 8) {
            width = (float) ((ymc + (311 - w) * 100000 / scalew) * 180 / (6371000 * Math.PI));
            long xmc=(long) (r*sin(Math.toRadians(90-width))*Math.toRadians(map.centerSize.y));
            longitude = (float) ((xmc - (446 - l) * 10000 / scalel) * 180 / (6371000 * sin(Math.toRadians(90 - width)) * Math.PI));
        //}
       /* if (map.getZoom() == 9) {
            width = (float) (map.centerSize.x + ((315 - w) * 10000 / 85) * 180 / (6371000 * Math.PI));
            longitude = (float) (map.centerSize.y - ((450 - l) * 1 / 9) * 180 / (6371000 * sin(Math.toRadians(90 - width) * Math.PI)));
        }*/
        System.out.print(width);
        System.out.print(' ');
        System.out.print(longitude);
        System.out.print('\n');
        
    }
}

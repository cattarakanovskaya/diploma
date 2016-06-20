package di;

import di.utils.Vector2f;
import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Ship {

    private int id;
    private float latitude;
    private float longitude;
    private float speed;
    private int course;
    private long time = 0;

    public Ship(int id, float latitude, float longitude, float speed, int course) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.speed = speed;
        this.course = course;
    }
    
    public Ship(int id, float latitude, float longitude, float speed, int course, long time) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.speed = speed;
        this.course = course;
        this.time = time;
    }

    public Vector2i getJPanelCoords(Map map) {
        float w = latitude;
        float l = longitude;
        float xPix = (float) 0.00451;
        float yPix = (float) 0.0043;
        Vector2i coords = new Vector2i();
        if(map.getZoom()>8){
            xPix=xPix*2*(map.getZoom()-8);
            yPix=yPix*2*(map.getZoom()-8);
        }
        if(map.getZoom()<8){
            xPix=xPix/2*(8-map.getZoom());
            yPix=yPix/2*(8-map.getZoom());
        }
        if(map.getZoom()==11){
            xPix=(float) (xPix+0.0088);
            yPix=(float) (yPix+0.0086);
        }

        int r = 6371000;
        long ym1=(long) (r*Math.toRadians(w));
        long xm1=(long) (r*sin(Math.toRadians(90-w))*Math.toRadians(l));
        long ymc=(long) (r*Math.toRadians(map.centerSize.x));
        long xmc=(long) (r*sin(Math.toRadians(90-w))*Math.toRadians(map.centerSize.y));
        coords.x = (int) (446-(xmc-xm1)*xPix); 
        coords.y =(int) (315+(ymc-ym1)*yPix);
        
        
        return coords;
    }

    public int getId() {
        return id;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public float getSpeed() {
        return speed;
    }

    public int getCourse() {
        return course;
    }
    
    public long getTime() {
        return time;
    }

    public class Vector2i {
        int x, y;
    }
    
    public Vector2i getCourse(Map map){
        Vector2i coords = new Vector2i();
        int ym = (int) (cos(Math.toRadians(course))*speed*1852);
        int xm = (int) (sin(Math.toRadians(course))*speed*1852);
        float xPix = (float) 0.00451;
        float yPix = (float) 0.0043;
        if(map.getZoom()>8){
            xPix=xPix*2*(map.getZoom()-8);
            yPix=yPix*2*(map.getZoom()-8);
        }
        if(map.getZoom()<8){
            xPix=xPix/2*(8-map.getZoom());
            yPix=yPix/2*(8-map.getZoom());
        }
        if(map.getZoom()==11){
            xPix=(float) (xPix+0.0088);
            yPix=(float) (yPix+0.0086);
        }
        coords.y = (int) (ym*yPix);
        coords.x = (int) (xm*xPix);
        
        
        
        return coords;
    }

}

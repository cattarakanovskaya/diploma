package di;

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
        Vector2i coords = new Vector2i();
        
        //int y = (int) (311 + ((Map1.centrwidth - w) / 0.0001) * 0.06);
        //int x = (int) (446 - ((Map1.centrlongitude - l) / 0.001) * 0.4);

        long r = 6371000;
        long ym1 = (long) (r * Math.toRadians(w));
        long xm1 = (long) (r * sin(Math.toRadians(90 - w)) * Math.toRadians(l));
        long ymc = (long) (r * Math.toRadians(map.centerSize.x));
        long xmc = (long) (r * sin(Math.toRadians(90 - map.centerSize.x)) * Math.toRadians(map.centerSize.y));
        coords.x = (int) (446 - (xmc - xm1) * 0.00374);
        coords.y = (int) (311 + (ymc - ym1) * 0.004);
        
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

}

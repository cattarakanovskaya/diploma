package di;

import di.utils.Vector2f;
import java.awt.Color;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Map {

    private ArrayList<Ship> ships = new ArrayList();

    private Vector2f mostSize = new Vector2f();
    private Vector2f leastSize = new Vector2f();
    public Vector2f centerSize = new Vector2f();
    public Vector2f imaginary;
    
    public long firstMoment = Long.MAX_VALUE;
    public long lastMoment = Long.MIN_VALUE;

    private JPanel mapPanel;
    private int zoom;
    public long now;

    public Map() {
        mostSize.x = mostSize.y = Float.MIN_VALUE;
        leastSize.x = leastSize.y = Float.MAX_VALUE;
        centerSize.x = centerSize.y = Float.MIN_VALUE;
        zoom = 8;
    }

    public void addShip(Ship ship) {
        mostSize.x = Math.max(mostSize.x, ship.getLatitude());
        mostSize.y = Math.max(mostSize.y, ship.getLongitude());
        leastSize.x = Math.min(leastSize.x, ship.getLatitude());
        leastSize.y = Math.min(leastSize.y, ship.getLongitude());
        firstMoment = Math.min(firstMoment, ship.getTime());
        lastMoment = Math.max(lastMoment, ship.getTime());
        now=firstMoment;
        ships.add(ship);
    }

    public void calculateCenter() {
        centerSize.x = leastSize.x + (mostSize.x - leastSize.x) / 2;
        centerSize.y = leastSize.y + (mostSize.y - leastSize.y) / 2;
    }

    public void setPanel(JPanel mapPanel) {
        this.mapPanel = mapPanel;
    }

    private Image getMapImage() throws MalformedURLException {
        String url = "https://maps.googleapis.com/maps/api/staticmap?center="+centerSize.x+","+centerSize.y+"&zoom="+zoom+"&scale=2&size=450x325&maptype=roadmap&markers=color:blue%7Clabel:C%7C42.76455,132.75894&markers=color:white%7Clabel:Q%7C42.7735,132.7679&markers=color:white%7Clabel:Q%7C42.7734,132.952&markers=color:red%7Clabel:Q%7C42.5485,132.951&key=AIzaSyDm3iFPxUwcgpWmNEI_wto0mHwT_99JQUk";

        return new ImageIcon(new URL(url)).getImage();
    }

    public void draw(long moment) {
        try {
            mapPanel.getGraphics().drawImage(getMapImage(), 0, 0, mapPanel.getSize().width, mapPanel.getSize().height, null);
        } catch (Exception ex) {
            Logger.getLogger(LoadFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        mapPanel.getGraphics().setColor(Color.LIGHT_GRAY);
        mapPanel.getGraphics().drawOval(446, 315, 7, 7); // центр
       // mapPanel.getGraphics().drawOval(453, 315, 7, 7);
       // mapPanel.getGraphics().drawOval(453, 307, 7, 7);
        ArrayList<Ship> shipsAtMoment = searchShipsAtMoment(moment);
        mapPanel.getGraphics().setColor(Color.red);
        for (Ship ship : shipsAtMoment) {
             mapPanel.getGraphics().setColor(Color.LIGHT_GRAY);
            mapPanel.getGraphics().fillOval(
                    ship.getJPanelCoords(this).x,
                    ship.getJPanelCoords(this).y, 7, 7
            );
            mapPanel.getGraphics().drawLine(
                    ship.getJPanelCoords(this).x+4,
                    ship.getJPanelCoords(this).y+4, ship.getJPanelCoords(this).x+ship.getCourse(this).x, ship.getJPanelCoords(this).y-ship.getCourse(this).y
            );
        }
    }

    public ArrayList<Ship> searchShipsAtMoment(long moment) {
        ArrayList<Ship> momentShips = new ArrayList();
        for (Ship ship : ships) {
            if (moment == ship.getTime()) {
                momentShips.add(ship);
            }
        }
        return momentShips;
    }
    
    public void more(){
        zoom++;
        this.draw(now);
    }
    
    public void less(){
        zoom--;
        this.draw(now);
    }
    
    public void left(){
        centerSize.y=(float) (centerSize.y-0.1);
        this.draw(now);
    }
    
    public void right(){
        centerSize.y=(float) (centerSize.y+0.1);
        this.draw(now);
    }
    
    public void up(){
        centerSize.x=(float) (centerSize.x+0.01);
        this.draw(now);
    }
    
    public void down(){
        centerSize.x=(float) (centerSize.x-0.01);
        this.draw(now);
    }
    
    public int getZoom(){
        return this.zoom;
    }
    
    public void imaginaryGet(float w, float l){
        imaginary.x = w;
        imaginary.y = l;
    }
    
}

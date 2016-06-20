package di;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MapPlayer {

    private Map map;
    private long start, finish, now;
    private MapPlayerState state;
    private Thread thread;
    private javax.swing.JSlider slider;

    public MapPlayer(final Map map, final javax.swing.JSlider slider) {
        this.map = map;
        this.slider = slider;
        start = map.firstMoment;
        finish = map.lastMoment;
        now = start;
        state = MapPlayerState.PLAY;
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    map.draw(now);
                    if (state == MapPlayerState.PLAY) {
                        now += 60;
                        map.now = now;
                        slider.setValue(getTimeBarCoord());
                    }
                    if (now >= finish) {
                        now = start;
                        map.now = now;
                        state = MapPlayerState.PAUSE;
                        slider.setValue(getTimeBarCoord());
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        thread.start();
    }
    
    public MapPlayer(final Map map, int bar){
        this.map = map;
        start = map.firstMoment;
        finish = map.lastMoment;
        now = start+bar*(finish-start)/100;
        System.out.print(now);
        System.out.print('\n');
        map.draw(now);
        
    }

    public void play() {
        if (state == MapPlayerState.PLAY) {
            state = MapPlayerState.PAUSE;
        } else if (state == MapPlayerState.PAUSE) {
            state = MapPlayerState.PLAY;
        }
    }

    public int getTimeBarCoord() {
        return (int) ( (now - start)*100 /  (finish - start));
    }

    public enum MapPlayerState {
        PLAY,
        PAUSE
    }
}

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
                    map.now = now;
                    map.draw(now);
                    if (state == MapPlayerState.PLAY) {
                        now += 60;
                        slider.setValue(getTimeBarCoord());
//                        ImaginaryShip s = new ImaginaryShip(map.imaginary.x, map.imaginary.y);
                       // Count c = new Count(s, map.searchShipsAtMoment(now));
                       /* System.out.print(map.imaginary.x);
                        System.out.print(' ');
                        System.out.print(map.imaginary.y);
                        System.out.print('\n');
                        slider.setValue(getTimeBarCoord());*/
                    }
                    if (now >= finish) {
                        now = start;
                        state = MapPlayerState.PAUSE;
                        slider.setValue(getTimeBarCoord());
                    }
                    try {
                        Thread.sleep(1000/6);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        thread.start();
    }

    public MapPlayer(final Map map, int bar) {
        this.map = map;
        start = map.firstMoment;
        finish = map.lastMoment;
        now = start + bar * (finish - start) / 100;
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
    
    public void pause() {
        state = MapPlayerState.PAUSE;
    }

    public int getTimeBarCoord() {
        return (int) ((now - start) * 100 / (finish - start));
    }

    public long getBarCoordTime(int val) {
        int n = (int) ((finish - start) / 60);
        
        return start + 60 * (val * n / 100);
    }

    public void setNow(long now) {
        if (state != MapPlayerState.PLAY) {
            this.now = now;
            pause();
        }
    }

    public enum MapPlayerState {
        PLAY,
        PAUSE
    }
}

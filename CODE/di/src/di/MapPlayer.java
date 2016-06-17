package di;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MapPlayer {

    private Map map;
    private long start, finish, now;
    private MapPlayerState state;
    private Thread thread;

    public MapPlayer(final Map map) {
        this.map = map;
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
                    }
                    if (now >= finish) {
                        now = start;
                        state = MapPlayerState.PAUSE;
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

    public void play() {
        if (state == MapPlayerState.PLAY) {
            state = MapPlayerState.PAUSE;
        } else if (state == MapPlayerState.PAUSE) {
            state = MapPlayerState.PLAY;
        }
    }

    public float getTimeBarCoord() {
        return ((float) (now - start)) / ((float) (finish - start));
    }

    public enum MapPlayerState {
        PLAY,
        PAUSE
    }
}

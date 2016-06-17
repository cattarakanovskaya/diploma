package di;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MapInfoFileReader {

    private BufferedReader reader;

    public MapInfoFileReader(String fileName) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(fileName));
    }

    public Map read() throws IOException, ParseException {
        Map map = new Map();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parsedLines = line.split("\\t");
            if (parsedLines.length < 7) {
                throw new ParseException("Parsing line exception", 0);
            }
            int id = Integer.parseInt(parsedLines[0]);
            float latitude = Float.parseFloat(parsedLines[2]);
            float longitude = Float.parseFloat(parsedLines[1]);
            float speed = Integer.parseInt(parsedLines[3]);
            int course = Integer.parseInt(parsedLines[4]);
            int age = Integer.parseInt(parsedLines[5]);
            long time = parseTime(age, parsedLines[6]);
            if (course == 511 || age > 10) {
                continue;
            }
            Ship ship = new Ship(id, latitude, longitude, speed, course, time);
            map.addShip(ship);
        }
        map.calculateCenter();
        return map;
    }

    private long parseTime(int age, String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("d.M.y hh:mm");
        Date d = format.parse(date);
        System.out.print(d.getDay());
        System.out.print(' ');
        System.out.print(d.getMonth());
        System.out.print(' ');
        System.out.print(d.getYear());
        System.out.print(' ');
        System.out.print(d.getHours());
        
        System.out.print(' ');
        System.out.print(d.getMinutes());
        System.out.print(' ');
        System.out.print(d);
        System.out.print('\n');
        System.out.print(date);
        System.out.print('\n');
        return d.getTime() / 1000 - age * 60;
    }

}

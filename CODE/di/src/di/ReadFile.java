package di;
import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.String;
import java.util.Calendar;


public class ReadFile {
    static ArrayList <Line> AllLists = new ArrayList(); //записи(строки в текстовом файле с кораблями)
    ArrayList <Ship> ShipsAtMoment = new ArrayList();//суда в момент времени
    static boolean flag; //флаг загрузки файла, чтобы отрисовать карту
    static float mostwidth; //самая большая широта
    static float leastwidth; //самая маленькая широта
    static float mostlongitude; //самая большая долгота
    static float leastlongitude; //самая маленькая долгота
    static float centrwidth; // широта центра
    static float centrlongitude; // долгота центра
    
    
    
    
    ReadFile(String filename) throws FileNotFoundException, IOException, ParseException{ //конструктор
        flag=false;
        FileInputStream fileToRead = new FileInputStream(filename);
        InputStreamReader myfile = new InputStreamReader( new FileInputStream(filename));
        FileReader reader = new FileReader(filename);
        int c;
        String s = "";
        boolean first=true;
        while((c = fileToRead.read())!= -1){
            if((char)c != '\r'&&(char)c!='\n'){
                if((char)c == ','){
                    s = s + '.';
                }
                else s = s + (char)c;
            }
            else{
                if((char)c=='\n'){
                    Line l = parseString(s);
                    AllLists.add(l);
                    s = "";
                    if(first==true){
                        first=false;
                        mostwidth=l.width;
                        leastwidth=l.width;
                        mostlongitude=l.longitude;
                        leastlongitude=l.longitude;
                    }
                }
            }
        }
        centrwidth=(float) (mostwidth - ((mostwidth+0.0001)-(leastwidth-0.001))/2);
        //System.out.print(centrwidth);
        centrlongitude=(float) (mostlongitude-((mostlongitude+0.0001)-(leastlongitude-0.001))/2);
        flag=true;
    }
    
    
    
    Line parseString(String bigS) throws ParseException{ //парсилка одной строки и инииализация объектов ship 
        int ID = 0;
        int course = 0;
        int age = 0;
        float width = 0;
        float longitude = 0;
        float speed = 0;
        long date = 0;
        int columns = 7;
        for(int i = 0; i<bigS.length(); i++){
            String field = "";
            while(i<bigS.length() && bigS.charAt(i)!='\t'){
                field = field + bigS.charAt(i);
                i++;
            }
            columns--;
            switch (columns){
                case 0: {
                    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm");
                    Date d = format.parse(field);
                    long dm = d.getTime() - age*60000;
                    date = dm;
                }
                    break;
                case 1: age = Integer.parseInt(field);
                    break;
                case 2: course = Integer.parseInt(field);
                    break;
                case 3: speed = Float.parseFloat(field);
                    break;
                case 4: width = Float.parseFloat(field);
                    break;
                case 5: longitude = Float.parseFloat(field);
                    break;
                case 6: ID = Integer.parseInt(field);
                    break;
            }
        }
        Line l = new Line(ID, width, longitude, speed, course, date);
        if(width>mostwidth) mostwidth=width;
        if(width<leastwidth) leastwidth=width;
        if(longitude>mostlongitude) mostlongitude=longitude;
        if(longitude<leastlongitude) leastlongitude=longitude;
        return l;
    }
    
    
    
    ArrayList <Ship> searchShipsAtMoment(long moment){ // поиск всех судов в один момент времени
        ArrayList <Ship> Ships = new ArrayList();
        int i = 0;
        while(i<AllLists.size()){
            if(moment==AllLists.get(i).date){
                Ship s = new Ship(AllLists.get(i).ID, AllLists.get(i).width, AllLists.get(i).longitude, AllLists.get(i).speed, AllLists.get(i).course);
                Ships.add(s);
            }
            i++;
        }
        return Ships;
    }
    
    
    
}

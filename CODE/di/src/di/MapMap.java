package di;

import static di.ReadFile.AllLists;
import java.util.ArrayList;


public class MapMap {
    static float centrwidth; //широта центра
    static float centrlongitude; //долгота центра
    static long moment;
    static ArrayList <Ship> ShipsAtMoment;
    static ArrayList <ShipsAtJPanel> ShipAtJPanel;
    
    
    MapMap(float w, float l, long m){
        centrwidth = w;
        centrlongitude = l;
        moment = m;
        ShipsAtMoment = searchShipsAtMoment(m);
    }
    
    
    static ArrayList <Ship> searchShipsAtMoment(long moment){ // поиск всех судов в один момент времени
        ArrayList <Ship> Ships = new ArrayList();
        int i = 0;
        while(i<AllLists.size()){
           /* System.out.print('\n');
            System.out.print(AllLists.size());
            System.out.print('\n');
            System.out.print(AllLists.get(i).ID);*/
            if(moment==AllLists.get(i).date){
              //  System.out.print(i);
                Ship s = new Ship(AllLists.get(i).ID, AllLists.get(i).width, AllLists.get(i).longitude, AllLists.get(i).speed, AllLists.get(i).course);
                Ships.add(s);
               /* System.out.print('\n');
                System.out.print(s.ID);
                System.out.print(s.course);
                System.out.print('\n');*/
            }
            i++;
        }
        return Ships;
    }
    
    
    static void XY(){
        ArrayList <ShipsAtJPanel> NewXY = new ArrayList();
        ShipsAtMoment = searchShipsAtMoment(moment);
        for (Ship ShipsAtMoment1 : ShipsAtMoment) {
            ShipsAtJPanel s = new ShipsAtJPanel(ShipsAtMoment1.width, ShipsAtMoment1.longitude);
            NewXY.add(s);
        }
        ShipAtJPanel = NewXY;
    }
}

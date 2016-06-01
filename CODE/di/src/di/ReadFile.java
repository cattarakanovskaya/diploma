/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Kate
 */
public class ReadFile {
    ArrayList <Line> AllLists = new ArrayList();
    ReadFile(String filename) throws FileNotFoundException, IOException, ParseException{
        FileInputStream fileToRead = new FileInputStream(filename);
        InputStreamReader myfile = new InputStreamReader( new FileInputStream(filename));
        FileReader reader = new FileReader(filename);
        int c;
        String s = "";
        while((c = fileToRead.read())!= -1){
            if((char)c != '\r'&&(char)c!='\n'){
                if((char)c == ','){
                    s = s + '.';
                }
                else s = s + (char)c;
            }
            else{
                //Line l = new Line(0, 0, 0, 0, 0, "");
                if((char)c=='\n'){
                Line l = parseString(s);
                AllLists.add(l);
                s = "";
                }
            }
        } 
        for(int i = 0; i<AllLists.size(); i++){
            System.out.print(AllLists.get(i).ID);
            System.out.print('\n');
        }
    }
    Line parseString(String bigS) throws ParseException{
        int ID = 0;
        int course = 0;
        int age = 0;
        float width = 0;
        float longitude = 0;
        float speed = 0;
        String date = "";
        int columns = 7;
        for(int i = 0; i<bigS.length(); i++){
           // while(bigS.charAt(i)!='\n'){
                String field = "";
                while(i<bigS.length() && bigS.charAt(i)!='\t'){
                    field = field + bigS.charAt(i);
                    i++;
                }
                columns--;
                switch (columns){
                    case 0: date = field;
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
            /*SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("dd.MM.yyyy");
            Date docDate= format.parse(s);*/
            }
       // }
        Line l = new Line(ID, width, longitude, speed, course, date);
        //AllLists.add(l);
        return l;
    }
}

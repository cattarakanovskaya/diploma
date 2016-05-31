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
import java.io.InputStreamReader;

/**
 *
 * @author Kate
 */
public class ReadFile {
    ArrayList <Line> AllLists;
    ReadFile(String filenme) throws FileNotFoundException{
        //FileInputStream fileToRead = new FileInputStream("filename");
        InputStreamReader myfile = new InputStreamReader( new FileInputStream("filename"));
        //String s1 = myfile.read();
        
    }
}

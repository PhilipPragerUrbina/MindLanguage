package Brainf.Language.Mind;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

//represents mind source files to read
//source files are .mind files
public class SourceFile {
    private File file;
    private Scanner scanner;

    //create file from full path with filename and extension
    public SourceFile(String path){
        try {
            file = new File(path);
            scanner = new Scanner(file);
            System.out.println("Opened file: " +  file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //get next line in file
    //returns null if at end of file
    public String nextLine(){
        while (scanner.hasNextLine()){
            String next_line =  scanner.nextLine();
            if(!next_line.contains("//")){
                return next_line; //ignore comment lines
            }
        }
            return null;
    }
    //close file
    public void close(){
            scanner.close();
            System.out.println("Finished reading: " + file.getName());
    }
}

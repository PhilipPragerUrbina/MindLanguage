package Brainf.Language.Mind;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//represent an output file of brainF
public class BrainFile {
    private File file;
    private FileWriter writer;

    //create file from full path with filename and extension
    public BrainFile(String path){
        try {
            file = new File(path);
            file.createNewFile();
            writer = new FileWriter(file);
            System.out.println("Created file: " +  file.getName());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //create file from location and name without extension
    public BrainFile(String location, String name){
        this(location + "/" + name + ".bf");
    }

    //write instructions
    public void writeInstruction(char instruction){
        try {
            writer.append(instruction);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeInstructions(String instructions){
        try {
            writer.write(instructions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //close file
    public void close(){
        try {
            writer.close();
            System.out.println("Finished writing: " + file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
package Brainf.Language.Mind;

import java.util.ArrayList;

public class Expander {
    ArrayList<Instruction> instructions;
    BrainFile output;
    State state;

    public Expander(  ArrayList<Instruction> instructions){
        this.instructions = instructions;
        this.state = new State();
    }
    public void compile(BrainFile output){
        this.output = output;
        for (Instruction instruction : instructions) {
            output.writeInstructions(instruction.expand(state));
        }
        output.close();
    }

    public static String move(int offset){
        String out = "";
       if(offset > 0){
           for (int i = 0; i < offset; i++) {
               out+= ">";
           }
       }else {
           for (int i = 0; i > offset; i--) {
               out+= "<";
           }
       }
       return out;
    }

    public static String constant(char num){
        String out = "";
        for (char i = 0; i < num ; i++) {
            out += "+";
        }
        return out;
    }



}

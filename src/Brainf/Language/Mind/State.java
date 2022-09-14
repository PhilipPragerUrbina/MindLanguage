package Brainf.Language.Mind;

import java.util.HashMap;
import java.util.Stack;

//store the state of the compiler
public class State {
    private HashMap<String, Integer> variables = new HashMap<>(); //all variables and their position in memory
    private Stack<String> loop_variables = new Stack<>();    //variables being used in loops

    //pointers
    private final static int num_registers = 2; //number of reserved spaces for operations
    //todo make new var pointer overwrite unused variables, both with keeping track of finished vars in array, and with moving it back
    private int new_var_pointer = num_registers+1; //where to add new variables, starts after registers
    private int current_pointer = 0;

    //register a new loop
    public void registerLoop(String variable){
        loop_variables.push(variable);
    }
    //end a loop and return it's variable
    public String endLoop(){
        return loop_variables.pop();
    }

    //create a variable if it doesn't exist and return it's index
    public int getVariableIndex(String variable){
        if(variables.containsKey(variable)){
            return variables.get(variable);
        }
        //create new variable
        variables.put(variable,new_var_pointer );
        new_var_pointer++;
        return new_var_pointer-1;
    }

    //movement functions
    public int getCurrentPointer(){
        return current_pointer;
    }
    public void setCurrentPointer(int idx){
        current_pointer = idx;
    }
    public void moveCurrentPointer(int offset){
        current_pointer += offset;
    }
    public void moveVariablePointer(int offset){
        new_var_pointer += offset;
    }
    public String goToIndex(int idx){

        String to_ret =  Expander.move(idx - getCurrentPointer());
        current_pointer = idx;
        return to_ret;
    }

}

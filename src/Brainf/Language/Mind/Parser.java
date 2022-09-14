package Brainf.Language.Mind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//create set of instructions from code
public class Parser {
    class Function{
        String param_a;
        String param_b;
        ArrayList<Instruction> instructions = new ArrayList<>();

        public Function(String param_a, String param_b) {
            this.param_a = param_a;
            this.param_b = param_b;
        }
        public Function(String param_a) {
            this.param_a = param_a;
            this.param_b = null;
        }
        public void addInstruction(Instruction in){
            instructions.add(in);
        }

    }
    private HashMap<String, Function> functions = new HashMap<>();
    private ArrayList<Instruction> final_instructions = new ArrayList<>();

    //regex
    Pattern binary_function = Pattern.compile("function\\s(\\w+)\\s[:]\\s(\\w+)\\s(\\w+)\\s*\\{?\\s*\\Z"); //function name : a b     {
    Pattern unary_function = Pattern.compile("function\\s(\\w+)\\s[:]\\s(\\w+)\\s*\\{?\\s*\\Z");    //function name : a         {
    Pattern while_statement = Pattern.compile("\\s*while\\s(\\w+)\\s*\\[?\\s*\\Z");     //     while a   [


    //input files
    public Parser(SourceFile... files) throws Exception {
        for (SourceFile file : files) {
            processFile(file);
        }
        resolve();
    }
    public Parser(String... filenames) throws Exception {
        for (int i = 0; i < filenames.length; i++) {
            SourceFile file =  new SourceFile(filenames[i]);
            processFile(file);
        }
        resolve();
    }

    private void resolve() throws Exception {
        //put everything in one big instruction set
        if(functions.containsKey("main")){
            resolve(functions.get("main"),null,null);

        }else{
            throw new Exception("No main function!");
        }
    }
    //recursively resolve
    private void resolve(Function function, String replace_a,String replace_b) throws Exception {
        for (Instruction in : function.instructions) {
            in.replaceVariable(function.param_a, replace_a);
            in.replaceVariable(function.param_b, replace_b);
            if(in.isFunctionPlaceholder()){
                resolve(functions.get(in.expand(null)), in.getOperands()[0], in.getOperands()[1]);
            }else{
                final_instructions.add(in);
            }

        }
    }

    //get instructions
    public ArrayList<Instruction> getInstructions(){
        return final_instructions;
    }

    //debug print
    public void print() {
        for (Instruction in : final_instructions) {
            System.out.println(in);
        }
       /*
        for (Map.Entry<String,Function> entry: functions.entrySet()) {
            Function function = entry.getValue();
            if(function.param_b != null){
                System.out.println("Function " + entry.getKey() + " " + function.param_a + " " + function.param_b);
            }else{
                System.out.println("Function " + entry.getKey() + " " + function.param_a);
            }
            for (Instruction in : function.instructions) {
                System.out.println(in);
            }

        }*/
    }

    private void processFile(SourceFile file) throws Exception {
        System.out.println("Processing file...");
        String line = file.nextLine();
        while (line != null){
            if(line.contains("function")){
                //is a function do regex to get info
                Matcher regex = binary_function.matcher(line);
                if(regex.find()) { //is binary function
                    processFunction(file, regex.group(1), regex.group(2), regex.group(3));
                    line = file.nextLine();
                    continue;
                }
                regex = unary_function.matcher(line); //is unary function
                if(regex.find()){
                    processFunction(file,regex.group(1), regex.group(2));
                    line = file.nextLine();
                    continue;
                }
                throw new Exception("Function syntax not correct");
            }
            line = file.nextLine();
        }
        file.close();
    }
    public void processFunction(SourceFile file, String name, String var_a, String var_b) throws Exception {
        if(var_a.equals(var_b)){
            throw new Exception("Param a cannot equal Param b in a function!");
        }
        Function function = new Function(var_a,var_b);
        String line = "";
        while (true){

            line = file.nextLine();

            if(line == null){
                throw new Exception("Function did not close");
            }
            if(line.contains("}")){
                functions.put(name, function);
                return;
            }
            if( line.trim().length() > 0){
                function.addInstruction(processLine(line));
            }


        }

    }
    public void processFunction(SourceFile file, String name, String var_a) throws Exception {
        processFunction(file,name,var_a,null); //create unary function
    }

    public Instruction processLine(String line) throws Exception {
        Matcher regex = while_statement.matcher(line);
        if(regex.matches()){
            //while loop
            return new While(regex.group(1));
        }

        if (line.trim().length() == 1){
            //stopper or starter
            switch (line.trim()){
                case "}":
                    return new EndFunction();
                case "{":
                    return new StartFunction();
                case "[":
                    return new StartWhile();
                case "]":
                    return new EndWhile();

            }
        }

        String[] tokens = line.split("\\s+");

        if (tokens.length == 2){
            //unary
            switch (tokens[1]) {
                case "++":
                    return new IncOrDec(tokens[0], true);
                case "--":
                    return new IncOrDec(tokens[0], false);
                case "print":
                    return new Output(tokens[0]);
                case "input":
                    return new Input(tokens[0]);
                default:
                    return new UnaryFunction(tokens[0], tokens[1]);
            }
        }
        if (tokens.length == 3){
            //binary
            return new BinaryFunction(tokens[0], tokens[2], tokens[1]);
        }
        throw new Exception("Unknown sequence!");
    }


    //make sure to add variable creation instructions as well

    //heres the idea
    //you specify a ... number of files
    //each fucntion in each file is parsed the final intrcutions are put in a list or map
    //then in a resolve proccess all fucntion call instruction placeholders are recusivley replaced to make the final instuctions sey
    //instructions are empty-> add main function -> replacemain fcuntion calls->replace the call of those functions-> of those->...
    //then the expander just gets one set of continous instructions to expand
}

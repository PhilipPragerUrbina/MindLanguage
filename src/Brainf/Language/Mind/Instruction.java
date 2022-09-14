package Brainf.Language.Mind;

public interface Instruction {
    boolean isFunctionPlaceholder(); //should be replaced in resolve step
    String expand(State state); //expand to brainf and update state
    void replaceVariable(String variable, String new_variable); //change a variable name

    String[] getOperands();
}
class BinaryFunction implements Instruction{
    String param_a;
    String param_b;

    String replace_values_a;
    String replace_values_b;

    String name;
    public BinaryFunction(String param_a, String param_b, String name) {
        this.param_a = param_a;
        this.param_b = param_b;
        this.replace_values_a = param_a;
        this.replace_values_b = param_b;
        this.name = name;
    }

    @Override
    public boolean isFunctionPlaceholder() {
        return true;
    }

    @Override
    public String[] getOperands() {
        return new String[]{replace_values_a,replace_values_b};
    }

    @Override
    public String expand(State state) {
        return name;
    }

    @Override
    public void replaceVariable(String variable, String new_variable) {
        if(param_a.equals(variable)){
            replace_values_a = new_variable;
        }
        if(param_b.equals(variable)){
            replace_values_b = new_variable;
        }
    }

    @Override
    public String toString() {
        return "BinaryFunction{" +
                "param_a='" + replace_values_a + '\'' +
                ", param_b='" + replace_values_b + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
class UnaryFunction implements Instruction{
    String param_a;
    String replace_values_a;
    String name;
    public UnaryFunction(String param_a, String name) {
        this.param_a = param_a;
        this.replace_values_a = param_a;
        this.name = name;
    }

    @Override
    public boolean isFunctionPlaceholder() {
        return true;
    }

    @Override
    public String[] getOperands() {
        return new String[]{replace_values_a,null};
    }

    @Override
    public String expand(State state) {
        return name;
    }

    @Override
    public void replaceVariable(String variable, String new_variable) {
        if(param_a.equals(variable)){
            replace_values_a = new_variable;
        }
    }

    @Override
    public String toString() {
        return "UnaryFunction{" +
                "param_a='" + replace_values_a + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

class StartFunction implements Instruction{
    @Override
    public boolean isFunctionPlaceholder() {
        return false;
    }

    @Override
    public String expand(State state) {
        return null;
    }

    @Override
    public String[] getOperands() {
        return null;
    }

    @Override
    public void replaceVariable(String variable,String new_variable) {}

    @Override
    public String toString() {
        return "startFunction";
    }
}
class EndFunction implements Instruction{
    @Override
    public boolean isFunctionPlaceholder() {
        return false;
    }

    @Override
    public String expand(State state) {
        return null;
    }

    @Override
    public void replaceVariable(String variable,String new_variable) {}

    @Override
    public String[] getOperands() {
        return new String[0];
    }

    @Override
    public String toString() {
        return "endFunction";
    }
}


class StartWhile implements Instruction{
    @Override
    public boolean isFunctionPlaceholder() {
        return false;
    }

    @Override
    public String expand(State state) {
        return null;
    }

    @Override
    public void replaceVariable(String variable,String new_variable) {}

    @Override
    public String[] getOperands() {
        return new String[0];
    }

    @Override
    public String toString() {
        return "startWhile";
    }
}
class EndWhile implements Instruction{
    @Override
    public boolean isFunctionPlaceholder() {
        return false;
    }

    @Override
    public String expand(State state) {
        return null;
    }

    @Override
    public void replaceVariable(String variable,String new_variable) {}

    @Override
    public String[] getOperands() {
        return new String[0];
    }

    @Override
    public String toString() {
        return "endWhile";
    }
}

class IncOrDec implements Instruction{
    String param_a;
    String replace_values_a;
    boolean inc;
    public IncOrDec(String param_a, boolean inc) {
        this.param_a = param_a;
        this.replace_values_a = param_a;
        this.inc = inc;
    }

    @Override
    public boolean isFunctionPlaceholder() {
        return false;
    }

    @Override
    public String expand(State state) {
        return null;
    }

    @Override
    public void replaceVariable(String variable, String new_variable) {
        if(param_a.equals(variable)){
            replace_values_a = new_variable;
        }
    }

    @Override
    public String[] getOperands() {
        return new String[0];
    }

    @Override
    public String toString() {
        return "IncOrDec{" +
                "param_a='" + replace_values_a + '\'' +
                ","+inc+
                '}';
    }
}

class Input implements Instruction{
    String param_a;
    String replace_values_a;
    public Input(String param_a) {
        this.param_a = param_a;
        this.replace_values_a = param_a;
    }

    @Override
    public boolean isFunctionPlaceholder() {
        return false;
    }

    @Override
    public String expand(State state) {
        return null;
    }

    @Override
    public void replaceVariable(String variable, String new_variable) {
        if(param_a.equals(variable)){
            replace_values_a = new_variable;
        }
    }

    @Override
    public String[] getOperands() {
        return new String[0];
    }

    @Override
    public String toString() {
        return "input{" +
                "param_a='" + replace_values_a + '\'' +
                '}';
    }
}
class Output implements Instruction{
    String replace_values_a;
    String param_a;
    public Output(String param_a) {
        this.param_a = param_a;
        this.replace_values_a = param_a;
    }

    @Override
    public boolean isFunctionPlaceholder() {
        return false;
    }

    @Override
    public String expand(State state) {
        return null;
    }

    @Override
    public void replaceVariable(String variable, String new_variable) {
        if(param_a.equals(variable)){
            replace_values_a = new_variable;
        }
    }

    @Override
    public String[] getOperands() {
        return new String[0];
    }

    @Override
    public String toString() {
        return "Output{" +
                "param_a='" + replace_values_a + '\'' +
                '}';
    }
}
class While implements Instruction{
    String replace_values_a;
    String param_a;
    public While(String param_a) {
        this.param_a = param_a;
        this.replace_values_a = param_a;
    }

    @Override
    public boolean isFunctionPlaceholder() {
        return false;
    }

    @Override
    public String expand(State state) {
        return null;
    }


    @Override
    public void replaceVariable(String variable, String new_variable) {
        if(param_a.equals(variable)){
            replace_values_a = new_variable;
        }
    }

    @Override
    public String[] getOperands() {
        return new String[0];
    }

    @Override
    public String toString() {
        return "While{" +
                "param_a='" + replace_values_a + '\'' +
                '}';
    }
}

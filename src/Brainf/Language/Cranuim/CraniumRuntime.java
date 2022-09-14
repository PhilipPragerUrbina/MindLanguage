package Brainf.Language.Cranuim;

import java.util.*;

//the brainf interpreter
public class CraniumRuntime {
    private byte[] memory;
    private Deque<Integer> stack;
    private int cursor;

    public CraniumRuntime(int size){
        memory = new byte[size];
        cursor = 0;
        stack = new ArrayDeque<>();
    }
    public void clear(){
        for (int i = 0; i < memory.length; i++) {
            memory[i] = 0;
        }
        cursor = 0;
    }
    public void interpret(String code, String input){
        int input_idx = 0;
        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            switch (c){
                case '<':
                   cursor --;
                   break;
                case '>':
                    cursor ++;
                    break;
                case '.':
                    System.out.print((char)memory[cursor]);
                    break;
                case ',':
                    if(input_idx >= input.length()){return;}
                    memory[cursor] = (byte)input.charAt(input_idx);
                    input_idx++;
                    break;
                case '+':
                    memory[cursor]++;
                    break;
                case '-':
                    memory[cursor]--;
                    break;
                case '[':
                    stack.push(i);
                    break;
                case ']':
                    if( memory[cursor] == 0){
                        stack.pop();
                    }else{
                        i = stack.peek();
                    }
                default:
                    continue;

            }

        }

    }

}

package Brainf.Language;

import Brainf.Language.Cranuim.CraniumRuntime;
import Brainf.Language.Mind.Parser;

public class Main {

    public static void main(String[] args) {
        try {
            Parser parser = new Parser("test1.mind", "test2.mind");
            parser.print();
        } catch (Exception e) {
            e.printStackTrace();
        }


       // CraniumRuntime interpreter = new CraniumRuntime(1000);
     //   interpreter.interpret(",>,>++++++[-<--------<-------->>]<<[->[->+>+<<]>[-<+>]<<]>[-]\n" +
             //   ">+>[->+<<<<+>>>]>[<<[-]+>>>[-]++++++++++<[->-[>]<<]<[-<<-----\n" +
            //    "----->>>>>>>+<<<<<]<[-<]>>>]>>>[-<<<<<<+>>>>>>]<<[-]<<<++++++\n" +
            //    "[-<++++++++<++++++++>>]<.[-]<.[-]", "22");
    }
}

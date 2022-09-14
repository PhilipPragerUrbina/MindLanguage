package Brainf.Language;

import Brainf.Language.Cranuim.CraniumRuntime;
import Brainf.Language.Mind.Expander;
import Brainf.Language.Mind.MindCompiler;
import Brainf.Language.Mind.Parser;

public class Main {

    public static void main(String[] args) {

        MindCompiler m = new MindCompiler("test1.mind");


       // CraniumRuntime interpreter = new CraniumRuntime(1000);
     //   interpreter.interpret(",>,>++++++[-<--------<-------->>]<<[->[->+>+<<]>[-<+>]<<]>[-]\n" +
             //   ">+>[->+<<<<+>>>]>[<<[-]+>>>[-]++++++++++<[->-[>]<<]<[-<<-----\n" +
            //    "----->>>>>>>+<<<<<]<[-<]>>>]>>>[-<<<<<<+>>>>>>]<<[-]<<<++++++\n" +
            //    "[-<++++++++<++++++++>>]<.[-]<.[-]", "22");
    }
}

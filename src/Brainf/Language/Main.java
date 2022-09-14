package Brainf.Language;

import Brainf.Language.Cranuim.CraniumRuntime;
import Brainf.Language.Mind.Expander;
import Brainf.Language.Mind.MindCompiler;
import Brainf.Language.Mind.Parser;
import Brainf.Language.Mind.SourceFile;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        MindCompiler m = new MindCompiler("test1.mind");
        SourceFile file = new SourceFile("out.bf");
        System.out.println("Initialized runtime");
        CraniumRuntime runtime = new CraniumRuntime(1000);
        System.out.println("Enter input: ");
        Scanner scanner = new Scanner(System.in);
        String in = scanner.nextLine();
        System.out.println("\n output: ");
        runtime.interpret(file.getWhole(),in );


       // CraniumRuntime interpreter = new CraniumRuntime(1000);
     //   interpreter.interpret(",>,>++++++[-<--------<-------->>]<<[->[->+>+<<]>[-<+>]<<]>[-]\n" +
             //   ">+>[->+<<<<+>>>]>[<<[-]+>>>[-]++++++++++<[->-[>]<<]<[-<<-----\n" +
            //    "----->>>>>>>+<<<<<]<[-<]>>>]>>>[-<<<<<<+>>>>>>]<<[-]<<<++++++\n" +
            //    "[-<++++++++<++++++++>>]<.[-]<.[-]", "22");
    }
}

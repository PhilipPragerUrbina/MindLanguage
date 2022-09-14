package Brainf.Language.Mind;

public class MindCompiler {
    public MindCompiler(String... files){
        try {
            Parser parser = new Parser(files);
            parser.print();
            Expander compiler = new Expander(parser.getInstructions());
            compiler.compile(new BrainFile("", "out"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

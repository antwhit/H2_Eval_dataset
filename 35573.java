import java.io.*;

/**
* Kompilator kodu pascalowego
*
* Compiler of pascal code
*/
public class Compiler {

    private Analizator analizator;

    private Leksemy lista_slow;

    private PascalParser rozpoznawanie_slow;

    private RecWordsVector rozpoznane_slowa;

    private Validator valid;

    private CodeConverter codeConverter;

    /**
	* Kompiluje plik pascalowy na plik wykonywalny przez klas� Executor
	*
	* Compile pascal source file to execution file
	*/
    public void compile(String inputFile, String outputFile) throws Exception {
        Logger.log("inputFile: " + inputFile + "outputFile " + outputFile);
        analizator = new Analizator();
        lista_slow = new Leksemy();
        rozpoznawanie_slow = new PascalParser();
        valid = new Validator();
        codeConverter = new CodeConverter();
        FileReader fr = new FileReader(inputFile);
        BufferedReader bf = new BufferedReader(fr);
        StringBuffer pascalCode = new StringBuffer();
        while (bf.ready()) pascalCode.append(bf.readLine() + "\n");
        bf.close();
        lista_slow = analizator.analizuj(pascalCode.toString());
        rozpoznane_slowa = rozpoznawanie_slow.Regognize(lista_slow);
        valid.Validate(rozpoznane_slowa);
        codeConverter.convert(rozpoznane_slowa);
        codeConverter.getCode().setVeribles(valid.varibles);
        codeConverter.getCode().saveToFile(outputFile);
        analizator = null;
        lista_slow = null;
        rozpoznawanie_slow = null;
        valid = null;
        codeConverter = null;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Error! Uruchmienie kompilatora z konsoli: ");
            System.out.println("java Compiler plikWej�ciowy plikWyjsciowy\n");
            System.out.println("Error!  Running compiler from console: ");
            System.out.println("java Compiler inputFileName outputFileName");
            System.exit(1);
        }
        try {
            System.out.println("-- Kompilator --");
            System.out.println("Running compiler");
            Compiler c = new Compiler();
            c.compile(args[0], args[1]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        System.out.println("Kompilacja zako�czona");
        System.out.println("Compilation complete");
    }
}

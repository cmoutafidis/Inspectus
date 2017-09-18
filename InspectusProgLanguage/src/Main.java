import java.io.FileInputStream;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;


public class Main {
	
	public static void main(String[] args) {
	    try {
	        ANTLRInputStream input = new ANTLRInputStream(new FileInputStream("main.txt"));    

	        InspectusLexer lexer = new InspectusLexer(input);
	        InspectusParser parser = new InspectusParser(new CommonTokenStream(lexer));
	        parser.addParseListener(new MyListener());

	        // Start parsing
	        parser.program(); 
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}

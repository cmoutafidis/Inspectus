// Generated from Inspectus.g4 by ANTLR 4.4
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class InspectusLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__1=1, T__0=2, ID=3, LOGOPERATOR=4, BOOLS=5, WS=6;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'"
	};
	public static final String[] ruleNames = {
		"T__1", "T__0", "ID", "LOGOPERATOR", "BOOLS", "WS"
	};


	public InspectusLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Inspectus.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\b?\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3"+
		"\3\3\3\4\6\4\32\n\4\r\4\16\4\33\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\5\5,\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\67"+
		"\n\6\3\7\6\7:\n\7\r\7\16\7;\3\7\3\7\2\2\b\3\3\5\4\7\5\t\6\13\7\r\b\3\2"+
		"\5\4\2C\\c|\4\2##*+\5\2\13\f\17\17\"\"G\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3"+
		"\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\3\17\3\2\2\2\5\26\3\2\2\2"+
		"\7\31\3\2\2\2\t+\3\2\2\2\13\66\3\2\2\2\r9\3\2\2\2\17\20\7r\2\2\20\21\7"+
		"t\2\2\21\22\7k\2\2\22\23\7p\2\2\23\24\7v\2\2\24\25\7\"\2\2\25\4\3\2\2"+
		"\2\26\27\7=\2\2\27\6\3\2\2\2\30\32\t\2\2\2\31\30\3\2\2\2\32\33\3\2\2\2"+
		"\33\31\3\2\2\2\33\34\3\2\2\2\34\b\3\2\2\2\35,\t\3\2\2\36\37\7C\2\2\37"+
		" \7P\2\2 ,\7F\2\2!\"\7Q\2\2\",\7T\2\2#$\7Z\2\2$%\7Q\2\2%,\7T\2\2&\'\7"+
		"/\2\2\',\7@\2\2()\7G\2\2),\7S\2\2*,\7?\2\2+\35\3\2\2\2+\36\3\2\2\2+!\3"+
		"\2\2\2+#\3\2\2\2+&\3\2\2\2+(\3\2\2\2+*\3\2\2\2,\n\3\2\2\2-.\7V\2\2./\7"+
		"T\2\2/\60\7W\2\2\60\67\7G\2\2\61\62\7H\2\2\62\63\7C\2\2\63\64\7N\2\2\64"+
		"\65\7U\2\2\65\67\7G\2\2\66-\3\2\2\2\66\61\3\2\2\2\67\f\3\2\2\28:\t\4\2"+
		"\298\3\2\2\2:;\3\2\2\2;9\3\2\2\2;<\3\2\2\2<=\3\2\2\2=>\b\7\2\2>\16\3\2"+
		"\2\2\7\2\33+\66;\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
// Generated from Inspectus.g4 by ANTLR 4.4
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link InspectusParser}.
 */
public interface InspectusListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link InspectusParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(@NotNull InspectusParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link InspectusParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(@NotNull InspectusParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by {@link InspectusParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(@NotNull InspectusParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link InspectusParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(@NotNull InspectusParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link InspectusParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(@NotNull InspectusParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link InspectusParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(@NotNull InspectusParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link InspectusParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterOperation(@NotNull InspectusParser.OperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link InspectusParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitOperation(@NotNull InspectusParser.OperationContext ctx);
}
package structures;

public class OperationStructure {
	//STRUCTURE contains left value, right value and its statement.
	private int leftValue;
	private int rightValue;
	private boolean statement;
	
	public OperationStructure(int left, int right, boolean st){
		this.leftValue = left;
		this.rightValue = right;
		this.statement = st;
	}
	
	public int getLeftValue(){
		return this.leftValue;
	}
	
	public int getRightValue(){
		return this.rightValue;
	}
	
	public boolean getStatement(){
		return this.statement;
	}
	
	//Let's say we have an operation containing b = TRUE AND (TRUE OR FALSE).
	//                       List:     'b' '=' 'TRUE' 'AND' '(' 'TRUE' 'OR' 'FALSE' ')'
	//						 Pointers: '0' '1'   '2'   '3'  '4'   '5'  '6'    '7'   '8'
	//We start with brackets.
	//(TRUE OR FALSE) evaluates to TRUE.
	//We replace (TRUE OR FALSE) with a structure: [4, 8, TRUE].
	//The 'AND' operator on position 3 check's it's left and right values.
	//The pointer next to 'AND' has a value of 4. We check the leftValues list and find the number 4 in it.
	//So, instead of checking the value of List[4] we take the statement value of structure with leftValue==4.
	//TRUE AND TRUE evaluates to TRUE.
	//We have assignment operator so we assign the value TRUE to variable 'b'.

}

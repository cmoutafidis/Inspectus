import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import structures.OperationStructure;

public class MyListener extends InspectusBaseListener {

	//Map contains <VariableName, Value>
	//Stores all the variables.
	private Map<String, Boolean> variables;
	
	//Outcome contains the return value of an operation.
	private boolean outcome = false;
	
    public MyListener() {
    	variables = new HashMap<>();
    }
    
    @Override
    public void exitOperation(InspectusParser.OperationContext ctx) {
    	//Operation's length.
    	int length = ctx.getChildCount() - 1;
    	
    	ArrayList<String> list = new ArrayList<String>();
    	
    	//Pointers to operators.
    	ArrayList<Integer> openingBR = new ArrayList<Integer>();
    	ArrayList<Integer> closingBR = new ArrayList<Integer>();
    	ArrayList<Integer> not = new ArrayList<Integer>();
    	ArrayList<Integer> and = new ArrayList<Integer>();
    	ArrayList<Integer> or = new ArrayList<Integer>();
    	ArrayList<Integer> xor = new ArrayList<Integer>();
    	ArrayList<Integer> implication = new ArrayList<Integer>();
    	ArrayList<Integer> equivalent = new ArrayList<Integer>();
    	
    	//Left and right values of a structure.
    	ArrayList<Integer> leftValues = new ArrayList<Integer>();
    	ArrayList<Integer> rightValues = new ArrayList<Integer>();
    	
    	//List of structures.
    	ArrayList<OperationStructure> structures = new ArrayList<OperationStructure>();
    	
    	//true if we have an assignment.
    	boolean assignment = false;
    	
    	int i=0;
    	while(i<length){
    		if(leftValues.contains(i)){
    			for(OperationStructure s : structures){
    				if(i == s.getLeftValue()){
    					i = s.getRightValue();
    				}
    			}
    		}
    		else{
    			//we add every part of the operation to 'list'.
        		String item = ctx.getChild(i).getText();
        		list.add(item);
        		
        		//if we have an operator, we add it's pointer to the correct list.
        		switch (item){
        		case "(":
        			openingBR.add(i);
        			break;
        		case ")":
        			closingBR.add(i);
        			break;
        		case "!":
        			not.add(i);
        			break;
        		case "AND":
        			and.add(i);
        			break;
        		case "OR":
        			or.add(i);
        			break;
        		case "XOR":
        			xor.add(i);
        			break;
        		case "->":
        			implication.add(i);
        			break;
        		case "EQ":
        			equivalent.add(i);
        			break;
        		case "=":
        			assignment = true;
        			break;
        		}
        		i++;
    		}
    	}
    	
    	//length==1 means we have an operator: "TRUE" or "FALSE" or "var", where var is a variable.
    	if(length==1){
    		String s = list.get(0);
    		if(s.equals("TRUE")){
    			outcome = true;
    		}
    		else if(s.equals("FALSE")){
    			outcome = false;
    		}
    		else{
    			outcome = variables.get(s);
    		}
    	}
    	
    	//length==1 and assignment == true is the same case as above.
    	if(assignment && length==3){
    		String s = list.get(2);
    		if(s.equals("TRUE")){
    			outcome = true;
    		}
    		else if(s.equals("FALSE")){
    			outcome = false;
    		}
    		else{
    			outcome = variables.get(s);
    		}
    	}
    	//we start processing our operation.
    	
    	//first we check for brackets.
    	while(openingBR.size()>0){
    		
    		//brackets contain an operation so we handle it as one.
    		int index1 = openingBR.remove(openingBR.size()-1);
    		int index2 = closingBR.remove(0);
    		
    		ArrayList<String> currList = new ArrayList<String>();
        	
        	ArrayList<Integer> openingBR2 = new ArrayList<Integer>();
        	ArrayList<Integer> closingBR2 = new ArrayList<Integer>();
        	ArrayList<Integer> not2 = new ArrayList<Integer>();
        	ArrayList<Integer> and2 = new ArrayList<Integer>();
        	ArrayList<Integer> or2 = new ArrayList<Integer>();
        	ArrayList<Integer> xor2 = new ArrayList<Integer>();
        	ArrayList<Integer> implication2 = new ArrayList<Integer>();
        	ArrayList<Integer> equivalent2 = new ArrayList<Integer>();
        	
    		for(int index=index1+1;index<index2;index++){
    			if(leftValues.contains(index)){
    				for(OperationStructure s : structures){
        				if(index == s.getLeftValue()){
        					index = s.getRightValue();
        				}
        			}
    			}
    			else{
    				String item = list.get(index);
        			currList.add(item);
        			
        			switch (item){
            		case "(":
            			openingBR2.add(index);
            			break;
            		case ")":
            			closingBR2.add(index);
            			break;
            		case "!":
            			not2.add(index);
            			break;
            		case "AND":
            			and2.add(index);
            			break;
            		case "OR":
            			or2.add(index);
            			break;
            		case "XOR":
            			xor2.add(index);
            			break;
            		case "->":
            			implication2.add(index);
            			break;
            		case "EQ":
            			equivalent2.add(index);
            			break;
            		}
    			}
    		}
    		int length2 = currList.size();
    		
    		//onlyFlag shows if the brackets contain an operation with length==1.
        	boolean onlyFlag = false;
        	if(length2==1){
        		String s = currList.get(0);
        		if(s.equals("TRUE")){
        			outcome = true;
        		}
        		else if(s.equals("FALSE")){
        			outcome = false;
        		}
        		else{
        			outcome = variables.get(s);
        		}
        		onlyFlag = true;
        	}
        	
        	//first we check for '!' operators.
        	while(not2.size()>0){
        		int index = not2.get(not2.size()-1);
        		not.remove(not2.size()-1);
        		not2.remove(not2.size()-1);
        		
        		if(!leftValues.contains(index+1)){
        			String item = list.get(index+1);
            		if(item.equals("TRUE")){
            			item = "FALSE";
            			outcome = false;
            		}
            		else if(item.equals("FALSE")){
            			item = "TRUE";
            			outcome = true;
            		}
            		else{
            			boolean var = variables.get(item);
            			var = !var;
            			outcome = var;
            		}
            		OperationStructure struct = new OperationStructure(index, index+1, outcome);
            		structures.add(struct);
            		leftValues.add(index);
            		rightValues.add(index+1);
        		}
        		else{
        			OperationStructure struc = null;
        			for(OperationStructure s : structures){
        				if(s.getLeftValue() == index+1){
        					struc = s;
        					leftValues.add(index);
        					rightValues.add(s.getRightValue());
        				}
        			}
        			OperationStructure struct = new OperationStructure(index, struc.getRightValue(), !struc.getStatement());
					structures.add(struct);
        			structures.remove(struc);
        			outcome = !struc.getStatement();
        		}
        	}

        	//Then we check for 'AND' operators.
        	while(and2.size()>0){
        		int index = and2.get(and2.size()-1);
        		and.remove(and2.size()-1);
        		and2.remove(and2.size()-1);
        		
        		boolean outcomeRight = false;
        		boolean outcomeLeft = false;
        		int rightVal = index+1;
        		int leftVal = index-1;
        		if(!leftValues.contains(index+1)){
        			String item = list.get(index+1);
            		if(item.equals("TRUE")){
            			item = "TRUE";
            			outcomeRight = true;
            		}
            		else if(item.equals("FALSE")){
            			item = "FALSE";
            			outcomeRight = false;
            			
            		}
            		else{
            			outcomeRight = variables.get(item);
            		}
        		}
        		else{
        			OperationStructure struc = null;
        			for(OperationStructure s : structures){
        				if(s.getLeftValue() == index+1){
        					struc = s;
        					outcomeRight = s.getStatement();
        					rightVal = s.getRightValue();
        				}
        			}
        			structures.remove(struc);
        		}
        		
        		if(!rightValues.contains(index-1)){
        			String item = list.get(index-1);
            		if(item.equals("TRUE")){
            			item = "TRUE";
            			outcomeLeft = true;
            		}
            		else if(item.equals("FALSE")){
            			item = "FALSE";
            			outcomeLeft = false;
            		}
            		else{
            			outcomeLeft = variables.get(item);
            		}
        		}
        		else{
        			OperationStructure struc = null;
        			for(OperationStructure s : structures){
        				if(s.getRightValue() == index-1){
        					struc = s;
        					outcomeLeft = s.getStatement();
        					leftVal = s.getLeftValue();
        				}
        			}
        			structures.remove(struc);
        		}
        		if(outcomeLeft == true && outcomeRight == true){
        			outcome = true;
        		}
        		else{
        			outcome = false;
        		}
        		OperationStructure struct = new OperationStructure(leftVal, rightVal, outcome);
        		structures.add(struct);
        		leftValues.add(leftVal);
        		rightValues.add(rightVal);
        	}
        	
        	//Then we check for 'OR' operators.
        	while(or2.size()>0){
        		int index = or2.get(or2.size()-1);
        		or.remove(or2.size()-1);
        		or2.remove(or2.size()-1);
        		
        		boolean outcomeRight = false;
        		boolean outcomeLeft = false;
        		int rightVal = index+1;
        		int leftVal = index-1;
        		if(!leftValues.contains(index+1)){
        			String item = list.get(index+1);
            		if(item.equals("TRUE")){
            			item = "TRUE";
            			outcomeRight = true;
            		}
            		else if(item.equals("FALSE")){
            			item = "FALSE";
            			outcomeRight = false;
            		}
            		else{
            			outcomeRight = variables.get(item);
            		}
        		}
        		else{
        			OperationStructure struc = null;
        			for(OperationStructure s : structures){
        				if(s.getLeftValue() == index+1){
        					struc = s;
        					outcomeRight = s.getStatement();
        					rightVal = s.getRightValue();
        				}
        			}
        			structures.remove(struc);
        		}
        		
        		if(!rightValues.contains(index-1)){
        			String item = list.get(index-1);
            		if(item.equals("TRUE")){
            			item = "TRUE";
            			outcomeLeft = true;
            		}
            		else if(item.equals("FALSE")){
            			item = "FALSE";
            			outcomeLeft = false;
            		}
            		else{
            			outcomeLeft = variables.get(item);
            		}
        		}
        		else{
        			OperationStructure struc = null;
        			for(OperationStructure s : structures){
        				if(s.getRightValue() == index-1){
        					struc = s;
        					outcomeLeft = s.getStatement();
        					leftVal = s.getLeftValue();
        				}
        			}
        			structures.remove(struc);
        		}
        		if(outcomeLeft == true || outcomeRight == true){
        			outcome = true;
        		}
        		else{
        			outcome = false;
        		}
        		OperationStructure struct = new OperationStructure(leftVal, rightVal, outcome);
        		structures.add(struct);
        		leftValues.add(leftVal);
        		rightValues.add(rightVal);
        	}
        	
        	//Then we check for 'XOR' operators.
        	while(xor2.size()>0){
        		int index = xor2.get(xor2.size()-1);
        		xor.remove(xor2.size()-1);
        		xor2.remove(xor2.size()-1);
        		
        		boolean outcomeRight = false;
        		boolean outcomeLeft = false;
        		int rightVal = index+1;
        		int leftVal = index-1;
        		if(!leftValues.contains(index+1)){
        			String item = list.get(index+1);
            		if(item.equals("TRUE")){
            			item = "TRUE";
            			outcomeRight = true;
            		}
            		else if(item.equals("FALSE")){
            			item = "FALSE";
            			outcomeRight = false;
            		}
            		else{
            			outcomeRight = variables.get(item);
            		}
        		}
        		else{
        			OperationStructure struc = null;
        			for(OperationStructure s : structures){
        				if(s.getLeftValue() == index+1){
        					struc = s;
        					outcomeRight = s.getStatement();
        					rightVal = s.getRightValue();
        				}
        			}
        			structures.remove(struc);
        		}
        		
        		if(!rightValues.contains(index-1)){
        			String item = list.get(index-1);
            		if(item.equals("TRUE")){
            			item = "TRUE";
            			outcomeLeft = true;
            		}
            		else if(item.equals("FALSE")){
            			item = "FALSE";
            			outcomeLeft = false;
            		}
            		else{
            			outcomeLeft = variables.get(item);
            		}
        		}
        		else{
        			OperationStructure struc = null;
        			for(OperationStructure s : structures){
        				if(s.getRightValue() == index-1){
        					struc = s;
        					outcomeLeft = s.getStatement();
        					leftVal = s.getLeftValue();
        				}
        			}
        			structures.remove(struc);
        		}
        		if(outcomeLeft!=outcomeRight){
        			outcome = true;
        		}
        		else{
        			outcome = false;
        		}
        		OperationStructure struct = new OperationStructure(leftVal, rightVal, outcome);
        		structures.add(struct);
        		leftValues.add(leftVal);
        		rightValues.add(rightVal);
        	}
        	
        	//Then we check for '->' operators.
        	while(implication2.size()>0){
        		int index = implication2.get(implication2.size()-1);
        		implication.remove(implication2.size()-1);
        		implication2.remove(implication2.size()-1);
        		
        		boolean outcomeRight = false;
        		boolean outcomeLeft = false;
        		int rightVal = index+1;
        		int leftVal = index-1;
        		if(!leftValues.contains(index+1)){
        			String item = list.get(index+1);
            		if(item.equals("TRUE")){
            			item = "TRUE";
            			outcomeRight = true;
            		}
            		else if(item.equals("FALSE")){
            			item = "FALSE";
            			outcomeRight = false;
            		}
            		else{
            			outcomeRight = variables.get(item);
            		}
        		}
        		else{
        			OperationStructure struc = null;
        			for(OperationStructure s : structures){
        				if(s.getLeftValue() == index+1){
        					struc = s;
        					outcomeRight = s.getStatement();
        					rightVal = s.getRightValue();
        				}
        			}
        			structures.remove(struc);
        		}
        		
        		if(!rightValues.contains(index-1)){
        			String item = list.get(index-1);
            		if(item.equals("TRUE")){
            			item = "TRUE";
            			outcomeLeft = true;
            		}
            		else if(item.equals("FALSE")){
            			item = "FALSE";
            			outcomeLeft = false;
            		}
            		else{
            			outcomeLeft = variables.get(item);
            		}
        		}
        		else{
        			OperationStructure struc = null;
        			for(OperationStructure s : structures){
        				if(s.getRightValue() == index-1){
        					struc = s;
        					outcomeLeft = s.getStatement();
        					leftVal = s.getLeftValue();
        				}
        			}
        			structures.remove(struc);
        		}
        		if(outcomeLeft == true && outcomeRight == false){
        			outcome = false;
        		}
        		else{
        			outcome = true;
        		}
        		OperationStructure struct = new OperationStructure(leftVal, rightVal, outcome);
        		structures.add(struct);
        		leftValues.add(leftVal);
        		rightValues.add(rightVal);
        	}
        	
        	//Then we check for 'EQ' operators.
        	while(equivalent2.size()>0){
        		int index = equivalent2.get(equivalent2.size()-1);
        		equivalent.remove(equivalent2.size()-1);
        		equivalent2.remove(equivalent2.size()-1);
        		
        		boolean outcomeRight = false;
        		boolean outcomeLeft = false;
        		int rightVal = index+1;
        		int leftVal = index-1;
        		if(!leftValues.contains(index+1)){
        			String item = list.get(index+1);
            		if(item.equals("TRUE")){
            			item = "TRUE";
            			outcomeRight = true;
            		}
            		else if(item.equals("FALSE")){
            			item = "FALSE";
            			outcomeRight = false;
            		}
            		else{
            			outcomeRight = variables.get(item);
            		}
        		}
        		else{
        			OperationStructure struc = null;
        			for(OperationStructure s : structures){
        				if(s.getLeftValue() == index+1){
        					struc = s;
        					outcomeRight = s.getStatement();
        					rightVal = s.getRightValue();
        				}
        			}
        			structures.remove(struc);
        		}
        		
        		if(!rightValues.contains(index-1)){
        			String item = list.get(index-1);
            		if(item.equals("TRUE")){
            			item = "TRUE";
            			outcomeLeft = true;
            		}
            		else if(item.equals("FALSE")){
            			item = "FALSE";
            			outcomeLeft = false;
            		}
            		else{
            			outcomeLeft = variables.get(item);
            		}
        		}
        		else{
        			OperationStructure struc = null;
        			for(OperationStructure s : structures){
        				if(s.getRightValue() == index-1){
        					struc = s;
        					outcomeLeft = s.getStatement();
        					leftVal = s.getLeftValue();
        				}
        			}
        			structures.remove(struc);
        		}
        		if(outcomeLeft==outcomeRight){
        			outcome = true;
        		}
        		else{
        			outcome = false;
        		}
        		OperationStructure struct = new OperationStructure(leftVal, rightVal, outcome);
        		structures.add(struct);
        		leftValues.add(leftVal);
        		rightValues.add(rightVal);
        	}    		

        	OperationStructure struc = null;
        	for(OperationStructure s : structures){
        		if(s.getLeftValue() == index1 + 1){
        			struc = s;
            		leftValues.add(index1);
            		rightValues.add(index2);
        		}
        	}
        	structures.remove(struc);
        	if(!onlyFlag){
        		OperationStructure struct = new OperationStructure(struc.getLeftValue()-1, struc.getRightValue()+1, struc.getStatement());
        		structures.add(struct);
            	leftValues.add(struc.getLeftValue()-1);
            	rightValues.add(struc.getRightValue()+1);
        	}
        	else{
        		OperationStructure struct = new OperationStructure(index1, index2, outcome);
        		structures.add(struct);
        		leftValues.add(index1);
            	rightValues.add(index2);
        	}
    	}
    	
    	//Then we check for '!' operators.
    	while(not.size()>0){
    		int index = not.get(not.size()-1);
    		not.remove(not.size()-1);
    		
    		if(!leftValues.contains(index+1)){
    			String item = list.get(index+1);
        		if(item.equals("TRUE")){
        			item = "FALSE";
        			outcome = false;
        		}
        		else if(item.equals("FALSE")){
        			item = "TRUE";
        			outcome = true;
        		}
        		else{
        			boolean var = variables.get(item);
        			var = !var;
        			outcome = var;
        		}
        		OperationStructure struct = new OperationStructure(index, index+1, outcome);
        		structures.add(struct);
        		leftValues.add(index);
        		rightValues.add(index+1);
    		}
    		else{
    			OperationStructure struc = null;
    			for(OperationStructure s : structures){
    				if(s.getLeftValue() == index+1){
    					struc = s;
    					leftValues.add(index);
    	        		rightValues.add(s.getRightValue());
    				}
    			}
    			structures.remove(struc);
    			OperationStructure struct = new OperationStructure(index, struc.getRightValue(), !struc.getStatement());
				structures.add(struct);
				outcome = !struc.getStatement();
    		}
    	}
    	
    	//Then we check for 'AND' operators.
    	while(and.size()>0){
    		int index = and.get(and.size()-1);
    		and.remove(and.size()-1);
    		
    		boolean outcomeRight = false;
    		boolean outcomeLeft = false;
    		int rightVal = index+1;
    		int leftVal = index-1;
    		if(!leftValues.contains(index+1)){
    			String item = list.get(index+1);
        		if(item.equals("TRUE")){
        			item = "TRUE";
        			outcomeRight = true;
        		}
        		else if(item.equals("FALSE")){
        			item = "FALSE";
        			outcomeRight = false;
        			
        		}
        		else{
        			outcomeRight = variables.get(item);
        		}
    		}
    		else{
    			OperationStructure struc = null;
    			for(OperationStructure s : structures){
    				if(s.getLeftValue() == index+1){
    					struc = s;
    					outcomeRight = s.getStatement();
    					rightVal = s.getRightValue();
    				}
    			}
    			structures.remove(struc);
    		}
    		
    		if(!rightValues.contains(index-1)){
    			String item = list.get(index-1);
        		if(item.equals("TRUE")){
        			item = "TRUE";
        			outcomeLeft = true;
        		}
        		else if(item.equals("FALSE")){
        			item = "FALSE";
        			outcomeLeft = false;
        		}
        		else{
        			outcomeLeft = variables.get(item);
        		}
    		}
    		else{
    			OperationStructure struc = null;
    			for(OperationStructure s : structures){
    				if(s.getRightValue() == index-1){
    					struc = s;
    					outcomeLeft = s.getStatement();
    					leftVal = s.getLeftValue();
    				}
    			}
    			structures.remove(struc);
    		}
    		if(outcomeLeft == true && outcomeRight == true){
    			outcome = true;
    		}
    		else{
    			outcome = false;
    		}
    		OperationStructure struct = new OperationStructure(leftVal, rightVal, outcome);
    		structures.add(struct);
    		leftValues.add(leftVal);
    		rightValues.add(rightVal);
    	}

    	//Then we check for 'OR' operators.
    	while(or.size()>0){
    		int index = or.get(or.size()-1);
    		or.remove(or.size()-1);
    		
    		boolean outcomeRight = false;
    		boolean outcomeLeft = false;
    		int rightVal = index+1;
    		int leftVal = index-1;
    		if(!leftValues.contains(index+1)){
    			String item = list.get(index+1);
        		if(item.equals("TRUE")){
        			item = "TRUE";
        			outcomeRight = true;
        		}
        		else if(item.equals("FALSE")){
        			item = "FALSE";
        			outcomeRight = false;
        		}
        		else{
        			outcomeRight = variables.get(item);
        		}
    		}
    		else{
    			OperationStructure struc = null;
    			for(OperationStructure s : structures){
    				if(s.getLeftValue() == index+1){
    					struc = s;
    					outcomeRight = s.getStatement();
    					rightVal = s.getRightValue();
    				}
    			}
    			structures.remove(struc);
    		}
    		
    		if(!rightValues.contains(index-1)){
    			String item = list.get(index-1);
        		if(item.equals("TRUE")){
        			item = "TRUE";
        			outcomeLeft = true;
        		}
        		else if(item.equals("FALSE")){
        			item = "FALSE";
        			outcomeLeft = false;
        		}
        		else{
        			outcomeLeft = variables.get(item);
        		}
    		}
    		else{
    			OperationStructure struc = null;
    			for(OperationStructure s : structures){
    				if(s.getRightValue() == index-1){
    					struc = s;
    					outcomeLeft = s.getStatement();
    					leftVal = s.getLeftValue();
    				}
    			}
    			structures.remove(struc);
    		}
    		if(outcomeLeft == true || outcomeRight == true){
    			outcome = true;
    		}
    		else{
    			outcome = false;
    		}
    		OperationStructure struct = new OperationStructure(leftVal, rightVal, outcome);
    		structures.add(struct);
    		leftValues.add(leftVal);
    		rightValues.add(rightVal);
    	}
    	
    	//Then we check for 'XOR' operators.
    	while(xor.size()>0){
    		int index = xor.get(xor.size()-1);
    		xor.remove(xor.size()-1);
    		
    		boolean outcomeRight = false;
    		boolean outcomeLeft = false;
    		int rightVal = index+1;
    		int leftVal = index-1;
    		if(!leftValues.contains(index+1)){
    			String item = list.get(index+1);
        		if(item.equals("TRUE")){
        			item = "TRUE";
        			outcomeRight = true;
        		}
        		else if(item.equals("FALSE")){
        			item = "FALSE";
        			outcomeRight = false;
        		}
        		else{
        			outcomeRight = variables.get(item);
        		}
    		}
    		else{
    			OperationStructure struc = null;
    			for(OperationStructure s : structures){
    				if(s.getLeftValue() == index+1){
    					struc = s;
    					outcomeRight = s.getStatement();
    					rightVal = s.getRightValue();
    				}
    			}
    			structures.remove(struc);
    		}
    		
    		if(!rightValues.contains(index-1)){
    			String item = list.get(index-1);
        		if(item.equals("TRUE")){
        			item = "TRUE";
        			outcomeLeft = true;
        		}
        		else if(item.equals("FALSE")){
        			item = "FALSE";
        			outcomeLeft = false;
        		}
        		else{
        			outcomeLeft = variables.get(item);
        		}
    		}
    		else{
    			OperationStructure struc = null;
    			for(OperationStructure s : structures){
    				if(s.getRightValue() == index-1){
    					struc = s;
    					outcomeLeft = s.getStatement();
    					leftVal = s.getLeftValue();
    				}
    			}
    			structures.remove(struc);
    		}
    		if(outcomeLeft!=outcomeRight){
    			outcome = true;
    		}
    		else{
    			outcome = false;
    		}
    		OperationStructure struct = new OperationStructure(leftVal, rightVal, outcome);
    		structures.add(struct);
    		leftValues.add(leftVal);
    		rightValues.add(rightVal);
    	}
    	
    	//Then we check for '->' operators.
    	while(implication.size()>0){
    		int index = implication.get(implication.size()-1);
    		implication.remove(implication.size()-1);
    		
    		boolean outcomeRight = false;
    		boolean outcomeLeft = false;
    		int rightVal = index+1;
    		int leftVal = index-1;
    		if(!leftValues.contains(index+1)){
    			String item = list.get(index+1);
        		if(item.equals("TRUE")){
        			item = "TRUE";
        			outcomeRight = true;
        		}
        		else if(item.equals("FALSE")){
        			item = "FALSE";
        			outcomeRight = false;
        		}
        		else{
        			outcomeRight = variables.get(item);
        		}
    		}
    		else{
    			OperationStructure struc = null;
    			for(OperationStructure s : structures){
    				if(s.getLeftValue() == index+1){
    					struc = s;
    					outcomeRight = s.getStatement();
    					rightVal = s.getRightValue();
    				}
    			}
    			structures.remove(struc);
    		}
    		
    		if(!rightValues.contains(index-1)){
    			String item = list.get(index-1);
        		if(item.equals("TRUE")){
        			item = "TRUE";
        			outcomeLeft = true;
        		}
        		else if(item.equals("FALSE")){
        			item = "FALSE";
        			outcomeLeft = false;
        		}
        		else{
        			outcomeLeft = variables.get(item);
        		}
    		}
    		else{
    			OperationStructure struc = null;
    			for(OperationStructure s : structures){
    				if(s.getRightValue() == index-1){
    					struc = s;
    					outcomeLeft = s.getStatement();
    					leftVal = s.getLeftValue();
    				}
    			}
    			structures.remove(struc);
    		}
    		if(outcomeLeft == true && outcomeRight == false){
    			outcome = false;
    		}
    		else{
    			outcome = true;
    		}
    		OperationStructure struct = new OperationStructure(leftVal, rightVal, outcome);
    		structures.add(struct);
    		leftValues.add(leftVal);
    		rightValues.add(rightVal);
    	}
    	
    	//Then we check for 'EQ' operators.
    	while(equivalent.size()>0){
    		int index = equivalent.get(equivalent.size()-1);
    		equivalent.remove(equivalent.size()-1);
    		
    		boolean outcomeRight = false;
    		boolean outcomeLeft = false;
    		int rightVal = index+1;
    		int leftVal = index-1;
    		if(!leftValues.contains(index+1)){
    			String item = list.get(index+1);
        		if(item.equals("TRUE")){
        			item = "TRUE";
        			outcomeRight = true;
        		}
        		else if(item.equals("FALSE")){
        			item = "FALSE";
        			outcomeRight = false;
        		}
        		else{
        			outcomeRight = variables.get(item);
        		}
    		}
    		else{
    			OperationStructure struc = null;
    			for(OperationStructure s : structures){
    				if(s.getLeftValue() == index+1){
    					struc = s;
    					outcomeRight = s.getStatement();
    					rightVal = s.getRightValue();
    				}
    			}
    			structures.remove(struc);
    		}
    		
    		if(!rightValues.contains(index-1)){
    			String item = list.get(index-1);
        		if(item.equals("TRUE")){
        			item = "TRUE";
        			outcomeLeft = true;
        		}
        		else if(item.equals("FALSE")){
        			item = "FALSE";
        			outcomeLeft = false;
        		}
        		else{
        			outcomeLeft = variables.get(item);
        		}
    		}
    		else{
    			OperationStructure struc = null;
    			for(OperationStructure s : structures){
    				if(s.getRightValue() == index-1){
    					struc = s;
    					outcomeLeft = s.getStatement();
    					leftVal = s.getLeftValue();
    				}
    			}
    			structures.remove(struc);
    		}
    		if(outcomeLeft==outcomeRight){
    			outcome = true;
    		}
    		else{
    			outcome = false;
    		}
    		OperationStructure struct = new OperationStructure(leftVal, rightVal, outcome);
    		structures.add(struct);
    		leftValues.add(leftVal);
    		rightValues.add(rightVal);
    	}
    	
    	if(assignment){
    		String name = list.get(0);
    		variables.put(name, outcome);
    	}
    	
    }
    
    @Override
    public void exitPrint(InspectusParser.PrintContext ctx) {
    	System.out.println(outcome);
    }

}
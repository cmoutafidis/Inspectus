# Inspectus

~~~~~INSTRUCTIONS~~~~~

 Any file in InspectusProgLanguage project folder named main.txt will be our main class.
 You can find examples of our programming language in Examples folder.
 If you want to use the Runnable.jar file to run your main.txt then:
 1. Open the InspectusProgLanguage project folder.
 2. use (ShiftKey)+(RightClick) and select (Open command window here).
 3. Write down 'java -jar Runnable.jar' and hit 'Enter'.
 
 An operation contains: Operators/(TRUE/FALSE)/Variables
 
 Operator's priority: Usage
 Brackets:            (...)
 Not:                 !...
 And:                 ...AND...
 Or:                  ...OR...
 Xor:                 ...XOR...
 Implication:         ...->...
 Equivalent:          ...EQ...
 Assign:              'varName' = ...
 
 Functions:   Usage
 print:       print 'operation'
 
 AFTER EACH OPERATION USE ';'.
 
 VALUES:
 
 _________
 | x | ! |
 | 0 | 1 |
 | 1 | 0 |
 |-------|
 ____________________________________
 | x | y | AND | OR | XOR | -> | EQ |
 |-----------------------------------
 | 0 | 0 |  0  | 0  |  0  | 1  | 1  |
 | 0 | 1 |  0  | 1  |  1  | 1  | 0  |
 | 1 | 0 |  0  | 1  |  1  | 0  | 0  |
 | 1 | 1 |  1  | 1  |  0  | 1  | 1  |
 |-----------------------------------
 
 Source: https://en.wikipedia.org/wiki/Boolean_algebra

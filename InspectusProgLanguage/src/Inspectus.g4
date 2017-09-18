grammar Inspectus;
program   : statement+;
          
statement : operation | print ;

operation : (ID | LOGOPERATOR | BOOLS)+ ';' ;
print     : 'print ' operation ;

ID     : [a-zA-Z]+ ;
// '(...)' > '!' > 'AND' > 'OR' > 'XOR' > '->' > 'EQ'
LOGOPERATOR : '(' | ')' | '!' | 'AND' | 'OR' | 'XOR' | '->' | 'EQ' | '=';
BOOLS : 'TRUE' | 'FALSE';

WS     : [ \r\n\t]+ -> skip;
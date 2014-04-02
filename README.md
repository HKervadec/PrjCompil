# Projet Compilateur 3INFO


Le but est d'implémenter un compilateur Yaka vers assembleur intel 8086, en utilisant javacc.

Projet durant presque un semestre, il s'agit de l'aboutissement des modules d'assembleur et de grammaire et languages.


## Syntaxe Yaka


Un exemple sera probablement plus explicite, même si la grammaire est nécessaire pour saisir toutes les subtilités du language.


### Exemple


	PROGRAMME max2
	VAR ENTIER i,j, max;
	VAR BOOLEEN c1,c2;
	    LIRE(i);
	    ALALIGNE;
	    LIRE(j);
	    ALALIGNE;

	    SI i > j ALORS
	        max = i;
	    SINON
	        max = j;
	    FSI;
	    ECRIRE(max);

	    ECRIRE("IT'S THE FINAL COUNTDOWN")
	    ALALIGNE;
	    TANTQUE max > 0 ALORS
	    	ECRIRE(max);
	    	ALALIGNE;
	    	max = max - 1;
	    FAIT;

	    c1 = VRAI;
	    c2 = FAUX;
	    ECRIRE(c1 ET c2);
	FPROGRAMME



### Grammaire


| prog | PROGRAMME ident bloc FPROGRAMME|
| bloc | { declConst }* { declVar }* suiteInstr|
|----|----|
| declConst | CONST defConst {,defConst}*|
| defConst | ident = valConst|
| valConst | entier | ident | VRAI | FAUX|
|----|----|
| declVar | VAR type ident {, ident }*|
| type | ENTIER | BOOLEEN|
|----|----|
| suiteInstr :: = {instruction {; {instruction}+ }* }+|
|----|----|
| instruction | affectation | lecture | ecriture | iteration | conditionnelle|
|----|----|
| affectation | ident = expr|
|----|----|
| lecture | LIRE ( ident )|
|----|----|
| ecriture | { ECRIRE ( expression | chaine ) } | ALALIGNE|
|----|----|
| iteration | TANTQUE expr FAIRE suiteInstr FAIT|
|----|----|
| conditionnelle | SI expr ALORS suiteInstr {SINON suiteInstr}+ FSI|
|----|----|
|----|----|
| expr | terme { opAdd terme }*|
| terme | facteur { opMult facteur }*|
| facteur | opNeg primaire | primaire|
| valeur | entier | ident | VRAI | FAUX|
| opRel | > | < | >= | <= | = | <>|
| opAdd :: = + | - | OU|
| opMult | * | / | ET|
| opNef | - | NON


## Howto


### Requirements


- Jdk
- javacc 5
- python3


### Compiling


	javacc Yaka.jj
	javac *.java

### Running


	java Yaka <fileToCompile>

### Multiple tests


	edit the test list in launchTest.py
	./launchTest.py
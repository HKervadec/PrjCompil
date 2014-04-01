PrjCompil
=========

Projet Compilateur 3INFO


VOTRE PRESENCE EST OBLIGATOIRE BANDE DE LACHEUR
===============


Regle d'harmonisation  :

  - Un tab = 4 espaces. 
  


Grand mère
=========

- prog ::= PROGRAMME ident bloc FPROGRAMME
- bloc ::= { declConst }* { declVar }* suiteExpr
- declConst ::= CONST defConst {,defConst}*
- defConst ::= ident = valConst
- valConst ::= entier | ident | VRAI | FAUX
- declVar ::= VAR type ident {, ident }*
- type ::= ENTIER | BOOLEEN
- suiteExpr ::= terme { opAdd terme }*
- terme ::= facteur { opMult facteur }*
- facteur ::= opNeg primaire | primaire
- valeur ::= entier | ident | VRAI | FAUX
- opRel ::= > | < | >= | <= | = | <>
- opAdd + | - | OU
- opMult ::= * | / | ET
- opNef ::= - | NON

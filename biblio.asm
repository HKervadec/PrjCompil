;*******************************************************
;   Matiere : Schemas de traduction
;   Sujet   : Codage des procedures sans parametres
;   Module  : entrees sorties
;   Auteurs :  Marie-Jo Pedrono
;   Groupe :  
;*******************************************************
;
;   Description : Bibliotheque des entrees-sorties DOS
;    pour le compilateur ; sources pour le module biblio.obj
;
;*******************************************************
;*******************************************************
;
;   Variables globales :	aucune
;   Procedures exportees :	lirent, ecrent,ecrbool
;					ecrch,ecrbool
;   Variables exportees :	aucune
; 
;*******************************************************

	PUBLIC lirent,ecrent,ecrcar,ecrbool,ecrch,ligsuiv
	.MODEL small
	.586
	.DATA
vlu	dw	?
	.CODE
;-------------------------------------------------------
;   Nom : lirent
;   Description : lit une suite de caracteres representant
;		un chiffre. Le traduit en binaire et stocke
;		le resultat a l'adresse situee en sommet de pile
;	(on ne traite pas les cas de debordement)
;-------------------------------------------------------
;   Parametres d'entree : aucun
;   Parametres de sortie : L'adresse de N est dans la pile
;   Mode de transfert des parametres : pile
;   Variables globales modifiees : aucune
;   Variables locales : aucune (utilisation des registres)
;-------------------------------------------------------
N	EQU	4		;deplacement pour @N
signe dw	?

lirent proc near
	enter	0,0
	mov	cx,0
	mov	bx,0	; 		nombre := 0;
	mov	signe,1	; 	signe :=positif;	
				;	jqa chiffre trouve faire
faire: mov	ah,01h	; 
	int	21h	; 		;lire(car)
	cmp	al,'-'	;	si carlu = '-' alors signe := negatif
	jne	cherchif
	mov	signe,-1
	mov	ah,01h
	int	21h			; lire(car)
					;fsi
cherchif:
	cmp	al,'0'	; 	si car < '0' ou car > '9'
	jl	fait	;
	cmp	al,'9'	;
	jg	fait	;	 	sortir par chiftrouv
	jle   chiftrouv

fait: jmp faire	

chiftrouv:
	sub	al,'0'		; nombre := car -'0'		
	mov	cl,al	
	mov	bx,0
	mov	bl,al	


bouc:	mov	ah,01h	; tant que chiffre trouve faire
			;
	int	21h	; 	lire(car)
	cmp	al,'0'	; 	si car < '0' ou car > '9'
	jl	finb	;
	cmp	al,'9';	
	jg	finb	;		sortir par fin
	sub	al,'0'	;
	mov	cl,al	;
	mov	ax,bx	;
	imul	ax,10	;	N:=N*10+car-'0'
	mov	bx,ax	;
	add	bx,cx	;
	jmp	bouc	;	;fait
	
finb: cmp	signe,-1	;si signe = negatif alors
	jne	positif
	neg	bx		;	N:=-N
positif:
	mov  di,[bp+N]
	mov  [di],bx
	leave	
	ret	2
	endp			; fin lirent

;-------------------------------------------------------
;   Nom : ecrent
;   Description : affichage d'un entier
;-------------------------------------------------------
;   Parametres d'entree :l'entier a afficher est dans la pile
;   Parametres de sortie : aucun
;   Mode de transfert des parametres : pile
;   Variables globales modifiees : aucune
;   Variables locales : 
;-------------------------------------------------------
	.DATA
chaine	db	10 DUP (?)	; Reservation de 10 octets non initialises
finch	  db	?
Pt	EQU	bx
X	EQU	4			; deplacement dans la pile

	.CODE
ecrent proc near
	enter	0,0
	mov	Pt,OFFSET finch
	mov 	byte ptr[Pt],'$'	; $ est la marque de fin de chaine
	mov	signe,1
	mov	ax,[bp+X]		;cas 

	cmp	ax,0			;	X=0 -> met '0' dans le tampon
	jne	casuiv
	dec   Pt
	mov	byte ptr [Pt],'0'
	jmp	affiche

casuiv:
	jg	posit			;    X<0   Signe := negatif
	mov	signe,-1
	neg	ax			;		X:=-X
					; fcas
posit :	mov	cx,10
fairee:	mov	dx,0			; faire tant que nombre positif
	div	cx				;
	cmp	al,0				;
	jz	fine				;
	dec	Pt				;
	add	dl,'0'			;
	mov	[Pt],dl			; 	Inserer le car dans la chaine
	jmp	fairee			; fait
fine:   dec	Pt
	add	dl,'0'
	mov	[Pt],dl

	cmp	signe,-1
	jne	affiche		;	si signe negatif mettre '-' dans le tampon
	dec	Pt
	mov   byte ptr [Pt],'-'

affiche :
	mov	dx,Pt
	mov	ah,09h		; Fonction 0 = ecriture d'une chaine
	int	21h			; Interruption 21 : appel a DOS
	leave
	ret	2
	endp			; fin ecrent
;--------------------------------------------------------
;-------------------------------------------------------
;   Nom : ecrcar
;   Description : affichage d'un caractere
;-------------------------------------------------------
;   Parametres d'entree :le caractere  est dans la pile
;   Parametres de sortie : aucun
;   Mode de transfert des parametres : pile
;   Variables globales modifiees : aucune
;   Variables locales : 
;-------------------------------------------------------

car	equ	4	;deplacement dans la pile

ecrcar	proc	near
	enter	0,0		
	mov	dl,[bp+car]
	mov	ah,02
	int	21h		; ecriture du caractere
	leave
	ret	2
	endp

;-------------------------------------------------------
;   Nom : ecrbool
;   Description : affichage d'un booleen
;-------------------------------------------------------
;   Parametres d'entree :le booleen est dans la pile
;   Parametres de sortie : aucun
;   Mode de transfert des parametres : pile
;   Variables globales modifiees : aucune
;   Variables locales : 
;-------------------------------------------------------
	.DATA
vrai	db	'vrai$'	; notation vrai et faux
faux  db	'faux$'
bool	equ	4
	.CODE
ecrbool proc near
	enter	0,0
	mov	ax,[bp+bool]
	cmp	ax,0
	je	casfaux	;	si bool = vrai
	lea	dx,vrai	;        alors ecrire('vrai')
	jmp	ecrb
casfaux: 
	lea	dx,faux	;	sinon ecrire('faux')
ecrb :
	mov	ah,09h
	int	21h
	leave
	ret	2
	endp
	
;-------------------------------------------------------
;   Nom : ecrch
;   Description : affichage d'une chaine
;-------------------------------------------------------
;   Parametres d'entree :adresse de la chaine  dans la pile
;   Parametres de sortie : aucun
;   Mode de transfert des parametres : pile
;   Variables globales modifiees : aucune
;   Variables locales : 
;-------------------------------------------------------
depch	equ	4
ecrch proc near
	enter	0,0
	mov	dx,[bp+depch]	; ecriture de la chaine
	mov	ah,09h
	int	21h
	leave
	ret	2
	endp

;-------------------------------------------------------
;   Nom : ligsuiv
;   Description : passage a la ligne
;-------------------------------------------------------
;   Parametres d'entree : aucun
;   Parametres de sortie : aucun
;   Mode de transfert des parametres : 
;   Variables globales modifiees : aucune
;   Variables locales : 
;-------------------------------------------------------
	.DATA
alalign	db	13,10,'$'	; chaine a afficher
	.CODE
ligsuiv	proc near
	lea	dx,alalign
	mov	ah,09h		; passage a la ligne
	int	21h
	ret
	endp

	end


;-------------------------------------------------------
;
; programme de test a garder en cas d'erreur
;
;-------------------------------------------------------
	.DATA
cha	db	'bonjour$'
chb	db	'au revoir$'
	.CODE
	
debut :
	STARTUPCODE
	lea	bx,cha	;ecrire('bonjour')
	push	bx
	call	ecrch

	call	ligsuiv	; alaligne
	
	push	word ptr ' x' ; caractere cadre a droite 
	call	ecrcar


	lea	bx,vlu 	; lire(vlu)
	push 	bx
	call	lirent

	call	ligsuiv	; alaligne

	push	vlu		; ecrire(vlu)
	call	ecrent

	call	ligsuiv	; alaligne

	push	word ptr -1	; ecrire(vrai)
	call	ecrbool
	call	ligsuiv	; alaligne

	push word ptr 0	; ecrire(faux)
	call ecrbool

	call	ligsuiv	; alaligne

	lea	bx,chb	; ecrire('au revoir')
	push	bx
	call	ecrch


	EXITCODE
	end	debut	

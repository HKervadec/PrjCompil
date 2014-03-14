	; entete
extrn lirent:proc, ecrent:proc
extrn ecrbool:proc
extrn ecrch:proc, ligsuiv:proc

.model SMALL
.586

.CODE
debut:
	STARTUPCODE

	; ouvrePrinc 10
	mov bp,sp
	sub sp,10

	; ecrireChaine "x = "
.DATA
	mess0 DB "x = $"
.CODE
	lea dx, mess0
	push dx
	call ecrch

	; lireEnt -2
	lea dx,[bp-2]
	push dx
	call lirent

	; aLaLigne
	call ligsuiv

	; ecrireChaine "y = "
.DATA
	mess1 DB "y = $"
.CODE
	lea dx, mess1
	push dx
	call ecrch

	; lireEnt -4
	lea dx,[bp-4]
	push dx
	call lirent

	; aLaLigne
	call ligsuiv

	; ecrireChaine "x+y="
.DATA
	mess2 DB "x+y=$"
.CODE
	lea dx, mess2
	push dx
	call ecrch

	; iload -2
	push word ptr [bp-2]

	; iload -4
	push word ptr [bp-4]

	; iadd
	pop bx
	pop ax
	add ax,bx
	push ax

	; ecrireEnt
	call ecrent

	; aLaLigne
	call ligsuiv

	; iload -2
	push word ptr [bp-2]

	; iload -4
	push word ptr [bp-4]

	; iconst 2
	push word ptr 2

	; idiv
	pop bx
	pop ax
	cwd
	idiv bx
	push ax

	; iadd
	pop bx
	pop ax
	add ax,bx
	push ax

	; iconst 5
	push word ptr 5

	; idiv
	pop bx
	pop ax
	cwd
	idiv bx
	push ax

	; istore -6
	pop ax
	mov word ptr [bp-6], ax

	; iload -4
	push word ptr [bp-4]

	; iconst 3
	push word ptr 3

	; iload -4
	push word ptr [bp-4]

	; imul
	pop bx
	pop ax
	imul bx
	push ax

	; iadd
	pop bx
	pop ax
	add ax,bx
	push ax

	; iconst 4
	push word ptr 4

	; isub
	pop bx
	pop ax
	sub ax, bx
	push ax

	; istore -2
	pop ax
	mov word ptr [bp-2], ax

	; ecrireChaine "z= "
.DATA
	mess3 DB "z= $"
.CODE
	lea dx, mess3
	push dx
	call ecrch

	; iload -6
	push word ptr [bp-6]

	; ecrireEnt
	call ecrent

	; iload -2
	push word ptr [bp-2]

	; iload -6
	push word ptr [bp-6]

	; iinf
	pop bx
	pop ax
	cmp ax,bx
	jge FALSE0
	TRUE0:
		push -1
		jmp ENDTRUE0
	FALSE0:
		push 0
	ENDTRUE0:

	; istore -8
	pop ax
	mov word ptr [bp-8], ax

	; iload -4
	push word ptr [bp-4]

	; iload -6
	push word ptr [bp-6]

	; iegal
	pop bx
	pop ax
	cmp ax,bx
	jne FALSE1
	TRUE1:
		push -1
		jmp ENDTRUE1
	FALSE1:
		push 0
	ENDTRUE1:

	; istore -10
	pop ax
	mov word ptr [bp-10], ax

	; ecrireChaine "c1 = "
.DATA
	mess4 DB "c1 = $"
.CODE
	lea dx, mess4
	push dx
	call ecrch

	; iload -8
	push word ptr [bp-8]

	; ecrireEnt
	call ecrent

	; aLaLigne
	call ligsuiv

	; ecrireChaine "c2 = "
.DATA
	mess5 DB "c2 = $"
.CODE
	lea dx, mess5
	push dx
	call ecrch

	; iload -10
	push word ptr [bp-10]

	; ecrireEnt
	call ecrent

	; aLaLigne
	call ligsuiv

	; queue
	nop
	EXITCODE
End debut



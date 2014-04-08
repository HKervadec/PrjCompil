#!/usr/bin/python3

# Fuck this I don't need to comment it

from subprocess import *
from sys import exit

testFolder = "test"
testFiles = ["exemple_entree_sortie.yaka", "expr1.yaka", "expr2.yaka", "expr3.yaka", \
            "expr4.yaka", "expr5.yaka", "err1.yaka", "err2.yaka", "err3.yaka", \
            "exemple_iteration.yaka", "exemple_conditionnelle.yaka", "iter2.yaka",\
            "exemple_fct.yaka"]


firstCleanup = ["*.class", "TokenMgrError.java", "ParseException.java",\
                "Token.java", "SimpleCharStream.java", "Yaka.java"]

shellV = False
delC = "rm"
delO = "-f"

def pPrint(ab): 
    # print(ab)
    ab = ab.replace(b'\r',b'')
    ab = ab.decode("utf-8")
    ab = '\t' + ab
    ab = ab.replace('\n', '\n\t')
    print(ab)

print("**** Compiling... ****")
pPrint(check_output([delC, delO] + firstCleanup, shellV, stderr=STDOUT))
try:
    pPrint(check_output("javacc Yaka.jj", shell=True, stderr=STDOUT))
except CalledProcessError as e:
    pPrint(e.output)
    exit("Javacc il est cassé")

pPrint(check_output("javac *.java", shell=True))

print()



print("**** Launching tests ****")
# print("System.out will be redirected in the trash")

for file in testFiles:
    print(">>> " + file)
    pPrint(check_output("java Yaka {0}/{1}".format(testFolder, file), \
                        shell=True, stderr=STDOUT))
    print()
    
print()

    
print("**** Cleaning Up ****")
pPrint(check_output(delC + " -f *.asm *.yvm", shell=True,stderr=STDOUT))

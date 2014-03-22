# Fuck this I don't need to comment it

import subprocess

testFolder = "test"
testFiles = ["exemple_entree_sortie.yaka", "expr1.yaka", "expr2.yaka", "expr3.yaka", \
            "expr4.yaka", "expr5.yaka", "err1.yaka", "err2.yaka", "err3.yaka", \
            "exemple_iteration.yaka"]
# trashName = "trash"

def pPrint(ab): 
    # print(ab)
    ab = ab.replace(b'\r',b'')
    ab = ab.decode("utf-8")
    ab = '\t' + ab
    ab = ab.replace('\n', '\n\t')
    print(ab)

print("**** Compiling... ****")
pPrint(subprocess.check_output(["javacc", "Yaka.jj"], shell=True))
pPrint(subprocess.check_output(["javac", "*.java"], shell=True))
print()



print("**** Launching tests ****")
# print("System.out will be redirected in the trash")

for file in testFiles:
    print(">>> " + file)
    pPrint(subprocess.check_output(["java", "Yaka", "{0}\{1}".format(testFolder, file)], shell=True))
    print()
    
print()

    
print("**** Cleaning Up ****")
pPrint(subprocess.check_output(["del", "*.asm", "*.yvm"], shell=True))

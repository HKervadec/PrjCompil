import os

testFolder = "test"
testFiles = ["exemple_expression.yaka", "expr1.yaka", "expr2.yaka", "expr3.yaka", \
            "expr4.yaka", "expr5.yaka", "err1.yaka", "err2.yaka", "err3.yaka"]
trashName = "trash"


print("**** Compiling... ****")
os.system("javacc Yaka.jj")
os.system("javac *.java")


print("**** Launching tests ****")
# print("System.out will be redirected in the trash")

for file in testFiles:
    print(">>> " + file)
    # os.system("java Yaka {0}\{1} > trash".format(testFolder, file))
    os.system("java Yaka {0}\{1}".format(testFolder, file))
    print()
    

    
print("**** Cleaning Up ****")
os.system("del *.asm *.yvm trash")

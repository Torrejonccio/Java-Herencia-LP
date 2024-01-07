JFLAGS = -g -Xlint:all
JC = javac
JVM = java
MAIN = JavaHack
JAVAS := $(wildcard JavaHack/*.java) $(wildcard *.java)
CLASSES := $(JAVAS:.java=.class)

all: $(CLASSES)

.PHONY: all clean run

%.class: %.java
	$(JC) $(JFLAGS) $^

run: $(CLASSES)
	$(JVM) $(MAIN)

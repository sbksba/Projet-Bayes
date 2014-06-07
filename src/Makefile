CC=javac
EXEC1= ReadTextFile
EXEC2= Proba
EXEC3= Projet

all: $(EXEC1) $(EXEC2) $(EXEC3) run

#EXECUTABLES
$(EXEC1): $(EXEC1).java
	$(CC) $<

$(EXEC2): $(EXEC2).java
	$(CC) $<

$(EXEC3): $(EXEC3).java
	$(CC) $<

.PHONY: all clean proper run

clean: proper
	rm -f *.class

proper:
	rm -f *~

run:
	java $(EXEC3)

JAVAC = /usr/bin/javac
.SUFFIXES: .java .class

SRCDIR=src
BINDIR=bin
DOCDIR=doc

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<

CLASSES=PowerUser.class OperationCount.class CSVUtility.class BinaryTreeNode.class BTQueueNode.class BTQueue.class BinaryTree.class BinarySearchTree.class PowerArrayApp.class PowerBSTApp.class PowerUserApp.class

CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

run:
	java -cp $(BINDIR) PowerArrayApp

docs:
	javadoc -d $(DOCDIR) $(SRCDIR)/*.java

clean:
	rm $(BINDIR)/*.class


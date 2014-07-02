JAVAC=/usr/bin/javac

all: jar

prepare:
	mkdir -p classes

build: prepare src/com/tagish/auth/*
	$(JAVAC) src/com/tagish/auth/*.java
	
install: build
	mkdir -p classes/com/tagish/auth
	cp src/com/tagish/auth/*.class classes/com/tagish/auth

jar: install
	cd classes && jar cvf ../tagishauth.jar com/* && cd -

clean:
	rm -rf classes
	rm -f src/com/tagish/auth/*.class
	rm -f tagishauth.jar

.PHONY: all assemble build clean lint test

all: clean lint test assemble

assemble:
	./gradlew assembleDebug

build:
	./gradlew buildDebug

clean:
	./gradlew clean

lint:
	./gradlew lintDebug

test:
	./gradlew testDebugUnitTest

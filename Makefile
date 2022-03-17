.PHONY: all assemble build clean format lint test

all: clean format lint test assemble

assemble:
	./gradlew assembleDebug

build:
	./gradlew buildDebug

clean:
	./gradlew clean

format:
	./gradlew formatKotlin

lint:
	./gradlew lintDebug lintKotlin

test:
	./gradlew testDebugUnitTest

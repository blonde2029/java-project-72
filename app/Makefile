run-dist:
	./build/install/app/bin/app
run:
	./gradlew run
build:
	./gradlew clean
	./gradlew build
test:
	./gradlew test
lint:
	./gradlew checkstyleMain
report:
	./app/gradlew -p app jacocoTestReport

.PHONY: build
.PHONY: test
.PHONY: jacocoTestReport

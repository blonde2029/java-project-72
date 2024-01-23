run-dist:
	./app/build/install/app/bin/app
run:
	./app/gradlew run
build:
	./app/gradlew clean
	./app/gradlew build
test:
	./app/gradlew test
lint:
	./app/gradlew checkstyleMain
report:
	./app/gradlew jacocoTestReport

.PHONY: build
.PHONY: test
.PHONY: jacocoTestReport

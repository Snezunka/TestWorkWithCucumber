# TestWorkWithCucumber


### Suported browsers

* firefox
* chrome

### How to run tests

* use such command: mvn clean test -Dcucumber.options="src/test/java/features --tags @Smoke"

### How to build Allure report after running tests

    * enter in IDEA terminal command: "mvn allure:report"
    * report will be generated in folder target/site
# Probability game calculator

### Requirements

* At least java 21
* Gradle if you don't utilise the gradlew wrapper(version 8.5+)

### How to build
From the terminal using the gradle wrapper. This will create a shaded jar in the build folder.

```shell
./gradlew shadowJar
```


### How to run
From the terminal after building the jar file you should run this command

```shell
java -jar build/libs/scratchgame-1.0-SNAPSHOT-all.jar --config <path_to_file> --betting-amount <betting_amount>
```

### Assumptions

* Latest Java LTS is acceptable
* Unit testing will mostly focus on happy path
* An exectuable jar will be the output
* All config files as valid values as it's not possible to ascertain what is a valid and what is not valid from the presented data
* Time has become limited so some optional aspects of the test have not yet been implemented
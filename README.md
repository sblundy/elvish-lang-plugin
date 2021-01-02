# Elvish Shell Language Plugin

An IntelliJ Language Plugin for the [Elvish shell](https://elv.sh).

Plugin page: https://plugins.jetbrains.com/plugin/12788-elvish-shell-language

# Development

## Building

**Prerequisites**
* Java 11 JDK

The plugin uses Gradle to build from the command line. 

```shell
./gradlew buildPlugin
```

## Testing

Run the tests by:
```shell
./gradlew test
```

Most of the tests, PSI parsing particularly, are implemented as [Parameterized Tests](https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests) 
with the parameter a file name (or basename) of the input file, from which the same of expected output files are derived. 
These files can be found in `src/test/resources`. This system leverages the Jetbrains test framework.

## References

* [IntelliJ Platform SDK](https://jetbrains.org/intellij/sdk/docs/intro/welcome.html)
* [gradle-intellij-plugin](https://github.com/JetBrains/gradle-intellij-plugin/)

# Frege LSP

## Setup

It depends on the Frege Gradle Plugin. Follow the simple steps described in
[https://github.com/tricktron/frege-gradle-plugin/tree/master](https://github.com/tricktron/frege-gradle-plugin/tree/master)
to set the plugin up.

Tested with:

- Gradle 8.10
- Java 17.0.9

## Building and executing

Run `gradle shadowJar` to build a jar file and execute it with `java -jar ./build/libs/frege-lsp.jar`.

To see log outputs, add the path to an empty file to the `java -jar`-command. Follow
the logs using `tail -f path/to/log/file`.

If you run into stack overflows, consider to increase the max stack size using
an `-Xss`-flag.

## Running the code

Once you have the plugin installed, run `gradle runFrege` to run the Main module
of the app.

## Development

Run `eval $(gradle -q clean replFrege)` to open a Frege REPL. Run `main` to
execute the main function.


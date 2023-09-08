# Customer statement processor

This repository contains the source code of the customer statement processor application. This is a command line tool 
that performs validation over transaction records from an input file and print validation error on the standard output.
Currently, the application supports XML and CSV formats.

The application will report the following issues:

* Duplicate transaction references
* Incorrect end balance calculation

## Table of contents
* [Build & Run](#build--run)
* [Components](#components)

### Build & Run

Requires Java 11 and Apache [maven](https://maven.apache.org)  

Clone the git repository
```shell
git clone https://github.com/flombois/csp-assignment
```

Run the maven install phase that will build the core and cli modules, a runnable jar will be created in the cli/target 
directory
```shell
cd csp-assignment
mvn install
```

Run the application using java
```shell
java -jar ./cli/target/cli.jar <input_file>
```

### Components

The application is made of two maven modules:

* **core** contains the application core functionalities (parsing, validation, reporting...)
* **cli** contains the command line related functionalities (reading argument, printing on stdout)

#### Core

The application design is data processing oriented:

![flow](./flow.png)

- The input file is parsed
- The transactions are processed
- The process results are aggregated into a report
- The report is printed on stdout

![alt text](https://travis-ci.org/vkorobkov/jfixtures-cmd.svg?branch=master "Build status")

# jfixtures-cmd
Command line interface for JFixtures project

## Dependencies
**JFixtures requires Java 8** or higher and it is written in Java 8.

## Usage
* Build an executable jar with required dependencies by executing `mvn clean install`
* You are ready to use JFixturesCMD!  
**Available options:**
```bash
    java -jar options:
    * -type
     SQL type
     Possible Values: [POSTGRES, MYSQL, H2, CLICKHOUSE]
    * -src
     Source YAML folder path
    * -dst
     Destination SQL file path
    -h, --help
     Print help message
```

### Examples
```bash
java -jar jfixtures-cmd-1.0.0.jar -src path/to/fixures/folder -dst path/to/ouput/file.sql -type postgres
```

![alt text](https://travis-ci.org/vkorobkov/jfixtures-cmd.svg?branch=master "Build status")

# jfixtures-cmd
Command line interface for [JFixtures project](https://github.com/vkorobkov/jfixtures)

## Dependencies
**JFixtures requires Java 8** or higher and it is written in Java 8.

## Usage
* Download the last version of jfixtures-cmd jar file from maven central(TODO: place a link here)
* Navigate to the folder where jfixtures-jar is localed
* Type a bash command:
```bash
java -jar jfixtures-cmd-1.XX.jar <options>

where options are:

  -type [optional]
  the database dialect to general SQL result for. Possible values are: 
  * mysql
  * mssql
  * sql99 (such as Oracle, Sybase, Postgres, H2, SQLite and etc.)
  Not cases sensitive.
  
  -src [required] 
  relative or full path to the folder where the source yaml fixtures are
  
  -dst [required] 
  relative or full path to the destination SQL/XML file. Old file(if exists) will be overriten.
  
  -xml [optional] [default: false]
  export to XML format instead of SQL one. The -type option is ignored in this case. 
  
  -h, --help [optional]
  Print help message
```

### Examples
```bash
java -jar jfixtures-cmd-1.0.0.jar -src path/to/fixures/folder -dst path/to/ouput/file.sql -type sql99
```

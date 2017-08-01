![alt text](https://travis-ci.org/vkorobkov/jfixtures-cmd.svg?branch=master "Build status")

# jfixtures-cmd
Command line interface for JFixtures project

## Available commands
java -jar target/jfixtures-cmd-1.0.0-SNAPSHOT-jar-with-dependencies.jar
java -jar target/jfixtures-cmd-1.0.0-SNAPSHOT-jar-with-dependencies.jar -h
java -jar target/jfixtures-cmd-1.0.0-SNAPSHOT-jar-with-dependencies.jar -src src/test/resources/fixtures -target out.sql -type mysql
java -jar target/jfixtures-cmd-1.0.0-SNAPSHOT-jar-with-dependencies.jar -src src/test/resources/fixtures -target out.sql -type mysql -c
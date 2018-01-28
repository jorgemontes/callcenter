# Simple Callcenter

## Assumptions

* All data is complete, every record on the file has the INSTRUMENT_NAME, DATE and VALUE
* DATE and VALUE are in correct formats, there is no validation on incorrect formats whatsoever
* Initial data on the database can be inserted from Java classes as well as the creation of the table itself
* The application has all permissions to access the home `~` so it can create the database files `test.mv.db`

## Design Decisions

* The domain class `com.sii.tech.task.domain.Instrument` is an immutable object so arithmetic operations can be done safe.
* Strategy Pattern used to calculate the reports, just by implementing the `com.sii.tech.task.reporter.TSDRReporterStrategy` the Manager can extend to more reporters.
* Data Access Object `com.sii.tech.task.database.impl.TSDRDatabaseH2DAO`

## Run the program

* `cd` to the project's root directory.
* run the Maven command `mvn clean compile assembly:single`
* if the build is successful it should log the path of the java archive created

`[INFO] Building jar: /home/jorge/workspace/callcenter/target/callcenter-0.0.1-SNAPSHOT-jar-with-dependencies.jar`

* Run the application using as the first param the number of calls and for second parameter the number of employees of each type of employees. For example:

`java -jar target/callcenter-0.0.1-SNAPSHOT-jar-with-dependencies.jar 100 10`

will create 100 calls, 10 Operators, 10 Directors and 10 Supervisors

* please wait... processing...
* check the resulting report using any tool like `cat`
* the file content should be the contents of the database and the reports for every case

# Simple Callcenter

## Assumptions

* A call should be the work to make.
* There are not logging/report requirements.

## Design Approach

* Builder pattern was used in the entity classes to simplify Object creation. This was made with lomboks annotation `@Builder`
* Enums were used as singleton classes because of their thread-safe features
* Java Executor Service was used to handle the Thread Pool.
* Calls were created and queued so its easy to check which Calls are still pending to be attended.
* Employees are also stored in queues that represents the ones that are available.
* The thread pool size is as big as the Callcenter is. That means that if the callcenter has 30 employees, 30 calls can be attended at the same time.
* Class Diagram
![alt text][img/class1.jpg]

## Run the program

* `cd` to the project's root directory.
* run the Maven command `mvn clean compile assembly:single`
* if the build is successful it should log the path of the java archive created

`[INFO] Building jar: /home/jorge/workspace/callcenter/target/callcenter-0.0.1-SNAPSHOT-jar-with-dependencies.jar`

* Run the application using as the first param the number of calls and for second parameter the number of employees of each type of employees. For example:

`java -jar target/callcenter-0.0.1-SNAPSHOT-jar-with-dependencies.jar 100 10`

will create 100 calls, 10 Operators, 10 Directors and 10 Supervisors

* please wait... processing...
* check the resulting output that should look like this:
```
[main] INFO com.test.callcenter.main.Application - Creating 10 calls
[main] INFO com.test.callcenter.main.Application - Creating 10 employees
[main] INFO com.test.callcenter.dispatcher.Dispatcher - Started serving calls
[pool-1-thread-1] INFO com.test.callcenter.entity.Call - Start Call id : 637beefd-1dd9-46f3-9555-bdf86a6f5923 time for this call: 6s employee will service Operator 33cf5b75-db31-4f98-8963-b1ec68074d84
[pool-1-thread-2] INFO com.test.callcenter.entity.Call - Start Call id : dd1947f5-bc90-4c32-a6a2-e8baf7d8e92f time for this call: 7s employee will service Operator 06f5b9e0-9c13-4172-936d-6faf945df02f
[pool-1-thread-3] INFO com.test.callcenter.entity.Call - Start Call id : a4b334ea-4c00-4501-b331-a1c224c3687e time for this call: 7s employee will service Operator 713ca2e5-03ee-4f31-815d-3c2f3a3df0ff
[pool-1-thread-4] INFO com.test.callcenter.entity.Call - Start Call id : 24583bc7-734c-467d-b8c4-058b5c98b72f time for this call: 6s employee will service Operator d0e6db20-b92d-43d8-a5a9-54565f9b4b14
[pool-1-thread-5] INFO com.test.callcenter.entity.Call - Start Call id : b9d7c199-9d1b-4358-8498-70809350512d time for this call: 7s employee will service Operator d5b32c6a-63e0-4f6f-bf0f-180d494cf010
[pool-1-thread-6] INFO com.test.callcenter.entity.Call - Start Call id : 6eca5bf1-88be-4e99-9ed8-5d738f213f12 time for this call: 6s employee will service Operator e3d6922a-11ee-4c3c-ba55-cda5aed8b40c
[pool-1-thread-7] INFO com.test.callcenter.entity.Call - Start Call id : 4b0c510c-a1c9-4b52-835f-64b7228daeac time for this call: 8s employee will service Operator c35f5229-0a74-4ae3-ac27-791cd5a93759
[pool-1-thread-8] INFO com.test.callcenter.entity.Call - Start Call id : 8f67fc58-2baa-4df1-9e39-b16e1c3c7e05 time for this call: 8s employee will service Operator 4639586c-bd66-40c3-a1f0-404c8389f3a5
[pool-1-thread-9] INFO com.test.callcenter.entity.Call - Start Call id : fbee2bb5-5cae-472d-ab30-7ab35759145f time for this call: 9s employee will service Operator 9e1db3d4-1bbc-425f-8f0e-a41eaf85fbad
[main] INFO com.test.callcenter.dispatcher.Dispatcher - All Calls served
[pool-1-thread-10] INFO com.test.callcenter.entity.Call - Start Call id : 52004f63-ca95-428e-96a7-b8978742f1f1 time for this call: 6s employee will service Operator b4cdbad4-cf3c-46d3-afb1-cc731ba2eae1
[pool-1-thread-1] INFO com.test.callcenter.entity.Call - End Call 637beefd-1dd9-46f3-9555-bdf86a6f5923 served by Operator 33cf5b75-db31-4f98-8963-b1ec68074d84
[pool-1-thread-4] INFO com.test.callcenter.entity.Call - End Call 24583bc7-734c-467d-b8c4-058b5c98b72f served by Operator d0e6db20-b92d-43d8-a5a9-54565f9b4b14
[pool-1-thread-6] INFO com.test.callcenter.entity.Call - End Call 6eca5bf1-88be-4e99-9ed8-5d738f213f12 served by Operator e3d6922a-11ee-4c3c-ba55-cda5aed8b40c
[pool-1-thread-10] INFO com.test.callcenter.entity.Call - End Call 52004f63-ca95-428e-96a7-b8978742f1f1 served by Operator b4cdbad4-cf3c-46d3-afb1-cc731ba2eae1
[pool-1-thread-2] INFO com.test.callcenter.entity.Call - End Call dd1947f5-bc90-4c32-a6a2-e8baf7d8e92f served by Operator 06f5b9e0-9c13-4172-936d-6faf945df02f
[pool-1-thread-3] INFO com.test.callcenter.entity.Call - End Call a4b334ea-4c00-4501-b331-a1c224c3687e served by Operator 713ca2e5-03ee-4f31-815d-3c2f3a3df0ff
[pool-1-thread-5] INFO com.test.callcenter.entity.Call - End Call b9d7c199-9d1b-4358-8498-70809350512d served by Operator d5b32c6a-63e0-4f6f-bf0f-180d494cf010
[pool-1-thread-7] INFO com.test.callcenter.entity.Call - End Call 4b0c510c-a1c9-4b52-835f-64b7228daeac served by Operator c35f5229-0a74-4ae3-ac27-791cd5a93759
[pool-1-thread-8] INFO com.test.callcenter.entity.Call - End Call 8f67fc58-2baa-4df1-9e39-b16e1c3c7e05 served by Operator 4639586c-bd66-40c3-a1f0-404c8389f3a5
[pool-1-thread-9] INFO com.test.callcenter.entity.Call - End Call fbee2bb5-5cae-472d-ab30-7ab35759145f served by Operator 9e1db3d4-1bbc-425f-8f0e-a41eaf85fbad
```
* The content has a call id and also what happened to every call and who served the call
```
[pool-1-thread-9] INFO com.test.callcenter.entity.Call - Start Call id : fbee2bb5-5cae-472d-ab30-7ab35759145f time for this call: 9s employee will service Operator 9e1db3d4-1bbc-425f-8f0e-a41eaf85fbad
[pool-1-thread-9] INFO com.test.callcenter.entity.Call - End Call fbee2bb5-5cae-472d-ab30-7ab35759145f served by Operator 9e1db3d4-1bbc-425f-8f0e-a41eaf85fbad`
```

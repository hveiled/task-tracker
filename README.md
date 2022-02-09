##Test task for a candidate on Java intern position
###1. How to start

To start the application run following commands from project root directory:
1) `mvn clean install -DskipTests` to make package
2) `docker-compose up` to start the app

After you start the app database will be created and populated with the start data.

###2. How to check API endpoints
####2.1 To `GET` all existing projects:
```http request 
http://localhost:8080/api/project
```

Endpoint should return JSON:
```JSON
[
  {
    "id": 1,
    "projectName": "New project 1",
    "projectStartDate": "2022-02-08",
    "projectCompletionDate": "2022-02-18",
    "currentStatus": "Active",
    "priority": 1,
    "tasks": [
      {
        "id": 1,
        "taskName": "Task one",
        "taskDescription": "Do task one",
        "status": "Done",
        "priority": 1
      },
      {
        "id": 4,
        "taskName": "Task two",
        "taskDescription": "Do task two",
        "status": "ToDo",
        "priority": 1
      },
      {
        "id": 7,
        "taskName": "Task three",
        "taskDescription": "Do task three",
        "status": "ToDo",
        "priority": 1
      }
    ]
  },
  {
    "id": 2,
    "projectName": "New project 3",
    "projectStartDate": "2022-02-08",
    "projectCompletionDate": "2022-02-18",
    "currentStatus": "NotStarted",
    "priority": 2,
    "tasks": [
      {
        "id": 2,
        "taskName": "Task one",
        "taskDescription": "Do task one",
        "status": "ToDo",
        "priority": 1
      },
      {
        "id": 5,
        "taskName": "Task two",
        "taskDescription": "Do task two",
        "status": "InProgress",
        "priority": 1
      },
      {
        "id": 8,
        "taskName": "Task three",
        "taskDescription": "Do task three",
        "status": "InProgress",
        "priority": 1
      }
    ]
  },
  {
    "id": 3,
    "projectName": "New project 2",
    "projectStartDate": "2022-02-08",
    "projectCompletionDate": "2022-02-18",
    "currentStatus": "Completed",
    "priority": 3,
    "tasks": [
      {
        "id": 3,
        "taskName": "Task one",
        "taskDescription": "Do task one",
        "status": "InProgress",
        "priority": 1
      },
      {
        "id": 6,
        "taskName": "Task two",
        "taskDescription": "Do task two",
        "status": "Done",
        "priority": 1
      },
      {
        "id": 9,
        "taskName": "Task three",
        "taskDescription": "Do task three",
        "status": "Done",
        "priority": 1
      }
    ]
  }
]
```
And status code:
```
200 OK
```
####2.2 To create a new project `POST`:
``` http request
http://localhost:8080/api/project
```
With the request body:
```JSON
{
  "projectName": "Brand new project 42",
  "projectStartDate": "2022-02-06",
  "projectCompletionDate": "2023-02-07",
  "currentStatus": "NotStarted",
  "priority": 1,
  "taskList": [
    {
      "taskName": "One",
      "taskDescription": "Do one",
      "status": "Done",
      "priority": 1
    }
  ]
}
```
Endpoint should return JSON:
```json
{
  "id": 4
}
```
And status code:
```
200 OK
```
####2.3 To change an existing project `PUT`
```http request
http://localhost:8080/api/project/change/4
```
With the request body:
```JSON
{
  "projectName": "Brand new project 42",
  "projectStartDate": "2022-02-06",
  "projectCompletionDate": "2023-02-07",
  "currentStatus": "NotStarted",
  "priority": 1,
  "taskList": [
    {
      "taskName": "One",
      "taskDescription": "Do one",
      "status": "ToDo",
      "priority": 3
    },
    {
      "taskName": "Two",
      "taskDescription": "Do two",
      "status": "Done",
      "priority": 1
    }
  ]
}
```
Endpoint should return JSON:
```json
{
  "id": 4
}
```
And status code:
```
200 OK
```

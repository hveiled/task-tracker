##Test task for a candidate on Java intern position
###1. How to start

To start the application run following commands from project root directory:
1) `mvn clean package` to make package. Key `-DskipTests` to skip unit tests tests
2) `docker-compose up` to start the app
3) `docker-compose down` to turn off and remove container data

After you start the app database will be created and populated with the start data.

###2. How to check API endpoints
Swagger interface for checking endpoints 
```http request
http://localhost:8080/api/swagger.html
```

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
#####To sort request parameters:
```http request
http://localhost:8081/api/project?pageNumber=1&sortField=Id&sortDirection=asc
```
Response should be alike:
```JSON
{
    "content": [
        {
            "id": 1,
            "projectName": "New project 1",
            "projectStartDate": "2022-02-08",
            "projectCompletionDate": "2022-02-18",
            "currentStatus": "Active",
            "priority": 1,
            "tasks": [
                {
                    "id": 4,
                    "taskName": "Task two",
                    "taskDescription": "Do task two",
                    "status": "ToDo",
                    "priority": 1
                },
                {
                    "id": 1,
                    "taskName": "Task one",
                    "taskDescription": "Do task one",
                    "status": "Done",
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
                },
                {
                    "id": 2,
                    "taskName": "Task one",
                    "taskDescription": "Do task one",
                    "status": "ToDo",
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
                    "id": 9,
                    "taskName": "Task three",
                    "taskDescription": "Do task three",
                    "status": "Done",
                    "priority": 1
                },
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
                }
            ]
        },
        {
            "id": 4,
            "projectName": "New project 11",
            "projectStartDate": "2022-02-08",
            "projectCompletionDate": "2022-02-08",
            "currentStatus": "Completed",
            "priority": 3,
            "tasks": [
                {
                    "id": 10,
                    "taskName": "A pretty task",
                    "taskDescription": "Do task three",
                    "status": "Done",
                    "priority": 1
                }
            ]
        },
        {
            "id": 5,
            "projectName": "New project 22",
            "projectStartDate": "2022-02-08",
            "projectCompletionDate": "2022-02-08",
            "currentStatus": "Completed",
            "priority": 3,
            "tasks": [
                {
                    "id": 11,
                    "taskName": "A pretty task",
                    "taskDescription": "Do task three",
                    "status": "Done",
                    "priority": 1
                }
            ]
        },
        {
            "id": 6,
            "projectName": "New project 33",
            "projectStartDate": "2022-02-08",
            "projectCompletionDate": "2022-02-08",
            "currentStatus": "Completed",
            "priority": 3,
            "tasks": [
                {
                    "id": 12,
                    "taskName": "A pretty task",
                    "taskDescription": "Do task three",
                    "status": "Done",
                    "priority": 1
                }
            ]
        }
    ],
    "pageable": {
        "sort": {
            "empty": false,
            "unsorted": false,
            "sorted": true
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 6,
        "paged": true,
        "unpaged": false
    },
    "totalPages": 2,
    "totalElements": 9,
    "last": false,
    "size": 6,
    "number": 0,
    "sort": {
        "empty": false,
        "unsorted": false,
        "sorted": true
    },
    "numberOfElements": 6,
    "first": true,
    "empty": false
}
```
####2.2 To create a new project `POST`:
``` http request
http://localhost:8080/api/project/new
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
  "id": 6,
  "projectName": "New project 42 is changed",
  "projectStartDate": "2022-02-08",
  "projectCompletionDate": "2022-02-18",
  "currentStatus": "Completed",
  "priority": 3,
  "tasks": [
    {
      "id": 12,
      "taskName": "A pretty task after changing",
      "taskDescription": "Do pretty task after c",
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

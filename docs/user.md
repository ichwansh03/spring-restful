# User API Spec

## Register User
Endpoint: POST /api/users

Request Body :
```json
{
  "username" : "string",
  "password" : "string",
  "name" : "string"
}
```
Response Body [Success] :
```json
{
  "data" : "OK"
}
```
Response Body [Failed] :
```json
{
  "errors" : "username is exists"
}
```

## Login User
Endpoint: POST /api/auth/login

Request Body :
```json
{
  "username" : "string",
  "password" : "string"
}
```
Response Body [Success] :
```json
{
  "data" : {
    "token" : "barrier <your token>",
    "expiredAt" : "time millisecond"
  }
}
```
Response Body [Failed] :
```json
{
  "errors" : "username is not registered"
}
```

## Get User
Endpoint: GET /api/users/current

Request Header :
- -X-API-TOKEN: `YOUR TOKEN`

Response Body [Success] :
```json
{
  "data" : {
    "username" : "string",
    "name" : "string"
  }
}
```
Response Body [Failed] :
```json
{
  "errors" : "unauthorized"
}
```

## Update User
Endpoint: PATCH /api/users/current

Request Header :
- -X-API-TOKEN: `YOUR TOKEN`

Request Body :
```json
{
  "name" : "string",
  "password" : "string"
}
```
Response Body [Success] :
```json
{
  "data" : {
    "username" : "string",
    "name" : "string"
  }
}
```
Response Body [Failed] :
```json
{
  "errors" : "unauthorized"
}
```

## Logout User

Endpoint : DELETE /api/auth/logout

Request Header :
- X-API-TOKEN : `YOUR TOKEN`

Request Body (Success) :
```json
{
  "data" : "OK"
}
```
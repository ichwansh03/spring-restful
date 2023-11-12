# User API Spec

## Register User
Endpoint: POST /api/users

Request Body :
```json
{
  "username" : "sa",
  "password" : "admin",
  "name" : "Ichwan Sholihin"
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
  "username" : "sa",
  "password" : "admin"
}
```
Response Body [Success] :
```json
{
  "data" : {
    "token" : "barrier <your token>",
    "expiredAt" : "time ms"
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
    "username" : "sa",
    "name" : "Ichwan Sholihin"
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
  "name" : "Ichwan",
  "password" : "new password"
}
```
Response Body [Success] :
```json
{
  "data" : {
    "username" : "sa",
    "name" : "Ichwan Sholihin"
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
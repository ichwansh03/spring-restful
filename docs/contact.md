# Contact API Spec

## Create Contact

Endpoint : POST /api/contacts

Request Header :
- -X-API-TOKEN: `YOUR TOKEN`

Request Body :
```json
{
  "firstName" : "Ichwan",
  "lastName" : "Sholihin",
  "email" : "ichwansholihin@gmail.com",
  "phone" : "0812131314"
}
```
Response Body [Success] :
```json
{
  "data" : {
    "id" : "primary key auto_inc",
    "firstName" : "Ichwan",
    "lastName" : "Sholihin",
    "email" : "ichwansholihin@gmail.com",
    "phone" : "0812131314"
  }
}
```
Response Body [Failed] :
```json
{
  "errors" : "invalid data"
}
```

## Get Contact

Endpoint : GET /api/contacts

Request Header :
- -X-API-TOKEN: `YOUR TOKEN`

Request Body :
```json
{
  "firstName" : "Ichwan",
  "lastName" : "Sholihin",
  "email" : "ichwansholihin@gmail.com",
  "phone" : "0812131314"
}
```
Response Body [Success] :
```json
{
  "data" : {
    "id" : "primary key auto_inc",
    "firstName" : "Ichwan",
    "lastName" : "Sholihin",
    "email" : "ichwansholihin@gmail.com",
    "phone" : "0812131314"
  }
}
```
Response Body [Failed] :
```json
{
  "errors" : "not found"
}
```

## Update Contact

Endpoint : PUT /api/contacts/{id}

Request Header :
- -X-API-TOKEN: `YOUR TOKEN`

Request Body :
```json
{
  "firstName" : "Ichwan",
  "lastName" : "Sholihin",
  "email" : "ichwansholihin@gmail.com",
  "phone" : "0812131314"
}
```
Response Body [Success] :
```json
{
  "data" : {
    "id" : "primary key auto_inc",
    "firstName" : "Ichwan",
    "lastName" : "Sholihin",
    "email" : "ichwansholihin@gmail.com",
    "phone" : "0812131314"
  }
}
```
Response Body [Failed] :
```json
{
  "errors" : "invalid data"
}
```

## Search Contact

Endpoint : GET /api/contacts

Query Param :
- name : String
- phone : String
- email : String
- page : Integer, start from 0 and default 0
- size : Integer, default 0

Request Header :
- -X-API-TOKEN: `YOUR TOKEN`

Request Body :
```json
{
  "firstName" : "Ichwan",
  "lastName" : "Sholihin",
  "email" : "ichwansholihin@gmail.com",
  "phone" : "0812131314"
}
```
Response Body [Success] :
```json
{
  "data" : {
    "id" : "primary key auto_inc",
    "firstName" : "Ichwan",
    "lastName" : "Sholihin",
    "email" : "ichwansholihin@gmail.com",
    "phone" : "0812131314"
  }
}
```
Response Body [Failed] :
```json
{
  "errors" : "invalid data"
}
```

## Delete Contact

Endpoint : DELETE /api/contacts/{id}

Request Header :
- -X-API-TOKEN: `YOUR TOKEN`

Response Body [Success] :
```json
{
  "data" : "OK"
}
```
Response Body [Failed] :
```json
{
  "errors" : "not found"
}
```
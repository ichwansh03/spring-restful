# Address API Spec

## Create Address

Endpoint : POST /api/contacts/{idContact}/address

Request Header :
- -X-API-TOKEN: `YOUR TOKEN`

Request Body :
```json
{
  "street" : "string",
  "city" : "string",
  "province" : "string, email format",
  "country" : "string",
  "postalCode" : "string"
}
```
Response Body [Success] :
```json
{
  "data" : {
    "id" : "string, primary key",
    "street" : "string",
    "city" : "string",
    "province" : "string, email format",
    "country" : "string",
    "postalCode" : "string"
  }
}
```
Response Body [Failed] :
```json
{
  "errors" : "contact is not found"
}
```

## Get Address

Endpoint : GET /api/contacts/{idContact}/address/{id}

Request Header :
- -X-API-TOKEN: `YOUR TOKEN`

Response Body [Success] :
```json
{
  "data" : {
    "id" : "string, primary key",
    "street" : "string",
    "city" : "string",
    "province" : "string, email format",
    "country" : "string",
    "postalCode" : "string"
  }
}
```
Response Body [Failed] :
```json
{
  "errors" : "address is not found"
}
```

## Update Address

Endpoint : PUT /api/contacts/{idContact}/address/{id}

Request Header :
- -X-API-TOKEN: `YOUR TOKEN`

Request Body :
```json
{
  "street" : "string",
  "city" : "string",
  "province" : "string, email format",
  "country" : "string",
  "postalCode" : "string"
}
```
Response Body [Success] :
```json
{
  "data" : {
    "id" : "string, primary key",
    "street" : "string",
    "city" : "string",
    "province" : "string, email format",
    "country" : "string",
    "postalCode" : "string"
  }
}
```
Response Body [Failed] :
```json
{
  "errors" : "address is not found"
}
```

## Delete Address

Endpoint : DELETE /api/contacts/{idContact}/address/{id}

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
  "errors" : "address is not found"
}
```

## List Address

Endpoint : GET /api/contacts/{idContact}/address

Request Header :
- -X-API-TOKEN: `YOUR TOKEN`

Response Body [Success] :
```json
{
  "data" : [
    {
      "id" : "string, primary key",
      "street" : "string",
      "city" : "string",
      "province" : "string, email format",
      "country" : "string",
      "postalCode" : "string"
    }
  ]
}
```
Response Body [Failed] :
```json
{
  "errors" : "contact is not found"
}
```
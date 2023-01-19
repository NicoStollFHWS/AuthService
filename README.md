# Auth Service

This simple server can be used to authenticate users via JWT (JSON Webtoken).
Connections can be established over HTTPS. To enable HTTPS you need to create your own Certificate

```keytool -genkeypair -alias springboot -keyalg RSA -keysize 4096 -storetype PKCS12 -keystore springboot.p12 -validity 3650 -storepass password```

Place your SSL certificate into  the resources folder and adapt config in application.properties.

The application doesn't come with an embedded db. 

# Endpoints

___

## https://localhost:8443/api/signUp
### Method: POST

### Body (**raw**)
```json
{
  "email" : "example@email.com",
  "username" : "exampleuser",
  "password" : "secretpassword"
}
```

___

## https://localhost:8443/api/login
### Method: POST

### Body (**raw**)
```json
{
  "email" : "example@email.com",
  "password" : "secretpassword"
}
```
___

## https://localhost:8443/api/validateToken
### Method: GET

### Headers

|Header|Value|
|---|---|
|Authorization| Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJleGFtcGxldXNlciIsImlzcyI6IkF1dGhTZXJ2aWNlIiwiaWF0IjoxNjc0MTM2MjY2LCJleHAiOjE2NzQxMzk4NjZ9.bjkzrF6ZBrO1XxM35jl3Mxf-I3hGgUVDdkTAAfxmLzc |
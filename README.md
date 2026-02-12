# ReadMe
### Steps to reproduce the CDS mapping data to Incorrect Data Type

1. run the command: `mvn spring-boot:run`
2. run the command: 
```
curl --location 'http://localhost:8080/odata/v4/Sample/onFooEvent' \
--header 'Content-Type: application/json' \
--data '{
    "data" : {
        "actionId": 42
    }
}'
```
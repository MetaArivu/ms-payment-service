# Payment Micro Service

## BUILD & Run Locally
- mvn clean install
- mvn spring-boot:run 
  - This will start server on port 6062


## API

- Welcome API
  - curl --location --request GET 'http://localhost:6068/payment-service/welcome'

- Payment API
  - curl --location --request POST 'http://localhost:6068/payment-service/api/v1/' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlJPTEVfQURNSU4iXSwic3ViIjoiNjFhZTAxODk5NTNlYTI2MTdlNWRlZGIxfGtldGFuLmdvdGVAZ21haWwuY29tIiwiaWF0IjoxNjM4ODQ4OTkyLCJleHAiOjE2Mzg4Nzc3OTJ9.3Fe5hCikw8j5Fl7RMJkC9HwliuNJU3ZMv6cma61gNvS7OcL-WA8wjRXW0RWp0N998AOsSSyw3f2ymH4ZqwAr9g' \
--header 'Content-Type: application/json' \
--data-raw '{
    "cartId": "61aef027ef24f43772d40287",
    "customerId": "61ae0189953ea2617e5dedb1",
    "total": 416000
}'

## License  

Copyright © [MetaMagic Global Inc](http://www.metamagicglobal.com/), 2021-22.  All rights reserved.

Licensed under the Apache 2.0 License.

**Enjoy!**


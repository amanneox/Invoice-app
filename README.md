## Technology Used

### Amazon SES - simple email service

### Amazon S3 - storing invoices

### EBS - server deployed on elastic Beanstalk

## Frameworks Used

### Apache PDFBox

### Javax mail

# Paperless

Paperless is a simple API allowing users to create invoice , download and mail them.
Create a invoice template to get started , then simply send a restful post to generate a user invoice.

# Installation

Create a file application.properties in api\src\main\resources with following information

## Local DB Settings

spring.datasource.url= jdbc:postgresql://localhost:5432/test  

spring.datasource.username=postgres

spring.datasource.password=amanneox

spring.jpa.hibernate.ddl-auto=create-drop

spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect

spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true

server.port=${PORT:8080}

## Set AWS Config using your bucket-name and bucket-url

cloud.aws.region.static = ap-south-1

cloud.aws.bucket = paperless-invoices

cloud.aws.bucket.baseurl = https://s3.ap-south-1.amazonaws.com/paperless-invoices/

## AwsCredentials File

Create a file AwsCredentials.properties api\src\main\resources with following information

accessKey=XXXXXXXXXXXX

secretKey=XXXXXXXXXXXXXXXX

>Keep these files in .gitignore

## Invoice Template  [/invoice]

### List All Invoice Template [GET]
List all the invoice templates created so far.

+ Response 200 (application/json)
  ```
  content[]
  ```

        {
            "createdAt": "2018-11-24T13:18:31.377+0000",
            "updatedAt": "2018-11-24T13:18:31.377+0000",
            "id": 1,
            "name": "10bbd53c-8c67-4346-b15a-9b1f3730337f",
            "logo": "www.google.com/image",
            "address": "NYC, USA",
            "cname": "Google",
            "number": 10800,
            "email": "help@google.com",
            "terms": "No Terms"
        }




### Create a New Invoice Template [POST]

You may create your own invoice template using this action. It takes a JSON
object containing a invoice info.

+ Request (application/json)

        {
            "logo": "www.google.com/image",
            "address":"NYC, USA",
            "cname":"Google",
            "number":"0010800",
            "email":"help@google.com",
            "terms":"No Terms"
        }

+ Response 201 (application/json)

    + Headers

            Location: /invoice/

    + Body

            {
                "createdAt": "2018-11-24T13:18:31.377+0000",
                "updatedAt": "2018-11-24T13:18:31.377+0000",
                "id": 1,
                "name": "10bbd53c-8c67-4346-b15a-9b1f3730337f",
                "logo": "www.google.com/image",
                "address": "NYC, USA",
                "cname": "Google",
                "number": 10800,
                "email": "help@google.com",
                "terms": "No Terms"
            }
## Invoice Template Update  [/invoice/update/{invoiceId}]            
### Update a Invoice Template [POST]  

+ Request (application/json)

        {
            "logo": "www.google.com/image",
            "address":"NYC, USA",
            "cname":"Google",
            "number":"0010800",
            "email":"help@google.com",
            "terms":"No Terms"
        }

+ Response 201 (application/json)

    + Headers

            Location: /invoice/update/{invoiceId}

    + Body

            {
                "createdAt": "2018-11-24T13:18:31.377+0000",
                "updatedAt": "2018-11-24T13:18:31.377+0000",
                "id": 1,
                "name": "10bbd53c-8c67-4346-b15a-9b1f3730337f",
                "logo": "www.google.com/image",
                "address": "NYC, USA",
                "cname": "Google",
                "number": 10800,
                "email": "help@google.com",
                "terms": "No Terms"
            }
## Invoice Template Delete  [/invoice/delete/{invoiceId}]            
### Delete a Invoice Template [POST]

+ Response 201 (application/json)

    + Headers

            Location: /invoice/update/{invoiceId}

    + Body

            OK

## User Invoice Template [/user/invoice/]  
### List All Invoice Template [GET]
List all the user invoice templates created so far.

+ Response 200 (application/json)

```
content[]
```
       {
            "createdAt": "2018-11-24T13:46:28.648+0000",
            "updatedAt": "2018-11-24T13:46:28.648+0000",
            "id": 3,
            "user_name": "Aman Adhikari",
            "total_value": 999,
            "invoice_url": "https://s3.ap-south-1.amazonaws.com/paperless-invoices/a9c22fc2-4517-452f-a576-7a7f2029067b.pdf",
            "items": [
                {
                    "name": "IPHONE X",
                    "desc": "Amazing Product",
                    "price": 999
                },
                {
                    "name": "IPHONE X Max",
                    "desc": "Amazing Product",
                    "price": 1200
                },
                {
                    "name": "IPHONE X +",
                    "desc": "Amazing Product",
                    "price": 1050
                }
            ]
        }

## Create User Invoice Template [/user/invoice/{invoiceid}]
### Create a New User Invoice Template [POST]

This is where the magic happens just post the user invoice details and the API will generate a pdf.


+ Request (application/json)

        {
            "user_name": "Aman Adhikari",
            "total_value":999,
            "items": [
        {
            "name": "IPHONE X",
            "price": "999",
            "desc": "Amazing Product"
        },
        {
            "name": "IPHONE X Max",
            "price": "1200",
            "desc": "Amazing Product"
        },
        {
            "name": "IPHONE X +",
            "price": "1050",
            "desc": "Amazing Product"
        }
    ]

        }
+ Response 201 (application/json)

    + Headers

            Location: /user/invoice/

    + Body
    ```
    content[]
    ```
           {
                "createdAt": "2018-11-24T13:46:28.648+0000",
                "updatedAt": "2018-11-24T13:46:28.648+0000",
                "id": 3,
                "user_name": "Aman Adhikari",
                "total_value": 999,
                "invoice_url": "https://s3.ap-south-1.amazonaws.com/paperless-invoices/a9c22fc2-4517-452f-a576-7a7f2029067b.pdf",
                "items": [
                    {
                        "name": "IPHONE X",
                        "desc": "Amazing Product",
                        "price": 999
                    },
                    {
                        "name": "IPHONE X Max",
                        "desc": "Amazing Product",
                        "price": 1200
                    },
                    {
                        "name": "IPHONE X +",
                        "desc": "Amazing Product",
                        "price": 1050
                    }
                ]
            }
## User Invoice Template Delete  [/user/invoice/delete/{invoiceId}]            
### Delete a User Invoice Template [POST]

+ Response 201 (application/json)

    + Headers

            Location: /user/invoice/delete/{invoiceId}

    + Body

            OK


## Dashboard User   [/user/]            
### Create a User [POST]

            + Response 201 (application/json)

                + Headers

                        Location: /user/

                + Body

                        {
                          "name":"username",
                          "number":"usernumber",
                          "email": useremail,
                          "password":"userpassword",
                          "role": "userrole",
                        }

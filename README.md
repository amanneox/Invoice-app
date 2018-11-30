HOST: http://paperless-dev.ap-south-1.elasticbeanstalk.com/

# Paperless

Paperless is a simple API allowing users to create invoice , download and mail them.
Create a invoice template to get started , then simply send a restful post to generate a user invoice.

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
{
    "content": [
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

        {   "createdAt": "2018-11-24T13:55:41.821+0000",
            "updatedAt": "2018-11-24T13:55:41.821+0000",
            "id": 4,
            "invoice_url": "https://s3.ap-south-1.amazonaws.com/paperless-invoices/f509d89e-040b-487d-951f-0cc704c016d8.pdf",
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
## User Invoice Template Delete  [/user/invoice/delete/{invoiceId}]            
### Delete a User Invoice Template [POST]

+ Response 201 (application/json)

    + Headers

            Location: /user/invoice/delete/{invoiceId}

    + Body

            OK

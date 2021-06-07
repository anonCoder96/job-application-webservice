# Job Interview Web Application

Following the instructions within this readme should allow anyone to fully assess the functionality of this application.

Note that the database will be populated on application launch with two accounts:

    Account 1, with ID: 1, and balance 2209.29
    Account 2, with ID: 2, and balance 3569.00

## 1. Database

### 1.1. Setting Up a Local Postgres Docker Container

We will use a docker container for this application. 

Firstly install and configure docker, then:

run:

    `docker pull postgres:11`

followed by:

    `docker run --name local-dev-postgres -p 5432:5432 -e POSTGRES_PASSWORD="password" -d postgres:11`

note: we only use a weak password such as "password" because this is a demo app.

create a database:

    `docker exec local-dev-postgres psql -U postgres -c"CREATE DATABASE bankdb" postgres`


### 1.2. To query the local running DB

if it is necessary to start the docker container: `docker start local-dev-postgres`

followed by: `docker exec -it local-dev-postgres bash`

then launch psql: `psql -U postgres`

enter the database: `\c bankdb`

finally, use `\dt` to view tables, and then any valid SQL query should be sufficient. 


## 2. Building

First set up the database.

The build system in use is gradle, all the normal rules for gradle apply here.

To build:

    `gradlew build`

To run:

    `gradlew bootrun`

By default, the application will run on port 8080, with the address localhost.

## 3. Endpoints

Currently, the API here implements two endpoints:

    `/statement/{id}` (request type GET)

where ID is the ID of the account. By default the database will contain 2 accounts, with ID 1 and 2.
This endpoint returns a JSON object containing the accounts ID, current balance, and a list of all transactions the account was a part of, both as payee and recipient.

And:

    `/transaction/pay/{payeeId}/{recipientId}/{amount}` (request type POST)

Where payeeId is the ID of the payers account, recipientId is the ID of the recipients account, and amount is the amount of money as a floating point value.
This endpoint will return a boolean status flag, true if the transaction was successful, false if not. An error may also be returned if the account does not exist.

## 4. Examples of Usage

    `http://localhost:8080/transaction/pay/1/2/200.00`
    `http://localhost:8080/transaction/pay/2/1/304.23`
    `http://localhost:8080/statement/1`
### Database

We will use a docker container here as that is easiet to set up. assuming you have already installed and configured docker.

run:

`docker pull postgres:11`

followed by:

`docker run --name local-dev-postgres -p 5432:5432 -e POSTGRES_PASSWORD="password" -d postgres:11`

note we only use a weak password such as "password" because this is a demo app.

create a database:

`docker exec local-dev-postgres psql -U postgres -c"CREATE DATABASE bankdb" postgres`

### Building

Since this software is built with gradle, all the normal rules for gradle apply here.

To build:

`gradlew build`

To run:

`gradlew bootrun`

By default the application will run on port 8080.

### endpoints

Currently the API here implements two endpoints:

`/statement` (request type GET)

and

`/transaction/pay` (request type POST)

# Model Agnostic Query Builder

This repository contains a simple Query Generator that takes a desired database query
and applies them to different database architectures. In this simplified application,
the desired query can be dispatched on a MongoDB sharded database, and a PostgreSQL
database which has initializing scripts in this repo.

To run the database, simply run `docker compose up -d`.

The MongoDB initialization is more complicated, involving set initialization and
routing setup. For instructions on how to set up the Mongo database that is used
for this project, follow the instructions in the 
[mongo-sharded-project](https://github.com/rlodhicode/mongo-sharded-project)
repository that I have created.

Further Potential implementations include a CLI compatibility, for users to run the
application with their query requirements as CLI arguments. So far, we don't implement
any CRUD capabilities for our query generator, a more robust library implementation
can include these capabilities, potentially even expanding the database architectures
that this project currently supports. Another avenue for improvement would be to allow
OR conditions for our query objects - right now all inputted conditions are AND conditions.
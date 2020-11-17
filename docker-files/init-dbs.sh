#!/bin/bash
set -e

# create flag database
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE USER admin WITH PASSWORD 'admin';
    CREATE DATABASE javawebapi;
    GRANT ALL PRIVILEGES ON DATABASE javawebapi TO admin;
EOSQL
EOSQL
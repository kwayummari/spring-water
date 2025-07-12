-- PostgreSQL Database Setup for NIDA Service
-- Run this script as postgres user

-- Create database
CREATE DATABASE nida_service;

-- Create user
CREATE USER nida_user WITH PASSWORD 'nida_password_123';

-- Grant privileges
GRANT ALL PRIVILEGES ON DATABASE nida_service TO nida_user;

-- Connect to the new database
\c nida_service;

-- Grant schema privileges
GRANT ALL ON SCHEMA public TO nida_user;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO nida_user;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO nida_user;

-- Set default privileges for future objects
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO nida_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO nida_user;

-- Enable UUID extension
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Verify setup
SELECT datname FROM pg_database WHERE datname = 'nida_service';
SELECT usename FROM pg_user WHERE usename = 'nida_user';

-- Test connection (this should work without errors)
\c nida_service nida_user;
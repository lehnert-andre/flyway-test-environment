\connect flyway;
DROP SCHEMA IF EXISTS flyway CASCADE;

CREATE SCHEMA flyway AUTHORIZATION flyway;

SET search_path TO flyway;

-- permissions

GRANT ALL ON SCHEMA flyway TO flyway;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA flyway TO flyway;
GRANT USAGE ON ALL SEQUENCES IN SCHEMA flyway TO flyway;

CREATE SCHEMA IF NOT EXISTS "dashboard-dev";
-- Remove all executed migration info to able migration (re)execution and old migration deletion
DELETE FROM databasechangeloglock;
DELETE FROM databasechangelog WHERE dateexecuted < NOW();

-- DROP all existent tables but migration's control
DO $$ DECLARE
    r RECORD;
BEGIN
    FOR r IN (
        SELECT
        	tablename
        FROM
        	pg_tables
        WHERE
        	schemaname = 'public' AND
        	tablename <> 'databasechangelog' AND
        	tablename <> 'databasechangeloglock'
    ) LOOP
        EXECUTE 'DROP TABLE IF EXISTS ' || quote_ident(r.tablename) || ' CASCADE';
    END LOOP;
END $$;
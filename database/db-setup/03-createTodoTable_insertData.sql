\connect flyway;
SET search_path TO flyway;

DROP TABLE IF EXISTS todo;

CREATE TABLE IF NOT EXISTS todo (
    id          SERIAL PRIMARY KEY,
    task        TEXT,
    created_at  TIMESTAMP WITH TIME ZONE NOT NULL
);

INSERT INTO todo (id, task, created_at) VALUES (1, 'Hallo Welt', now()) ON CONFLICT DO NOTHING;
INSERT INTO todo (id, task, created_at) VALUES (2, 'Flyway lernen', now()) ON CONFLICT DO NOTHING;
INSERT INTO todo (id, task, created_at) VALUES (3, 'Datenbankmigration von A bis Z', now()) ON CONFLICT DO NOTHING;
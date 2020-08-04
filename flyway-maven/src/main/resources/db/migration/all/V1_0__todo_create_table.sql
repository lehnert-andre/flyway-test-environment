CREATE TABLE IF NOT EXISTS todo (
    id          SERIAL PRIMARY KEY,
    task        TEXT,
    created_at  TIMESTAMP WITH TIME ZONE NOT NULL
);

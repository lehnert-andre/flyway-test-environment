INSERT INTO todo (id, task, created_at) VALUES (1, 'Hallo Welt', now()) ON CONFLICT DO NOTHING;
INSERT INTO todo (id, task, created_at) VALUES (2, 'Flyway lernen', now()) ON CONFLICT DO NOTHING;
INSERT INTO todo (id, task, created_at) VALUES (3, 'Datenbankmigration von A bis Z', now()) ON CONFLICT DO NOTHING;

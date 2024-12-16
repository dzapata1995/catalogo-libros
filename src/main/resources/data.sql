INSERT INTO languages (code, name)
SELECT 'en', 'Ingles'
WHERE NOT EXISTS (
    SELECT 1 FROM languages WHERE code = 'en'
);

INSERT INTO languages (code, name)
SELECT 'es', 'Español'
WHERE NOT EXISTS (
    SELECT 1 FROM languages WHERE code = 'es'
);

INSERT INTO languages (code, name)
SELECT 'fr', 'Frances'
WHERE NOT EXISTS (
    SELECT 1 FROM languages WHERE code = 'fr'
);

INSERT INTO languages (code, name)
SELECT 'pt', 'Portugues'
WHERE NOT EXISTS (
    SELECT 1 FROM languages WHERE code = 'pt'
);
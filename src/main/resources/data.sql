INSERT INTO footballers
VALUES
    ('6baaeae1-39b3-4fe1-ab3d-35b6ac08a1fa',
     '2000-05-25',
     4,0);
INSERT INTO footballers
VALUES
    ('6baaeae1-39b3-4fe1-ab3d-35b6ac08a1fb',
     '2000-05-25',
     0,
     0);
INSERT INTO exclusions(id,footballer_id, type, quantity,active,till)
VALUES
    ('6baaeae1-39b3-4fe1-ab3d-35b6ac08a1fc',
     '6baaeae1-39b3-4fe1-ab3d-35b6ac08a1fa',
     'QUANTITATIVE',
    2,
     null, null);
INSERT INTO exclusions(id,footballer_id, type, quantity,active,till)
VALUES
    ('6baaeae1-39b3-4fe1-ab3d-35b6ac08a1fd',
     '6baaeae1-39b3-4fe1-ab3d-35b6ac08a1fb',
     'STATE',
     null,
     true, null);

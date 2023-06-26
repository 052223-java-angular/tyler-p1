UPDATE items
SET image_url = 'https://i.imgur.com/5QBF7ND.jpg'
WHERE id = 'burger-combo';

UPDATE items
SET image_url = 'https://i.imgur.com/6U18Z0f.jpg'
WHERE id = 'burger-beef';

UPDATE items
SET image_url = 'https://i.imgur.com/fz9p5ge.jpg'
WHERE id = 'burger-steak';

UPDATE items
SET image_url = 'https://i.imgur.com/1odumnd.jpg'
WHERE id = 'burger-marsbeef';

UPDATE items 
SET image_url = 'https://i.imgur.com/UjUVX7q.jpg'
WHERE id = 'burger-marssteak';

UPDATE items
SET image_url = 'https://i.imgur.com/ZNgCo2h.jpg', description = 'From regular Earth potatoes'
WHERE id IN ( 
    'fries',
    'fries-large'
);

UPDATE items
SET image_url = 'https://i.imgur.com/vkKl1PY.jpg', description = 'From awesome Mars potatoes'
WHERE id IN (
    'fries-mars',
    'fries-mars-large'
);

UPDATE items
SET image_url = 'https://i.imgur.com/iIg5nhU.jpg', description = 'More flavors coming soon!'
WHERE id = 'shake-mars';

UPDATE items
SET image_url = 'https://i.imgur.com/Rv1UcMs.jpg', description = 'More options coming soon'
WHERE id in (
    'fountain-drink-medium',
    'fountain-drink-large'
);

/*

ROLLBACK:

UPDATE items
SET image_url = NULL
WHERE id NOT IN ('');

UPDATE items
SET description = NULL
WHERE id IN (
    'fries',
    'fries-large',
    'fries-mars',
    'fries-mars-large',
    'shake-mars',
    'fountain-drink-medium',
    'fountain-drink-large'
);

*/
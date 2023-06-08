INSERT INTO service_types(
    id,
    name
) VALUES (
    'TAKEOUT',
    'Takeout'
), (
    'DELIVERY',
    'Delivery'
);

INSERT INTO restaurants(
    id,
    description,
    name
) VALUES (
    'marstown',
    'Interplanetary chain serving pizza, burgers and shakes, with ingredients fresh from Mars.',
    'MarsTown'
);

INSERT INTO menus (
    id, 
    name
) VALUES (
    'marstown-menu-1', 
    'Menu'
);

INSERT INTO services (
    id,
    menu_id,
    restaurant_id,
    service_type_id
) VALUES (
    'takeout-marstown-menu-1',
    'marstown-menu-1',
    'marstown',
    'TAKEOUT'
);

/*

ROLLBACK:

DELETE FROM services WHERE id IN ('takeout-marstown-menu-1');
DELETE FROM menus WHERE id IN ('marstown-menu-1');
DELETE FROM restaurants WHERE id IN ('marstown');
DELETE FROM service_types WHERE id IN ('DELIVERY', 'TAKEOUT');


*/
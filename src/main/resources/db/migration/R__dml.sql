-- ${flyway:timestamp}

INSERT INTO roles (
    id,
    name
) VALUES (
    'USER',
    'USER'
);

INSERT INTO service_types (
    id,
    name
) VALUES (
    'service-type-takeout',
    'Takeout'
), (
    'service-type-delivery',
    'Delivery'
);

INSERT INTO restaurants (
    id,
    description,
    name
) VALUES (
    'restaurant-marstown',
    'Interplanetary chain serving pizza, burgers and shakes, with ingredients fresh from Mars.',
    'MarsTown'
);

INSERT INTO menus (
    id, 
    name
) VALUES (
    'menu-marstown', 
    'Menu'
);

INSERT INTO services (
    id,
    menu_id,
    restaurant_id,
    service_type_id
) VALUES (
    'service-takeout-marstown',
    'menu-marstown',
    'restaurant-marstown',
    'service-type-takeout'
);

INSERT INTO sections (
    id,
    name,
    description
) VALUES (
    'section-combo-meals',
    'Combo Meals',
    ''
), (
    'section-burgers',
    'Burgers',
    ''
), (
    'section-beverages',
    'Beverages',
    ''
), (
    'section-burger-extras',
    'Extras',
    ''
), (
    'section-burger-combo-sides',
    'Choose a Side',
    ''
);

-- burgers
INSERT INTO items (
    id,
    name,
    description
) VALUES (
    'burger-combo', 
    'Burger Combo',
    'Burger, fries, and a drink'
), (
    'burger-beef', 
    'Beef Burger',
    'From regular Earth-grown cows'
), (
    'burger-steak',
    'Steak Burger',
    'From regular Earth-grown cows'
), (
    'burger-marsbeef',
    'MarsBeef Burger',
    'From enhanced Mars-grown cows!'
), (
    'burger-marssteak',
    'MarsSteak Burger',
    'From enhanced Mars-grown cows!'
);

-- burger extras
INSERT INTO items (
    id,
    name
) VALUES (
    'lettuce',
    'Lettuce'
), (
    'tomato',
    'Tomato'
), (
    'ketchup',
    'Ketchup'
), (
    'mayonaise',
    'Mayonaise'
), (
    'mustard',
    'Mustard'
), (
    'sauce-ranch',
    'Ranch Sauce'
);

-- burger sides
INSERT INTO items (
    id,
    name
) VALUES (
    'fries',
    'Fries'
), (
    'fries-large',
    'Large Fries'
), (
    'fries-mars',
    'MarsFries'
), (
    'fries-mars-large',
    'Large MarsFries'
);

-- drinks
INSERT INTO items (
    id,
    name
) VALUES (
    'shake-mars',
    'MarsShake'
), (
    'fountain-drink-medium',
    'Medium Fountain Drink'
), (
    'fountain-drink-large',
    'Large Fountain Drink'
);

-- Top level menu sections
INSERT INTO menu_sections (
    id,
    display_order,
    max_quantity,
    min_quantity,
    menu_id,
    parent_menu_item_id,
    section_id
) VALUES (
    'menu-marstown-section-combo-meals',
    1,
    null,
    null,
    'menu-marstown',
    null,
    'section-combo-meals'
);

-- Burger Combo Menu Item
INSERT INTO menu_items (
    id,
    display_order,
    item_id,
    menu_section_id
) VALUES (
    'menu-marstown-item-burger-combo',
    1,
    'burger-combo',
    'menu-marstown-section-combo-meals'
);

-- Burger Combo Menu Burger Section
INSERT INTO menu_sections (
    id,
    display_order,
    max_quantity,
    min_quantity,
    menu_id,
    parent_menu_item_id,
    section_id 
) VALUES (
    'menu-marstown-section-burger-combo-burgers',
    1,
    1,
    1,
    'menu-marstown',
    'menu-marstown-item-burger-combo',
    'section-burgers'
);


-- Burger Combo Menu Burger Items
INSERT INTO menu_items (
    id,
    display_order,
    item_id,
    menu_section_id
) VALUES (
    'menu-marstown-item-burger-combo-burger-beef',
    1,
    'burger-beef',
    'menu-marstown-section-burger-combo-burgers'
), (
    'menu-marstown-item-burger-combo-burger-steak',
    2,
    'burger-steak',
    'menu-marstown-section-burger-combo-burgers'
), (
    'menu-marstown-item-burger-combo-burger-marsbeef',
    3,
    'burger-marsbeef',
    'menu-marstown-section-burger-combo-burgers'
), (
    'menu-marstown-item-burger-combo-burger-marssteak',
    4,
    'burger-marssteak',
    'menu-marstown-section-burger-combo-burgers'
);

-- Burger Combo Menu Extras Section
INSERT INTO menu_sections (
    id,
    display_order,
    max_quantity,
    min_quantity,
    menu_id,
    parent_menu_item_id,
    section_id 
) VALUES (
    'menu-marstown-section-burger-combo-extras',
    2,
    null,
    0,
    'menu-marstown',
    'menu-marstown-item-burger-combo',
    'section-burger-extras'
);

-- Burger Combo Menu Extras Items
INSERT INTO menu_items (
    id,
    display_order,
    item_id,
    menu_section_id
) VALUES (
    'menu-marstown-item-burger-combo-extras-lettuce',
    1,
    'lettuce',
    'menu-marstown-section-burger-combo-extras'
), (
    'menu-marstown-item-burger-combo-extras-tomato',
    2,
    'tomato',
    'menu-marstown-section-burger-combo-extras'
), (
    'menu-marstown-item-burger-combo-extras-ketchup',
    3,
    'ketchup',
    'menu-marstown-section-burger-combo-extras'
), (
    'menu-marstown-item-burger-combo-extras-mayonaise',
    4,
    'mayonaise',
    'menu-marstown-section-burger-combo-extras'
), (
    'menu-marstown-item-burger-combo-extras-mustard',
    5,
    'mustard',
    'menu-marstown-section-burger-combo-extras'
), (
    'menu-marstown-item-burger-combo-extras-sauce-ranch',
    6,
    'sauce-ranch',
    'menu-marstown-section-burger-combo-extras'
);

-- Burger Combo Menu Sides Section
INSERT INTO menu_sections (
    id,
    display_order,
    max_quantity,
    min_quantity,
    menu_id,
    parent_menu_item_id,
    section_id 
) VALUES (
    'menu-marstown-section-burger-combo-sides',
    3,
    1,
    1,
    'menu-marstown',
    'menu-marstown-item-burger-combo',
    'section-burger-extras'
);

INSERT INTO menu_items (
    id,
    display_order,
    item_id,
    menu_section_id
) VALUES (
    'menu-marstown-item-burger-combo-sides-fries',
    1,
    'fries',
    'menu-marstown-section-burger-combo-sides'
), (
    'menu-marstown-item-burger-combo-sides-fries-large',
    2,
    'fries-large',
    'menu-marstown-section-burger-combo-sides'
), (
    'menu-marstown-item-burger-combo-sides-fries-mars',
    3,
    'fries-mars',
    'menu-marstown-section-burger-combo-sides'
), (
    'menu-marstown-item-burger-combo-sides-fries-mars-large',
    4,
    'fries-mars-large',
    'menu-marstown-section-burger-combo-sides'
);


/*

ROLLBACK:

DELETE FROM menu_items WHERE id IN (
    'menu-marstown-item-burger-combo-sides-fries',
    'menu-marstown-item-burger-combo-sides-fries-large',
    'menu-marstown-item-burger-combo-sides-fries-mars',
    'menu-marstown-item-burger-combo-sides-fries-mars-large'
);

DELETE FROM menu_sections WHERE id IN (
    'menu-marstown-section-burger-combo-sides'
);

DELETE FROM menu_items WHERE id IN (
    'menu-marstown-item-burger-combo-extras-lettuce',
    'menu-marstown-item-burger-combo-extras-tomato',
    'menu-marstown-item-burger-combo-extras-ketchup',
    'menu-marstown-item-burger-combo-extras-mayonaise',
    'menu-marstown-item-burger-combo-extras-mustard',
    'menu-marstown-item-burger-combo-extras-sauce-ranch'
);
DELETE FROM menu_sections WHERE id IN (
    'menu-marstown-section-burger-combo-extras'
);

DELETE FROM menu_items WHERE id IN (
    'menu-marstown-item-burger-combo-burger-beef',
    'menu-marstown-item-burger-combo-burger-steak',
    'menu-marstown-item-burger-combo-burger-marsbeef',
    'menu-marstown-item-burger-combo-burger-marssteak'
);

DELETE FROM menu_sections WHERE id IN (
    'menu-marstown-section-burger-combo-burgers'
);

DELETE FROM menu_items WHERE id IN (
    'menu-marstown-item-burger-combo'
);

DELETE FROM menu_sections WHERE id IN (
    'menu-marstown-section-combo-meals'
);

-- delete drinks
DELETE FROM items WHERE id IN (
    'shake-mars',
    'fountain-drink-medium',
    'fountain-drink-large'
);
-- delete burger sides
DELETE FROM items WHERE id IN (
     'fries',
     'fries-large',
     'fries-mars',
     'fries-mars-large'
);
-- delete burger extras
DELETE FROM items WHERE id IN (
    'lettuce',
    'tomato',
    'ketchup',
    'mayonaise',
    'mustard',
    'sauce-ranch'
);
-- delete burgers
DELETE FROM items WHERE id IN (
    'burger-combo', 
    'burger-beef', 
    'burger-steak',
    'burger-marsbeef',
    'burger-marssteak'
);
-- delete sections
DELETE FROM sections WHERE id IN (
    'section-combo-meals',
    'section-burgers',
    'section-beverages',
    'section-burger-extras',
    'section-burger-combo-sides'
);
DELETE FROM services WHERE id IN ('service-takeout-marstown');
DELETE FROM menus WHERE id IN ('menu-marstown');
DELETE FROM restaurants WHERE id IN ('restaurant-marstown');
DELETE FROM service_types WHERE id IN ('service-type-takeout', 'service-type-delivery');
DELETE FROM roles WHERE id IN ('USER');

*/
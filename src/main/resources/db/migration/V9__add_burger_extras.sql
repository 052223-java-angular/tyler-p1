-- ${flyway:timestamp}

-- Burger extras sections
INSERT INTO menu_sections (
    id,
    display_order,
    max_quantity,
    min_quantity,
    menu_id,
    parent_menu_item_id,
    section_id 
) VALUES (
    'menu-marstown-section-burger-beef-extras',
    1,
    NULL,
    0,
    'menu-marstown',
    'menu-marstown-item-burger-beef',
    'section-burger-extras'
), (
    'menu-marstown-section-burger-steak-extras',
    1,
    NULL,
    0,
    'menu-marstown',
    'menu-marstown-item-burger-steak',
    'section-burger-extras'
), (
    'menu-marstown-section-burger-marsbeef-extras',
    1,
    NULL,
    0,
    'menu-marstown',
    'menu-marstown-item-burger-marsbeef',
    'section-burger-extras'
), (
    'menu-marstown-section-burger-marssteak-extras',
    1,
    NULL,
    0,
    'menu-marstown',
    'menu-marstown-item-burger-marssteak',
    'section-burger-extras'
);

-- Burger Beef Menu Item Extras
INSERT INTO menu_items (
    id,
    display_order,
    item_id,
    menu_section_id
) VALUES (
    'menu-marstown-item-burger-beef-extras-lettuce',
    1,
    'lettuce',
    'menu-marstown-section-burger-beef-extras'
), (
    'menu-marstown-item-burger-beef-extras-tomato',
    2,
    'tomato',
    'menu-marstown-section-burger-beef-extras'
), (
    'menu-marstown-item-burger-beef-extras-ketchup',
    3,
    'ketchup',
    'menu-marstown-section-burger-beef-extras'
), (
    'menu-marstown-item-burger-beef-extras-mayonaise',
    4,
    'mayonaise',
    'menu-marstown-section-burger-beef-extras'
), (
    'menu-marstown-item-burger-beef-extras-mustard',
    5,
    'mustard',
    'menu-marstown-section-burger-beef-extras'
), (
    'menu-marstown-item-burger-beef-extras-sauce-ranch',
    6,
    'sauce-ranch',
    'menu-marstown-section-burger-beef-extras'
);

-- Burger Steak Menu Item Extras
INSERT INTO menu_items (
    id,
    display_order,
    item_id,
    menu_section_id
) VALUES (
    'menu-marstown-item-burger-steak-extras-lettuce',
    1,
    'lettuce',
    'menu-marstown-section-burger-steak-extras'
), (
    'menu-marstown-item-burger-steak-extras-tomato',
    2,
    'tomato',
    'menu-marstown-section-burger-steak-extras'
), (
    'menu-marstown-item-burger-steak-extras-ketchup',
    3,
    'ketchup',
    'menu-marstown-section-burger-steak-extras'
), (
    'menu-marstown-item-burger-steak-extras-mayonaise',
    4,
    'mayonaise',
    'menu-marstown-section-burger-steak-extras'
), (
    'menu-marstown-item-burger-steak-extras-mustard',
    5,
    'mustard',
    'menu-marstown-section-burger-steak-extras'
), (
    'menu-marstown-item-burger-steak-extras-sauce-ranch',
    6,
    'sauce-ranch',
    'menu-marstown-section-burger-steak-extras'
);

-- Burger MarsBeef Menu Item Extras
INSERT INTO menu_items (
    id,
    display_order,
    item_id,
    menu_section_id
) VALUES (
    'menu-marstown-item-burger-marsbeef-extras-lettuce',
    1,
    'lettuce',
    'menu-marstown-section-burger-marsbeef-extras'
), (
    'menu-marstown-item-burger-marsbeef-extras-tomato',
    2,
    'tomato',
    'menu-marstown-section-burger-marsbeef-extras'
), (
    'menu-marstown-item-burger-marsbeef-extras-ketchup',
    3,
    'ketchup',
    'menu-marstown-section-burger-marsbeef-extras'
), (
    'menu-marstown-item-burger-marsbeef-extras-mayonaise',
    4,
    'mayonaise',
    'menu-marstown-section-burger-marsbeef-extras'
), (
    'menu-marstown-item-burger-marsbeef-extras-mustard',
    5,
    'mustard',
    'menu-marstown-section-burger-marsbeef-extras'
), (
    'menu-marstown-item-burger-marsbeef-extras-sauce-ranch',
    6,
    'sauce-ranch',
    'menu-marstown-section-burger-marsbeef-extras'
);

-- Burger MarsSteak Menu Item Extras
INSERT INTO menu_items (
    id,
    display_order,
    item_id,
    menu_section_id
) VALUES (
    'menu-marstown-item-burger-marssteak-extras-lettuce',
    1,
    'lettuce',
    'menu-marstown-section-burger-marssteak-extras'
), (
    'menu-marstown-item-burger-marssteak-extras-tomato',
    2,
    'tomato',
    'menu-marstown-section-burger-marssteak-extras'
), (
    'menu-marstown-item-burger-marssteak-extras-ketchup',
    3,
    'ketchup',
    'menu-marstown-section-burger-marssteak-extras'
), (
    'menu-marstown-item-burger-marssteak-extras-mayonaise',
    4,
    'mayonaise',
    'menu-marstown-section-burger-marssteak-extras'
), (
    'menu-marstown-item-burger-marssteak-extras-mustard',
    5,
    'mustard',
    'menu-marstown-section-burger-marssteak-extras'
), (
    'menu-marstown-item-burger-marssteak-extras-sauce-ranch',
    6,
    'sauce-ranch',
    'menu-marstown-section-burger-marssteak-extras'
);

-- Burger Beef Menu Item Offer Extras
INSERT INTO menu_item_offers (
    id, 
    max_quantity, 
    min_quantity, 
    price, 
    price_currency, 
    stripe_price_id, 
    menu_item_id
) VALUES (
    'menu-marstown-item-burger-beef-extras-lettuce-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NK7vsAT9USe7yEjY7eTP5aY',
    'menu-marstown-item-burger-beef-extras-lettuce'
), (
    'menu-marstown-item-burger-beef-extras-tomato-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NKM2EAT9USe7yEj1rBBq2KK',
    'menu-marstown-item-burger-beef-extras-tomato'
), (
    'menu-marstown-item-burger-beef-extras-ketchup-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NKM2cAT9USe7yEjpuEWtmG8',
    'menu-marstown-item-burger-beef-extras-ketchup'
), (
    'menu-marstown-item-burger-beef-extras-mayonaise-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NKM2pAT9USe7yEj3sFxj20D',
    'menu-marstown-item-burger-beef-extras-mayonaise'
), (
    'menu-marstown-item-burger-beef-extras-mustard-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NKM2xAT9USe7yEjVG5obUw1',
    'menu-marstown-item-burger-beef-extras-mustard'
), (
    'menu-marstown-item-burger-beef-extras-sauce-ranch-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NKM3AAT9USe7yEjfXV085R5',
    'menu-marstown-item-burger-beef-extras-sauce-ranch'
);

-- Burger Steak Menu Item Offer Extras
INSERT INTO menu_item_offers (
    id, 
    max_quantity, 
    min_quantity, 
    price, 
    price_currency, 
    stripe_price_id, 
    menu_item_id
) VALUES (
    'menu-marstown-item-burger-steak-extras-lettuce-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NK7vsAT9USe7yEjY7eTP5aY',
    'menu-marstown-item-burger-steak-extras-lettuce'
), (
    'menu-marstown-item-burger-steak-extras-tomato-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NKM2EAT9USe7yEj1rBBq2KK',
    'menu-marstown-item-burger-steak-extras-tomato'
), (
    'menu-marstown-item-burger-steak-extras-ketchup-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NKM2cAT9USe7yEjpuEWtmG8',
    'menu-marstown-item-burger-steak-extras-ketchup'
), (
    'menu-marstown-item-burger-steak-extras-mayonaise-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NKM2pAT9USe7yEj3sFxj20D',
    'menu-marstown-item-burger-steak-extras-mayonaise'
), (
    'menu-marstown-item-burger-steak-extras-mustard-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NKM2xAT9USe7yEjVG5obUw1',
    'menu-marstown-item-burger-steak-extras-mustard'
), (
    'menu-marstown-item-burger-steak-extras-sauce-ranch-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NKM3AAT9USe7yEjfXV085R5',
    'menu-marstown-item-burger-steak-extras-sauce-ranch'
);

-- Burger MarsBeef Menu Item Offer Extras
INSERT INTO menu_item_offers (
    id, 
    max_quantity, 
    min_quantity, 
    price, 
    price_currency, 
    stripe_price_id, 
    menu_item_id
) VALUES (
    'menu-marstown-item-burger-marsbeef-extras-lettuce-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NK7vsAT9USe7yEjY7eTP5aY',
    'menu-marstown-item-burger-marsbeef-extras-lettuce'
), (
    'menu-marstown-item-burger-marsbeef-extras-tomato-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NKM2EAT9USe7yEj1rBBq2KK',
    'menu-marstown-item-burger-marsbeef-extras-tomato'
), (
    'menu-marstown-item-burger-marsbeef-extras-ketchup-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NKM2cAT9USe7yEjpuEWtmG8',
    'menu-marstown-item-burger-marsbeef-extras-ketchup'
), (
    'menu-marstown-item-burger-marsbeef-extras-mayonaise-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NKM2pAT9USe7yEj3sFxj20D',
    'menu-marstown-item-burger-marsbeef-extras-mayonaise'
), (
    'menu-marstown-item-burger-marsbeef-extras-mustard-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NKM2xAT9USe7yEjVG5obUw1',
    'menu-marstown-item-burger-marsbeef-extras-mustard'
), (
    'menu-marstown-item-burger-marsbeef-extras-sauce-ranch-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NKM3AAT9USe7yEjfXV085R5',
    'menu-marstown-item-burger-marsbeef-extras-sauce-ranch'
);

-- Burger MarsSteak Menu Item Offer Extras
INSERT INTO menu_item_offers (
    id, 
    max_quantity, 
    min_quantity, 
    price, 
    price_currency, 
    stripe_price_id, 
    menu_item_id
) VALUES (
    'menu-marstown-item-burger-marssteak-extras-lettuce-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NK7vsAT9USe7yEjY7eTP5aY',
    'menu-marstown-item-burger-marssteak-extras-lettuce'
), (
    'menu-marstown-item-burger-marssteak-extras-tomato-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NKM2EAT9USe7yEj1rBBq2KK',
    'menu-marstown-item-burger-marssteak-extras-tomato'
), (
    'menu-marstown-item-burger-marssteak-extras-ketchup-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NKM2cAT9USe7yEjpuEWtmG8',
    'menu-marstown-item-burger-marssteak-extras-ketchup'
), (
    'menu-marstown-item-burger-marssteak-extras-mayonaise-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NKM2pAT9USe7yEj3sFxj20D',
    'menu-marstown-item-burger-marssteak-extras-mayonaise'
), (
    'menu-marstown-item-burger-marssteak-extras-mustard-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NKM2xAT9USe7yEjVG5obUw1',
    'menu-marstown-item-burger-marssteak-extras-mustard'
), (
    'menu-marstown-item-burger-marssteak-extras-sauce-ranch-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NKM3AAT9USe7yEjfXV085R5',
    'menu-marstown-item-burger-marssteak-extras-sauce-ranch'
);


/*

ROLLBACK:

DELETE FROM menu_item_offers
WHERE id IN (
    'menu-marstown-item-burger-beef-extras-lettuce-offer-usd',
    'menu-marstown-item-burger-beef-extras-tomato-offer-usd',
    'menu-marstown-item-burger-beef-extras-ketchup-offer-usd',
    'menu-marstown-item-burger-beef-extras-mayonaise-offer-usd',
    'menu-marstown-item-burger-beef-extras-mustard-offer-usd',
    'menu-marstown-item-burger-beef-extras-sauce-ranch-offer-usd',
    'menu-marstown-item-burger-steak-extras-lettuce-offer-usd',
    'menu-marstown-item-burger-steak-extras-tomato-offer-usd',
    'menu-marstown-item-burger-steak-extras-ketchup-offer-usd',
    'menu-marstown-item-burger-steak-extras-mayonaise-offer-usd',
    'menu-marstown-item-burger-steak-extras-mustard-offer-usd',
    'menu-marstown-item-burger-steak-extras-sauce-ranch-offer-usd',
    'menu-marstown-item-burger-marsbeef-extras-lettuce-offer-usd',
    'menu-marstown-item-burger-marsbeef-extras-tomato-offer-usd',
    'menu-marstown-item-burger-marsbeef-extras-ketchup-offer-usd',
    'menu-marstown-item-burger-marsbeef-extras-mayonaise-offer-usd',
    'menu-marstown-item-burger-marsbeef-extras-mustard-offer-usd',
    'menu-marstown-item-burger-marsbeef-extras-sauce-ranch-offer-usd',
    'menu-marstown-item-burger-marssteak-extras-lettuce-offer-usd',
    'menu-marstown-item-burger-marssteak-extras-tomato-offer-usd',
    'menu-marstown-item-burger-marssteak-extras-ketchup-offer-usd',
    'menu-marstown-item-burger-marssteak-extras-mayonaise-offer-usd',
    'menu-marstown-item-burger-marssteak-extras-mustard-offer-usd',
    'menu-marstown-item-burger-marssteak-extras-sauce-ranch-offer-usd'
);

DELETE FROM menu_items
WHERE id IN (
    'menu-marstown-item-burger-marssteak-extras-lettuce',
    'menu-marstown-item-burger-marssteak-extras-tomato',
    'menu-marstown-item-burger-marssteak-extras-ketchup',
    'menu-marstown-item-burger-marssteak-extras-mayonaise',
    'menu-marstown-item-burger-marssteak-extras-mustard',
    'menu-marstown-item-burger-marssteak-extras-sauce-ranch'
);

DELETE FROM menu_items
WHERE id IN (
    'menu-marstown-item-burger-marsbeef-extras-lettuce',
    'menu-marstown-item-burger-marsbeef-extras-tomato',
    'menu-marstown-item-burger-marsbeef-extras-ketchup',
    'menu-marstown-item-burger-marsbeef-extras-mayonaise',
    'menu-marstown-item-burger-marsbeef-extras-mustard',
    'menu-marstown-item-burger-marsbeef-extras-sauce-ranch'
);

DELETE FROM menu_items
WHERE id IN (
    'menu-marstown-item-burger-steak-extras-lettuce',
    'menu-marstown-item-burger-steak-extras-tomato',
    'menu-marstown-item-burger-steak-extras-ketchup',
    'menu-marstown-item-burger-steak-extras-mayonaise',
    'menu-marstown-item-burger-steak-extras-mustard',
    'menu-marstown-item-burger-steak-extras-sauce-ranch'
);

DELETE FROM menu_items
WHERE id IN (
    'menu-marstown-item-burger-beef-extras-lettuce',
    'menu-marstown-item-burger-beef-extras-tomato',
    'menu-marstown-item-burger-beef-extras-ketchup',
    'menu-marstown-item-burger-beef-extras-mayonaise',
    'menu-marstown-item-burger-beef-extras-mustard',
    'menu-marstown-item-burger-beef-extras-sauce-ranch'
);

DELETE FROM menu_sections
WHERE id IN (
    'menu-marstown-section-burger-beef-extras',
    'menu-marstown-section-burger-steak-extras',
    'menu-marstown-section-burger-marsbeef-extras',
    'menu-marstown-section-burger-marssteak-extras'
);

*/
-- ${flyway:timestamp}

-- Sections
INSERT INTO sections (
    id,
    name,
    description
) VALUES (
    'section-sides',
    'Sides',
    ''
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
    'menu-marstown-section-burgers',
    10,
    null,
    null,
    'menu-marstown',
    null,
    'section-burgers'
), (
    'menu-marstown-section-sides',
    20,
    null,
    null,
    'menu-marstown',
    null,
    'section-sides'
), (
    'menu-marstown-section-beverages',
    30,
    null,
    null,
    'menu-marstown',
    null,
    'section-beverages'
);

-- Burger Menu Items
INSERT INTO menu_items (
    id,
    display_order,
    item_id,
    menu_section_id
) VALUES (
    'menu-marstown-item-burger-beef',
    1,
    'burger-beef',
    'menu-marstown-section-burgers'
), (
    'menu-marstown-item-burger-steak',
    2,
    'burger-steak',
    'menu-marstown-section-burgers'
), (
    'menu-marstown-item-burger-marsbeef',
    3,
    'burger-marsbeef',
    'menu-marstown-section-burgers'
), (
    'menu-marstown-item-burger-marssteak',
    4,
    'burger-marssteak',
    'menu-marstown-section-burgers'
);

-- Burger Menu Item Offers
INSERT INTO menu_item_offers (
    id,
    max_quantity,
    min_quantity,
    price,
    price_currency,
    stripe_price_id,
    menu_item_id
) VALUES (
    'menu-marstown-item-burger-beef-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NK1XrAT9USe7yEjxcmOQPcy',
    'menu-marstown-item-burger-beef'
), (
    'menu-marstown-item-burger-steak-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NK82oAT9USe7yEj7FrC2cpF',
    'menu-marstown-item-burger-steak'
), (
    'menu-marstown-item-burger-marsbeef-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NK85oAT9USe7yEjgv1d3hrV',
    'menu-marstown-item-burger-marsbeef'
), (
    'menu-marstown-item-burger-marssteak-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NKM0lAT9USe7yEjDvxCtVcC',
    'menu-marstown-item-burger-marssteak'
);

-- Sides Menu Items
INSERT INTO menu_items (
    id,
    display_order,
    item_id,
    menu_section_id
) VALUES (
    'menu-marstown-item-fries',
    1,
    'fries',
    'menu-marstown-section-sides'
), (
    'menu-marstown-item-fries-large',
    2,
    'fries-large',
    'menu-marstown-section-sides'
), (
    'menu-marstown-item-fries-mars',
    3,
    'fries-mars',
    'menu-marstown-section-sides'
), (
    'menu-marstown-item-fries-mars-large',
    4,
    'fries-mars-large',
    'menu-marstown-section-sides'
);

-- Sides Menu Item Offers

INSERT INTO menu_item_offers (
    id,
    max_quantity,
    min_quantity,
    price,
    price_currency,
    stripe_price_id,
    menu_item_id
) VALUES (
    'menu-marstown-item-fries-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NK1ThAT9USe7yEjWfagWG2p',
    'menu-marstown-item-fries'
), (
    'menu-marstown-item-fries-large-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NKM4WAT9USe7yEji27vJO0V',
    'menu-marstown-item-fries-large'
), (
    'menu-marstown-item-fries-mars-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NKM5FAT9USe7yEjSLzAezg5',
    'menu-marstown-item-fries-mars'
), (
    'menu-marstown-item-fries-mars-large-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NKM5qAT9USe7yEjVBUQtvDy',
    'menu-marstown-item-fries-mars-large'
);

-- Beverages Menu Items

INSERT INTO menu_items (
    id,
    display_order,
    item_id,
    menu_section_id
) VALUES (
    'menu-marstown-item-shake-mars',
    1,
    'shake-mars',
    'menu-marstown-section-beverages'
), (
    'menu-marstown-item-fountain-drink-medium',
    2,
    'fountain-drink-medium',
    'menu-marstown-section-beverages'
), (
    'menu-marstown-item-fountain-drink-large',
    3,
    'fountain-drink-large',
    'menu-marstown-section-beverages'
);

-- Beverages Menu Item Offers

INSERT INTO menu_item_offers (
    id,
    max_quantity,
    min_quantity,
    price,
    price_currency,
    stripe_price_id,
    menu_item_id
) VALUES (
    'menu-marstown-item-shake-mars-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NKMBMAT9USe7yEjs1lD4RKQ',
    'menu-marstown-item-shake-mars'
), (
    'menu-marstown-item-fountain-drink-medium-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NK1W5AT9USe7yEjeaVM43qS',
    'menu-marstown-item-fountain-drink-medium'
), (
    'menu-marstown-item-fountain-drink-large-offer-usd',
    25,
    1,
    0.00,
    'USD',
    'price_1NK7zeAT9USe7yEjoBuYymGL',
    'menu-marstown-item-fountain-drink-large'
);

/* 

ROLLBACK:

DELETE FROM menu_item_offers WHERE id IN (
    'menu-marstown-item-shake-mars-offer-usd',
    'menu-marstown-item-fountain-drink-medium-offer-usd',
    'menu-marstown-item-fountain-drink-large-offer-usd'
);

DELETE FROM menu_items WHERE id IN (
    'menu-marstown-item-shake-mars',
    'menu-marstown-item-fountain-drink-medium',
    'menu-marstown-item-fountain-drink-large'
);

DELETE FROM menu_item_offers WHERE id IN (
    'menu-marstown-item-fries-offer-usd',
    'menu-marstown-item-fries-large-offer-usd',
    'menu-marstown-item-fries-mars-offer-usd',
    'menu-marstown-item-fries-mars-large-offer-usd'
);

DELETE FROM menu_items WHERE id IN (
    'menu-marstown-item-fries',
    'menu-marstown-item-fries-large',
    'menu-marstown-item-fries-mars',
    'menu-marstown-item-fries-mars-large'
);

DELETE FROM menu_item_offers WHERE id IN (
    'menu-marstown-item-burger-beef-offer-usd',
    'menu-marstown-item-burger-steak-offer-usd',
    'menu-marstown-item-burger-marsbeef-offer-usd',
    'menu-marstown-item-burger-marssteak-offer-usd'
);

DELETE FROM menu_items WHERE id IN (
    'menu-marstown-item-burger-beef',
    'menu-marstown-item-burger-steak',
    'menu-marstown-item-burger-marsbeef',
    'menu-marstown-item-burger-marssteak'
);

DELETE FROM menu_sections WHERE id IN (
    'menu-marstown-section-burgers',
    'menu-marstown-section-sides',
    'menu-marstown-section-beverages'
);

DELETE FROM sections WHERE id IN (
    'section-sides'
);

*/
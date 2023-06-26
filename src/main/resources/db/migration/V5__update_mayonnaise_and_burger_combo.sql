UPDATE items
SET name = 'Mayonnaise'
WHERE id = 'mayonaise';

UPDATE menu_item_offers
SET stripe_price_id = 'price_1NKNk4AT9USe7yEj2TcOb8Sw'
WHERE id = 'menu-marstown-item-burger-combo-offer-usd';

/*

ROLLBACK

UPDATE items
SET name = 'Mayonaise'
WHERE id = 'mayonaise';

UPDATE menu_item_offers
SET stripe_price_id = 'price_1NK8E5AT9USe7yEjC5fxVeQx
WHERE id = 'menu-marstown-item-burger-combo-offer-usd';

*/
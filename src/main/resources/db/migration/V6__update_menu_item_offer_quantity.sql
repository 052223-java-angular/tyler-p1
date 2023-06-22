UPDATE menu_item_offers
SET max_quantity = 25
WHERE max_quantity = 1;

UPDATE menu_item_offers
SET stripe_price_id = 'price_1NLuM1AT9USe7yEjTOf0BcTa'
WHERE stripe_price_id = 'price_1NK85oAT9USe7yEjXUituokf';

/* 

ROLLBACK:

UPDATE menu_item_offers
SET max_quantity = 1
WHERE max_quantity = 25;

UPDATE menu_item_offers
SET stripe_price_id = 'price_1NK85oAT9USe7yEjXUituokf';
WHERE stripe_price_id = 'price_1NLuM1AT9USe7yEjTOf0BcTa'

*/
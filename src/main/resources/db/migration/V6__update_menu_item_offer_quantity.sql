UPDATE menu_item_offers
SET max_quantity = 25
WHERE max_quantity = 1;

/* 

ROLLBACK:

UPDATE menu_item_offers
SET max_quantity = 1
WHERE max_quantity = 25;

*/
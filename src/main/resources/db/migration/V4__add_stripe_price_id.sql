UPDATE menu_sections
SET section_id = 'section-burger-combo-sides'
WHERE id = 'menu-marstown-section-burger-combo-sides';

-- UPDATE menu_item_offers with stripe_price_id

UPDATE menu_item_offers
SET stripe_price_id = 'price_1NK8E5AT9USe7yEjC5fxVeQx'
WHERE id = 'menu-marstown-item-burger-combo-offer-usd';

UPDATE menu_item_offers
SET stripe_price_id = 'price_1NK1M3AT9USe7yEjIsfNx9lk'
WHERE id = 'menu-marstown-item-burger-combo-burger-beef-offer-usd';

UPDATE menu_item_offers
SET stripe_price_id = 'price_1NK82oAT9USe7yEjAsqHg2Cq'
WHERE id = 'menu-marstown-item-burger-combo-burger-steak-offer-usd';

UPDATE menu_item_offers
SET stripe_price_id = 'price_1NK85oAT9USe7yEjXUituokf'
WHERE id = 'menu-marstown-item-burger-combo-burger-marsbeef-offer-usd';

UPDATE menu_item_offers
SET stripe_price_id = 'price_1NKM0lAT9USe7yEjUNAPdz6K'
WHERE id = 'menu-marstown-item-burger-combo-burger-marssteak-offer-usd';

UPDATE menu_item_offers
SET stripe_price_id = 'price_1NK7vsAT9USe7yEjY7eTP5aY'
WHERE id = 'menu-marstown-item-burger-combo-extras-lettuce-offer-usd';

UPDATE menu_item_offers
SET stripe_price_id = 'price_1NKM2EAT9USe7yEj1rBBq2KK'
WHERE id = 'menu-marstown-item-burger-combo-extras-tomato-offer-usd';

UPDATE menu_item_offers
SET stripe_price_id = 'price_1NKM2cAT9USe7yEjpuEWtmG8'
WHERE id = 'menu-marstown-item-burger-combo-extras-ketchup-offer-usd';

UPDATE menu_item_offers
SET stripe_price_id = 'price_1NKM2pAT9USe7yEj3sFxj20D'
WHERE id = 'menu-marstown-item-burger-combo-extras-mayonaise-offer-usd';

UPDATE menu_item_offers
SET stripe_price_id = 'price_1NKM2xAT9USe7yEjVG5obUw1'
WHERE id = 'menu-marstown-item-burger-combo-extras-mustard-offer-usd';

UPDATE menu_item_offers
SET stripe_price_id = 'price_1NKM3AAT9USe7yEjfXV085R5'
WHERE id = 'menu-marstown-item-burger-combo-extras-sauce-ranch-offer-usd';

UPDATE menu_item_offers
SET stripe_price_id = 'price_1NK1ThAT9USe7yEjrphOMQ1I'
WHERE id = 'menu-marstown-item-burger-combo-sides-fries-offer-usd';

UPDATE menu_item_offers
SET stripe_price_id = 'price_1NKM8ZAT9USe7yEjxKCjaGeq'
WHERE id = 'menu-marstown-item-burger-combo-sides-fries-large-offer-usd';

UPDATE menu_item_offers
SET stripe_price_id = 'price_1NKM6YAT9USe7yEju6Yb4lqJ'
WHERE id = 'menu-marstown-item-burger-combo-sides-fries-mars-offer-usd';

UPDATE menu_item_offers
SET stripe_price_id = 'price_1NKM5qAT9USe7yEjpipkvhq4'
WHERE id = 'menu-marstown-item-burger-combo-sides-fries-mars-large-offer-usd';

UPDATE menu_item_offers
SET stripe_price_id = 'price_1NKMBMAT9USe7yEjp0JeY3Bg'
WHERE id = 'menu-marstown-item-burger-combo-beverages-shake-mars-offer-usd';

UPDATE menu_item_offers
SET stripe_price_id = 'price_1NK1W5AT9USe7yEj4XSNfiKG'
WHERE id = 'menu-marstown-item-burger-combo-beverages-fountain-drink-medium-offer-usd';

UPDATE menu_item_offers
SET stripe_price_id = 'price_1NK7zeAT9USe7yEj2k2kjvYh'
WHERE id = 'menu-marstown-item-burger-combo-beverages-fountain-drink-large-offer-usd';


/*

ROLLBACK

UPDATE menu_sections
SET section_id = 'section-burger-extras'
WHERE id = 'menu-marstown-section-burger-combo-sides';

UPDATE menu_item_offers
SET stripe_price_id = NULL
WHERE id IN (
    'menu-marstown-item-burger-combo-offer-usd',
    'menu-marstown-item-burger-combo-burger-beef-offer-usd',
    'menu-marstown-item-burger-combo-burger-steak-offer-usd',
    'menu-marstown-item-burger-combo-burger-marsbeef-offer-usd',
    'menu-marstown-item-burger-combo-burger-marssteak-offer-usd',
    'menu-marstown-item-burger-combo-extras-lettuce-offer-usd',
    'menu-marstown-item-burger-combo-extras-tomato-offer-usd',
    'menu-marstown-item-burger-combo-extras-ketchup-offer-usd',
    'menu-marstown-item-burger-combo-extras-mayonaise-offer-usd',
    'menu-marstown-item-burger-combo-extras-mustard-offer-usd',
    'menu-marstown-item-burger-combo-extras-sauce-ranch-offer-usd',
    'menu-marstown-item-burger-combo-sides-fries-offer-usd',
    'menu-marstown-item-burger-combo-sides-fries-large-offer-usd',
    'menu-marstown-item-burger-combo-sides-fries-mars-offer-usd',
    'menu-marstown-item-burger-combo-sides-fries-mars-large-offer-usd',
    'menu-marstown-item-burger-combo-beverages-shake-mars-offer-usd',
    'menu-marstown-item-burger-combo-beverages-fountain-drink-medium-offer-usd',
    'menu-marstown-item-burger-combo-beverages-fountain-drink-large-offer-usd'
);

*/
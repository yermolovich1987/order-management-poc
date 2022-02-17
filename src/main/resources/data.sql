insert into received_order (id, version, status, order_number, comment) values (1, 0, 'DRAFT', 'order_1', 'Some comment');
insert into order_item (id, order_id, external_item_id, quantity) values
    (1, 1, 'item_1', 1),
    (2, 1, 'item_2', 2),
    (3, 1, 'item_3', 3);
insert into received_order (id, version, status, order_number, comment) values (2, 0, 'APPROVED', 'order_2', 'Some comment');
insert into order_item (id, order_id, external_item_id, quantity) values
    (4, 2, 'item_4', 4),
    (5, 2, 'item_5', 5),
    (6, 2, 'item_6', 6);

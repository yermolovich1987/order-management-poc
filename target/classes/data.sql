insert into "order" (id, version, status, order_number, comment) values (1, 0, 'DRAFT', 'order_1', 'Some comment');
insert into order_item (id, order_id, external_item_id, quantity) values (1, 1, 'item_1', 1);
insert into order_item (id, order_id, external_item_id, quantity) values (2, 1, 'item_2', 2);
insert into order_item (id, order_id, external_item_id, quantity) values (3, 1, 'item_3', 2);
--insert into "order" (version, status, order_number, comment) values (0, 'APPROVED', 'order_2', '');
--insert into order_item (external_item_id, quantity) values ('item_1', 11);
--insert into order_item (external_item_id, quantity) values ('item_2', 22);
--insert into order_item (external_item_id, quantity) values ('item_3', 33);

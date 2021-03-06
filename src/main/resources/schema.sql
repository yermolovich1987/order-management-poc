CREATE TABLE received_order (
    id BIGINT NOT NULL AUTO_INCREMENT,
    status VARCHAR(30) NOT NULL,
    version BIGINT NOT NULL,
    order_number VARCHAR(128) NOT NULL,
    comment VARCHAR(1000),
    created_date TIMESTAMP WITH TIME ZONE,
    created_by VARCHAR(20),
    updated_date TIMESTAMP WITH TIME ZONE,
    updated_by VARCHAR(20),
    PRIMARY KEY (id),
    CONSTRAINT uc_order_number UNIQUE (order_number)
);

CREATE TABLE order_item (
    id BIGINT NOT NULL AUTO_INCREMENT,
    order_id BIGINT,
    external_item_id VARCHAR(30) NOT NULL,
    quantity INTEGER NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_order_item_to_order FOREIGN KEY (order_id) REFERENCES received_order(id)
);
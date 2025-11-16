CREATE TABLE devices (
    id BIGSERIAL PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    max_hourly_energy_consumption VARCHAR(50),
    user_id BIGINT
);

DROP TABLE devices;

SELECT * 
FROM devices

INSERT INTO devices (description, address, max_hourly_energy_consumption, user_id) VALUES
    -- Dispozitive pentru utilizatorii de tip Administrator
    ('Office Printer', '123 Corporate Ave, Suite 100', '1.5', 1),
    ('Conference Room Projector', '123 Corporate Ave, Suite 100', '0.6', 5),
    ('Main Server Rack', '789 Tech Park, Room 200', '5.0', 7),
    ('Admin Office AC', '321 Administration Rd, Office 10', '3.5', 15),

    -- Dispozitive pentru utilizatorii de tip Client
    ('Living Room AC', '123 Main St, Apt 4B', '2.5', 2),
    ('Bedroom Heater', '123 Main St, Apt 4B', '3.0', 2),
    ('Kitchen Refrigerator', '123 Main St, Apt 4B', '1.2', 2),
    ('Garage EV Charger', '123 Main St, Garage', '7.5', 3),
    ('Home Security System', '123 Main St, Apt 4B', '0.3', 3),
    ('Smart TV', '456 Elm St, Suite 12', '0.4', 4),
    ('Kitchen Oven', '456 Elm St, Suite 12', '2.0', 4),
    ('Bedroom Air Purifier', '456 Elm St, Suite 12', '0.5', 6),
    ('Smart Washing Machine', '789 Oak Dr, House 2', '1.8', 8),
    ('Living Room Lights', '789 Oak Dr, House 2', '0.2', 8),
    ('Gaming Console', '789 Oak Dr, House 2', '0.9', 10),
    ('Home Thermostat', '101 Pine Ave, Apt 3C', '0.1', 11),
    ('Water Heater', '101 Pine Ave, Apt 3C', '2.7', 11),
    ('Office Computer', '102 Maple St, Apt 2D', '0.8', 12),
    ('Electric Stove', '103 Cedar Rd, Apt 5A', '2.2', 13),
    ('Smart Door Lock', '103 Cedar Rd, Apt 5A', '0.1', 13),
    ('Coffee Maker', '104 Birch St, Apt 7B', '0.5', 14),
    ('Living Room Speaker System', '104 Birch St, Apt 7B', '0.4', 14),
    ('Dishwasher', '104 Birch St, Apt 7B', '1.6', 14);
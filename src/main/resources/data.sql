INSERT INTO users (id, active, email, first_name, last_name, password,address)
VALUES
    (1, 1, 'admin@example.com', 'Admin', 'Adminov', '95c1933b8ffe84f085f2839899d1673260be58dbd9c2c787ac35515805502c996417596dae9a92880aaa50a046fc7151', 'Sofia'),
    (2, 1, 'user@example.com', 'User', 'Userov', '95c1933b8ffe84f085f2839899d1673260be58dbd9c2c787ac35515805502c996417596dae9a92880aaa50a046fc7151', 'Plovdiv');


INSERT INTO roles (`id`, `role`)
VALUES
    (1, 'ADMIN'),
    (2, 'SERVICE'),
    (3, 'USER');

INSERT INTO users_roles(`user_id`, `role_id`)
VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (2, 3);
INSERT INTO services(id, address, email, name, password, phone_number, role)
VALUES (1, '32 Al. Malinov Street, Sofia, Bulgaria', 'serviceOne@example.com', 'FullRepairService EOOD','95c1933b8ffe84f085f2839899d1673260be58dbd9c2c787ac35515805502c996417596dae9a92880aaa50a046fc7151', '988455211', 2),
       (2, '52 Tsar Boris III, Sofia, Bulgaria', 'serviceTwo@example.com', 'SomeOtherService LTD','95c1933b8ffe84f085f2839899d1673260be58dbd9c2c787ac35515805502c996417596dae9a92880aaa50a046fc7151', '988411211', 2);

INSERT INTO cars(id, engine_type,  kilometers, make, model, registration_number, uuid, vehicle_type, vin, year, user_id)
VALUES (1, 'DIESEL', 231500, 'SUBARU', 'LEGACY', 'CB0117TX', '08c71152-c552-42e7-b094-f510ff44e9cb', 'CAR','1GKFK13067J306859', 2008, 1),
 (2, 'GASOLINE', 221500, 'BMW', '3', 'CB0127TX', 'c558a80a-f319-4c10-95d4-4282ef745b44', 'CAR','KMHDH4AE2FU375283', 2008, 2),
(3, 'ELECTRIC', 241500, 'AUDI', 'Q7', 'CB3117TX', '1ad1fccc-d279-46a0-8980-1d91afd6ba67', 'CAR','1FDKF37G9VEB31560', 2008, 1);

INSERT INTO repairs(id, description, mileage, repair, repair_date, uuid, car_id, service_id)
VALUES (1, 'Mobile 4liters 10X40', 230000, 'OilChange', '2023-10-18', 'af7c1fe6-d669-414e-b066-e9733f0de7a8', 1 , 1  ),
 (2, 'Mobile 4liters 10X40', 230000, 'TireReplacement', '2023-10-15', 'af7c1fe6-d669-414e-b066-e9733f0de7a8', 1 , 1  ),
 (3, 'Mobile 4liters 10X40', 230000, 'AntifreezeAndCoolingSystem', '2023-10-22', 'af7c1fe6-d669-414e-b066-e9733f0de7a8', 1 , 1  );



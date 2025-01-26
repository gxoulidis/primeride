INSERT INTO citizen (afm, first_name, last_name, email, password) VALUES
('123456789', 'Giannis', 'Papadopoulos', 'giannis.papadopoulos@example.com', 'qwerty'),
('987654321', 'Dimitris', 'Dimitriadis', 'dimitris.dimitriadis@example.com', 'qwerty'),
('012345678', 'Giorgos', 'Georgiadis', 'giorgos.georgiadis@example.com', 'qwerty');


INSERT INTO dealership (afm, dealership_name, owner_name, email, password) VALUES
('111222333', 'Prime Cars', 'Aristotelis Lidakis', 'aristotelis.lidakis@primecars.com', 'qwerty'),
('444555666', 'AutoMart', 'Periklis Thomopoulos', 'periklis.thomopoulos@automart.com', 'qwerty'),
('777888999', 'The Best Cars in Town', 'Giorgos Giorgakis', 'giorgos.giorgakis@thebestcarsintown.com', 'qwerty');

INSERT INTO car (brand, model, fuel_type, engine_type, seats, price, additional_info, number_of_cars, dealership_afm) VALUES
('Toyota', 'Corolla', 'PETROL', 'INLINE4', 5, 18000.00, 'Reliable sedan', 5, '111222333'),
('Ford', 'Focus', 'DIESEL', 'INLINE4', 5, 20000.00, 'Compact car with great fuel economy', 3, '111222333'),
('Tesla', 'Model 3', 'ELECTRIC', 'ELECTRIC', 5, 35000.00, 'Premium electric vehicle', 2, '444555666');

INSERT INTO booking (date, days_of_booking, citizen_afm, car_id) VALUES
('2025-01-15 10:30:00', 3, '123456789', 1),
('2025-01-16 14:00:00', 7, '123456789', 3),
('2025-01-17 09:00:00', 2, '987654321', 2);
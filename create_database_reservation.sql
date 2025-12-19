Create database reservation;

use reservation;

CREATE TABLE flight (
    flight_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    airline_name VARCHAR(100) NOT NULL,
    departure_time DATETIME NOT NULL,
    arrival_time DATETIME NOT NULL,
    origin VARCHAR(100) NOT NULL,
    destination VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL
);

use reservation;
INSERT INTO flight (airline_name, departure_time, arrival_time, origin, destination, price) VALUES
('Air Canada', '2025-11-01 08:00:00', '2025-11-01 11:00:00', 'Toronto', 'Vancouver', 350.00),
('WestJet', '2025-11-02 14:30:00', '2025-11-02 17:30:00', 'Montreal', 'Calgary', 400.00),
('Air Canada', '2025-11-03 06:15:00', '2025-11-03 09:45:00', 'Toronto', 'Ottawa', 150.00),
('WestJet', '2025-11-03 12:00:00', '2025-11-03 15:30:00', 'Vancouver', 'Calgary', 220.00),
('Air Canada', '2025-11-04 09:00:00', '2025-11-04 12:30:00', 'Toronto', 'Montreal', 180.00),
('WestJet', '2025-11-05 07:45:00', '2025-11-05 10:30:00', 'Calgary', 'Toronto', 360.00),
('Air Canada', '2025-11-06 16:00:00', '2025-11-06 18:30:00', 'Ottawa', 'Toronto', 140.00),
('WestJet', '2025-11-07 10:00:00', '2025-11-07 13:00:00', 'Montreal', 'Vancouver', 420.00),
('Air Canada', '2025-11-08 13:00:00', '2025-11-08 16:00:00', 'Toronto', 'Calgary', 380.00),
('WestJet', '2025-11-09 09:30:00', '2025-11-09 12:30:00', 'Vancouver', 'Montreal', 450.00);


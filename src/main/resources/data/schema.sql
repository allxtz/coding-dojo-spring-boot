CREATE TABLE weather (
    id INT NOT NULL AUTO_INCREMENT,
    city VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    temperature NUMERIC(5, 2),
    PRIMARY KEY (id)
);
ALTER TABLE weather
    ADD weather_api_id INT NULL;
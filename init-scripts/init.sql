CREATE TABLE sharded_demo (
    id TEXT PRIMARY KEY,
    tripduration INT,
    start_station_id INT,
    start_station_name TEXT,
    end_station_id INT,
    end_station_name TEXT,
    bikeid INT,
    usertype TEXT,
    birth_year INT,
    gender INT,
    start_station_location TEXT,
    end_station_location TEXT,
    start_time TIMESTAMP,
    stop_time TIMESTAMP
);


COPY sharded_demo(id, tripduration, start_station_id, start_station_name, end_station_id, end_station_name, bikeid, usertype, birth_year, gender, start_station_location, end_station_location, start_time, stop_time)
FROM '/docker-entrypoint-initdb.d/biketrips.csv'
DELIMITER ','
CSV HEADER;
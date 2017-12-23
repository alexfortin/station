create table if not exists station (
station_id number auto_increment primary key,
name varchar(100) unique,
hd_enabled boolean,
call_sign varchar(100)
);


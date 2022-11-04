insert into airlines(id,name,hold_bag_price,cabin_bag_price,small_bag_price,age_less_than2price,age_less_than9price,age_more_than9price) values(1,'Ryanair',70.50,19.99,0,0,10,20);
insert into airlines(id,name,hold_bag_price,cabin_bag_price,small_bag_price,age_less_than2price,age_less_than9price,age_more_than9price) values(2,'Easyjet',50.50,14.99,0,0,15,15);

insert into airports(id,name) values(1,'Funchal');
insert into airports(id,name) values(2,'Lisbon');
insert into airports(id,name) values(3,'Seville');
insert into airports(id,name) values(4,'Porto');
insert into airports(id,name) values(5,'Madrid');

insert into flights(id,airline_id,origin_id,destination_id,departure,arrival,flight_number,flight_duration_minutes,price,total_seats,available_seats,total_small_bags,available_small_bags,total_cabin_bags,available_cabin_bags,total_hold_bags,available_hold_bags) values(1,2,1,2,'2022-11-23 15:30:00','2022-11-23 17:00:00','E1',90,50.00,100,10,100,10,50,25,120,75);

insert into flights(id,airline_id,origin_id,destination_id,departure,arrival,flight_number,flight_duration_minutes,price,total_seats,available_seats,total_small_bags,available_small_bags,total_cabin_bags,available_cabin_bags,total_hold_bags,available_hold_bags) values(2,1,1,2,'2022-11-23 16:30:00','2022-11-23 18:00:00','R2',90,50.00,100,10,100,10,50,25,120,75);

insert into flights(id,airline_id,origin_id,destination_id,departure,arrival,flight_number,flight_duration_minutes,price,total_seats,available_seats,total_small_bags,available_small_bags,total_cabin_bags,available_cabin_bags,total_hold_bags,available_hold_bags) values(3,1,1,2,'2022-11-23 12:30:00','2022-11-23 14:00:00','R3',90,50.00,100,10,100,10,50,25,120,75);

insert into flights(id,airline_id,origin_id,destination_id,departure,arrival,flight_number,flight_duration_minutes,price,total_seats,available_seats,total_small_bags,available_small_bags,total_cabin_bags,available_cabin_bags,total_hold_bags,available_hold_bags) values(4,1,2,1,'2022-11-25 16:30:00','2022-11-25 18:00:00','R4',90,50.00,100,10,100,10,50,25,120,75);

insert into flights(id,airline_id,origin_id,destination_id,departure,arrival,flight_number,flight_duration_minutes,price,total_seats,available_seats,total_small_bags,available_small_bags,total_cabin_bags,available_cabin_bags,total_hold_bags,available_hold_bags) values(5,2,2,1,'2022-11-25 12:30:00','2022-11-25 14:00:00','E5',90,50.00,100,10,100,10,50,25,120,75);
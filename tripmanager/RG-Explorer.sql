show databases;
use rgexplorer;
show tables;
select * from usercredentials;
select * from userTripDetails;
select * from travellerinformantion;
create table userTripDetails(username varchar (20) , mobileNumber varchar(15) , Package varchar(30) , Package_Type varchar(30) , from_Date varchar(20) , to_date varchar(20) , Payment_Status varchar(20) , Mode_of_Payment varchar (20) , Amount varchar(20)); 
alter table userTripDetails add column Country varchar(30) after Package ;
alter table userTripDetails change column Country Destination varchar(30);
delete from usercredentials where mail = 'rakeshspartans007@gmail.com';
delete from userTripDetails where username = 'Rakesh';
truncate travellerinformantion;
truncate userTripDetails;
truncate usercredentials;
select * from usercredentials;
select * from userTripDetails;
select * from travellerinformantion;
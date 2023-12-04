create table if not exists team(
id bigint primary key,
name varchar(25),
is_active boolean,
created_by varchar(25),
created_date timestamp,
updated_by varchar(25),
updated_date timestamp
);

create table if not exists employee_type(
id bigint primary key,
designation varchar(25),
is_active boolean,
created_by varchar(25),
created_date timestamp,
updated_by varchar(25),
updated_date timestamp
);



create table if not exists quarter(
	id bigint primary key,
	name varchar(25),
	year varchar(10),
	is_active boolean,
	created_by varchar(25),
	created_date timestamp,
	updated_by varchar(25),
	updated_date date);

create table if not exists frequency(
		id int primary key,
		name varchar(25),
		is_active boolean ,
		created_by varchar(25),
		created_date timestamp,
		updated_by varchar(25),
		updated_date date);
	
	create table if not exists awards
(
	id BigInt primary key,
	name varchar(20) not null,
	frequency_id int,
	foreign key (frequency_id) references frequency(id),
	description varchar(100) not null,
	is_active boolean ,
	created_by varchar(25),
	created_date TimeStamp,
	updated_by varchar(25),
	updated_date TimeStamp
);
	
	create table  if not exists questions(
	id  bigint primary key,
	question varchar(25) not null ,
	award_id BIGINT,
	FOREIGN KEY (award_id) references awards(id),
	is_active boolean,
	created_by varchar(25),
	created_date date,
	updated_by varchar(25) ,
	updated_date  date);
	

	



create table  if not exists award_employee_types(
	id bigint primary key,
	eligible_employee_type_id bigint, 
	nominee_employee_type_id bigint ,
	is_active boolean,
	created_by varchar(25),
	created_date timestamp,
	updated_by varchar(25),
	updated_date date,
	foreign key (eligible_employee_type_id ) references employee_type(id),
	foreign key(nominee_employee_type_id)references employee_type(id));




create table if not exists  employee(
	id bigint primary key,
    first_name varchar(20),
    last_name varchar(20),
    email varchar(40),
    phone_number varchar(20),
    team_id bigint,
    employee_type_id bigint,
    joining_date timestamp,
    employee_id varchar(20),
    manager_id bigint,
    password varchar(20),
    first_time_user bit,
    otp_gen varchar(6),
    otp_gen_time bigint,
    is_active boolean,
    created_by varchar(25),
    created_date timestamp,
    updated_by varchar(25),
    updated_date timestamp,
    FOREIGN KEY (team_id) REFERENCES team(id),
    FOREIGN KEY (employee_type_id) REFERENCES employee_type(id)
);
create table if not exists awardees
(
	id bigint primary key,
	employee_id bigint,
	foreign key (employee_id) references employee(id),
	award_id bigint,
	foreign key (award_id) references awards(id),
	quarter_id bigint,
	foreign key (quarter_id) references quarter(id),
	is_active boolean,
	created_by varchar(25),
	created_date TimeStamp,
	updated_by varchar(25),
	updated_date TimeStamp
);


CREATE TABLE  if not exists nominees (
    id int primary key,
    employee_id bigint,
    nominated_by bigint,
    award_id bigint,
    quarter_id bigint,
    average_rating float,
    comments varchar(15) not null,
    FOREIGN KEY (employee_id) REFERENCES employee(id),
    FOREIGN KEY (nominated_by) REFERENCES employee(id),
    FOREIGN KEY (award_id) REFERENCES awards(id),
    FOREIGN KEY (quarter_id) REFERENCES quarter(id),
    is_active boolean ,
    created_by varchar(25) ,
    created_date date ,
    updated_by varchar(25) ,
    updated_date  date 
    );



























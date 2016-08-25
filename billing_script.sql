create table `servercis`.far_billing_parameter
(
	param_code VARCHAR(10),
	param_name VARCHAR(30),
	param_value DOUBLE PRECISION,
	param_type VARCHAR(30),
	display_option VARCHAR(30),
	enable VARCHAR(5)
);

create table `servercis`.far_customer_dtl
(
	bill_no VARCHAR(30) not null,
	txn_date TIMESTAMP not null,
	item_cd VARCHAR(30) not null,
	item_desc VARCHAR(300),
	item_amt DECIMAL(10),
	quantity DECIMAL(5),
	location VARCHAR(30),
	customer_id VARCHAR(30),
	primary key (bill_no, txn_date, item_cd)
);

create table `servercis`.far_customer_hdr
(
	customer_id VARCHAR(30) not null,
	bill_no VARCHAR(30) not null,
	txn_date TIMESTAMP not null,
	item_desc VARCHAR(300),
	item_amt DECIMAL(10),
	quantity DECIMAL(5),
	location VARCHAR(30),
	order_no VARCHAR(30),
	payment VARCHAR(10),
	amt_paid DOUBLE PRECISION,
	primary key (customer_id, bill_no, txn_date)
);

create table `servercis`.far_customer_ledger
(
	customer_id VARCHAR(30) not null,
	txn_date TIMESTAMP not null,
	bill_no VARCHAR(30) not null,
	bill_desc VARCHAR(300),
	bill_amt DECIMAL(10),
	location VARCHAR(30),
	pay_method VARCHAR(30),
	cr_amt_1 DECIMAL(10),
	dr_amt_1 DECIMAL(10),
	cr_amt_2 DECIMAL(10),
	dr_amt_2 DECIMAL(10),
	cr_amt_3 DECIMAL(10),
	dr_amt_3 DECIMAL(10),
	cr_amt_4 DECIMAL(10),
	dr_amt_4 DECIMAL(10),
	cr_amt_5 DECIMAL(10),
	dr_amt_5 DECIMAL(10),
	cr_amt_6 DECIMAL(10),
	dr_amt_6 DECIMAL(10),
	cr_amt_7 DECIMAL(10),
	dr_amt_7 DECIMAL(10),
	cr_amt_8 DECIMAL(10),
	dr_amt_8 DECIMAL(10),
	cr_amt_9 DECIMAL(10),
	dr_amt_9 DECIMAL(10),
	cr_amt_10 DECIMAL(10),
	dr_amt_10 DECIMAL(10),
	cr_amt_11 DECIMAL(10),
	dr_amt_11 DECIMAL(10),
	cr_amt_12 DECIMAL(10),
	dr_amt_12 DECIMAL(10),
	cr_amt_13 DECIMAL(10),
	dr_amt_13 DECIMAL(10),
	primary key (customer_id, txn_date, bill_no)
);

create table `servercis`.far_last_seq_no
(
	Id INT not null primary key,
	module_name VARCHAR(100),
	year DATE,
	last_seq_no VARCHAR(10)
);

create table `servercis`.far_miscellaneous_item
(
	Id INT not null primary key,
	item_code VARCHAR(30),
	item_desc VARCHAR(300),
	buying_price DOUBLE PRECISION,
	selling_price DOUBLE PRECISION,
	discount INT
);

create table `servercis`.far_year_end_parameter
(
	code VARCHAR(10),
	process_status INT,
	processed_year VARCHAR(5)
);

insert into far_miscellaneous_item (id, item_code, item_desc, buying_price, selling_price, discount) values ('1', 'RG00001', 'Consultation fee (Student)', '1', '1', '0');
insert into far_miscellaneous_item (id, item_code, item_desc, buying_price, selling_price, discount) values ('2', 'RG00002', 'Consultation fee (Staff)', '2', '5', '0');
insert into far_miscellaneous_item (id, item_code, item_desc, buying_price, selling_price, discount) values ('3', 'RG00003', 'Consultation fee (Other)', '5', '5', '0');

insert into far_last_seq_no (id, module_name, last_seq_no) values ('1', 'B', '1');
insert into far_last_seq_no (id, module_name, last_seq_no) values ('2', 'R', '1');
insert into far_last_seq_no (id, module_name, last_seq_no) values ('3', 'I', '1');

insert into far_billing_parameter (param_code, param_name, param_value, param_type, display_option, enable) values ('BP001', 'GST', '0.06', 'double', 'checkbox', 'no');
insert into far_billing_parameter (param_code, param_name, param_value, param_type, display_option, enable) values ('BP002', 'Service Charge', '0.1', 'double', 'checkbox', 'no');
insert into far_billing_parameter (param_code, param_name, param_value, param_type, display_option, enable) values ('BP003', 'Discount', '0', 'double', 'checkbox', 'no');

insert into far_year_end_parameter (code, process_status, processed_year) values ('yep', '0', '2015');
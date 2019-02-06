drop table if exists rsvp;
drop table if exists guest;

drop table if exists flyway_testing_migrate;
drop table if exists flyway_schema_history;

create table rsvp (
  uuid char(36) NOT NULL,
  guest_count int,
  PRIMARY KEY (uuid));

create table guest(
    uuid char(36) NOT NULL,
    rsvp_id char(36),
    rsvp_type varchar(200),
    first_name varchar(200),
    last_name varchar(200),
    email varchar(200),
    mehndiAccepted varchar(200),
    receptionAccepted varchar(200),
    civilAccepted varchar(200),
    whiteWine varchar(200),
    redWine varchar(200),
    roseWine varchar(200),
    beer varchar(200),
    vodka varchar(200),
    gin varchar(200),
    whiskey varchar(200),
    rum varchar(200),
    disaronno varchar(200),
    nonAlcoholicOption varchar(200),
    vegan varchar(200),
    jain varchar(200),
    rsvp_date varchar(200),
    ip_address varchar(200),
    PRIMARY KEY (uuid));
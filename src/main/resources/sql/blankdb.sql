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
    first_name varchar(200),
    last_name varchar(200),
    email varchar(200),
    mehndiAccepted int,
    receptionAccepted int,
    civilAccepted int,
    PRIMARY KEY (uuid));
drop table if exists definition;
drop table if exists record_definition;

drop table if exists flyway_testing_migrate;
drop table if exists flyway_schema_history;

create table definition (
  uuid char(36) NOT NULL,
  name varchar(200),
  calculated bool,
  PRIMARY KEY (uuid));

create table record_definition (
  uuid char(36) NOT NULL,
  name varchar(200),
  PRIMARY KEY (uuid));


-- Joining mapping table between the many to many relationship of definition and record definition above
create table record_definition_map (
  uuid char(36) NOT NULL,
  name varchar(200),
  PRIMARY KEY (uuid));
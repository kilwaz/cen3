drop table if exists definition;
drop table if exists record_definition;
drop table if exists definition_in_record;
drop table if exists definition_group;

drop table if exists flyway_testing_migrate;
drop table if exists flyway_schema_history;

create table definition (
  uuid char(36) NOT NULL,
  name varchar(200),
  expression varchar(1000),
  calculated char(1),
  definition_type int,
  PRIMARY KEY (uuid));

create table record_definition (
  uuid char(36) NOT NULL,
  name varchar(200),
  PRIMARY KEY (uuid));

-- Joining mapping table between the many to many relationship of definition and record definition above
create table definition_group (
  uuid char(36) NOT NULL,
  definition_id char(36),
  record_definition_id char(36),
  PRIMARY KEY (uuid));

create table record_definition_child (
  uuid char(36) NOT NULL,
  record_definition_parent_uuid char(36),
  record_definition_child_uuid char(36)
  );
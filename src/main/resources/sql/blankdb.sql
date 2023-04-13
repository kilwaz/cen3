drop table definition;
drop table record_definition;
-- drop table definition_in_record;
drop table definition_group;
drop table record_definition_child;

drop table custom_imports;

-- drop table flyway_testing_migrate;
-- drop table flyway_schema_history;

create table definition (
  uuid char(36) NOT NULL,
  name varchar(200),
  expression varchar(1000),
  calculated int,
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
  record_definition_child_uuid char(36),
  PRIMARY KEY (uuid)
  );



create table custom_imports (
    uuid char(36) NOT NULL,




    PRIMARY KEY (uuid)
);


REC_EMPLOYEE_RAW -- imported values straight into here - taps import
REC_EMPLOYEE_CALC -- used as reference for other calculations - mid view - all calc go into static, maybe only calculated fields go into here, static raw go straight to end if they are needed
REC_EMPLOYEE_STATIC -- generated from all - final values only - final view



--static for whole table?




-- Columns say what base data tables have in them
-- Imports only import into base data tables, linking column names to specific base tables/columns

-- Definitions can be used to
-- Definitions are linked to a record, this could be the base data one, or at least there could be a flag against a definition for it


-- base_data - can be split into different base tables, related to imports / grouping
--   formulas will need to be able to grab from any table
-- static_data - is the only place front end values come from, is strictly read only apart from what the formulas output

-- column grouping to help with organisation



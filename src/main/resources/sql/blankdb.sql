drop table definition;
drop table record_definition;
drop table definition_group;
drop table defined_template;
drop table record_definition_child;

drop table custom_imports;

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
  primary_key char(36),
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

drop table defined_template;
create table defined_template (
  uuid char(36) NOT NULL,
  name varchar(1000),
  primary_key char(36),
  PRIMARY KEY (uuid));

drop table defined_bridge;
create table defined_bridge (
  uuid char(36) NOT NULL,
  column_title varchar(1000),
  definition_id char(36),
  record_definition_id char(36),
  defined_template_id char(36),
  PRIMARY KEY (uuid));






-- Columns say what base data tables have in them
-- Imports only import into base data tables, linking column names to specific base tables/columns

-- Definitions can be used to
-- Definitions are linked to a record, this could be the base data one, or at least there could be a flag against a definition for it


-- base_data - can be split into different base tables, related to imports / grouping
--   formulas will need to be able to grab from any table
-- static_data - is the only place front end values come from, is strictly read only apart from what the formulas output

-- column grouping to help with organisation



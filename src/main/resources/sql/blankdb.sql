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
  context_type int,
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

drop table worksheet_config;
create table worksheet_config (
    uuid char(36) NOT NULL,
    name varchar(200),
    PRIMARY KEY (uuid));

drop table worksheet_config_details;
create table worksheet_config_details (
  uuid char(36) NOT NULL,
  worksheet_config_id char(36),
  column_title varchar(200),
  definition_id char(36),
  column_type varchar(200),
  column_order int,
  text_align varchar(200),
  colour varchar(200),
  PRIMARY KEY (uuid));

drop table application_parameters;
create table application_parameters (
    uuid char(36) NOT NULL,
    name varchar(200),
    value varchar(200),
    PRIMARY KEY (uuid));

drop table hierarchy;
create table hierarchy (
     uuid char(36) NOT NULL,
     node_reference varchar(200),
     parent_reference varchar(200),
     tree_uuid char(36),
     employee_uuid char(36),
     PRIMARY KEY (uuid));

drop table hierarchy_nodes;
create table hierarchy_nodes (
    uuid char(36) NOT NULL,
    node_reference varchar(200),
    node_name varchar(200),
    employee_uuid char(36),
    parent_reference varchar(200),
    tree_uuid char(36),
    path_from_top varchar(200),
    is_leaf number,
    node_type varchar(200),
    PRIMARY KEY (uuid));

drop table hierarchy_nodes_calc;
create table hierarchy_nodes_calc (
    uuid char(36) NOT NULL,
    node_reference varchar(200),
    node_name varchar(200),
    employee_uuid char(36),
    parent_reference varchar(200),
    tree_uuid char(36),
    path_from_top varchar(200),
    is_leaf number,
    node_type varchar(200),
    PRIMARY KEY (uuid));

drop table hierarchy_trees;
create table hierarchy_trees (
    uuid char(36) NOT NULL,
    name varchar(200),
    PRIMARY KEY (uuid));

drop table event_log;
create table event_log (
    uuid char(36) NOT NULL,
    user_uuid char(36),
    session_uuid char(36),
    emplid varchar(200),
    event_type varchar(200),
    role_uuid char(36),
    before varchar(200),
    after varchar(200),
    data_reference varchar(200),
    audited_employee_uuid char(36),
    event_page varchar(200),
    node_reference varchar(200),
    tree_uuid char(36),
    event_comment varchar(200),
    event_date date,
    PRIMARY KEY (uuid));

drop table formula_context;
create table formula_context (
    uuid char(36) NOT NULL,
    name varchar(200),
    PRIMARY KEY (uuid));

drop table formula_context_group;
create table formula_context_group (
    uuid char(36) NOT NULL,
    definition_id char(36),
    formula_context_id char(36),
    PRIMARY KEY (uuid));





-- Columns say what base data tables have in them
-- Imports only import into base data tables, linking column names to specific base tables/columns

-- Definitions can be used to
-- Definitions are linked to a record, this could be the base data one, or at least there could be a flag against a definition for it


-- base_data - can be split into different base tables, related to imports / grouping
--   formulas will need to be able to grab from any table
-- static_data - is the only place front end values come from, is strictly read only apart from what the formulas output

-- column grouping to help with organisation



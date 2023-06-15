create or replace PROCEDURE tree_populate(tree_uuid_to_calc CHAR) is
    tree_name VARCHAR2(128 byte);
BEGIN
    --    dbms_output.enable;
--    dbms_output.put_line  (user || ' Tables in the database:');

    EXECUTE IMMEDIATE 'TRUNCATE TABLE hierarchy_nodes_calc';

    Select t.name into tree_name from hierarchy_trees t where t.uuid = tree_uuid_to_calc; -- Find tree name for variable

    -- Initial Populate of all nodes taken from hierarchy table
    INSERT INTO hierarchy_nodes_calc ( uuid, node_reference, parent_reference,tree_uuid, node_type)
    select randomUUID as uuid, node_reference, parent_reference,tree_uuid, 'Employee' from (
                                                                                               SELECT DISTINCT node_reference, coalesce(parent_reference, tree_name) as parent_reference, tree_uuid from hierarchy where tree_uuid = tree_uuid_to_calc
                                                                                           );

    insert into hierarchy_nodes_calc (uuid, node_reference, node_name, parent_reference, tree_uuid, node_type) values (randomUUID, tree_name, tree_name, null, tree_uuid_to_calc, 'Node');   -- Node at top of the tree
    insert into hierarchy_nodes_calc (uuid, node_reference, node_name, parent_reference, tree_uuid, node_type) values (randomUUID, 'Unallocated', 'Unallocated', tree_name, tree_uuid_to_calc, 'Node');  -- Unallocated node for tree

    -- Generate path from top 1st time
    MERGE INTO hierarchy_nodes_calc hnc
    USING
        (with ilv as (SELECT distinct LEVEL-1 AS DEPTH#
                                    ,node_reference
                                    ,tree_uuid
                                    ,CONNECT_BY_ISLEAF AS is_leaf
                                    ,SUBSTR(SYS_CONNECT_BY_PATH(node_reference,'.'),2,999) as path_from_top
                      FROM (select hnc.* from hierarchy_nodes_calc hnc where tree_uuid = tree_uuid_to_calc)
                      START WITH node_reference = tree_name
                      CONNECT BY PRIOR node_reference = parent_reference
        )
         SELECT a.*
         FROM ilv a
                  INNER JOIN (
             SELECT node_reference, MAX(DEPTH#) depth#, tree_uuid
             FROM ilv
             GROUP BY node_reference, tree_uuid
         ) b ON a.node_reference = b.node_reference AND a.tree_uuid = b.tree_uuid and a.depth# = b.depth#
        ) ilv2
    ON(hnc.node_reference = ilv2.node_reference and hnc.tree_uuid = ilv2.tree_uuid)
    WHEN MATCHED THEN UPDATE SET
                                 hnc.is_leaf = ilv2.is_leaf,
                                 hnc.path_from_top = ilv2.path_from_top;

    -- Find the natural root nodes and update them to null parent
    update hierarchy_nodes_calc set parent_reference = tree_name where uuid in (select uuid from hierarchy_nodes_calc where parent_reference = 'Unallocated' and is_leaf = 0 and tree_uuid = tree_uuid_to_calc);

    -- Insert Grade 9 node versions of all nodes that have children (ONLY FOR ARUP TREE)
    IF tree_name = 'ARUP' THEN
        INSERT INTO hierarchy_nodes_calc hnc (
            hnc.uuid,
            hnc.node_reference,
            hnc.employee_uuid,
            hnc.parent_reference,
            hnc.tree_uuid,
            hnc.node_type
        )
        select
            randomUUID AS uuid,
            'G9-' || node_reference,
            employee_uuid,
            case when parent_reference in ('ARUP','Unallocated') then 'N9-' || node_reference else parent_reference end ,
            tree_uuid,
            'Node'                      AS node_type
        from hierarchy_nodes_calc where node_reference in (
            SELECT
                unique substr(parent_reference,4)
            FROM
                hierarchy_nodes_calc
            WHERE
                    node_type = 'Employee' and parent_reference like 'G9-%')
                                    and tree_uuid = tree_uuid_to_calc;
    END IF;

    -- Turn all N9 references into nodes
    update hierarchy_nodes_calc set node_type = 'Node' where uuid in (select uuid from hierarchy_nodes_calc where node_reference like 'N9-%' and tree_uuid = tree_uuid_to_calc);

    -- Insert node versions of all non leaf nodes, to make distinction between employees and nodes / This might be to capture changes and fixed nodes
    INSERT INTO hierarchy_nodes_calc hnc (
        hnc.uuid,
        hnc.node_reference,
        hnc.node_name,
        hnc.employee_uuid,
        hnc.parent_reference,
        hnc.tree_uuid,
        hnc.path_from_top,
        hnc.is_leaf,
        hnc.node_type
    )
    SELECT
        randomUUID AS uuid,
        node_reference,
        node_name,
        employee_uuid,
        parent_reference,
        tree_uuid,
        path_from_top,
        is_leaf,
        'Node'                      AS node_type
    FROM
        hierarchy_nodes_calc
    WHERE
            is_leaf = 0 and node_type = 'Employee';

    -- Generate path from top 2nd time
    MERGE INTO hierarchy_nodes_calc hnc
    USING
        (with ilv as (SELECT distinct LEVEL-1 AS DEPTH#
                                    ,node_reference
                                    ,tree_uuid
                                    ,CONNECT_BY_ISLEAF AS is_leaf
                                    ,SUBSTR(SYS_CONNECT_BY_PATH(node_reference,'.'),2,999) as path_from_top
                      FROM (select hnc.* from hierarchy_nodes_calc hnc where tree_uuid = tree_uuid_to_calc)
                      START WITH node_reference = tree_name
                      CONNECT BY PRIOR node_reference = parent_reference
        )
         SELECT a.*
         FROM ilv a
                  INNER JOIN (
             SELECT node_reference, MAX(DEPTH#) depth#, tree_uuid
             FROM ilv
             GROUP BY node_reference, tree_uuid
         ) b ON a.node_reference = b.node_reference AND a.tree_uuid = b.tree_uuid and a.depth# = b.depth#
        ) ilv2
    ON(hnc.node_reference = ilv2.node_reference and hnc.tree_uuid = ilv2.tree_uuid)
    WHEN MATCHED THEN UPDATE SET
                                 hnc.is_leaf = ilv2.is_leaf,
                                 hnc.path_from_top = ilv2.path_from_top;

    --    -- CUSTOM CHANGES FLATTEN/SET PARENT
--    --- New Node - Create a new folder to be part of the hierarchy
--    INSERT INTO fr_arup_nodes_calc an (
--        an.node_seq_id,
--        an.node_reference,
--        an.node_name,
--        an.person_seq_id,
--        an.parent_reference,
--        an.tree_seq_id,
--        an.path_from_top,
--        an.is_leaf,
--        an.node_type
--    )
--    SELECT
--        fr_arup_node_id_seq.NEXTVAL AS node_seq_id,
--        ane.node_reference,
--        ane.node_name,
--        null as person_seq_id,
--        ane.parent_reference,
--        ane.tree_seq_id,
--        null as path_from_top,
--        null as is_leaf,
--        'Node' as node_type
--    FROM
--        fr_arup_node_exceptions ane
--    WHERE
--            ane.node_type = 'Node' and ane.exception_type = 'NewNode' and tree_seq_id = tree_id
--      and ane.node_reference not in (select node_reference from fr_arup_nodes_calc where tree_seq_id = tree_id and node_type = 'Node');
--
--    -- Fixed node updates to tree
--    MERGE INTO fr_arup_nodes_calc anc
--    USING
--        (select
--             node_reference,
--             parent_reference,
--             tree_seq_id,
--             node_type
--         from fr_arup_node_exceptions where tree_seq_id = tree_id and node_type = 'Node' and exception_type = 'FixedNode'
--        ) ilv
--    ON(anc.node_reference = ilv.node_reference and anc.tree_seq_id = ilv.tree_seq_id and anc.node_type = ilv.node_type)
--    WHEN MATCHED THEN UPDATE SET
--        anc.parent_reference = ilv.parent_reference;
--
--    --- Fixed Employee - Move an employee to be placed under a certain folder
--    MERGE INTO fr_arup_nodes_calc anc
--    USING
--        (select
--             node_reference,
--             parent_reference,
--             tree_seq_id,
--             node_type
--         from fr_arup_node_exceptions where tree_seq_id = tree_id and node_type = 'Employee' and exception_type = 'FixedEmployee'
--        ) ilv
--    ON(anc.node_reference = ilv.node_reference and anc.tree_seq_id = ilv.tree_seq_id and anc.node_type = ilv.node_type)
--    WHEN MATCHED THEN UPDATE SET
--        anc.parent_reference = ilv.parent_reference;
--
--    -- Flatten node - set all child nodes to report to this node
--    MERGE INTO fr_arup_nodes_calc anc
--    USING
--        (select
--             node_reference,
--             tree_seq_id,
--             node_type
--         from fr_arup_node_exceptions where tree_seq_id = tree_id and node_type = 'Node' and exception_type = 'Flatten'
--        ) ilv
--    ON(anc.path_from_top like '%.' || ilv.node_reference || '.%' and anc.tree_seq_id = ilv.tree_seq_id and anc.node_type = ilv.node_type)
--    WHEN MATCHED THEN UPDATE SET
--        anc.parent_reference = ilv.node_reference;
--    --   ***************************************************

    -- Generate path from top 3rd time, nodes only
    MERGE INTO hierarchy_nodes_calc hnc
    USING
        (with ilv as (SELECT distinct LEVEL-1 AS DEPTH#
                                    ,node_reference
                                    ,tree_uuid
                                    ,CONNECT_BY_ISLEAF AS is_leaf
                                    ,SUBSTR(SYS_CONNECT_BY_PATH(node_reference,'.'),2,999) as path_from_top
                      FROM (select hnc.* from hierarchy_nodes_calc hnc where tree_uuid = tree_uuid_to_calc and node_type = 'Node')
                      START WITH node_reference = tree_name
                      CONNECT BY PRIOR node_reference = parent_reference
        )
         SELECT a.*
         FROM ilv a
                  INNER JOIN (
             SELECT node_reference, MAX(DEPTH#) depth#, tree_uuid
             FROM ilv
             GROUP BY node_reference, tree_uuid
         ) b ON a.node_reference = b.node_reference AND a.tree_uuid = b.tree_uuid and a.depth# = b.depth#
        ) ilv2
    ON(hnc.node_reference = ilv2.node_reference and hnc.tree_uuid = ilv2.tree_uuid)
    WHEN MATCHED THEN UPDATE SET
                                 hnc.is_leaf = ilv2.is_leaf,
                                 hnc.path_from_top = ilv2.path_from_top;



    -- Move any flattened employees
    MERGE INTO hierarchy_nodes_calc hnc
    USING
        (select
             hnc.node_reference,
             hnc.parent_reference,
             hnc2.path_from_top || '.' || hnc.node_reference as new_path_from_top,
             hnc.tree_uuid,
             hnc.node_type
         from hierarchy_nodes_calc hnc
                  left join hierarchy_nodes_calc hnc2 on (hnc2.tree_uuid = hnc.tree_uuid and hnc2.node_type = 'Node' and hnc2.node_reference = hnc.parent_reference)
         where
                 hnc.tree_uuid = tree_uuid_to_calc
           and hnc.node_type = 'Employee'
        ) ilv
    ON(hnc.node_reference = ilv.node_reference and hnc.tree_uuid = ilv.tree_uuid and hnc.node_type = 'Employee')
    WHEN MATCHED THEN UPDATE SET
        hnc.path_from_top = ilv.new_path_from_top;

    -- Generate node names and link to employees as required
    MERGE INTO hierarchy_nodes_calc hnc
    USING
        (select distinct
             hnc.node_reference,
             hnc.tree_uuid,
             res.uuid,
             coalesce(res.preferred_name, res.first_name) || ' ' || res.last_name as node_name
         from hierarchy_nodes_calc hnc
                  left join rec_employee_static res on (hnc.node_reference = res.employee_number)
         where hnc.parent_reference is not null and hnc.node_reference <> 'Unallocated') ilv
    ON(hnc.node_reference = ilv.node_reference and hnc.tree_uuid = ilv.tree_uuid)
    WHEN MATCHED THEN UPDATE SET
                                 hnc.node_name = initcap(trim(ilv.node_name)),
                                 hnc.employee_uuid = ilv.uuid;

    --    -- Generate node names for Grade 9 nodes
--    MERGE INTO fr_arup_nodes_calc an
--    USING
--        (select distinct
--             an.node_reference,
--             an.tree_seq_id,
--             ed.person_seq_id,
--             coalesce(ed.preferred_name, ed.first_name) || ' ' || ed.last_name || ' (Grade 9)' as node_name
--         from fr_arup_nodes_calc an
--                  left join fr_emp_details ed on (substr(an.node_reference,4) = ed.emplid and ed.period_seq_id = 315)
--         where an.parent_reference is not null and an.node_reference <> 'Unallocated'
--           and an.node_reference like 'G9-%'
--        ) ilv
--    ON(an.node_reference = ilv.node_reference and an.tree_seq_id = ilv.tree_seq_id)
--    WHEN MATCHED THEN UPDATE SET
--                                 an.node_name = initcap(trim(ilv.node_name)),
--                                 an.person_seq_id = ilv.person_seq_id;
--
    -- Generate node names for N9 nodes
    MERGE INTO hierarchy_nodes_calc hnc
    USING
        (select distinct
             hnc.node_reference,
             hnc.tree_uuid,
             res.uuid,
             coalesce(res.preferred_name, res.first_name) || ' ' || res.last_name as node_name
         from hierarchy_nodes_calc hnc
                  left join rec_employee_static res on (substr(hnc.node_reference,4) = res.employee_number)
         where hnc.parent_reference is not null and hnc.node_reference <> 'Unallocated'
           and hnc.node_reference like 'N9-%'
        ) ilv
    ON(hnc.node_reference = ilv.node_reference and hnc.tree_uuid = ilv.tree_uuid)
    WHEN MATCHED THEN UPDATE SET
                                 hnc.node_name = initcap(trim(ilv.node_name)),
                                 hnc.uuid = ilv.uuid;

    --    -- Generate node names for R9 nodes
--    MERGE INTO fr_arup_nodes_calc an
--    USING
--        (select distinct
--             an.node_reference,
--             an.tree_seq_id,
--             ed.person_seq_id,
--             coalesce(ed.preferred_name, ed.first_name) || ' ' || ed.last_name || ' (' || coalesce(edr.preferred_name, edr.first_name) || ' ' || edr.last_name || ')' as node_name
--         from fr_arup_nodes_calc an
--                  left join fr_emp_details ed on (substr(substr(an.node_reference,4),0,instr(substr(an.node_reference,4),'-') - 1) = ed.emplid and ed.period_seq_id = 315)
--                  left join fr_emp_details edr on (substr(substr(an.node_reference,4),instr(substr(an.node_reference,4),'-') + 1) = edr.emplid and edr.period_seq_id = 315)
--         where an.parent_reference is not null and an.node_reference <> 'Unallocated'
--           and an.node_reference like 'R9-%'
--        ) ilv
--    ON(an.node_reference = ilv.node_reference and an.tree_seq_id = ilv.tree_seq_id)
--    WHEN MATCHED THEN UPDATE SET
--                                 an.node_name = initcap(trim(ilv.node_name)),
--                                 an.person_seq_id = ilv.person_seq_id;
--
--    -- CUSTOM updates of node names
--    MERGE INTO fr_arup_nodes_calc anc
--    USING
--        (select
--             node_reference,
--             node_name,
--             tree_seq_id,
--             node_type
--         from fr_arup_node_exceptions where tree_seq_id = tree_id and node_type = 'Node' and exception_type = 'TitleChange'
--        ) ilv
--    ON(anc.node_reference = ilv.node_reference and anc.tree_seq_id = ilv.tree_seq_id and anc.node_type = ilv.node_type)
--    WHEN MATCHED THEN UPDATE SET
--        anc.node_name = ilv.node_name;
--    -- ***************************

    -- Remove any employee nodes for N or R folders
    delete from hierarchy_nodes_calc where (node_reference like 'N9-%' or node_reference like 'R9-%') and node_type = 'Employee';

    -- Remove nodes that are no longer referenced
    delete from hierarchy_nodes_calc where uuid not in (
        SELECT hnc.uuid
        FROM hierarchy_nodes_calc hnc
        where hnc.tree_uuid = tree_uuid_to_calc and hnc.node_type = 'Node'
          and (select count(hnc2.uuid) from hierarchy_nodes_calc hnc2 where hnc2.tree_uuid = tree_uuid_to_calc and hnc2.node_type = 'Employee' and hnc2.path_from_top like hnc.path_from_top || '.%' and rownum <=1) > 0)
                                       and node_type = 'Node';

    --    -- Generate node status records for those nodes that don't already have one
--    INSERT INTO fr_arup_node_status ans (
--        ans.node_status_seq_id,
--        ans.node_reference,
--        ans.tree_seq_id,
--        ans.status,
--        ans.node_type
--    )
--    SELECT
--        fr_arup_node_status_id_seq.NEXTVAL,
--        an.node_reference,
--        an.tree_seq_id,
--        'In Progress',
--        an.node_type
--    FROM
--        fr_arup_nodes_calc an
--    WHERE not exists(
--        select * from fr_arup_node_status where (node_reference = an.node_reference and tree_id = an.tree_seq_id));
--
--    -- Update TAPS not eligible for review - controled by flag 'Taps Not Eligible' - Auto moved to Unallocated
--    MERGE INTO fr_arup_nodes_calc anc
--    USING
--        (select distinct
--             ed.person_seq_id,
--             ed.emplid
--         from fr_emp_details ed
--                  inner join fr_emp_flags ef on (ef.person_seq_id = ed.person_seq_id and ef.period_seq_id = ed.period_seq_id and ef.flag_cd = 442) -- Taps Not Eligible
--         where ed.period_seq_id = 315
--        ) ilv
--    ON(anc.node_reference = ilv.emplid and anc.person_seq_id = ilv.person_seq_id and anc.tree_seq_id = tree_id and anc.node_type = 'Employee')
--    WHEN MATCHED THEN UPDATE SET
--                                 anc.parent_reference = 'Unallocated',
--                                 anc.path_from_top = (case when tree_id = 5 then 'ARUP.Unallocated.' when tree_id = 120 then 'Merit Review.Unallocated.' else 'Unallocated.' end || ilv.emplid),
--                                 anc.is_leaf = 1;
--
    -- ACTUAL UPDATES TO LIVE HIERARCHY FOLLOW
    -- Remove people from hierarchy that no longer exist
    delete from hierarchy_nodes where node_reference in (
        select hn.node_reference from hierarchy_nodes hn where hn.node_reference not in (
            select hnc.node_reference from hierarchy_nodes_calc hnc where tree_uuid_to_calc = hnc.tree_uuid and hnc.node_type = 'Employee'
        ) and tree_uuid_to_calc = hn.tree_uuid and node_type = 'Employee')
                                  and tree_uuid_to_calc = tree_uuid and node_type = 'Employee';

    delete from hierarchy_nodes where node_reference in (
        select hn.node_reference from hierarchy_nodes hn where hn.node_reference not in (
            select hnc.node_reference from hierarchy_nodes_calc hnc where tree_uuid_to_calc = hnc.tree_uuid and hnc.node_type = 'Node'
        ) and tree_uuid_to_calc = hn.tree_uuid and node_type = 'Node')
                                  and tree_uuid_to_calc = tree_uuid and node_type = 'Node';

    -- Insert new people into hierarchy
    insert into hierarchy_nodes (uuid, node_reference, node_name, employee_uuid, parent_reference, tree_uuid, path_from_top, is_leaf, node_type)
    select randomUUID, node_reference, node_name, employee_uuid, parent_reference, tree_uuid, path_from_top, is_leaf, node_type from hierarchy_nodes_calc where node_reference not in (
        select node_reference from hierarchy_nodes where tree_uuid_to_calc = tree_uuid and node_type = 'Employee') and tree_uuid_to_calc = tree_uuid and node_type = 'Employee';

    insert into hierarchy_nodes (uuid, node_reference, node_name, employee_uuid, parent_reference, tree_uuid, path_from_top, is_leaf, node_type)
    select randomUUID, node_reference, node_name, employee_uuid, parent_reference, tree_uuid, path_from_top, is_leaf, node_type from hierarchy_nodes_calc where node_reference not in (
        select node_reference from hierarchy_nodes where tree_uuid_to_calc = tree_uuid and node_type = 'Node') and tree_uuid_to_calc = tree_uuid and node_type = 'Node';

    -- Update those people that have an updated position
    MERGE INTO hierarchy_nodes hn
    USING
        (select distinct
             hnc.node_name,
             hnc.node_reference,
             hnc.tree_uuid,
             hnc.employee_uuid,
             hnc.parent_reference,
             hnc.path_from_top,
             hnc.is_leaf,
             hnc.node_type
         from hierarchy_nodes_calc hnc
                  left join hierarchy_nodes hn on (hn.node_reference = hnc.node_reference and hn.tree_uuid = hnc.tree_uuid and hn.node_type = hnc.node_type)
         where hnc.tree_uuid = tree_uuid_to_calc and hnc.node_type = 'Employee' and (hnc.path_from_top <> hn.path_from_top or hnc.node_name <> hn.node_name)
        ) ilv
    ON(hn.node_reference = ilv.node_reference and hn.tree_uuid = ilv.tree_uuid and hn.node_type = ilv.node_type)
    WHEN MATCHED THEN UPDATE SET
                                 hn.node_name = ilv.node_name,
                                 hn.employee_uuid = ilv.employee_uuid,
                                 hn.parent_reference = ilv.parent_reference,
                                 hn.path_from_top = ilv.path_from_top,
                                 hn.is_leaf = ilv.is_leaf;

    MERGE INTO hierarchy_nodes hn
    USING
        (select distinct
             hnc.node_name,
             hnc.node_reference,
             hnc.tree_uuid,
             hnc.employee_uuid,
             hnc.parent_reference,
             hnc.path_from_top,
             hnc.is_leaf,
             hnc.node_type
         from hierarchy_nodes_calc hnc
                  left join hierarchy_nodes hn on (hn.node_reference = hnc.node_reference and hn.tree_uuid = hnc.tree_uuid and hn.node_type = hnc.node_type)
         where hnc.tree_uuid = tree_uuid_to_calc and hnc.node_type = 'Node' and (hnc.path_from_top <> hn.path_from_top or hnc.node_name <> hn.node_name)
        ) ilv
    ON(hn.node_reference = ilv.node_reference and hn.tree_uuid = ilv.tree_uuid and hn.node_type = ilv.node_type)
    WHEN MATCHED THEN UPDATE SET
                                 hn.node_name = ilv.node_name,
                                 hn.employee_uuid = ilv.employee_uuid,
                                 hn.parent_reference = ilv.parent_reference,
                                 hn.path_from_top = ilv.path_from_top,
                                 hn.is_leaf = ilv.is_leaf;


end;







create or replace function randomUUID return char is
    uuid char(36);
BEGIN
    with t as (
        select rawtohex(sys_guid()) guid from dual
    )
    select lower(substr(guid, 1, 8)
        ||'-'|| substr(guid, 9, 4)
        ||'-'|| substr(guid, 13, 4)
        ||'-'|| substr(guid, 17, 4)
        ||'-'|| substr(guid, 21, 12)) as formatted_guid into uuid
    from t;

    return uuid;
END;

select randomUUID from dual;
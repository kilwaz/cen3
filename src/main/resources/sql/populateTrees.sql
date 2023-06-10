create or replace PROCEDURE tree_populate(tree_id NUMBER) is
    tree_name VARCHAR2(128 byte);
BEGIN
    --    dbms_output.enable;
--    dbms_output.put_line  (user || ' Tables in the database:');

    EXECUTE IMMEDIATE 'TRUNCATE TABLE fr_arup_nodes_calc';

    Select t.name into tree_name from fr_trees t where t.tree_seq_id = tree_id; -- Find tree name for variable

    -- Initial Populate of all nodes taken from hierarchy table
    INSERT INTO fr_arup_nodes_calc ( node_seq_id, node_reference, parent_reference,tree_seq_id, node_type)
    select fr_arup_node_id_seq.nextval as node_seq_id, node_reference, parent_reference,tree_seq_id, 'Employee' from (
                                                                                                                         SELECT DISTINCT node_reference, coalesce(parent_reference,tree_name) as parent_reference,tree_seq_id from fr_arup_hierarchy where tree_seq_id = tree_id);

    insert into fr_arup_nodes_calc (node_seq_id, node_reference, node_name, parent_reference, tree_seq_id, node_type) values (fr_arup_node_id_seq.NEXTVAL, tree_name, tree_name, null, tree_id, 'Node');   -- Node at top of the tree
    insert into fr_arup_nodes_calc (node_seq_id, node_reference, node_name, parent_reference, tree_seq_id, node_type) values (fr_arup_node_id_seq.NEXTVAL, 'Unallocated', 'Unallocated', tree_name, tree_id, 'Node');  -- Unallocated node for tree

    -- Generate path from top 1st time
    MERGE INTO fr_arup_nodes_calc an
    USING
        (with ilv as (SELECT distinct LEVEL-1 AS DEPTH#
                                    ,node_reference
                                    ,tree_seq_id
                                    ,CONNECT_BY_ISLEAF AS is_leaf
                                    ,SUBSTR(SYS_CONNECT_BY_PATH(node_reference,'.'),2,999) as path_from_top
                      FROM (select an.* from fr_arup_nodes_calc an where tree_seq_id = tree_id)
                      START WITH node_reference = tree_name
                      CONNECT BY PRIOR node_reference = parent_reference
        )
         SELECT a.*
         FROM ilv a
                  INNER JOIN (
             SELECT node_reference, MAX(DEPTH#) depth#, tree_seq_id
             FROM ilv
             GROUP BY node_reference, tree_seq_id
         ) b ON a.node_reference = b.node_reference AND a.tree_seq_id = b.tree_seq_id and a.depth# = b.depth#
        ) ilv2
    ON(an.node_reference = ilv2.node_reference and an.tree_seq_id = ilv2.tree_seq_id)
    WHEN MATCHED THEN UPDATE SET
                                 an.is_leaf = ilv2.is_leaf,
                                 an.path_from_top = ilv2.path_from_top;

    -- Find the natrual root nodes and update them to null parent
    update fr_arup_nodes_calc set parent_reference = tree_name where node_seq_id in (select node_seq_id from fr_arup_nodes_calc where parent_reference = 'Unallocated' and is_leaf = 0 and tree_seq_id = tree_id);

    -- Insert Grade 9 node versions of all nodes that have children (ONLY FOR ARUP TREE)
    IF tree_name = 'ARUP' THEN
        INSERT INTO fr_arup_nodes_calc an (
            an.node_seq_id,
            an.node_reference,
            an.person_seq_id,
            an.parent_reference,
            an.tree_seq_id,
            an.node_type
        )
        select
            fr_arup_node_id_seq.NEXTVAL AS node_seq_id,
            'G9-' || node_reference,
            person_seq_id,
            case when parent_reference in ('ARUP','Unallocated') then 'N9-' || node_reference else parent_reference end ,
            tree_seq_id,
            'Node'                      AS node_type
        from fr_arup_nodes_calc where node_reference in (
            SELECT
                unique substr(parent_reference,4)
            FROM
                fr_arup_nodes_calc
            WHERE
                    node_type = 'Employee' and parent_reference like 'G9-%')
                                  and tree_seq_id = tree_id;
    END IF;

    -- Turn all N9 references into nodes
    update fr_arup_nodes_calc set node_type = 'Node' where node_seq_id in (select node_seq_id from fr_arup_nodes_calc where node_reference like 'N9-%' and tree_seq_id = tree_id);

    -- Insert node versions of all non leaf nodes, to make distiction between employees and nodes / This might be to capture changes and fixed nodes
    INSERT INTO fr_arup_nodes_calc an (
        an.node_seq_id,
        an.node_reference,
        an.node_name,
        an.person_seq_id,
        an.parent_reference,
        an.tree_seq_id,
        an.path_from_top,
        an.is_leaf,
        an.node_type
    )
    SELECT
        fr_arup_node_id_seq.NEXTVAL AS node_seq_id,
        node_reference,
        node_name,
        person_seq_id,
        parent_reference,
        tree_seq_id,
        path_from_top,
        is_leaf,
        'Node'                      AS node_type
    FROM
        fr_arup_nodes_calc
    WHERE
            is_leaf = 0 and node_type = 'Employee';

    -- Generate path from top 2nd time
    MERGE INTO fr_arup_nodes_calc an
    USING
        (with ilv as (SELECT distinct LEVEL-1 AS DEPTH#
                                    ,node_reference
                                    ,tree_seq_id
                                    ,CONNECT_BY_ISLEAF AS is_leaf
                                    ,SUBSTR(SYS_CONNECT_BY_PATH(node_reference,'.'),2,999) as path_from_top
                      FROM (select an.* from fr_arup_nodes_calc an where tree_seq_id = tree_id)
                      START WITH node_reference = tree_name
                      CONNECT BY PRIOR node_reference = parent_reference
        )
         SELECT a.*
         FROM ilv a
                  INNER JOIN (
             SELECT node_reference, MAX(DEPTH#) depth#, tree_seq_id
             FROM ilv
             GROUP BY node_reference, tree_seq_id
         ) b ON a.node_reference = b.node_reference AND a.tree_seq_id = b.tree_seq_id and a.depth# = b.depth#
        ) ilv2
    ON(an.node_reference = ilv2.node_reference and an.tree_seq_id = ilv2.tree_seq_id)
    WHEN MATCHED THEN UPDATE SET
                                 an.is_leaf = ilv2.is_leaf,
                                 an.path_from_top = ilv2.path_from_top;

    -- CUSTOM CHANGES FLATTEN/SET PARENT
    --- New Node - Create a new folder to be part of the hierarchy
    INSERT INTO fr_arup_nodes_calc an (
        an.node_seq_id,
        an.node_reference,
        an.node_name,
        an.person_seq_id,
        an.parent_reference,
        an.tree_seq_id,
        an.path_from_top,
        an.is_leaf,
        an.node_type
    )
    SELECT
        fr_arup_node_id_seq.NEXTVAL AS node_seq_id,
        ane.node_reference,
        ane.node_name,
        null as person_seq_id,
        ane.parent_reference,
        ane.tree_seq_id,
        null as path_from_top,
        null as is_leaf,
        'Node' as node_type
    FROM
        fr_arup_node_exceptions ane
    WHERE
            ane.node_type = 'Node' and ane.exception_type = 'NewNode' and tree_seq_id = tree_id
      and ane.node_reference not in (select node_reference from fr_arup_nodes_calc where tree_seq_id = tree_id and node_type = 'Node');

    -- Fixed node updates to tree
    MERGE INTO fr_arup_nodes_calc anc
    USING
        (select
             node_reference,
             parent_reference,
             tree_seq_id,
             node_type
         from fr_arup_node_exceptions where tree_seq_id = tree_id and node_type = 'Node' and exception_type = 'FixedNode'
        ) ilv
    ON(anc.node_reference = ilv.node_reference and anc.tree_seq_id = ilv.tree_seq_id and anc.node_type = ilv.node_type)
    WHEN MATCHED THEN UPDATE SET
        anc.parent_reference = ilv.parent_reference;

    --- Fixed Employee - Move an employee to be placed under a certain folder
    MERGE INTO fr_arup_nodes_calc anc
    USING
        (select
             node_reference,
             parent_reference,
             tree_seq_id,
             node_type
         from fr_arup_node_exceptions where tree_seq_id = tree_id and node_type = 'Employee' and exception_type = 'FixedEmployee'
        ) ilv
    ON(anc.node_reference = ilv.node_reference and anc.tree_seq_id = ilv.tree_seq_id and anc.node_type = ilv.node_type)
    WHEN MATCHED THEN UPDATE SET
        anc.parent_reference = ilv.parent_reference;

    -- Flatten node - set all child nodes to report to this node
    MERGE INTO fr_arup_nodes_calc anc
    USING
        (select
             node_reference,
             tree_seq_id,
             node_type
         from fr_arup_node_exceptions where tree_seq_id = tree_id and node_type = 'Node' and exception_type = 'Flatten'
        ) ilv
    ON(anc.path_from_top like '%.' || ilv.node_reference || '.%' and anc.tree_seq_id = ilv.tree_seq_id and anc.node_type = ilv.node_type)
    WHEN MATCHED THEN UPDATE SET
        anc.parent_reference = ilv.node_reference;
    --   ***************************************************

    -- Generate path from top 3rd time, nodes only
    MERGE INTO fr_arup_nodes_calc an
    USING
        (with ilv as (SELECT distinct LEVEL-1 AS DEPTH#
                                    ,node_reference
                                    ,tree_seq_id
                                    ,CONNECT_BY_ISLEAF AS is_leaf
                                    ,SUBSTR(SYS_CONNECT_BY_PATH(node_reference,'.'),2,999) as path_from_top
                      FROM (select an.* from fr_arup_nodes_calc an where tree_seq_id = tree_id and node_type = 'Node')
                      START WITH node_reference = tree_name
                      CONNECT BY PRIOR node_reference = parent_reference
        )
         SELECT a.*
         FROM ilv a
                  INNER JOIN (
             SELECT node_reference, MAX(DEPTH#) depth#, tree_seq_id
             FROM ilv
             GROUP BY node_reference, tree_seq_id
         ) b ON a.node_reference = b.node_reference AND a.tree_seq_id = b.tree_seq_id and a.depth# = b.depth#
        ) ilv2
    ON(an.node_reference = ilv2.node_reference and an.tree_seq_id = ilv2.tree_seq_id)
    WHEN MATCHED THEN UPDATE SET
                                 an.is_leaf = ilv2.is_leaf,
                                 an.path_from_top = ilv2.path_from_top;

    -- Move any flattened employees
    MERGE INTO fr_arup_nodes_calc anc
    USING
        (select
             anc.node_reference,
             anc.parent_reference,
             anc2.path_from_top || '.' || anc.node_reference as new_path_from_top,
             anc.tree_seq_id,
             anc.node_type
         from fr_arup_nodes_calc anc
                  left join fr_arup_nodes_calc anc2 on (anc2.tree_seq_id = anc.tree_seq_id and anc2.node_type = 'Node' and anc2.node_reference = anc.parent_reference)
         where
                 anc.tree_seq_id = tree_id
           and anc.node_type = 'Employee'
        ) ilv
    ON(anc.node_reference = ilv.node_reference and anc.tree_seq_id = ilv.tree_seq_id and anc.node_type = 'Employee')
    WHEN MATCHED THEN UPDATE SET
        anc.path_from_top = ilv.new_path_from_top;

    -- Generate node names and link to employees as required
    MERGE INTO fr_arup_nodes_calc an
    USING
        (select distinct
             an.node_reference,
             an.tree_seq_id,
             ed.person_seq_id,
             coalesce(ed.preferred_name, ed.first_name) || ' ' || ed.last_name as node_name
         from fr_arup_nodes_calc an
                  left join fr_emp_details ed on (an.node_reference = ed.emplid and ed.period_seq_id = 315)
         where an.parent_reference is not null and an.node_reference <> 'Unallocated') ilv
    ON(an.node_reference = ilv.node_reference and an.tree_seq_id = ilv.tree_seq_id)
    WHEN MATCHED THEN UPDATE SET
                                 an.node_name = initcap(trim(ilv.node_name)),
                                 an.person_seq_id = ilv.person_seq_id;


    -- Generate node names for Grade 9 nodes
    MERGE INTO fr_arup_nodes_calc an
    USING
        (select distinct
             an.node_reference,
             an.tree_seq_id,
             ed.person_seq_id,
             coalesce(ed.preferred_name, ed.first_name) || ' ' || ed.last_name || ' (Grade 9)' as node_name
         from fr_arup_nodes_calc an
                  left join fr_emp_details ed on (substr(an.node_reference,4) = ed.emplid and ed.period_seq_id = 315)
         where an.parent_reference is not null and an.node_reference <> 'Unallocated'
           and an.node_reference like 'G9-%'
        ) ilv
    ON(an.node_reference = ilv.node_reference and an.tree_seq_id = ilv.tree_seq_id)
    WHEN MATCHED THEN UPDATE SET
                                 an.node_name = initcap(trim(ilv.node_name)),
                                 an.person_seq_id = ilv.person_seq_id;

    -- Generate node names for N9 nodes
    MERGE INTO fr_arup_nodes_calc an
    USING
        (select distinct
             an.node_reference,
             an.tree_seq_id,
             ed.person_seq_id,
             coalesce(ed.preferred_name, ed.first_name) || ' ' || ed.last_name as node_name
         from fr_arup_nodes_calc an
                  left join fr_emp_details ed on (substr(an.node_reference,4) = ed.emplid and ed.period_seq_id = 315)
         where an.parent_reference is not null and an.node_reference <> 'Unallocated'
           and an.node_reference like 'N9-%'
        ) ilv
    ON(an.node_reference = ilv.node_reference and an.tree_seq_id = ilv.tree_seq_id)
    WHEN MATCHED THEN UPDATE SET
                                 an.node_name = initcap(trim(ilv.node_name)),
                                 an.person_seq_id = ilv.person_seq_id;

    -- Generate node names for R9 nodes
    MERGE INTO fr_arup_nodes_calc an
    USING
        (select distinct
             an.node_reference,
             an.tree_seq_id,
             ed.person_seq_id,
             coalesce(ed.preferred_name, ed.first_name) || ' ' || ed.last_name || ' (' || coalesce(edr.preferred_name, edr.first_name) || ' ' || edr.last_name || ')' as node_name
         from fr_arup_nodes_calc an
                  left join fr_emp_details ed on (substr(substr(an.node_reference,4),0,instr(substr(an.node_reference,4),'-') - 1) = ed.emplid and ed.period_seq_id = 315)
                  left join fr_emp_details edr on (substr(substr(an.node_reference,4),instr(substr(an.node_reference,4),'-') + 1) = edr.emplid and edr.period_seq_id = 315)
         where an.parent_reference is not null and an.node_reference <> 'Unallocated'
           and an.node_reference like 'R9-%'
        ) ilv
    ON(an.node_reference = ilv.node_reference and an.tree_seq_id = ilv.tree_seq_id)
    WHEN MATCHED THEN UPDATE SET
                                 an.node_name = initcap(trim(ilv.node_name)),
                                 an.person_seq_id = ilv.person_seq_id;

    -- CUSTOM updates of node names
    MERGE INTO fr_arup_nodes_calc anc
    USING
        (select
             node_reference,
             node_name,
             tree_seq_id,
             node_type
         from fr_arup_node_exceptions where tree_seq_id = tree_id and node_type = 'Node' and exception_type = 'TitleChange'
        ) ilv
    ON(anc.node_reference = ilv.node_reference and anc.tree_seq_id = ilv.tree_seq_id and anc.node_type = ilv.node_type)
    WHEN MATCHED THEN UPDATE SET
        anc.node_name = ilv.node_name;
    -- ***************************

    -- Remove any employee nodes for N or R folders
    delete from fr_arup_nodes_calc where (node_reference like 'N9-%' or node_reference like 'R9-%') and node_type = 'Employee';

    -- Remove nodes that are no longer referenced
    delete from fr_arup_nodes_calc where node_seq_id not in (
        SELECT an.node_seq_id
        FROM fr_arup_nodes_calc an
        where an.tree_seq_id = tree_id and an.node_type = 'Node'
          and (select count(anc.node_seq_id) from fr_arup_nodes_calc anc where anc.tree_seq_id = tree_id and anc.node_type = 'Employee' and anc.path_from_top like an.path_from_top || '.%' and rownum <=1) > 0)
                                     and node_type = 'Node';

    -- Generate node status records for those nodes that don't alreay have one
    INSERT INTO fr_arup_node_status ans (
        ans.node_status_seq_id,
        ans.node_reference,
        ans.tree_seq_id,
        ans.status,
        ans.node_type
    )
    SELECT
        fr_arup_node_status_id_seq.NEXTVAL,
        an.node_reference,
        an.tree_seq_id,
        'In Progress',
        an.node_type
    FROM
        fr_arup_nodes_calc an
    WHERE not exists(
        select * from fr_arup_node_status where (node_reference = an.node_reference and tree_id = an.tree_seq_id));

    -- Update TAPS not eligible for review - controled by flag 'Taps Not Eligible' - Auto moved to Unallocated
    MERGE INTO fr_arup_nodes_calc anc
    USING
        (select distinct
             ed.person_seq_id,
             ed.emplid
         from fr_emp_details ed
                  inner join fr_emp_flags ef on (ef.person_seq_id = ed.person_seq_id and ef.period_seq_id = ed.period_seq_id and ef.flag_cd = 442) -- Taps Not Eligible
         where ed.period_seq_id = 315
        ) ilv
    ON(anc.node_reference = ilv.emplid and anc.person_seq_id = ilv.person_seq_id and anc.tree_seq_id = tree_id and anc.node_type = 'Employee')
    WHEN MATCHED THEN UPDATE SET
                                 anc.parent_reference = 'Unallocated',
                                 anc.path_from_top = (case when tree_id = 5 then 'ARUP.Unallocated.' when tree_id = 120 then 'Merit Review.Unallocated.' else 'Unallocated.' end || ilv.emplid),
                                 anc.is_leaf = 1;

    -- ACTUAL UPDATES TO LIVE HIERARCHY FOLLOW
    -- Remove people from hierarchy that no longer exist
    delete from fr_arup_nodes where node_reference in (
        select an.node_reference from fr_arup_nodes an where an.node_reference not in (
            select anc.node_reference from fr_arup_nodes_calc anc where tree_id = anc.tree_seq_id and anc.node_type = 'Employee'
        ) and tree_id = an.tree_seq_id and node_type = 'Employee')
                                and tree_id = tree_seq_id and node_type = 'Employee';

    delete from fr_arup_nodes where node_reference in (
        select an.node_reference from fr_arup_nodes an where an.node_reference not in (
            select anc.node_reference from fr_arup_nodes_calc anc where tree_id = anc.tree_seq_id and anc.node_type = 'Node'
        ) and tree_id = an.tree_seq_id and node_type = 'Node')
                                and tree_id = tree_seq_id and node_type = 'Node';

    -- Insert new people into hierarchy
    insert into fr_arup_nodes (node_seq_id, node_reference, node_name, person_seq_id, parent_reference, tree_seq_id, path_from_top, is_leaf, node_type)
    select fr_arup_node_id_seq.NEXTVAL, node_reference, node_name, person_seq_id, parent_reference, tree_seq_id, path_from_top, is_leaf, node_type  from fr_arup_nodes_calc where node_reference not in (
        select node_reference from fr_arup_nodes where tree_id = tree_seq_id and node_type = 'Employee') and tree_id = tree_seq_id and node_type = 'Employee';

    insert into fr_arup_nodes (node_seq_id, node_reference, node_name, person_seq_id, parent_reference, tree_seq_id, path_from_top, is_leaf, node_type)
    select fr_arup_node_id_seq.NEXTVAL, node_reference, node_name, person_seq_id, parent_reference, tree_seq_id, path_from_top, is_leaf, node_type  from fr_arup_nodes_calc where node_reference not in (
        select node_reference from fr_arup_nodes where tree_id = tree_seq_id and node_type = 'Node') and tree_id = tree_seq_id and node_type = 'Node';

    -- Update those people that have an updated position
    MERGE INTO fr_arup_nodes an
    USING
        (select distinct
             anc.node_name,
             anc.node_reference,
             anc.tree_seq_id,
             anc.person_seq_id,
             anc.parent_reference,
             anc.path_from_top,
             anc.is_leaf,
             anc.node_type
         from fr_arup_nodes_calc anc
                  left join fr_arup_nodes an on (an.node_reference = anc.node_reference and an.tree_seq_id = anc.tree_seq_id and an.node_type = anc.node_type)
         where anc.tree_seq_id = tree_id and anc.node_type = 'Employee' and (anc.path_from_top <> an.path_from_top or anc.node_name <> an.node_name)
        ) ilv
    ON(an.node_reference = ilv.node_reference and an.tree_seq_id = ilv.tree_seq_id and an.node_type = ilv.node_type)
    WHEN MATCHED THEN UPDATE SET
                                 an.node_name = ilv.node_name,
                                 an.person_seq_id = ilv.person_seq_id,
                                 an.parent_reference = ilv.parent_reference,
                                 an.path_from_top = ilv.path_from_top,
                                 an.is_leaf = ilv.is_leaf;

    MERGE INTO fr_arup_nodes an
    USING
        (select distinct
             anc.node_name,
             anc.node_reference,
             anc.tree_seq_id,
             anc.person_seq_id,
             anc.parent_reference,
             anc.path_from_top,
             anc.is_leaf,
             anc.node_type
         from fr_arup_nodes_calc anc
                  left join fr_arup_nodes an on (an.node_reference = anc.node_reference and an.tree_seq_id = anc.tree_seq_id and an.node_type = anc.node_type)
         where anc.tree_seq_id = tree_id and anc.node_type = 'Node' and (anc.path_from_top <> an.path_from_top or anc.node_name <> an.node_name)
        ) ilv
    ON(an.node_reference = ilv.node_reference and an.tree_seq_id = ilv.tree_seq_id and an.node_type = ilv.node_type)
    WHEN MATCHED THEN UPDATE SET
                                 an.node_name = ilv.node_name,
                                 an.person_seq_id = ilv.person_seq_id,
                                 an.parent_reference = ilv.parent_reference,
                                 an.path_from_top = ilv.path_from_top,
                                 an.is_leaf = ilv.is_leaf;

END;
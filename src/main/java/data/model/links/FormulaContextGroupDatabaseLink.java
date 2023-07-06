package data.model.links;

import clarity.definition.Definition;
import clarity.definition.FormulaContext;
import clarity.definition.RecordDefinition;
import data.model.DatabaseLink;
import data.model.objects.annotations.DatabaseLinkClass;

import java.util.UUID;

@DatabaseLinkClass(
        linkClass = clarity.definition.FormulaContextGroup.class,
        tableName = "formula_context_group"
)
public class FormulaContextGroupDatabaseLink extends DatabaseLink {
    public FormulaContextGroupDatabaseLink() {
        super();

        // Make sure the order is the same as column order in database
        link("uuid", method("getUuidString"), method("setUuid", UUID.class)); // 1
        link("definition_id", method("getDefinitionUUID"), method("definition", Definition.class)); // 2
        link("formula_context_id", method("getFormulaContextUUID"), method("formulaContext", FormulaContext.class)); // 3
    }
}

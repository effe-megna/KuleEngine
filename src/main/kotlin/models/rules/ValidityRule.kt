package models.rules

import models.entities.abstractions.IEntity
import models.rows.abstractions.IRuleRow
import models.rules.abstractions.AbstractRule
import models.rules.abstractions.IRule

class ValidityRule(
        override val name: String,
        override val entities: List<IEntity>,
        override val rows: List<IRuleRow>
) : AbstractRule() {
    override val activeRow: IRuleRow?
        get() = rows.single { it.isActive }

    override fun execute() {

    }

    override val type: IRule.RuleType = IRule.RuleType.Validity
}
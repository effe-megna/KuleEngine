package models.rules

import models.cells.abstractions.ICell
import models.entities.abstractions.IEntity
import models.rows.abstractions.IRuleRow
import models.rules.abstractions.AbstractRule
import models.rules.abstractions.IRule

data class DecisionRule(
        override val name: String,
        override val entities: List<IEntity>,
        override val rows: List<IRuleRow>
) : AbstractRule() {
    override val activeRow: IRuleRow?
        get() = rows.singleOrNull { it.isActive }

    override val type: IRule.RuleType = IRule.RuleType.Decision

    override fun execute() {
        executeWithStrategy { row, entities ->
            selectEntitiesRowByRuleRowValues(row, entities)
        }
    }

    private fun selectEntitiesRowByRuleRowValues(row: IRuleRow, entities: List<IEntity>) {
        row.apply {
            isActive = true
        }.apply {
            row.cells.forEach { cell: ICell ->
                entities.forEach {
                    it.getRowByKey(cell.value as String)?.run {
                        isSelected = true
                    }
                }
            }
        }
    }

    override fun toString(): String {
        return "DecisionRule(name='$name', entities=$entities, rows=$rows, type=$type)"
    }
}

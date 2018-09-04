import models.Kengine
import models.cells.Cell
import models.columns.KeyColumn
import models.columns.StringColumn
import models.columns.abstractions.IColumn
import models.entities.ListEntity
import models.rows.EntityRow
import models.rows.RuleRow
import models.rules.DecisionRule
import java.io.File

fun getBykesRow(columns: List<IColumn>): List<EntityRow> {
    val firstRowCells = listOf(
            Cell(0, "Key", "BE-M-01", true),
            Cell(0, "Descrizione", "da strada", false)
    )

    val firstRow = EntityRow(
            0,
            firstRowCells,
            columns
    )

    val secondRowCells = listOf(
            Cell(1, "Key", "BE-M-02", true),
            Cell(1, "Descrizione", "da corsa", false)
    )

    val secondRow = EntityRow(
            1,
            secondRowCells,
            columns
    )

    val thirdRowCells = listOf(
            Cell(2, "Key", "BE-M-03", true),
            Cell(2, "Descrizione", "da montagna", false)
    )

    val thirdRow = EntityRow(
            2,
            thirdRowCells,
            columns
    )

    return listOf(firstRow, secondRow, thirdRow)
}

fun getFrameColorRow(columns: List<IColumn>): List<EntityRow> {
    val firstRowCells = listOf(
            Cell(0, "ColorKey", "Bianca", true),
            Cell(0, "Descrizione", "bianco perlato", false)
    )

    val firstRow = EntityRow(
            0,
            firstRowCells,
            columns
    )

    val secondRowCells = listOf(
            Cell(1, "ColorKey", "Blu", true),
            Cell(1, "Descrizione", "blu perlato", false)
    )

    val secondRow = EntityRow(
            1,
            secondRowCells,
            columns
    )

    val thirdRowCells = listOf(
            Cell(2, "ColorKey", "Rosso", true),
            Cell(2, "Descrizione", "Rosso fuoco", false)
    )

    val thirdRow = EntityRow(
            2,
            thirdRowCells,
            columns
    )

    return listOf(firstRow, secondRow, thirdRow)
}

fun getDecisionRuleRow(keyColumns: List<KeyColumn>): List<RuleRow> {
    val firstRowCells = listOf(
            Cell(0, "Key", "BE-M-01", true),
            Cell(0, "ColorKey", "Bianca", true)
    )

    val firstRow = RuleRow(0, firstRowCells, keyColumns, false)

    val secondRowCells = listOf(
            Cell(1, "Key", "BE-M-03", true),
            Cell(1, "ColorKey", "Blu", true)
    )

    val secondRow = RuleRow(1, secondRowCells, keyColumns, false)

    val thirdRowCells = listOf(
            Cell(2, "Key", "BE-M-02", true),
            Cell(2, "ColorKey", "Rosso", true)
    )

    val thirdRow = RuleRow(2, thirdRowCells, keyColumns, false)

    return listOf(firstRow, secondRow, thirdRow)
}

fun main(args: Array<String>) {
    val bykesColumn = listOf(KeyColumn("Key", "Modello bici"), StringColumn("Descrizione", "Descrizione breve"))
    val frameColorColumn = listOf(KeyColumn("ColorKey", "Id colore"), StringColumn("Descrizione", "Descrizione colore"))
    val keyColumns = listOf(bykesColumn.first(), frameColorColumn.first()) as List<KeyColumn>

    val bykes = ListEntity.Builder()
            .withName("Bykes")
            .withDescription("Tipologia di bicicletta")
            .withColumns(bykesColumn)
            .withRows(getBykesRow(bykesColumn))
            .build()

    val frameColor = ListEntity.Builder()
            .withName("FrameColor")
            .withDescription("Tipo di colore")
            .withColumns(frameColorColumn)
            .withRows(getFrameColorRow(frameColorColumn))
            .build()

    val decisionRule = DecisionRule(
            "Model-FrameColor",
            listOf(bykes, frameColor),
            getDecisionRuleRow(keyColumns)
    )

    val engine = Kengine(
            listOf(bykes, frameColor),
            listOf(decisionRule)
    )

    engine.getEntity("FrameColor")?.apply {
        getRowByKey("Bianca")?.apply {
            isSelected = true
        }
    }

    engine.getRule("Model-FrameColor")?.execute()

    listOf(bykes, frameColor).forEach {
        it.rowSelected.toString().apply {
            println(this)
        }
    }

    engine.getRule("Model-FrameColor")?.activeRow?.toString().apply {
        println(this)
    }

    val file = File("C:\\Users\\Francesco\\Documents\\Kotlin\\Konfigurator-core\\assets\\entities-test-1.json").readText()
}
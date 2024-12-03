import java.io.File
import kotlin.math.abs

private fun getData(): List<List<Int>> =
    File("data/2.txt")
        .readLines()
        .map { row ->
            row.split(" ").map { it.toInt() }
        }

fun a2(): Int = getData()
    .fold(0) { count, report ->
        if (report.calculateSteps().isSafe()) {
            count + 1
        } else count
    }

fun b2(): Int = getData()
    .fold(0) { count, report ->
        if (report.calculateSteps().isSafe()) return@fold count + 1

        report.indices.forEach { index ->
            report.toMutableList().apply {
                removeAt(index)
                if (calculateSteps().isSafe()) return@fold count + 1
            }
        }

        count
    }

private fun List<Int>.calculateSteps() = this
    .windowed(size = 2, step = 1, partialWindows = false)
    .map { (first, second) ->
        second - first
    }

private fun List<Int>.isSafe() = all { abs(it) <= 3 } && (all { it > 0 } || all { it < 0 })

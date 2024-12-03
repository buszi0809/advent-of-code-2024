import java.io.File
import kotlin.math.abs

private fun getData(): Pair<List<Int>, List<Int>> =
    File("data/1.txt")
        .readLines()
        .map { row ->
            val (first, second) = row.split("   ")
            first.toInt() to second.toInt()
        }
        .unzip()

fun a1(): Int = getData()
    .let { (first, second) ->
        first.sortedDescending().zip(second.sortedDescending())
    }
    .sumOf { (first, second) ->
        abs(second - first)
    }

fun b1(): Int = getData()
    .let { (first, second) ->
        first.sumOf { entry ->
            entry * second.count { it == entry }
        }
    }

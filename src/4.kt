import java.io.File

private fun getData() =
    File("data/4.txt").readLines().map { it.toCharArray() }

private data class Direction(
    val dx: Int,
    val dy: Int,
)

fun a4(): Int {
    val grid = getData()
    val word = "XMAS"

    val directions = listOf(
        Direction(dx = 1, dy = 0),
        Direction(dx = 0, dy = 1),
        Direction(dx = 1, dy = 1),
        Direction(dx = -1, dy = 1),
    )

    var count = 0
    for (x in grid[0].indices) {
        for (y in grid.indices) {
            for ((dx, dy) in directions) {
                if (!grid.isInBounds(word.lastIndex, x, y, dx, dy)) continue
                val maybeWord = buildString {
                    for (i in word.indices) {
                        append(grid[y + i * dy][x + i * dx])
                    }
                }
                if (maybeWord.isWord(word)) count++
            }
        }
    }

    return count
}

private fun List<CharArray>.isInBounds(wordLastIndex: Int, x: Int, y: Int, dx: Int, dy: Int): Boolean =
    x + wordLastIndex * dx in this[0].indices && y + wordLastIndex * dy in this.indices

fun b4(): Int {
    val grid = getData()
    val word = "MAS"

    var count = 0
    for (x in grid[0].indices) {
        if (x == 0 || x == grid[0].lastIndex) continue
        for (y in grid.indices) {
            if (y == 0 || y == grid.lastIndex) continue

            val diagonal1 = buildString {
                append(grid[y - 1][x - 1])
                append(grid[y][x])
                append(grid[y + 1][x + 1])
            }
            val diagonal2 = buildString {
                append(grid[y + 1][x - 1])
                append(grid[y][x])
                append(grid[y - 1][x + 1])
            }

            if (diagonal1.isWord(word) && diagonal2.isWord(word)) count++
        }
    }

    return count
}

private fun String.isWord(word: String) =
    this == word || this == word.reversed()

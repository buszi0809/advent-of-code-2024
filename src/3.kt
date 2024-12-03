import java.io.File

private fun getData(regex: String) =
    File("data/3.txt").readText().let { memory ->
        Regex(regex).findAll(memory)
    }

fun a3(): Int =
    getData("""mul\((\d{1,3}),(\d{1,3})\)""")
        .sumOf { result ->
            result.groupValues[1].toInt() * result.groupValues[2].toInt()
        }

fun b3(): Int {
    var enabled = true
    return getData("""mul\((\d{1,3}),(\d{1,3})\)|do\(\)|don't\(\)""")
        .fold(0) { sum, match ->
            when {
                match.value == "do()" -> {
                    enabled = true
                    sum
                }

                match.value == "don't()" -> {
                    enabled = false
                    sum
                }

                enabled -> sum + (match.groupValues[1].toInt() * match.groupValues[2].toInt())

                else -> sum
            }
        }
}

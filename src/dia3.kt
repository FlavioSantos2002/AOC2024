import java.io.File

fun main() {
    // Lê a entrada do arquivo
    val filePath = "C:/Users/Lab 4.0/IdeaProjects/testeKotlin/docs/inputDia3.txt"
    val corruptedMemory = File(filePath).readText()

    // Expressões regulares para capturar instruções válidas
    val mulRegex = Regex("mul\\((\\d+),(\\d+)\\)") // Captura os parâmetros X e Y de mul(X,Y)
    val doRegex = Regex("do\\(\\)") // Captura "do()"
    val dontRegex = Regex("don't\\(\\)") // Captura "don't()"

    // Inicializa o estado e a soma total
    var isEnabled = true // Por padrão, os mul() estão habilitados
    var totalSum = 0

    // Itera por cada match de instrução no texto
    val instructions = mulRegex.findAll(corruptedMemory) + doRegex.findAll(corruptedMemory) + dontRegex.findAll(corruptedMemory)
    val sortedInstructions = instructions.sortedBy { it.range.first } // Ordena as instruções pela posição no texto

    for (match in sortedInstructions) {
        when {
            // Se for um "do()", habilita as multiplicações futuras
            doRegex.matches(match.value) -> isEnabled = true

            // Se for um "don't()", desabilita as multiplicações futuras
            dontRegex.matches(match.value) -> isEnabled = false

            // Se for um "mul(X,Y)", verifica se está habilitado e calcula o resultado
            mulRegex.matches(match.value) -> {
                if (isEnabled) {
                    val (x, y) = mulRegex.matchEntire(match.value)!!.destructured
                    val result = x.toInt() * y.toInt()
                    totalSum += result
                    println("Instrução válida encontrada: ${match.value} = $result")
                } else {
                    println("Instrução ignorada (mul desabilitado): ${match.value}")
                }
            }
        }
    }

    println("Soma total das instruções de mul habilitadas: $totalSum")
}

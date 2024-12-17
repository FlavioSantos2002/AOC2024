import java.io.File

fun main() {
    val caminhoArquivo = "C:/Users/Lab 4.0/IdeaProjects/testeKotlin/docs/dia2.txt"

    var totalListasSeguras = 0 // Contador de listas seguras
    File(caminhoArquivo).forEachLine { line ->

        val list: List<Int> = line.split("\\s+".toRegex()).map { it.toInt() }

        // Variável de controle para saber se pelo menos uma sublista foi aprovada
        var listaAprovada = false

        for (i in list.indices) {
            // Remove o elemento da posição i e cria a sublista
            val sublist = list.toMutableList().apply { removeAt(i) }

            if (sublist.size > 1 && verificarListaSegura(sublist)) {
                totalListasSeguras++
                listaAprovada = true
                break // Para de testar outras sublistas
            }
        }

        if (!listaAprovada && verificarListaSegura(list)) {
            totalListasSeguras++
        }
    }

    println("Total de listas seguras: $totalListasSeguras")
}

/**
 * Função para verificar se a lista é segura.
 * Regras:
 * 1. Não pode ser "crescendo e decrescendo" (usamos identificarEstado)
 * 2. A diferença entre dois números consecutivos deve estar no intervalo de [-3, 3]
 * 3. Não pode ter números consecutivos iguais
 */
fun verificarListaSegura(lista: List<Int>): Boolean {
    if (identificarEstado2(lista) == 0) return false // Verifica se a lista cresce e decresce

    for (i in 0 until lista.size - 1) {
        if (lista[i] == lista[i + 1]) return false // Verifica se há números consecutivos iguais
        if (kotlin.math.abs(lista[i] - lista[i + 1]) > 3) return false // Verifica se a diferença entre os números consecutivos é maior que 3
    }

    return true // Se todas as condições foram atendidas, a sublista é segura
}

/**
 * Função para identificar o estado da lista.
 * Retorna:
 *  1 -> apenas crescente
 * -1 -> apenas decrescente
 *  0 -> cresce e decresce
 */
fun identificarEstado2(lista: List<Int>): Int {
    var crescente = true
    var decrescente = true

    for (i in 0 until lista.size - 1) {
        if (lista[i] < lista[i + 1]) decrescente = false // Se está crescendo, não é decrescente
        if (lista[i] > lista[i + 1]) crescente = false // Se está decrescendo, não é crescente
    }

    return when {
        crescente -> 1
        decrescente -> -1
        else -> 0
    }
}

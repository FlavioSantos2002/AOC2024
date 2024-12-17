import java.io.File

fun main() {

    val caminhoArquivo = "C:/Users/Lab 4.0/IdeaProjects/testeKotlin/docs/input.txt"

    // Listas para armazenar as colunas
    val coluna1 = mutableListOf<Int>()
    val coluna2 = mutableListOf<Int>()

    // Leitura do arquivo e separação das colunas
    File(caminhoArquivo).forEachLine { linha ->
        val (valor1, valor2) = linha.split("\\s+".toRegex()).map { it.toInt() } // Divide por espaços múltiplos
        coluna1.add(valor1)
        coluna2.add(valor2)
    }

    var listaOrganizadaD = organizaLista(coluna1)
    var listaOrganizadaE = organizaLista(coluna2)

    var soma = comparaListas(listaOrganizadaE, listaOrganizadaD)

    var resultado = multiplicaListas(listaOrganizadaE, listaOrganizadaD)

    println("o resultado da soma muktiplicando é: $resultado")

    println("o valor da soma é: $soma")

}

fun organizaLista(list: List<Int>): List<Int>{
    val listaOrganizada = list.sorted()
    return listaOrganizada
}

fun comparaListas(list1: List<Int>, list2: List<Int>): Int{

    var sum:Int = 0
    for (valor in 0..list1.size-1){
        sum = sum + medDist(list1[valor], list2[valor])
    }

    return sum
}

fun multiplicaListas(list1: List<Int>, list2: List<Int>): Int{
    var sum = 0
    var valorSoma = 0
    for (vl1 in 0..list1.size-1){
        for (vl2 in 0..list1.size-1){
            if(list1[vl1] == list2[vl2]){
                sum = sum + list1[vl1]
            }
        }
        valorSoma = valorSoma + sum
        sum = 0
    }
    return valorSoma
}


fun medDist(int1: Int, int2: Int): Int{

    var dist = int1 - int2

    if(dist<0){
        dist = dist*(-1)
    }
    return dist
}

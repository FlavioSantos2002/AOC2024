import java.io.File

fun main() {
    val caminhoArquivo = "C:/Users/Lab 4.0/IdeaProjects/testeKotlin/docs/dia2.txt"

    var listSeguras = 0
    File(caminhoArquivo).forEachLine { line ->

        var list:List<Int> = line.split("\\s+".toRegex()).map { it.toInt() }

        var i = 0
        var cont = 0
        for (e in list){
            if (i>list.size-2) break

            if(identificarEstado(list) == 0){
                //println("inseguro")
                break
            }
            if (e == list[i+1]){
                //println("inseguro")
                break
            }
            if((e+3 < list[i+1]) || (e-3 > list[i+1])){
                //println("inseguro")
                break
            }
            else{
                cont = cont + 1
                //println("seguro")
            }
            i++

        }
        if (cont == list.size-1){
            listSeguras = listSeguras + 1
        }


    }

    println("temos $listSeguras listas seguras")

}

fun identificarEstado(lista: List<Int>): Int {
    var crescente = true
    var decrescente = true

    for (i in 0 until lista.size - 1) {
        if (lista[i] < lista[i + 1]) { // Se a lista está crescendo
            decrescente = false
        }
        if (lista[i] > lista[i + 1]) { // Se a lista está decrescendo
            crescente = false
        }
    }

    return when {
        crescente -> 1
        decrescente -> -1
        else -> 0
    }
}
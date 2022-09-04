const val line = "---------"
var table = mutableListOf(mutableListOf<Char>(' ', ' ', ' '),
    mutableListOf<Char>(' ', ' ', ' '),
    mutableListOf<Char>(' ', ' ', ' '))

fun wins(player: Char): Boolean {
    return (player == table[0][0] && player == table[0][1] && player == table[0][2] ||
            player == table[1][0] && player == table[1][1] && player == table[1][2] ||
            player == table[2][0] && player == table[2][1] && player == table[2][2] ||
            player == table[0][0] && player == table[1][0] && player == table[2][0] ||
            player == table[0][1] && player == table[1][1] && player == table[2][1] ||
            player == table[0][2] && player == table[1][2] && player == table[2][2] ||
            player == table[0][0] && player == table[1][1] && player == table[2][2] ||
            player == table[0][2] && player == table[1][1] && player == table[2][0])
}

fun withEmptys(): Boolean {
    return (' ' in table[0]) || (' ' in table[1]) || (' ' in table[2])
}

fun differenceImpossible(): Boolean {
    var sumX = 0
    var sumO = 0
    for (list in table) {
        for (i in list) {
            if (i == 'O') {
                sumO++
            } else if (i == 'X') {
                sumX++
            }
        }
    }
    return (Math.abs(sumX - sumO) > 1)
}

fun isFinished(): Boolean {
    if (differenceImpossible() || (wins('X') && wins('O'))) {
        println("Impossible")
        return true
    } else if (withEmptys() && !wins('X') && !wins('O')) {
        return false
    } else if (!withEmptys() && !wins('X') && !wins('O')) {
        println("Draw")
        return true
    } else if (wins('X')) {
        println("X wins")
        return true
    } else if (wins('O')) {
        println("O wins")
        return true
    }
    return true
}

fun isNumber(text: String): Boolean {
    for (ch in text) {
        if (!ch.isDigit()) {
            return false
        }
    }
    return true
}

fun isCorrectMove(x: String, y: String): Boolean {
    if (!isNumber(x) || !isNumber(y)) {
        println("You should enter numbers!")
        return false
    } else {
        val xInt = x.toInt()
        val yInt = y.toInt()
        val range = 1..3
        if (!(xInt in range) || !(yInt in range)) {
            println("Coordinates should be from 1 to 3!")
            return false
        } else if (table[xInt-1][yInt-1] != ' ') {
            println("This cell is occupied! Choose another one!")
            return false
        }
    }
    return true
}

fun move(player: Char) {
    val (x, y) = readln().split(" ")
    if (isCorrectMove(x, y)) {
        table[x.toInt()-1][y.toInt()-1] = player
        printTable()
    } else {
        move(player)
    }
}

fun printTable() {
    println(line)
    for (list in table) {
        print("| ")
        print(list.joinToString(" "))
        println(" |")
    }
    println(line)
}

fun main() {
    printTable()
    var count = 0
    while (!isFinished()) {
        move(if (count % 2 == 0) 'X' else 'O')
        count++
    }

}
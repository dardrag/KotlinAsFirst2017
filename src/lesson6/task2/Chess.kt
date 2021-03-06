@file:Suppress("UNUSED_PARAMETER")
package lesson6.task2

import java.lang.Math.abs

/**
 * Клетка шахматной доски. Шахматная доска квадратная и имеет 8 х 8 клеток.
 * Поэтому, обе координаты клетки (горизонталь row, вертикаль column) могут находиться в пределах от 1 до 8.
 * Горизонтали нумеруются снизу вверх, вертикали слева направо.
 */
data class Square(val column: Int, val row: Int) {
    /**
     * Пример
     *
     * Возвращает true, если клетка находится в пределах доски
     */
    fun inside(): Boolean = column in 1..8 && row in 1..8

    /**
     * Простая
     *
     * Возвращает строковую нотацию для клетки.
     * В нотации, колонки обозначаются латинскими буквами от a до h, а ряды -- цифрами от 1 до 8.
     * Для клетки не в пределах доски вернуть пустую строку
     */
    fun notation(): String {
        if (!this.inside()) return ""
        val col = 'a'.plus(column - 1)
	    return "$col$row"
    }
}

/**
 * Простая
 *
 * Создаёт клетку по строковой нотации.
 * В нотации, колонки обозначаются латинскими буквами от a до h, а ряды -- цифрами от 1 до 8.
 * Если нотация некорректна, бросить IllegalArgumentException
 */
fun square(notation: String): Square {
    if (notation.length != 2 || notation[1] - '0' !in 1..8) throw IllegalArgumentException("")
    val row = notation[1] - '0'
	val col = notation[0] - ('a' - 1)
    if (col !in 1..8) throw IllegalArgumentException("")
    return Square(col, row)
}

/**
 * Простая
 *
 * Определить число ходов, за которое шахматная ладья пройдёт из клетки start в клетку end.
 * Шахматная ладья может за один ход переместиться на любую другую клетку
 * по вертикали или горизонтали.
 * Ниже точками выделены возможные ходы ладьи, а крестиками -- невозможные:
 *
 * xx.xxххх
 * xх.хxххх
 * ..Л.....
 * xх.хxххх
 * xx.xxххх
 * xx.xxххх
 * xx.xxххх
 * xx.xxххх
 *
 * Если клетки start и end совпадают, вернуть 0.
 * Если любая из клеток некорректна, бросить IllegalArgumentException().
 *
 * Пример: rookMoveNumber(Square(3, 1), Square(6, 3)) = 2
 * Ладья может пройти через клетку (3, 3) или через клетку (6, 1) к клетке (6, 3).
 */
fun rookMoveNumber(start: Square, end: Square): Int {
	if (!(start.inside() && end.inside())) throw IllegalArgumentException("")
	return when {
		start == end -> 0
		start.row == end.row || start.column == end.column -> 1
		else -> 2
	}
}

/**
 * Средняя
 *
 * Вернуть список из клеток, по которым шахматная ладья может быстрее всего попасть из клетки start в клетку end.
 * Описание ходов ладьи см. предыдущую задачу.
 * Список всегда включает в себя клетку start. Клетка end включается, если она не совпадает со start.
 * Между ними должны находиться промежуточные клетки, по порядку от start до end.
 * Примеры: rookTrajectory(Square(3, 3), Square(3, 3)) = listOf(Square(3, 3))
 *          (здесь возможен ещё один вариант)
 *          rookTrajectory(Square(3, 1), Square(6, 3)) = listOf(Square(3, 1), Square(3, 3), Square(6, 3))
 *          (здесь возможен единственный вариант)
 *          rookTrajectory(Square(3, 5), Square(8, 5)) = listOf(Square(3, 5), Square(8, 5))
 * Если возможно несколько вариантов самой быстрой траектории, вернуть любой из них.
 */
fun rookTrajectory(start: Square, end: Square): List<Square> {
	var trajectory = mutableListOf(start)
	when {
		start == end -> {}
		start.row == end.row || start.column == end.column -> trajectory.add(end)
		else -> {
			trajectory.add(Square(start.column, end.row))
			trajectory.add(end)
		}
	}
	return trajectory
}

/**
 * Простая
 *
 * Определить число ходов, за которое шахматный слон пройдёт из клетки start в клетку end.
 * Шахматный слон может за один ход переместиться на любую другую клетку по диагонали.
 * Ниже точками выделены возможные ходы слона, а крестиками -- невозможные:
 *
 * .xxx.ххх
 * x.x.xххх
 * xxСxxxxx
 * x.x.xххх
 * .xxx.ххх
 * xxxxx.хх
 * xxxxxх.х
 * xxxxxхх.
 *
 * Если клетки start и end совпадают, вернуть 0.
 * Если клетка end недостижима для слона, вернуть -1.
 * Если любая из клеток некорректна, бросить IllegalArgumentException().
 *
 * Примеры: bishopMoveNumber(Square(3, 1), Square(6, 3)) = -1; bishopMoveNumber(Square(3, 1), Square(3, 7)) = 2.
 * Слон может пройти через клетку (6, 4) к клетке (3, 7).
 */
fun bishopMoveNumber(start: Square, end: Square): Int {
	if (!(start.inside() && end.inside())) throw IllegalArgumentException("")
	return when {
		start == end -> 0
		Math.abs(start.column - end.column) == Math.abs(start.row - end.row) -> 1
		abs(start.column - end.column) % 2 != abs(start.row - end.row) % 2 -> -1
		else -> 2
	}
}

/**
 * Сложная
 *
 * Вернуть список из клеток, по которым шахматный слон может быстрее всего попасть из клетки start в клетку end.
 * Описание ходов слона см. предыдущую задачу.
 *
 * Если клетка end недостижима для слона, вернуть пустой список.
 *
 * Если клетка достижима:
 * - список всегда включает в себя клетку start
 * - клетка end включается, если она не совпадает со start.
 * - между ними должны находиться промежуточные клетки, по порядку от start до end.
 *
 * Примеры: bishopTrajectory(Square(3, 3), Square(3, 3)) = listOf(Square(3, 3))
 *          bishopTrajectory(Square(3, 1), Square(3, 7)) = listOf(Square(3, 1), Square(6, 4), Square(3, 7))
 *          bishopTrajectory(Square(1, 3), Square(6, 8)) = listOf(Square(1, 3), Square(6, 8))
 * Если возможно несколько вариантов самой быстрой траектории, вернуть любой из них.
 */
fun bishopTrajectory(start: Square, end: Square): List<Square> {
	var trajecotry = mutableListOf(start)
	when(bishopMoveNumber(start, end)) {
		-1 -> trajecotry.removeAt(0)
		1 -> trajecotry.add(end)
		2 -> {
			for (i in 1..8) {
				if (colNeg(start, i) == colPos(end, i) && colPos(end, i) in 1..8) {
					trajecotry.add(Square(colPos(end, i), i))
					break
				}
				if (colPos(start, i) == colNeg(end, i) && colNeg(end, i) in 1..8) {
					trajecotry.add(Square(colPos(start, i), i))
					break
				}
			}
			trajecotry.add(end)
		}
	}
	return trajecotry
}
fun colPos(p: Square, column: Int): Int =
		column - p.row + p.column

fun colNeg(p: Square, column: Int): Int =
		p.row - column + p.column

/**
 * Средняя
 *
 * Определить число ходов, за которое шахматный король пройдёт из клетки start в клетку end.
 * Шахматный король одним ходом может переместиться из клетки, в которой стоит,
 * на любую соседнюю по вертикали, горизонтали или диагонали.
 * Ниже точками выделены возможные ходы короля, а крестиками -- невозможные:
 *
 * xxxxx
 * x...x
 * x.K.x
 * x...x
 * xxxxx
 *
 * Если клетки start и end совпадают, вернуть 0.
 * Если любая из клеток некорректна, бросить IllegalArgumentException().
 *
 * Пример: kingMoveNumber(Square(3, 1), Square(6, 3)) = 3.
 * Король может последовательно пройти через клетки (4, 2) и (5, 2) к клетке (6, 3).
 */
fun kingMoveNumber(start: Square, end: Square): Int {
	if (!(start.inside() && end.inside())) throw IllegalArgumentException("")
	return maxOf(abs(end.column - start.column), abs(end.row - start.row))
}

/**
 * Сложная
 *
 * Вернуть список из клеток, по которым шахматный король может быстрее всего попасть из клетки start в клетку end.
 * Описание ходов короля см. предыдущую задачу.
 * Список всегда включает в себя клетку start. Клетка end включается, если она не совпадает со start.
 * Между ними должны находиться промежуточные клетки, по порядку от start до end.
 * Примеры: kingTrajectory(Square(3, 3), Square(3, 3)) = listOf(Square(3, 3))
 *          (здесь возможны другие варианты)
 *          kingTrajectory(Square(3, 1), Square(6, 3)) = listOf(Square(3, 1), Square(4, 2), Square(5, 2), Square(6, 3))
 *          (здесь возможен единственный вариант)
 *          kingTrajectory(Square(3, 5), Square(6, 2)) = listOf(Square(3, 5), Square(4, 4), Square(5, 3), Square(6, 2))
 * Если возможно несколько вариантов самой быстрой траектории, вернуть любой из них.
 */
fun kingTrajectory(start: Square, end: Square): List<Square> {
	var currentColumn = start.column
	var currentRow = start.row
	var trajectory = mutableListOf(start)
	while (currentColumn != end.column || currentRow != end.row) {
		when {
			currentColumn > end.column -> currentColumn--
			currentColumn < end.column -> currentColumn++
		}
		when {
			currentRow > end.row -> currentRow--
			currentRow < end.row -> currentRow++
		}
		trajectory.add(Square(currentColumn, currentRow))
	}
	return trajectory
}

/**
 * Сложная
 *
 * Определить число ходов, за которое шахматный конь пройдёт из клетки start в клетку end.
 * Шахматный конь одним ходом вначале передвигается ровно на 2 клетки по горизонтали или вертикали,
 * а затем ещё на 1 клетку под прямым углом, образуя букву "Г".
 * Ниже точками выделены возможные ходы коня, а крестиками -- невозможные:
 *
 * .xxx.xxx
 * xxKxxxxx
 * .xxx.xxx
 * x.x.xxxx
 * xxxxxxxx
 * xxxxxxxx
 * xxxxxxxx
 * xxxxxxxx
 *
 * Если клетки start и end совпадают, вернуть 0.
 * Если любая из клеток некорректна, бросить IllegalArgumentException().
 *
 * Пример: knightMoveNumber(Square(3, 1), Square(6, 3)) = 3.
 * Конь может последовательно пройти через клетки (5, 2) и (4, 4) к клетке (6, 3).
 */
fun knightMoveNumber(start: Square, end: Square): Int {
	if (!start.inside() || !end.inside()) throw IllegalArgumentException()
	val moves = arrayOf(
			Square(-2, -1),
			Square(-2, 1),
			Square(-1, -2),
			Square(-1, 2),
			Square(1, -2),
			Square(1, 2),
			Square(2, -1),
			Square(2, 1))
	var pos = Array(9, { Array(9, { 0 }) })
	pos[start.column][start.row] = 1
	var count = 0
	var nodes = mutableListOf<Square>()
	var parentNodes = mutableListOf(start)
	while (!parentNodes.contains(end)) {
		for (node in parentNodes) {
			for (move in moves) {
				val temp = Square(node.column + move.column, node.row + move.row)
				if (temp.column in 1..8 && temp.row in 1..8 && pos[temp.column][temp.row] == 0){
					nodes.add(temp)
					pos[temp.column][temp.row] = 1
				}
                if (temp == end) break
			}
			if (nodes.contains(end)) break
		}
		count++
		parentNodes.clear()
		parentNodes.addAll(nodes)
		nodes.clear()
	}
	return count
}

/**
 * Очень сложная
 *
 * Вернуть список из клеток, по которым шахматный конь может быстрее всего попасть из клетки start в клетку end.
 * Описание ходов коня см. предыдущую задачу.
 * Список всегда включает в себя клетку start. Клетка end включается, если она не совпадает со start.
 * Между ними должны находиться промежуточные клетки, по порядку от start до end.
 * Примеры:
 *
 * knightTrajectory(Square(3, 3), Square(3, 3)) = listOf(Square(3, 3))
 * здесь возможны другие варианты)
 * knightTrajectory(Square(3, 1), Square(6, 3)) = listOf(Square(3, 1), Square(5, 2), Square(4, 4), Square(6, 3))
 * (здесь возможен единственный вариант)
 * knightTrajectory(Square(3, 5), Square(5, 6)) = listOf(Square(3, 5), Square(5, 6))
 * (здесь опять возможны другие варианты)
 * knightTrajectory(Square(7, 7), Square(8, 8)) =
 *     listOf(Square(7, 7), Square(5, 8), Square(4, 6), Square(6, 7), Square(8, 8))
 *
 * Если возможно несколько вариантов самой быстрой траектории, вернуть любой из них.
 */
fun knightTrajectory(start: Square, end: Square): List<Square> {
	if (!start.inside() || !end.inside()) throw IllegalArgumentException()
    var path = mutableListOf(end)
	return trajectory(path, start)
}

fun trajectory(path: MutableList<Square>, start: Square): List<Square> {
	val end = path[0]
	val moves = arrayOf(
			Square(-2, -1),
			Square(-2, 1),
			Square(-1, -2),
			Square(-1, 2),
			Square(1, -2),
			Square(1, 2),
			Square(2, -1),
			Square(2, 1))
	var pos = Array(9, { Array(9, { 0 }) })
	pos[start.column][start.row] = 1
	var nodes = mutableListOf<Square>()
	var parentNodes = mutableListOf(start)
	while (!parentNodes.contains(end)) {
		for (node in parentNodes) {
			for (move in moves) {
				val temp = Square(node.column + move.column, node.row + move.row)
				if (temp.column in 1..8 && temp.row in 1..8 && pos[temp.column][temp.row] == 0){
					nodes.add(temp)
					pos[temp.column][temp.row] = 1
				}
                if (temp == end){
					path.add(0, node)
					break
				}
			}
			if (nodes.contains(end)) break
		}
		parentNodes.clear()
		parentNodes.addAll(nodes)
		nodes.clear()
	}
	return if (path[0] == start) path else trajectory(path, start)
}

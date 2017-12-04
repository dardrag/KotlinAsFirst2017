@file:Suppress("UNUSED_PARAMETER", "unused")
package lesson7.task1

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E
    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)
    operator fun set(cell: Cell, value: E)
}

/**
 * Простая
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> {
    if (height <=0 || width <= 0) throw IllegalArgumentException()
    return MatrixImpl(height, width, e)
}

/**
 * Средняя сложность
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(override val height: Int, override val width: Int, e: E) : Matrix<E> {
    private val matrix = mutableListOf<E>()
    init {
        for (i in 0 until width * height) {
            matrix.add(e)
        }
    }

    override fun get(row: Int, column: Int): E {
        if (row !in 0 until height || column !in 0 until width) throw ArrayIndexOutOfBoundsException()
        return matrix[row * width + column]
    }

    override fun get(cell: Cell): E  {
        return get(cell.row, cell.column)
    }

    override fun set(row: Int, column: Int, value: E) {
        if (row !in 0 until height || column !in 0 until width) throw ArrayIndexOutOfBoundsException()
        matrix[row * width + column] = value
    }

    override fun set(cell: Cell, value: E) {
        set(cell.row, cell.column, value)
    }

    override fun equals(other: Any?): Boolean {
        if (!(other is MatrixImpl<*> && width == other.width && height == other.height)) return false
        for (i in 0 until height) {
            for (j in 0 until width) {
                if (other[i, j] != this[i, j]) return false
            }
        }
        return true
    }

    override fun toString(): String {
        val res = StringBuilder()
        for (i in 0 until height) {
            res.append("[ ")
            for (j in 0 until width) {
                res.append(this[i, j], ' ')
            }
            res.appendln(']')
        }
        return res.toString()
    }
}


@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
	// 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
	if (a == 0.0) {
		if (b == 0.0) return Double.NaN // ... и ничего больше не делать
		val bc = -c / b
		if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
		return -Math.sqrt(bc)
		// Дальше функция при a == 0.0 не идёт
	}
	val d = discriminant(a, b, c)   // 2
	if (d < 0.0) return Double.NaN  // 3
	// 4
	val y1 = (-b + Math.sqrt(d)) / (2 * a)
	val y2 = (-b - Math.sqrt(d)) / (2 * a)
	val y3 = Math.max(y1, y2)       // 5
	if (y3 < 0.0) return Double.NaN // 6
	return -Math.sqrt(y3)           // 7
}


fun main(args: Array<String>) {

}


/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
	when {
		age % 100 in 10..20 || age % 10 in 5..9 -> return "$age лет"
		age % 10 == 0 -> return "$age лет"
		age % 10 == 1 -> return "$age год"
		else -> return "$age года"
	}
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double, v1: Double,
				   t2: Double, v2: Double,
				   t3: Double, v3: Double): Double {
	val half_dist = (t1 * v1 + t2 * v2 + t3 * v3) / 2
	val dist1 = t1 * v1
	val dist2 = t2 * v2
	val dist3 = t3 * v3
	var time: Double
	when {
		half_dist == 0.0 -> time = 0.0
		half_dist > dist1 + dist2 -> time = t1 + t2 + (dist3 - half_dist) / v3
		half_dist > dist1 -> time = t1 + (half_dist - dist1) / v2
		else -> time = half_dist / v1
	}
	return time
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(kingX: Int, kingY: Int,
					   rookX1: Int, rookY1: Int,
					   rookX2: Int, rookY2: Int): Int {
	val rook1_dang = (kingX == rookX1 || kingY == rookY1)
	val rook2_dang = (kingX == rookX2 || kingY == rookY2)
	when {
		rook1_dang && rook2_dang -> return 3
		rook1_dang -> return 1
		rook2_dang -> return 2
		else -> return 0
	}
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(kingX: Int, kingY: Int,
						  rookX: Int, rookY: Int,
						  bishopX: Int, bishopY: Int): Int {
	val rook_dang = rookX == kingX || rookY == kingY
	val bishop_dang = Math.abs(bishopX - kingX) == Math.abs(bishopY - kingY)
	when {
		rook_dang && bishop_dang -> return 3
		rook_dang -> return 1
		bishop_dang -> return 2
		else -> return 0
	}
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
	if (a + b > c && a + c > b && b + c > a) {
		val cathet1: Double
		val cathet2: Double
		val hypo: Double
		if (a < b) {
			cathet1 = a
			if (b < c) {
				cathet2 = b
				hypo = c
			} else {
				cathet2 = c
				hypo = b
			}
		} else {
			cathet1 = b
			if (c < a) {
				cathet2 = c
				hypo = a
			} else {
				cathet2 = a
				hypo = c
			}
		}
		val hypoRect = hypotenuse(cathet1, cathet2)
		when {
			hypo < hypoRect -> return 0
			hypo == hypoRect -> return 1
			hypo > hypoRect -> return 2
		}
	}
	return -1
}


fun hypotenuse(cat1: Double, cat2: Double) =
		Math.sqrt(sqr(cat1) + sqr(cat2))

fun sqr(x: Double) = x*x

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
	val length = if (a < c) {
		if (b > d) {
			d - c
		} else {
			b - c
		}
	} else {
		if (d > b) {
			b - a
		} else {
			d - a
		}
	}
	if (length < 0) return -1
	return length
}

@file:Suppress("UNUSED_PARAMETER")
package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = Math.sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var sqrsum = 0.0
    for (i in v) {
        sqrsum += sqr(i)
    }
	return Math.sqrt(sqrsum)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    if (list.isEmpty()) return 0.0
    var sum = 0.0
    for (i in list) {
        sum += i
    }
	return sum / list.size.toDouble()
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    var meanList = mean(list)
    for (i in 0 until list.size) {
        list[i] -= meanList
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    var vecmuls = 0.0
    for (i in 0 until a.size) {
        vecmuls += a[i] * b[i]
    }
    return vecmuls
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double {
    var sum = 0.0
    for (i in 0 until p.size) {
        sum += p[i] * Math.pow(x, i.toDouble())
    }
    return sum
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    var sumprev = 0.0
	for (i in 0 until list.size) {
        list[i] += sumprev
        sumprev = list[i]
	}
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var number = n
    var primenums = mutableListOf<Int>()
    while (number % 2 == 0) {
	    number /= 2
	    primenums.add(2)
    }
	var num = 3
	val maxnum = Math.sqrt(n.toDouble()).toInt()
	while (number > 1) {
        if (number % num == 0) {
            number /= num
            primenums.add(num)
        } else {
            num += 2
	        if (num > maxnum && n == number){
                primenums.add(n)
                break
	        }
        }
	}
    return primenums
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var result = mutableListOf<Int>()
    var num = n
    if (num == 0) return listOf(0)
    while (num > 0) {
        result.add(num % base)
        num /= base
    }
	result.reverse()
    return result
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String {
    val converted = convert(n,base)
    var result = StringBuilder("")
    for (i in converted) {
	    if (i < 10) {
            result.append(i)
	    } else {
            result.append('a'.plus(i - 10))
	    }
    }
    return result.toString()
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var result = 0
    var list = digits.toMutableList()
	list.reverse()
    for (i in 0 until list.size) {
        result += list[i] * Math.pow(base.toDouble(),i.toDouble()).toInt()
    }
    return result
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int {
	val digits = mutableListOf<Int>()
    for (i in str) {
        if (i.isDigit()) {
            digits.add(i.minus('0'))
        } else {
            digits.add(i.minus('a') + 10)
        }
    }
    return decimal(digits, base)
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var num = n
    var romestr = StringBuilder("")
    val romedig = listOf("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")
    val numbers = listOf(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
    for (i in 0 until numbers.size) {
        while (num >= numbers[i]) {
            romestr.append(romedig[i])
            num -= numbers[i]
        }
    }
    return romestr.toString()
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val thousand = n / 1000
    val hundr = n % 1000
    var rusnum = StringBuilder()
    rusnum.append(hundreds(thousand))
    rusnum.append(decim(thousand))
    if (thousand % 100 < 11 || thousand % 100 > 19) rusnum.append(thUnits(thousand))
	rusnum.append(rusThousand(thousand))
    rusnum.append(hundreds(hundr))
	rusnum.append(decim(hundr))
    if (hundr % 100 < 11 || hundr % 100 > 19) rusnum.append(units(hundr))
	return rusnum.trim().toString()
}
fun units(n: Int): String =
    when(n % 10) {
        1 -> "один"
        2 -> "два"
        3 -> "три"
        4 -> "четыре"
        5 -> "пять"
        6 -> "шесть"
        7 -> "семь"
        8 -> "восемь"
        9 -> "девять"
        else -> ""
}
fun rusThousand(n: Int) =
     when {
         n == 0 -> ""
         (n % 100 in 10..20 || n % 10 in 5..9 || n % 10 == 0) -> "тысяч "
         (n % 10 == 1) -> "тысяча "
         else -> "тысячи "
     }

fun thUnits(n: Int): String =
    when(n % 10) {
        1 -> "одна "
        2 -> "две "
        3 -> "три "
        4 -> "четыре "
        5 -> "пять "
        6 -> "шесть "
        7 -> "семь "
        8 -> "восемь "
        9 -> "девять "
        else -> ""
    }
fun hundreds(n: Int): String =
    when(n / 100) {
        1 -> "сто "
        2 -> "двести "
        3 -> "триста "
        4 -> "четыреста "
        5 -> "пятьсот "
        6 -> "шестьсот "
        7 -> "семьсот "
        8 -> "восемьсот "
        9 -> "девятьсот "
        else -> ""
    }
fun decim(n: Int): String =
    when(n % 100 / 10) {
        1 -> when(n % 10) {
            1 -> "одиннадцать "
            2 -> "двенадцать "
            3 -> "тринадцать "
            4 -> "четырнадцать "
            5 -> "пятнадцать "
            6 -> "шестнадцать "
            7 -> "семнадцать "
            8 -> "восемнадцать "
            9 -> "девятнадцать "
	        else -> "десять "
        }
        2 -> "двадцать "
        3 -> "тридцать "
        4 -> "сорок "
        5 -> "пятьдесят "
        6 -> "шестьдесят "
        7 -> "семьдесят "
        8 -> "восемьдесят "
        9 -> "девяносто "
        else -> ""
    }

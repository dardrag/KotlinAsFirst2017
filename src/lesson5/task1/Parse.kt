@file:Suppress("UNUSED_PARAMETER")
package lesson5.task1

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main(args: Array<String>) {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        }
        else {
            println("Прошло секунд с начала суток: $seconds")
        }
    }
    else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}
fun isNumber(str: String): Boolean {
    if (str == "") return false
    for (i in str) {
        if (!i.isDigit()) return false
    }
    return true
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку
 */
fun dateStrToDigit(str: String): String {
    val date = str.split(" ")
	if (date.size != 3) return ""
	if (!isNumber(date[0])) return ""
    val month = when(date[1]) {
        "января" -> "01"
        "февраля" -> "02"
        "марта" -> "03"
        "апреля" -> "04"
        "мая" -> "05"
        "июня" -> "06"
        "июля" -> "07"
        "августа" -> "08"
        "сентября" -> "09"
        "октября" -> "10"
        "ноября" -> "11"
        "декабря" -> "12"
        else -> ""
    }
	if (month == "") return ""
    if (!isNumber(date[2])) return ""
    return "${twoDigitStr(date[0].toInt())}.$month.${date[2]}"
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 */
fun dateDigitToStr(digital: String): String {
	val date = digital.split(".")
    if (date.size != 3) return ""
    for (i in date) {
        if (!isNumber(i)) return ""
    }
    val month = when (date[1]) {
        "01" -> "января"
        "02" -> "февраля"
        "03" -> "марта"
        "04" -> "апреля"
        "05" -> "мая"
        "06" -> "июня"
        "07" -> "июля"
        "08" -> "августа"
        "09" -> "сентября"
        "10" -> "октября"
        "11" -> "ноября"
        "12" -> "декабря"
        else -> ""
    }
	if (month == "") return ""
    return "${date[0].toInt()} $month ${date[2]}"
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -98 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку
 */
fun flattenPhoneNumber(phone: String): String {
    val trPhone = StringBuilder(phone.trim())
    if (trPhone.isEmpty()) return ""
    var result = StringBuilder()
    if (trPhone[0] == '+') {
        if (trPhone.length == 1) return ""
        result.append('+')
        trPhone.deleteCharAt(0)
    }
    val symbols = setOf(' ', '-', '(', ')')
    for (i in trPhone) {
        if (!(i.isDigit() || symbols.contains(i))) return ""
        if (i.isDigit()) result.append(i)
    }
    return result.toString()
}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    if (jumps.isEmpty()) return -1
    val listJumps = jumps.split(Regex(" +"))
    val symbols = setOf("-", "%")
    var maxJump = -1
    for (i in listJumps) {
        val jump = i.trim()
        if (!(isNumber(jump) || symbols.contains(jump))) return -1
        if (isNumber(jump)) maxJump = maxOf(maxJump, jump.toInt())
    }
    return maxJump
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    val listJumps = jumps.split(" ")
    var isNumber = true
    val symbols = setOf('+', '%', '-')
    var maxJump = -1
    var currentJump = -1
    for (i in listJumps) {
        if (isNumber) {
            if (!isNumber(i)) return -1
            currentJump = i.toInt()
        } else {
            for (s in i) {
                if (!symbols.contains(s)) return -1
                if (s == '+') maxJump = maxOf(maxJump, currentJump)
            }
        }
        isNumber = isNumber.not()
    }
    return maxJump
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    var isNumber = true
    var result = 0
    val exception = IllegalArgumentException("")
    if (expression.isEmpty()) throw exception
    val list = expression.split(" ")
    val symbols = setOf("+", "-")
    var action = "+"
    for (i in list) {
        if (isNumber) {
            if (!isNumber(i)) throw exception
                when (action) {
                    "+" -> result += i.toInt()
                    "-" -> result -= i.toInt()
            }
        } else {
                if (!symbols.contains(i)) throw exception
                action = i
        }
	    isNumber = isNumber.not()
    }
	if (isNumber) throw exception
    return result
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
	val listWords = str.split(" ")
    var prevWord = ""
    var prevWordPosition = -1
    for (i in listWords) {
        if (prevWord.toLowerCase() == i.toLowerCase()) return prevWordPosition
        prevWordPosition += prevWord.length + 1
	    prevWord = i
    }
    return -1
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62.5; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть положительными
 */
fun mostExpensive(description: String): String {
	if (description.isEmpty()) return ""
    val productList = description.split("; ")
    var maxPrice = -1.0
    var maxPriceName = ""
    for (product in productList) {
        val productInfo = product.split(" ")
        if (productInfo.size != 2) return ""
        if (isDouble(productInfo[1])) {
            if (productInfo[1].toDouble() > maxPrice) {
                maxPrice = productInfo[1].toDouble()
                maxPriceName = productInfo[0]
            }
        } else {
            return ""
        }
    }
    return maxPriceName
}

fun isDouble(num: String): Boolean {
    var containDot = false
    for (i in num) {
        if (!(i.isDigit() || (i == '.' && !containDot))) return false
        if (i == '.') containDot = true
    }
	return true
}
/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int {
    if (roman.isEmpty()) return -1
    val romans = listOf("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")
    val arab = listOf(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
    var number = StringBuilder(roman)
	var count = 0
    var result = 0
    while(number.isNotEmpty()) {
	    if (number.indexOf(romans[count]) == 0) {
            result += arab[count]
            number.delete(0, romans[count].length)
        } else {
            count++
        }
        if (count == romans.size) return -1
    }
    return result
}

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> {
    checkSyntax(commands)
    var i = 0
    val exception = IllegalStateException("")
    var conveyor = MutableList(cells, { 0 })
    var position = cells / 2
    var countSteps = 0
	while (i != commands.length) {
        when(commands[i]) {
            '+' -> conveyor[position]++
            '>' -> if (position != conveyor.size - 1) position++ else throw exception
            '<' -> if (position != 0) position-- else throw exception
            '-' -> conveyor[position]--
            '[' -> if (conveyor[position] == 0) i = nextBrace(commands, i)
            ']' -> if (conveyor[position] != 0) i = prevBrace(commands, i)
            ' ' -> {}
	        else -> throw IllegalArgumentException("")
        }
        countSteps++
        if (countSteps == limit) break
        i++
    }
    return conveyor
}

fun checkSyntax(commands: String) {
    val symbols = setOf('+', '-', '<', '>', '[', ']', ' ')
    var deep = 0
    for (command in commands) {
        if (!symbols.contains(command)) throw IllegalArgumentException("")
        when(command) {
            '[' -> deep++
            ']' -> deep--
        }
        if (deep < 0) throw IllegalArgumentException("")
    }
    if (deep != 0) throw IllegalArgumentException("")
}

fun nextBrace(commands: String, pos: Int): Int {
    var deep = 1
	for (i in (pos + 1) until commands.length) {
        if (commands[i] == '[') deep++
        if (commands[i] == ']') {
            if (deep == 1) return i
	        deep--
        }
    }
    if (deep != 0) throw IllegalArgumentException("")
    return 0
}

fun prevBrace(commands: String, pos: Int): Int {
    var deep = 1
    for (i in (pos - 1) downTo 0) {
        if (commands[i] == ']') deep++
        if (commands[i] == '[') {
            if (deep == 1) return i
	        deep--
        }
    }
    if (deep != 0) throw IllegalArgumentException("")
	return 0
}

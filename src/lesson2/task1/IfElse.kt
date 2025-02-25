@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1


import lesson1.task1.discriminant
import kotlin.math.*
import kotlin.math.max
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

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
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    var tempage = age
    tempage %= 100
    return if (tempage !in 11..14) {
        when (age % 10) {
            1 -> "$age год"
            2, 3, 4 -> "$age года"
            else -> "$age лет"
        }
    } else "$age лет"
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val s1 = t1 * v1
    val s2 = t2 * v2
    val s3 = t3 * v3
    val length = s1 + s2 + s3
    val half = length / 2

    return when {
        s1 > half -> half / v1
        (s1 + s2) > half -> {
            val cut = half - s1
            ((cut / v2) + t1)
        }
        else -> {
            val cut2 = half - s1 - s2
            ((cut2 / v3) + t1 + t2)
        }
    }

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
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    val kingRookXY1 = (kingX - rookX1 == 0) || (kingY - rookY1 == 0)
    val kingRookXY2 = (kingX - rookX2 == 0) || (kingY - rookY2 == 0)
    return when {
        ((kingRookXY1) && (kingRookXY2)) -> 3
        (kingRookXY1) -> 1
        (kingRookXY2) -> 2
        else -> 0
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
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {
    val kingBishopPlus = (kingX + kingY) == (bishopX + bishopY)
    val kingBishopMinus = (kingX - kingY) == (bishopX - bishopY)
    val kingRookXY = (kingX - rookX == 0) || (kingY - rookY == 0)

    val kingRookX = kingX - rookX
    val kingRookY = kingY - rookY

    return when {
        (((kingBishopPlus) || (kingBishopMinus)) && (kingRookXY)) -> 3
        ((kingBishopPlus) || (kingBishopMinus)) -> 2
        ((kingRookX == 0) || kingRookY == 0) -> 1
        else -> 0
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
    if (a == b && b == c) return 0
    if ((a + b < c) || (a + c < b) || (b + c < a)) return -1
    val angleA = Math.toDegrees(acos((a.pow(2) + c.pow(2) - b.pow(2)) / (2 * a * c)))
    val angleB = Math.toDegrees(acos((a.pow(2) + b.pow(2) - c.pow(2)) / (2 * a * b)))
    val angleC = Math.toDegrees(acos((b.pow(2) + c.pow(2) - a.pow(2)) / (2 * c * b)))
    return when {
        (angleA > 0.0 && angleA < 90.0) && (angleB > 0.0 && angleB < 90.0) && (angleC > 0.0 && angleC < 90.0) -> 0
        angleA == 90.0 || angleB == 90.0 || angleC == 90.0 -> 1
        (angleA > 90.0 && angleA < 180.0) || (angleB > 90.0 && angleB < 180.0) || (angleC > 90.0 && angleC < 180.0) -> 2
        else -> -1
    }
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    return if ((d < a) || (b < c)) -1
    else min(b, d) - max(a, c)
}

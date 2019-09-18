@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1


import lesson1.task1.discriminant
import kotlin.math.*
import kotlin.math.abs
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
    if (tempage > 100) {
        tempage -= 100
    }
    return if (tempage != 11 && tempage != 12 && tempage != 13) {
        when (age % 10) {
            1 -> "$age год"
            2, 3, 4, 5 -> "$age года"
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

    if (s1 > half) return (half / v1)
    else if ((s1 + s2) > half) {
        val cut = half - s1
        return ((cut / v2) + t1)
    } else {
        val cut2 = half - s1 - s2
        return ((cut2 / v3) + t1 + t2)
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
    return if ((kingX - rookX1 == 0 || kingY - rookY1 == 0) && (kingX - rookX2 == 0 || kingY - rookY2 == 0)) 3
    else if (kingX - rookX1 == 0 || kingY - rookY1 == 0) 1
    else if (kingX - rookX2 == 0 || kingY - rookY2 == 0) 2
    else 0
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
    return if (((kingX + kingY == bishopX + bishopY) || (abs(kingX - kingY) == abs(bishopX - bishopY))) && (((kingX - rookX == 0) || (kingY - rookY) == 0))) 3
    else if ((kingX + kingY == bishopX + bishopY) || (abs(kingX - kingY) == abs(bishopX - bishopY))) 2
    else if ((kingX - rookX == 0) || (kingY - rookY) == 0) 1
    else 0
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
    var angleA = Math.toDegrees(acos((a.pow(2) + c.pow(2) - b.pow(2)) / (2 * a * c)))
    var angleB = Math.toDegrees(acos((a.pow(2) + b.pow(2) - c.pow(2)) / (2 * a * b)))
    var angleC = Math.toDegrees(acos((b.pow(2) + c.pow(2) - a.pow(2)) / (2 * c * b)))
    return if (angleA in 0..89 && angleB in 0..89 && angleC in 0..89) 0
    else if (angleA == 90.0 || angleB == 90.0 || angleC == 90.0) 1
    else if (angleA in 91..180 || angleB in 91..180 || angleC in 91..180) 2
    else -1
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
    if ((d < a) || (b < c)) return -1
    return if (a < c) {
        if (b <= d) {
            b - c
        } else {
            d - c
        }
    } else {
        if (b <= d) {
            b - a
        } else {
            d - a
        }
    }

}

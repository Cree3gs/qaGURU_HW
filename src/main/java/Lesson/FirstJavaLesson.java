package Lesson;

public class FirstJavaLesson {

    public static void main(String[] args){
        int a = 21, b=9;
        double c = 0.05, d = 177.75;

        System.out.println("a + b = " + (a + b));
        System.out.println("b - a = " + (b - a));
        System.out.println("a * b = " + (a * b));
        System.out.println("a / b = " + (a / b));
        System.out.println("b % a = " + (b % a));
        System.out.println("a++ = " + (a++));
        System.out.println("a-- = " + (a--));

        System.out.println("Применил несколько арифметических операций над int и double в одном выражении = " + ((a + c) * b / (d + c) * 8 - b));

        System.out.println("a == a = " + (a == a) );
        System.out.println("a != d = " + (a != d) );
        System.out.println("a > b = " + (a > b) );
        System.out.println("b < d = " + (b < d) );
        System.out.println("c >= a = " + (c >= a) );
        System.out.println("c <= a = " + (c <= a) );

        System.out.println("float: 32 бита, диапазон — от "+ Float.MIN_VALUE+ " до "+Float.MAX_VALUE);
        System.out.println("double: 64 бита, диапазон — от "+ Double.MIN_VALUE+ " до "+Double.MAX_VALUE);

        System.out.println("Переполнение при арифметической операции с типом данных float/double происходит,\n когда присваивают значение, " +
                                   "выходящее за пределы объявленного типа данных переменной.\n Если значение слишком велико или слишком мало.");
         float maxValueFloat = Float.MAX_VALUE * 10, minValueFloat = Float.MIN_VALUE - 1;
        double maxValueDouble = Double.MAX_VALUE * 10, minValueDouble = Double.MIN_VALUE - 1;
        System.out.println("Переполнение Float:\n Float.MAX_VALUE * 10 = " + maxValueFloat + " - Положительное\n Float.MIN_VALUE - 1 = " + minValueFloat + " - Отрицательное");
        System.out.println("Переполнение Double:\n Double.MAX_VALUE * 10 = " +  maxValueDouble + " - Положительное\n Double.MIN_VALUE - 1 " +
                                   "= " + minValueDouble + " - Отрицательное");
    }
}

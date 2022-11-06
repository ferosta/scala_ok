import scala.annotation.tailrec
import scala.io.StdIn

object Main {
  def main(args: Array[String]): Unit = {
    Task_3a()
    Task_3b()
    Task_3c(Read_Data_From_Keyboard = false)
    Task_3d()
    Task_3e()
    Task_3f()
    Task_3g()
    Task_3h()
    Task_3i_star()
    Task_3k_star()
    Task_3l_star()
    Task_3m_star()
    Task_3n_star()
    Task_3oi_star()
    Task_3oii_2stars()

  }

  /** Решение задачи 3а из модуля 2.2.
   * Параметры для модификации под условия задачи 3n
   * @param Scala - кого будем приветствовать (на англ и на русском)
   * @param ByBy_String - чем будем завершать приветствие
   * @param Print_Task_Text - надо ли печатать текст задания
   */
  def Task_3a(Scala:(String,String)=("Scala","Скала"), ByBy_String:String = "И ты, Питон :)", Print_Task_Text : Boolean = true): Unit = {
    if (Print_Task_Text) println(
      """
      ******************************************************
      3a.    Напишите программу, которая:
      i.   выводит фразу «Hello, Scala!» справа налево
      ii.  переводит всю фразу в нижний регистр
      iii. удаляет символ!
      iv.  добавляет в конец фразы «and goodbye python!»
      """)
    //привет из задания 3.n*
    val StrEn = s"Hi, ${Scala._1}!"
    val StrRu = s"Привет, ${Scala._2}!"
    val Str = StrEn + " *** " + StrRu

    println("0\t: " + Str)
    print("i1\t: ")
    for (i <- (Str.length - 1) to 0 by -1) print(Str(i))
    println()
    println("i2\t: " + (for (i <- (Str.length - 1) to 0 by -1) yield Str(i)).mkString)
    println("i3\t: " + Str.reverse)
    println("ii\t: " + Str.toLowerCase)
    println("iii\t: " + Str.replaceFirst("!", ""))
    println("iv\t: " + Str.concat(s" $ByBy_String"))
  }

  def Do_Monthly_Income(Salary: Double, Prize: Double, Food_Compensation: Double): Double =
    ((Salary * (100 + Prize) / 100 + Food_Compensation) / 12 * 100).toInt * 0.01


  def Task_3b(): Unit = {
    println(
      """
        *************************************************
        b. Напишите программу, которая вычисляет ежемесячный оклад сотрудника
        после вычета налогов. На вход вашей программе подается
        - значение годового дохода до вычета налогов,
        - размер премии – в процентах от годового дохода и
        - компенсация питания.""")
    val Salary: Double = StdIn.readLine("Введи значение годового дохода до вычета налогов: ").toDouble
    val Prize: Double = StdIn.readLine("Введи размер премии – в процентах от годового дохода: ").toDouble
    val Food_Compensation: Double = StdIn.readLine("Введи значение компенсации питания: ").toDouble

    println(s"Оклад: $Salary")
    println(s"Премия: $Prize")
    println(s"Компенсация питания: $Food_Compensation")
    //    val Monthly_Income = ((Salary * (100+Prize)/100 + Food_Compensation ) / 12 *100).toInt*0.01
    val Monthly_Income = Do_Monthly_Income(Salary, Prize, Food_Compensation)
    println(s"Ежемесячный доход сотрудника: $Salary * (100+$Prize)/100 + $Food_Compensation: " + Monthly_Income + " руб.")
  }


  def Task_3c(Read_Data_From_Keyboard: Boolean = true, Print_Task_Text: Boolean = true): List[Double] = {
    if (Print_Task_Text) println(
      """
        *************************************************
        3c. Напишите программу, которая рассчитывает для каждого сотрудника
        отклонение(в процентах) от среднего значения оклада на уровень всего отдела.
        В итоговом значении должно учитываться в большую или меньшую сторону отклоняется размер оклада.
        На вход вышей программе подаются все значения, аналогичные предыдущей программе,
        а также список со значениями окладов сотрудников отдела 100, 150, 200, 80, 120, 75.
        """)
    // исходные значения окладов в отделе
    val Salaries = List[Double](100, 150, 200, 80, 120, 75)
    println(Salaries + "\t- Оклады сотрудников.")

    // ввод значений надбавки и компенсации
    val Prize = if (!Read_Data_From_Keyboard) 10 else StdIn.readLine("Введи размер премии – в процентах от годового дохода: ").toDouble / 100.0
    val Food_Compensation = if (!Read_Data_From_Keyboard) 5 else StdIn.readLine("Введи значение компенсации питания: ").toDouble

    //    val Prize = StdIn.readLine("Введи размер премии – в процентах от годового дохода: ").toDouble / 100.0
    //    val Food_Compensation = StdIn.readLine("Введи значение компенсации питания: ").toDouble
    // окладды с начислениями - ежемесячно
    //    val Monthly_Incomes = Salaries.map(x => (x * (100+Prize)/100 + Food_Compensation) / 12  ).map(x => (100*x).toInt*0.01)
    val Monthly_Incomes = Salaries.map(x => Do_Monthly_Income(x, Prize, Food_Compensation))
    println(Monthly_Incomes + "\t- Ежемесячные оклады с начисленяими.")

    // средний оклад по отделу
    val Average_Income = (Monthly_Incomes.sum / Monthly_Incomes.length * 100).toInt * 0.01
    println("Средний оклад по отделу: " + Average_Income)

    //отклонение от среднего
    val Average_Incomes = Monthly_Incomes.map(x => -100 + 100.0 * x / Average_Income).map(x => (100.0 * x).toInt * 0.01)
    println(Average_Incomes + "\t- % отклонение от среднего.")

    Monthly_Incomes
  }

  def Task_3d(): Unit = {
    println(
      """
        *************************************************
        d. Попробуйте рассчитать новую зарплату сотрудника, добавив(или отняв, если сотрудник плохо себя вел)
        необходимую сумму с учетом результатов прошлого задания.
        Добавьте его зарплату в список и вычислите значение самой высокой зарплаты и самой низкой.
        """)
    // список доходов сотрудников из предыдущего задания
    val Monthly_Incomes = Task_3c(Read_Data_From_Keyboard = false, Print_Task_Text = false)
    //номер сотрудника в списке  сотрудника

    val Employee_Number = 1
    //новое значение зарплаты работника
    val New_Employee_Income: Double = 5.59
    // значения берутся из предыдущего задания
    // println( Monthly_Incomes +  "\t- исходные ежемесячные оклады сотрудников.")

    println(s"У работника #$Employee_Number меняем доход на значение $New_Employee_Income")
    // новый список с доходами: заменяем элемент в позиции N значением S один раз
    val New_Monthly_Incomes = Monthly_Incomes.patch(Employee_Number, Seq(New_Employee_Income), 1).map(x => (x * 100).toInt * 0.01)
    println(New_Monthly_Incomes + "\t- обновленнй список ежемесячных доходов сотрудников.")

    //    // минимальный и максимальный элемент в новом списке
    val Max_Monthly_Income = New_Monthly_Incomes.max // reduceLeft(_ max _)
    val Min_Monthly_Income = New_Monthly_Incomes.min // reduceLeft(_ min _)

    println(s"Самая высокая зарплата: $Max_Monthly_Income")
    println(s"Самая низкая зарплата: $Min_Monthly_Income")


  }

  def Task_3e(Print_Task_Text: Boolean = true): List[Double] = {
    if (Print_Task_Text) println(
      """
        *************************************************
        e. Также в вашу команду пришли два специалиста с окладами 350 и 90 тысяч рублей.
        Попробуйте отсортировать список сотрудников по уровню оклада от меньшего к большему.
      """)
    // исходные значения окладов в отделе
    val Salaries = List[Double](100, 150, 200, 80, 120, 75)
    println(Salaries + "\t- исходный список окладов сотрудников.")

    // новые сотрудники в отделе
    val New_Employers_Salaries = List[Double](350, 90)

    // добавить новых сотрудников в общий список
    val New_Salaries_List = Salaries.patch(Salaries.size, New_Employers_Salaries, 0)
    println(New_Salaries_List + "- список окладов с добавленными сотрудниками")

    // сортировка списка от меньшего к обольшему
    val New_Salaries_List_Sorted = New_Salaries_List.sorted(Ordering[Double])
    println(New_Salaries_List_Sorted + "- отсортированный список")

    New_Salaries_List_Sorted
  }

  def Task_3f(Print_Task_Text: Boolean = true): List[Double] = {
    if (Print_Task_Text) println(
      """
        *************************************************
        f. Кажется, вы взяли в вашу команду еще одного сотрудника и предложили ему оклад 130 тысяч.
        Вычислите самостоятельно номер сотрудника в списке так,
        чтобы сортировка не нарушилась и добавьте его на это место.
      """)
    //исходные данные
    val Salaries_List_Sorted = Task_3e(false)
    // новые сотрудники в отделе
    val New_Employer_Salary: Double = 130

    //индекс первого элемента списка, который больше вставляемого элемента
    val Index_To_Insert = Salaries_List_Sorted.indexWhere(x => x > New_Employer_Salary)

    // вставляем в позицию найденного элемента один новый элемент
    val New_Salaries_List_Sorted = Salaries_List_Sorted.patch(Index_To_Insert, List(New_Employer_Salary), 0)

    println(New_Salaries_List_Sorted + "- список с новым элементом по-прежнему отсортирован")

    New_Salaries_List_Sorted
  }

  def Task_3g(): Unit = {
    println(
      """
        *************************************************
        g. Попробуйте вывести номера сотрудников из полученного списка, которые попадают под категорию middle.
         На входе программе подается «вилка» зарплаты специалистов уровня middle.
      """)
    //исходные данные
    val Salaries_List_Sorted = Task_3f(false)
    println(Salaries_List_Sorted + " - Исходный список зарплат")

    // вилка зарплат сотрудников
    val Middle_Min_Salary: Double = 100
    val Middle_Max_Salary: Double = 150
    println(s"Ищем индексы в списке для сотрудников с зарплатами от $Middle_Min_Salary до $Middle_Max_Salary")

    // вариант с коллект
    val Middle_Indexes_v1 = Salaries_List_Sorted.zipWithIndex.collect { case (elm, index) if (Middle_Min_Salary <= elm) && (elm <= Middle_Max_Salary) => index }
    println(Middle_Indexes_v1 + "- индексы искомых сотрудников")

    // вариант с фильтром и мап
    val Middle_Indexes_v2 = Salaries_List_Sorted.zipWithIndex.filter(x => (Middle_Min_Salary <= x._1) && (x._1 <= Middle_Max_Salary)).map(x => x._2)
    println(Middle_Indexes_v2 + "- индексы искомых сотрудников")

  }

  def Task_3h(): Unit = {
    println(
      """
        *************************************************
        h.Однако наступил кризис и ваши сотрудники требуют повысить зарплату.
        Вам необходимо проиндексировать зарплату каждого сотрудника на уровень инфляции – 7%
        """)
    //исходные данные
    val Salaries_List_Sorted = Task_3f(false)
    //    println(Salaries_List_Sorted + " - исходный список зарплат")
    //уровень инфляции
    val Inflation_Rate = 7.0

    val Crisis_Salaries_List_ = Salaries_List_Sorted.map(x => x * (100 + Inflation_Rate) * 0.01).map(x => (x * 100).toInt * 0.01)
    println(Crisis_Salaries_List_ + s" - кризисный список зарплат, инфл.уровень $Inflation_Rate%")

  }

  def Task_3i_star(): Unit = {
    println(
      """
        *************************************************
        i. *Ваши сотрудники остались недовольны и просят индексацию на уровень рынка.
        Попробуйте повторить ту же операцию, как и в предыдущем задании, но теперь вам нужно проиндексировать зарплаты
        на процент отклонения от среднего по рынку с учетом уровня специалиста.
        На вход вашей программе подается 3 значения – среднее значение зарплаты на рынке
        для каждого уровня специалистов(junior, middle и senior)""")
    //исходные данные
    val Salaries_List = Task_3f(false)
    //    println(Salaries_List_Sorted + " - исходный список зарплат")

    //средние значения зарплат для каждого уровня специалистов(junior, middle и senior)
    val Avg_Junior = 50.0
    val Avg_Middle = 150.0
    val Avg_Senior = 250.0
    val Avg = List[Double](Avg_Junior, Avg_Middle, Avg_Senior)
    println(Avg + "- средние зарплаты по рынку ")
    //из средних значений получаем интервалы по зарплатам для трех групп:разбиваем на пары, в них находим средние, добавляем слева ноль, справа - максимум
    val Stages_Salaries_Tab = Avg.sliding(2).toList.map(_.sum / 2).patch(Avg.size, Seq(1000.0), 0).patch(0, Seq(0.0), 0)
    println(Stages_Salaries_Tab + "- границы по зарплатам")

    val Stages_Salaries_Bounds = Stages_Salaries_Tab.sliding(2).toList
    println(Stages_Salaries_Bounds + "- интервалы по зарплатам")

    // каждому работнику сопоставляем его уровень в соотв. с интервалами
    val Employers_Stages_Numbers = Salaries_List.map(x => Stages_Salaries_Bounds.zipWithIndex.filter(x >= _._1(0)).filter(x < _._1(1)).map(_._2).last)
    println(Employers_Stages_Numbers + "- распределение работников по группам")

    //    //    val New_Salaries_List = Salaries_List.zipWithIndex.map(x => s"${x._1}/${Avg(Employers_Stages_Numbers(x._2))}" )
    //    val New_Salaries_List = Salaries_List.zipWithIndex.map(x => x._1/Avg(Employers_Stages_Numbers(x._2)) )
    //    println(New_Salaries_List + "- новые зарплаты")
    //какие группы образовались
    val Stages_Numbers_List = Employers_Stages_Numbers.groupBy(x => x).keys
    //группировка зарплат по группам
    val Salaries_by_Stages = Stages_Numbers_List.map(x => Employers_Stages_Numbers.zipWithIndex.filter(_._1 == x).map(z => Salaries_List(z._2)))
    println(Salaries_by_Stages + "-  зарплаты в отделе по уровню работников")
    //средние по группам для имеющихся зарплат
    val Avg_Salaries_by_Stages = Salaries_by_Stages.map(x => x.sum / x.length).map(x => (100.0 * x).toInt * 0.01)
    println(Avg_Salaries_by_Stages + "- средние зарплаты в отделе по уровню работников")
    val Inflation_Ratio_for_Stages = Avg_Salaries_by_Stages.zip(Avg).map(x => x._1 / x._2).map(x => (100.0 * x).toInt * 0.01).toList
    println(Inflation_Ratio_for_Stages + "- коэффициенты для групп работников")

    val Inflation_Ratio_for_Employers = Employers_Stages_Numbers.zipWithIndex.map(x => Inflation_Ratio_for_Stages(x._1))
    println(Inflation_Ratio_for_Employers + "- коэффициенты для каждого работника")

    val New_Salaries_List = Salaries_List.zip(Inflation_Ratio_for_Employers).map(x => if (x._2 > 1) x._1 * (x._2 - 1) else x._1 * (1 + x._2)).map(x => (100.0 * x).toInt * 0.01)
    println(New_Salaries_List + "- ура! Новые зарплаты")

  }

  def Task_3k_star(Print_Task_Text: Boolean = true): Map[(String, String), Double] = {
    if (Print_Task_Text) println(
      """
      *************************************************
      k. *Попробуйте деанонимизировать ваших сотрудников – составьте структуру, которая позволит
      иметь знания о том, сколько зарабатывает каждый сотрудник(Фамилия и имя).""")
    val Second_Names = List[String]("Иванов", "Петров", "Сидорова", "Айзеншпис", "Кох", "Цыбуля", "Гогиберидзе", "Юняускас", "Бек", "Тарасюк")
    val Names = List[String]("Вася", "Коля", "Ася", "Ли", "Алэн", "Ныкола", "Вики", "Арно", "Изя", "Петро")
    val Full_Names = Second_Names zip Names
    val Salaries = List[Double](100.0, 150.0, 200.0, 80.0, 120.0, 75.0, 50.0, 120.0, 90.0, 180.0)
    val Workers = (Full_Names zip Salaries).toMap
    println(Workers + "\t- сотрудники с зарплатами.")

    Workers
  }

  def Task_3l_star(Print_Task_Text: Boolean = true): Unit = {
    if (Print_Task_Text) println(
      """
    *************************************************
    l.*Выведите фамилию и имя сотрудников с самой высокой и самой низкой зарплатой(только не рассказывайте им об этом факте).""")

    val Workers = Task_3k_star(Print_Task_Text = false)

    val Max_Salary_Worker = Workers.toList.maxBy(_._2)._1.toString().replaceAll("""[()]""", "").replace(",", " ")
    val Min_Salary_Worker = Workers.toList.minBy(_._2)._1.toString().replaceAll("""[()]""", "").replace(",", " ")

    println(Max_Salary_Worker + "\t- работник с самой высокой зарплатой")
    println(Min_Salary_Worker + "\t- работник с самой низкой зарплатой")

  }

  def Task_3m_star(Print_Task_Text: Boolean = true): Unit = {
    if (Print_Task_Text) println(
      """ *************************************************
          m.*Попробуйте запутать тех, кто может случайно наткнуться на эти данные –
          удалите для каждого сотрудника имя, переведите строку в нижний регистр,
          удалите гласные и
          разверните оставшиеся символы справа налево(abc -> cb).""")
    val Workers = Task_3k_star(Print_Task_Text = false)

    val Anonymous_Workers = Workers.map { case (name, salary) => (name._1.toLowerCase.replaceAll("""[аеийоуыэюя]""", "").reverse, salary) }
    println(Anonymous_Workers + " - анонимизированный список работников")

  }

  def Task_3n_star(Print_Task_Text: Boolean = true): Unit = {
    if (Print_Task_Text) println(
      """ *************************************************
        n.*Опробуйте завернуть программу из пункта 3.а в функцию и входные значения переделать в параметры функции.""")

    Task_3a(Scala = ("PostgreSQL", "Российская импортозамещающая СУБД"), ByBy_String = "И ты, MongoDB! :)")

  }

  def Recursive_Power(Base: Int=2, Pow:Int): Long = {
    if (Pow <= 1) Base
    else Base * Recursive_Power(Base=Base,Pow=Pow-1)

  }

  def Task_3oi_star(Print_Task_Text: Boolean = true): Unit = {
    if (Print_Task_Text) println(
      """ *************************************************
        o. *Попробуйте написать функцию, которая вычисляет значение степени двойки:
           i. С помощью обычной рекурсии""")

    val N = 2
    val P = 7
    println(s"$N в степени $P = " + Recursive_Power(Pow = P))
  }

  @tailrec
  def Recursive_Power_Xvost(Das_Xvost:Long=2, Pow: Int, Base: Int = 2 ): Long = {
    if (Pow <= 1) Das_Xvost
    else Recursive_Power_Xvost(Das_Xvost=Base*Das_Xvost, Pow=Pow - 1, Base=Base)
    //если базу зафиксировать (=2), то из параметров его можно убрать, а в коде заменить на 2
  }

  /**
   * Решение задания 3.o.ii** из раздела 2.2.
   * @param Print_Task_Text - надо ли печатать задание
   */
  def Task_3oii_2stars(Print_Task_Text: Boolean = true): Unit = {
    if (Print_Task_Text) println(
      """ *************************************************
        o. *Попробуйте написать функцию, которая вычисляет значение степени двойки:
          ii.**С помощью хвостовой рекурсии """)

    val Bs = 2 // основание
    val Pw = 7 // степеь
    println(s"$Bs в степени $Pw = Хвостовая рекурсия = " + Recursive_Power_Xvost(Das_Xvost = Bs, Pow = Pw, Base=Bs))
  }
}
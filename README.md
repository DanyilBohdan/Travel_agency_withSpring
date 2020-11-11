# :musical_note: Завдання
Spring framework homework

Rework your pet project with Spring framework, using java configuration,
DAO layer leave as is, without usage of ORM frameworks.

# :musical_note: Туристична агенція

1. Турагенство має каталог Туров
2. Для каталогу реалізувати можливість вибірки турів:
   - за типом (відпочинок, екскурсія, шопінг);
   - за ціною;
   - за кількістю осіб;
   - за типом готелю.

2. Користувач реєструється в системі, обирає Тур і робить Замовлення. 
   Після замовлення тур має статус 'зареєстрований'.
   Незареєстрований користувач не має можливості замовляти тур.
   Користувач має особистий кабінет, в якому міститься коротка інформація про нього, а також список обраних турів і їх поточний статус 
   (зареєстрований, сплачений, скасований).

3. Менеджер визначає тур як 'палаючий'. 'Палаючі' тури завжди відображаються нагорі переліку.
   Менеджер переводить статус туру з 'зареєстрований' у 'оплачений' або 'скасований'.

4. На кожен замовлений тур визначається знижка з кроком, який встановлюється менеджером, але не більше відсотка, який так само визначає менеджер.

5. Адміністратор системи володіє такими ж правами, як і менеджер, а додатково може:
   - додати/видалити тур, змінити інформацію про тур;
   - заблокувати/розблокувати користувача.

---
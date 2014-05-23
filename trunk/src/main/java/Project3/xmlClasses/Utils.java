package Project3.xmlClasses;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 5/23/14
 * Time: 4:39 AM
 */

public class Utils {
	private static String[] authors = new String[]{
			"А.Роговець", "О.Безвіконний", "С.Вересоцький", "Д.Папаря", "Є.Беспалов",
			"Є.Грязнов", "Н.Остапчук", "С.Давиденко", "Н.Бойко", "В.Стус", "Д.Дмитрієва",
			"І.Пастушенко", "О.Медвідь", "Є.Кресь", "А.Микитк", "В.Мазаєва", "Т.Дємідова",
			"Я.Кириченко", "Л.Дорнер", "Ю.Білановська", "Н.Харченко", "В.Петренко",
			"П.Петрушко", "А.Гранатова", "А.Котюк", "І.Римарчук", "Н.Кольчіба", "А.Ярова",
			"Н.Мельниченко", "М.Ковальова", "Є.Майковець", "А.Антончик", "М.Третьяк",
			"Т.Пиженко", "Ю.Богдан", "О.Кухаренко", "М.Крищанович", "Т.Ковердун" };

	private static String[] cardTypes = new String[]{
			"Вітання", "Реклама", "Звичайна" };

	private static String[] countrys = new String[]{
			"US", "UA", "EN", "FR", "TU", "IT", "BR", "BL", "CH", "JP" };

	private static String[] valueTypes = new String[]{
			"Історична ціність", "Колеційна цінність", "Тематична цінність"};

	private static String[] themes = new String[]{
			"Іграшки", "Їжа", "Ілюстратори", "Бібліотеки", "Аеропортти", "Акули", "Вироби",
			"Вітрильний спорт", "Відомі особи", "Авіалінії", "Банки", "Ведмеді", "Гейзери",
			"Водні види спорту", "Годинники", "Дисней", "Канатна дорога", "Веслувальні човни",
			"Велосипеди", "Велоспорт", "Ворота", "Динозаври", "Водонапірні башти", "Панди",
			"Дюни", "Дороги", "Жаби", "Дорогоцінні камені", "Жирафи", "Корови", "Мініатюри",
			"Китайський зодіак", "Зима", "Весна", "Літо", "Осінь", "Меблі", "Музичні інструменти",
			"Лисиці", "Коали", "Легенди", "Міські мури", "Медицина", "Пароплави", "Птахи", "Річки",
			"Обзерваторії", "Парки", "Острови", "Музеї", "Поети", "Традиції", "Странспорт",
			"Різдво", "Ринки", "Символи", "Фізики", "Школи", "Університети", "Фестивалі",
			"Рептилії", "Римська імперія", "Ссавці", "Рись", "Пошта", "Прапори", "Статуї",
			"Революції", "Плавання", "Соняшники" };

	private static String pickAuthor() {
		return authors[(int)(Math.random() * authors.length)];
	}

	public static String pickCardType() {
		return cardTypes[(int)(Math.random() * cardTypes.length)];
	}

	public static String pickCountry() {
		return countrys[(int)(Math.random() * countrys.length)];
	}

	public static String pickValue() {
		return valueTypes[(int)(Math.random() * valueTypes.length)];
	}

	public static int pickYear(){
		return (int)(Math.random() * 115 + 1900);
	}

	public static List<String> generateAuthors(){
		return new ArrayList<String>(){{
			for (int i = 0; i < (int)(Math.random() * 3 + 1); i++)
				add(pickAuthor());
		}};
	}

	public static String pickTheme(){
		return themes[(int)(Math.random() * themes.length)];
	}
}

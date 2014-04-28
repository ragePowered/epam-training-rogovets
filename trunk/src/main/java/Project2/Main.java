package Project2;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 4/26/14
 * Time: 3:52 PM
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Создать программу обработки текста учебника по программированию с использованием классов:
 * 	+ Символ;
 * 	+ Слово;
 * 	+ Предложение;
 * Во всех задачах с формированием текста заменять табуляции и последовательности пробелов
 * одним пробелом.
 *
 * Преобразовать каждое слово в тексте, удалив из него все последующие
 * вхождения первой буквы этого слова.
 */

public class Main {
	public static void main(String[] args) {
		Word.splitFileAndReplaceFirst("Project2.txt");
	}
}

class Symbol{
	private char symbol;

	Symbol(char symbol) {
		this.symbol = symbol;
	}

	public char getSymbol() {
		return symbol;
	}

	@Override
	public String toString() {
		return String.valueOf(symbol);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Symbol)) return false;

		Symbol symbol1 = (Symbol) o;

		return symbol == symbol1.symbol;
	}

	@Override
	public int hashCode() {
		return (int) symbol;
	}
}

class Word{
	private Symbol[] word;
	private String stringRepresentation;

	Word(String word){
		this.word = new Symbol[word.length()];
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < word.length(); i++){
			this.word[i] = new Symbol(word.charAt(i));
			stringBuilder.append(this.word[i].getSymbol());
		}
		this.stringRepresentation = stringBuilder.toString();
	}

	private static Word[] splitFile(String fileName){
		StringBuilder fileLikeString = new StringBuilder();
		BufferedReader bufferedReader = null;
		int charRepresentation;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		} catch (FileNotFoundException e) { e.printStackTrace(); }
		try {
			while ((charRepresentation = bufferedReader.read()) != -1){
				fileLikeString.append((char) charRepresentation);
			}
		} catch (IOException e) { e.printStackTrace(); }
		String[] strings = fileLikeString.toString().split("([\\s\\.!?\\-,]*\\s)|(\\.)");

		Word[] words = new Word[strings.length];
		for (int i = 0; i < words.length; i++){
			words[i] = new Word(strings[i]);
		}
		return words;
	}

	public static Word[] splitFileAndReplaceFirst(String fileName){
		Word[] words = splitFile(fileName);

		for (int i = 0; i < words.length; i++){
			words[i] = words[i].replaceAllFirst();
			words[i].show();
		}

		return words;
	}

	public Word replaceAllFirst(){
		Symbol first = word[0];
		ArrayList<Symbol> listOfSybols = new ArrayList<>();
		for (Symbol each : word){
			if (!first.equals(each)){
				listOfSybols.add(each);
			}
		}
		this.setWord(listOfSybols.toArray(word));

		for (Symbol each : listOfSybols){
			System.out.print(each);
		}
		System.out.print(" ");
		return this;
	}

	public void show(){
		System.out.println(this);
	}

	public void setWord(Symbol[] word) {
		this.word = word;
	}

	@Override
	public String toString() {
		return stringRepresentation;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Word)) return false;

		Word word1 = (Word) o;

		return stringRepresentation.equals(word1.stringRepresentation) && Arrays.equals(word, word1.word);
	}

	@Override
	public int hashCode() {
		int result = Arrays.hashCode(word);
		result = 31 * result + stringRepresentation.hashCode();
		return result;
	}
}

//class Sentence{
//	private Word[] sentence;
//	private String stringRepreentation;
//
//	Sentence() {}
//	Sentence(Word[] sentence) {
//		this.sentence = sentence;
//		StringBuilder stringBuilder = new StringBuilder();
//		for (Word each : sentence){
//			stringBuilder.append(each).append(".");
//		}
//		stringBuilder.append(".");
//		stringRepreentation = stringBuilder.toString();
//	}
//	Sentence(String sentense){
//		String[] words = sentense.trim().split(" ");
//		StringBuilder stringBuilder = new StringBuilder();
//		this.sentence = new Word[words.length];
//		for (int i = 0; i < words.length; i++){
//			this.sentence[i] = new Word(words[i]);
//			if (i == words.length-1) {
//				stringBuilder.append(words[i]).append(".");
//			} else stringBuilder.append(words[i]).append(" ");
//		}
//		stringRepreentation = stringBuilder.toString();
//	}
//
//	public static Sentence[] splitFile(String fileName){
//		StringBuilder stringBuilder = new StringBuilder();
//		StringBuilder sentenceBuilder = new StringBuilder();
//		BufferedReader bufferedReader = null;
//		int charRepresentation;
//		try {
//			bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
//		} catch (FileNotFoundException e) { e.printStackTrace(); }
//		try {
//			while ((charRepresentation = bufferedReader.read()) != -1){
//				stringBuilder.append((char) charRepresentation);
//			}
//		} catch (IOException e) { e.printStackTrace(); }
//		ArrayList<Sentence> array = new ArrayList<>();
//		String regex = "\\.";
//		String[] sentences = stringBuilder.toString().split(regex);
//		for (String each : sentences){
//			array.add(new Sentence(sentenceBuilder.append(each).toString()));
//			sentenceBuilder.delete(0, sentenceBuilder.length());
//		}
//		Sentence[] result = new Sentence[array.size()];
//		return array.toArray(result);
//	}
//
//	public Word[] getSentence() {
//		return sentence;
//	}
//	public void setSentence(Word[] sentence) {
//		this.sentence = sentence;
//	}
//
//	@Override
//	public String toString() {
//		return stringRepreentation;
//	}
//}

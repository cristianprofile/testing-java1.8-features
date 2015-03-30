package com.cristian.mylab;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PrintJustifiedTextImpl implements PrintJustifiedText {

	private LinkedHashMap<Integer, List<String>> generateMap(String phrase,
			int sizeLine) {
		String[] charArray = phrase.split(" ");

		LinkedHashMap<Integer, List<String>> mapLines = new LinkedHashMap<Integer, List<String>>();

		int numLine = 1;
		int sumCharInLine = 0;
		boolean changeLine = true;

		for (int i = 0; i < charArray.length; i++) {
			List<String> listWordsInLine;
			String word = charArray[i];
			sumCharInLine += word.length();
			if (!changeLine) {
				sumCharInLine++;
			}
			if (sumCharInLine <= sizeLine) {
				changeLine = (sumCharInLine == sizeLine) ? true : false;
				if (mapLines.get(numLine) == null) {
					listWordsInLine = new LinkedList<String>();
				} else {
					listWordsInLine = mapLines.get(numLine);
				}
			} else {
				changeLine = true;
				numLine++;
				sumCharInLine = word.length() + 1;
				listWordsInLine = new LinkedList<String>();
			}
			listWordsInLine.add(word);
			mapLines.put(numLine, listWordsInLine);
		}

		return mapLines;
	}

	private String generateWhiteSpaces(int num) {
		String whitesSpaces = "";
		for (int i = 1; i <= num; i++) {
			whitesSpaces = whitesSpaces + " ";
		}
		return whitesSpaces;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cristian.mylab.PrintJustifiedText#printJustifiedText(java.lang.String
	 * , int)
	 */
	@Override
	public String printJustifiedText(String phrase, int sizeLine) {

		LinkedHashMap<Integer, List<String>> mapLines = this.generateMap(
				phrase, sizeLine);

		int numLine = 0;
		StringBuilder builder = new StringBuilder();
		for (List<String> listWordsInLine : mapLines.values()) {
			numLine++;
			int sumChar = listWordsInLine.stream()
					.mapToInt(word -> word.length()).sum();
			int differenceChar = sizeLine - sumChar;
			int numWhiteSpaces = (listWordsInLine.size() - 1);
			int sizeWhiteSpace = 1;
			int modWhiteSpaces = 0;
			if (numWhiteSpaces != 0) {
				sizeWhiteSpace = differenceChar / numWhiteSpaces;
				modWhiteSpaces = differenceChar % numWhiteSpaces;
			}

			for (String word : listWordsInLine) {
				builder.append(word);
				if (numLine != mapLines.size()) {
					String printWhiteSpace = generateWhiteSpaces(sizeWhiteSpace);
					builder.append(printWhiteSpace);
					if (modWhiteSpaces > 0) {
						builder.append(this.generateWhiteSpaces(1));
						modWhiteSpaces--;
					}
				} else {

					builder.append(this.generateWhiteSpaces(1));
				}
			}
			builder.append("\n");
		}

		return builder.toString();
	}
}

package com.sensis.soccer.decoder;

import java.util.Scanner;

import com.sensis.soccer.model.Team;

public class SoccerDecoder implements Decoder<Team> {

	private static final String DATA_TXT_REGEX = "(\\d+\\.)\\s+\\w+((\\s+\\d+){5})\\s+-((\\s+\\d+){2})";
	private static final String[] IGNORE_TXT_REGEX = { "(-*)", "(\\w+(\\s+\\w+){7})" };

	public Team decode(String line) {
		if (matchesDataTxtRegex(line)) {
			Team.Builder builder = new Team.Builder();
			Scanner scanner = new Scanner(line);
			String srNo = scanner.next();
			builder = builder.name(scanner.next()).played(scanner.nextInt())
					.won(scanner.nextInt()).lost(scanner.nextInt())
					.draw(scanner.nextInt()).goalsFor(scanner.nextInt());
			String next = scanner.next();
			// Not elegant, need to skip the hyphen sign.
			if (matchesDataTxtRegex(next)) {
				builder.goalsAgainst(Integer.parseInt(next));
			} else {
				builder.goalsAgainst(scanner.nextInt());
			}
			builder.points(scanner.nextInt());
			scanner.close();
			return builder.build();

		} else if (matchesIgnoreTxt(line)) {
			return null;
		} else {
			throw new IllegalArgumentException("Invalid data found at line : "+ line);
		}

	}

	private boolean matchesDataTxtRegex(String line) {
		return line.trim().matches(DATA_TXT_REGEX);
	}

	private boolean matchesIgnoreTxt(String text) {
		for (String regex : IGNORE_TXT_REGEX) {
			if (text.trim().matches(regex))
				return true;
		}
		return false;
	}

	@Override
	public boolean isStartText(String text) {
		return text.trim().equals("<pre>");
	}

	@Override
	public boolean isEndText(String text) {
		return text.trim().equals("</pre>");
	}

}

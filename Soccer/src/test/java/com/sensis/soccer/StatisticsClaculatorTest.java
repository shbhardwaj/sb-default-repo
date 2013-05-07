package com.sensis.soccer;
import java.io.File;
import java.util.InputMismatchException;

import junit.framework.Assert;

import org.junit.Test;

import com.sensis.soccer.StatisticsCalculator;
import com.sensis.soccer.decoder.SoccerDecoder;
import com.sensis.soccer.model.NullTeam;
import com.sensis.soccer.model.Team;
import com.sensis.soccer.reader.TextFileReader;

public class StatisticsClaculatorTest {

	@Test
	public void findLeastDifferenceTeam() throws Exception {
		String filePath = "src/test/resources/football.dat";
		TextFileReader<Team> fileReader = TextFileReader.create(new SoccerDecoder(),
				new File(filePath));
		StatisticsCalculator calculator = new StatisticsCalculator(fileReader);
		Team actual = calculator.teamWithLeastDifferenceOfGoals();
		Team expected = new Team.Builder().name("Aston_Villa").played(38)
				.won(12).lost(14).draw(12).goalsFor(46).goalsAgainst(47)
				.points(50).build();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void withEmptyFile() throws Exception {
		String filePath = "src/test/resources/footballEmpty.dat";
		TextFileReader<Team> fileReader = TextFileReader.create(new SoccerDecoder(),
				new File(filePath));
		StatisticsCalculator calculator = new StatisticsCalculator(fileReader);
		Team actual = calculator.teamWithLeastDifferenceOfGoals();
		Assert.assertEquals(new NullTeam(), actual);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void withNegativeValues() throws Exception {
		String filePath = "src/test/resources/footballWithNegativeValue.dat";
		TextFileReader<Team> fileReader = TextFileReader.create(new SoccerDecoder(),
				new File(filePath));
		fileReader.read();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void withInvalidIntValues() throws Exception {
		String filePath = "src/test/resources/footballWithInvalidIntValues.dat";
		TextFileReader<Team> fileReader = TextFileReader.create(new SoccerDecoder(),
				new File(filePath));
		fileReader.read();
	}
}

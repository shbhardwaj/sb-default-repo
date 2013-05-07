package com.sensis.soccer;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sensis.soccer.model.NullTeam;
import com.sensis.soccer.model.Team;
import com.sensis.soccer.reader.FileReader;

/**
 * The class contains methods for performing operations on team statistics.
 * 
 * @author shankar
 */
public class StatisticsCalculator {
	
	private FileReader<Team> fileReader;
	
	public StatisticsCalculator(FileReader<Team> fileReader) {
		this.fileReader = fileReader;
	}
	
	
	public Team teamWithLeastDifferenceOfGoals() throws IOException {
		return teamWithLeastDifferenceOfGoals(fileReader.read());
	}

	private Team teamWithLeastDifferenceOfGoals(List<Team> teams) {
		Collections.sort(teams, new Comparator<Team>() {
			@Override
			public int compare(Team team1, Team team2) {
				int result1 = Math.abs(team1.getGoalsFor()-team1.getGoalsAgainst());
				int result2 = Math.abs(team2.getGoalsFor()-team2.getGoalsAgainst());
				return result1-result2;
			}
		});
		return !teams.isEmpty() ? teams.get(0) : new NullTeam();
	}
	
	
}

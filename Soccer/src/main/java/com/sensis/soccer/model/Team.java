package com.sensis.soccer.model;
public class Team {

	private String name;
	private int played;
	private int won;
	private int lost;
	private int draw;
	private int goalsFor;
	private int goalsAgainst;
	private int points;

	protected Team(Builder builder) {
		
		this.name = builder.name;
		this.played = builder.played;
		this.won = builder.won;
		this.lost = builder.lost;
		this.draw = builder.draw;
		this.goalsFor = builder.goalsFor;
		this.goalsAgainst = builder.goalsAgainst;
		this.points = builder.points;
	}

	public static class Builder {
		private String name;
		private int played;
		private int won;
		private int lost;
		private int draw;
		private int goalsFor;
		private int goalsAgainst;
		private int points;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder played(int played) {
			if(played < 0) throw new IllegalArgumentException("played field cannot be negative");
			this.played = played;
			return this;
		}

		public Builder won(int won) {
			if(won < 0) throw new IllegalArgumentException("won field cannot be negative");
			this.won = won;
			return this;
		}

		public Builder lost(int lost) {
			if(lost < 0) throw new IllegalArgumentException("lost field cannot be negative");
			this.lost = lost;
			return this;
		}

		public Builder draw(int draw) {
			if(draw < 0) throw new IllegalArgumentException("draw field cannot be negative");
			this.draw = draw;
			return this;
		}

		public Builder goalsFor(int goalsFor) {
			if(goalsFor < 0) throw new IllegalArgumentException("goalsFor field cannot be negative");
			this.goalsFor = goalsFor;
			return this;
		}

		public Builder goalsAgainst(int goalsAgainst) {
			if(goalsAgainst < 0) throw new IllegalArgumentException("goalsAgainst field cannot be negative");
			this.goalsAgainst = goalsAgainst;
			return this;
		}
		
		public Builder points(int points) {
			this.points = points;
			return this;
		}

		public Team build() {
			return new Team(this);
		}

	}
	

	public String getName() {
		return name;
	}

	public int getGoalsFor() {
		return goalsFor;
	}

	public int getGoalsAgainst() {
		return goalsAgainst;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + draw;
		result = prime * result + goalsAgainst;
		result = prime * result + goalsFor;
		result = prime * result + lost;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + played;
		result = prime * result + points;
		result = prime * result + won;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Team other = (Team) obj;
		if (draw != other.draw)
			return false;
		if (goalsAgainst != other.goalsAgainst)
			return false;
		if (goalsFor != other.goalsFor)
			return false;
		if (lost != other.lost)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (played != other.played)
			return false;
		if (points != other.points)
			return false;
		if (won != other.won)
			return false;
		return true;
	}
	
	
}

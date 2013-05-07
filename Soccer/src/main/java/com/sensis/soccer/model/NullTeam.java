package com.sensis.soccer.model;
public class NullTeam extends Team {

	public NullTeam() {
		super(new Builder().name("Null Team"));
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}

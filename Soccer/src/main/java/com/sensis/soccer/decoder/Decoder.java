package com.sensis.soccer.decoder;


public interface Decoder<T> {
	
	T decode(String line);
	
	boolean isStartText(String text);

	boolean isEndText(String line);
}

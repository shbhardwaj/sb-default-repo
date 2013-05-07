package com.sensis.soccer.reader;
import java.io.IOException;
import java.util.List;


public interface FileReader<T> {
	
	List<T> read() throws IOException;
	
}

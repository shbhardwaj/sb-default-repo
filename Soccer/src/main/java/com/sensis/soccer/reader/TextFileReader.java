package com.sensis.soccer.reader;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import com.sensis.soccer.decoder.Decoder;

public class TextFileReader<T> implements FileReader<T> {

	private final Decoder<T> decoder;
	private List<T> entries = new ArrayList<>();
	private File file;

	private TextFileReader(Decoder<T> decoder, File file) {
		this.decoder = decoder;
		this.file = file;
	}

	public static <T> TextFileReader<T> create(Decoder<T> decoder, File file) {
		return new TextFileReader<T>(decoder, file);
	}
	
	@Override
	public List<T> read() throws IOException {
		try (FileInputStream fileInputStream = new FileInputStream(file)) {
			FileChannel fileChannel = fileInputStream.getChannel();
			MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0,	fileChannel.size());
			byte[] buffer = new byte[(int) fileChannel.size()];
			mappedByteBuffer.get(buffer);
			try (BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(buffer)))) {
				boolean startDecode = false;
				for (String line = in.readLine(); line != null; line = in.readLine()) {
					if(decoder.isStartText(line)) {
						startDecode = true;
						continue;
					}
					if(decoder.isEndText(line)) {
						break;
					}
					if(startDecode) {
						T result = decoder.decode(line);
						if (null != result) {
							entries.add(result);
						}
					}
				}
			}
		}
		return entries;
	}

}
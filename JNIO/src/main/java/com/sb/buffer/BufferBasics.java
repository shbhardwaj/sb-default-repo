package com.sb.buffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class BufferBasics {
	
	// This would create a byte buffer in Heap.
	ByteBuffer buffer = ByteBuffer.allocate(16);
	
	public void bufferAttributes(){
		int capacity = buffer.capacity();
		int limit = buffer.limit();
		int position = buffer.position();
		System.out.println("capacity = "+capacity);
		System.out.println("limit = "+limit);
		System.out.println("position = "+position);
	}
	
	public static void main(String[] args) {
		new BufferBasics().bufferAttributes();
	}

}

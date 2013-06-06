import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.InvalidMarkException;

import junit.framework.Assert;

import org.junit.Test;


public class BufferBasicsTest {
	
	@Test
	public void newlyConstructedBufferHasPositionZeroCapacityAndLimitEqualsInitialCapacity(){
		// This would create a byte buffer in Heap and its a non direct, read & write buffer. 
		// Direct buffer are created outside of jvm heap using ByteBuffer.allocateDirect(16);
		ByteBuffer buffer = ByteBuffer.allocate(16);
		Assert.assertEquals(16, buffer.capacity());
		Assert.assertEquals(16, buffer.limit());
		Assert.assertEquals(0, buffer.position());
		Assert.assertEquals(false, buffer.isDirect());
		Assert.assertFalse(buffer.isReadOnly());
		
	}
	
	//0 <= mark <= position <= limit <= capacity
	
	@Test
	public void whenResetPositionIsSetToMark() {
		ByteBuffer buffer = ByteBuffer.allocate(16);
		// Initially position is 0 set it to 10 and mark it then change the position to 16 followed by reset, reset will change the position to mark which was set to 10.
		buffer.position(10).mark().position(16);
		Assert.assertEquals(16,buffer.position());
		buffer.reset();
		Assert.assertEquals(10,buffer.position());
		
	}
	
	@Test(expected = InvalidMarkException.class)
	public void resetWithoutMarkThrowsInvalidMarkException(){
		ByteBuffer buffer = ByteBuffer.allocate(16);
		buffer.reset();
	}
	
	@Test
	public void bufferWithAbsolutePutOrGetDoesNotChangePosition() {
		ByteBuffer buffer = ByteBuffer.allocate(16);
		buffer.put(5, (byte)'H');
		Assert.assertEquals(0,buffer.position());
		Assert.assertEquals((byte)'H',buffer.get(5));
	}
	
	@Test
	public void bufferWithRelativePutOrGetAdvancePositionByOne() {
		ByteBuffer buffer = ByteBuffer.allocate(16);
		((ByteBuffer)buffer.position(5)).put((byte)'H');
		Assert.assertEquals(6,buffer.position());
		buffer.get(); // Advances the position by 1.
		Assert.assertEquals(7,buffer.position());
	}
	
	@Test(expected = BufferOverflowException.class)
	public void relativePutBeyondLimitWillThrowBufferOverflowException() {
		ByteBuffer buffer = ByteBuffer.allocate(16);
		buffer.limit(5);
		((ByteBuffer)buffer.position(5)).put((byte)'H');
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void absolutePutBeyondLimitWillThrowIndexOutOfBoundException() {
		ByteBuffer buffer = ByteBuffer.allocate(16);
		buffer.limit(5);
		buffer.put(6,(byte)'H');
	}
	
	@Test(expected = BufferUnderflowException.class)
	public void whenPositionIsNotSmallerThanLimitRelativeGetWillThrowBufferUnderflowException(){
		ByteBuffer buffer = ByteBuffer.allocate(16);
		buffer.limit(5);
		buffer.position(5); // Even at equals it throws exception
		buffer.get();
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void whenPositionIsNotSmallerThanLimitAbsoluteGetWillThrowIndexOutOfBoundException(){
		ByteBuffer buffer = ByteBuffer.allocate(16);
		buffer.limit(5);
		buffer.get(5); // buffer starts at 0
	}
	
	@Test
	public void flipPreapresForReadingBySettingLimitToPositionAndPositionToZeroDiscardsTheMark(){
		ByteBuffer buffer = ByteBuffer.allocate(16);
		buffer.put((byte)'H').put((byte)'E').put((byte)'L').put((byte)'L').put((byte)'O');
		Assert.assertEquals(buffer.position(), 5);
		Assert.assertEquals(buffer.limit(), 16);
		buffer.flip(); // flip prepares a buffer to be read/write.
		Assert.assertEquals(buffer.position(), 0);
		Assert.assertEquals(buffer.limit(), 5); //limit Determines Till What Position Buffer Is Filled Should Be Read Till Limit.
	}
	
	@Test
	public void rewindDoesNotChangeTheLimitUsedToReReadTheBuffer(){
		ByteBuffer buffer = ByteBuffer.allocate(16);
		buffer.put((byte)'H').put((byte)'E').put((byte)'L').put((byte)'L').put((byte)'O');
		Assert.assertEquals(buffer.position(), 5);
		Assert.assertEquals(buffer.limit(), 16);
		buffer.rewind();
		Assert.assertEquals(buffer.position(), 0);
		Assert.assertEquals(buffer.limit(), 16); // Limit is not changed.
	}
	
	@Test(expected = BufferOverflowException.class)
	public void bufferFlippedTwice(){
		ByteBuffer buffer = ByteBuffer.allocate(16);
		buffer.put((byte)'H').put((byte)'E').put((byte)'L').put((byte)'L').put((byte)'O');
		buffer.flip(); // sets the limit to position which is 5 and position to 0.
		buffer.flip(); // sets the limit to position which is 0 and position to 0. Effectively buffer has nothing.
		Assert.assertEquals(buffer.capacity(), 16); // capacity still remains 16.
		// Trying to put/get will result in exception.
		buffer.put((byte)'H');
	}
	
	@Test
	public void bufferLimitReachedCheckedByHasRemaining() {
		byte[] arr = new byte[100];
		ByteBuffer buffer = ByteBuffer.allocate(7);
		buffer.put((byte)'A').put((byte)'B').put((byte)'C').put((byte)'D').put((byte)'E').put((byte)'F').put((byte)'G');
		buffer.flip(); // Need to flip the buffer before reading
		for(int i=0; buffer.hasRemaining();i++) {
			arr[i] = buffer.get();
		}
		Assert.assertEquals((char)arr[0], 'A');
		Assert.assertEquals((char)arr[6], 'G');
		
		buffer.flip(); // Flip it to write to the buffer.
		buffer.put((byte)'H').put((byte)'I').put((byte)'J').put((byte)'K').put((byte)'L').put((byte)'M').put((byte)'N');
		buffer.flip(); // flip it again to read from the buffer
		for(int i=0; buffer.remaining()>0;i++) { // Use this if u have exclusive access to the buffer as limit and position are not going to change.
			arr[i] = buffer.get();
		}
		Assert.assertEquals((char)arr[0], 'H');
		Assert.assertEquals((char)arr[6], 'N');
	}
	
	@Test
	public void whenClearBufferPositionSetToZeroAndLimitToCapacityDoesNotChangeData(){
		ByteBuffer buffer = ByteBuffer.allocate(7);
		buffer.put((byte)'A').put((byte)'B').put((byte)'C');
		buffer.limit(4);
		Assert.assertEquals(3, buffer.position());
		Assert.assertEquals(4, buffer.limit());
		buffer.clear();
		Assert.assertEquals(0, buffer.position());
		Assert.assertEquals(7, buffer.limit());
		// Data is not changed.
		Assert.assertEquals((char)buffer.get(0), 'A');
		Assert.assertEquals((char)buffer.get(1), 'B');
		Assert.assertEquals((char)buffer.get(2), 'C');
	}

}

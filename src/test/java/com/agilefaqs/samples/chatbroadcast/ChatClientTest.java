package com.agilefaqs.samples.chatbroadcast;

import static org.junit.Assert.fail;

import org.junit.Test;

public class ChatClientTest {
	
	@Test
	public void testNullMessageInHandle() {
		ChatClient client = new ChatClient("localhost", 9090);
		try {
			client.handle(null);
		} catch (Exception e) {
			e.printStackTrace();
			fail("handle(msg)  should handle null messages");
		}
	}

	@Test
	public void testValidMessageInHandle() {
		ChatClient client = new ChatClient("localhost", 9090);
		try {
			client.handle("Hello");
		} catch (Exception e) {
			e.printStackTrace();
			fail("Exception should not be caught while handle(msg) is called");
		}
	}
}
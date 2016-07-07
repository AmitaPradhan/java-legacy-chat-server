package com.agilefaqs.samples.chatbroadcast;

import java.io.IOException;
import java.net.Socket;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ChatServerTest {

	private static ChatServer server;
	private static int portNo;
	private static String host;

	@BeforeClass
	public static void setUp() {
		System.out.println(System.getProperty("user.dir"));
		portNo = 9090;
		server = new ChatServer(portNo);
		host = "localhost";
	}

	@Test
	public void testAddNullSocket() {
		int initialClientCount = server.getClientCount();
		try {
			Socket socket = null;
			assertFalse("Null socket should not get added", server.addThread(socket));
		} catch (Exception e) {
			fail("add thread should handle null sockets");
		}
		assertEquals("Client Count should not be incremented on adding null socket", initialClientCount , server.getClientCount());
	}

	@Test
	public void testAddValidSocket() {
		int initialClientCount = server.getClientCount();
		Socket socket;
		try {
			socket = new Socket(host, portNo);
			assertFalse("socket should get added", server.addThread(socket));
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals("Client Count should be incremented on adding socket", initialClientCount + 1, server.getClientCount());
	}

	@Test
	public void testFindClientForInvalidId() {
		assertEquals("-1 should be returned in case of invalid id", -1, server.findClient(-100));
	}

	@Test
	public void testFindClientForValidId() {
		Socket socket = null;
		try {
			socket = new Socket(host, portNo);
		} catch (IOException e) {
			e.printStackTrace();
			fail("failed to create socket");
		}
		server.addThread(socket);
		assertNotEquals("-1 should be returned in case of invalid id", -1, server.findClient(portNo));
	}



}
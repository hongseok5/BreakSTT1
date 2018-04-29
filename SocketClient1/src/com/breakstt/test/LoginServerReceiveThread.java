package com.breakstt.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class LoginServerReceiveThread extends Thread {
	private Socket socket;
	
	public LoginServerReceiveThread(Socket socket){
		this.socket = socket;
	}
	
	@Override
	public void run() {
		InetSocketAddress remoteSocketAddress =
				(InetSocketAddress)socket.getRemoteSocketAddress();
		int remoteHostPort = remoteSocketAddress.getPort();
		String remoteHostAddress =
				remoteSocketAddress.getAddress().getHostAddress();
		consoleLog("connected from" + remoteHostAddress + ":" + remoteHostPort);
		try {
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			while(true) {
				byte[] data = is.readAllBytes();
				System.out.println("Received Data is : ");
				for(int i = 0; i < data.length; i++)
				System.out.print(data[i]);
			}
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(socket != null && socket.isClosed() == false) {
					socket.close();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void consoleLog(String log) {
		System.out.println("[server:" + getId() + "]" + log);
	}
}

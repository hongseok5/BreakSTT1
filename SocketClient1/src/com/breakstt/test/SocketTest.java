package com.breakstt.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import com.breakstt.util.DataBody;
import com.breakstt.util.DataHeader;
import com.breakstt.util.DataMerging;

public class SocketTest {
	
	private static final String SERVER_IP = "45.119.147.81";
	private static final int SERVER_PORT = 9190;

	public static void main(String[] args) {
		
		Socket socket = null;
		String id= "hongseok";
		String passwd = "1234abcd";
		List<String> params= new ArrayList<String>();
		params.add(id);
		params.add(passwd);
		
		DataHeader dh;
		DataBody db;
		
		try {
			socket = new Socket();//1.Socket 생성
			dh = new DataHeader(params, (byte) 76);
			db = new DataBody(params, (byte) 76);
			DataMerging dm = new DataMerging(dh,db);
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));

			OutputStream os = socket.getOutputStream();

			os.write(dm.getMergedData());
			
			os.flush();
			InputStream is = socket.getInputStream();
			//String message = new String(echoData, 0, readByteCount, "UTF-8");


			os.close();
			is.close();

		} catch( ConnectException e ) {
			System.out.println( "[client] not connect" );
			System.out.println( e.toString());
		} catch( SocketTimeoutException e) {
		System.out.println( "[client] read timeout" );
	} catch( IOException e) {
		e.printStackTrace();
	} catch(Exception e)
		{
		 	System.out.println(e.toString());
		}
		finally {
		try {
			if( socket != null && socket.isClosed() == false ) {
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	}
	
}

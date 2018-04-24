package com.breakstt.util;

import java.util.List;

public class DataHeader {
	
	//Header is 64Bytes without STX
	
	private final byte STX = 0x02;
	private final byte DATA_HD_SIZE = 65;
	private final byte DATA_HD_LENGTH = 8;
	
	static final byte[] LOGIN_DATA_SIZE = new byte[] {0,0,0,0,0,0,6,4};

	final static byte NVL = 0x00;
	private byte[] dataHeader = new byte[DATA_HD_SIZE];

	int arrayIndex = 0;
	// DATA TYPE : SQL = I, U, D, S, TEXT = T,  LOGIN = L 
	//  D =1024  I L=64 S=2048  T=9999,9744 U 
	private final byte[] DATA_TYPE = new byte[] {68, 73, 76, 83, 84, 85}; 
	
	/*
	public DataHeader(byte[] length, byte dataType) {
		int arrayIndex = 0;		
		//check dataType and length
		if(length.length != 8 ) {
			System.out.println("DataLength is not valid");
		}
		
		this.dataHeader[arrayIndex++] = STX;
		// first is STX
		
		for(int i=0; i < DATA_HD_LENGTH; i++) {
			this.dataHeader[arrayIndex++] = length[i];
		}
		
		
		for(int i=0; i < DATA_TYPE.length; i++) {
			if(dataType == DATA_TYPE[i]) {
				this.dataHeader[arrayIndex++] = dataType;
			}
			else {
				System.out.println("DataType is not valid");
			}
		}		
		
		for(int i = arrayIndex; i < DATA_HD_SIZE; i++) {
			this.dataHeader[i] = NVL;
			
		}
		for(int i = 0; i < DATA_HD_SIZE; i++) {
			System.out.print(this.dataHeader[i]);
		}
		System.out.println("DataHeader : DataHeader object created successfully!" );		
		
	}
	*/
	public DataHeader(List<String> params, byte dataType) {
		System.out.println("DataHeader : " + params.size());
		int paramNumber = params.size() * 32;
		System.out.println("DataHeader : " + paramNumber);
		//String dataSize = paramNumber.toString();
		//System.out.println("DataHeader : dataSize is " + dataSize);
		
		for(String param:params) {
			if(param.getBytes().length > 32) {
				System.out.println("DataHeader : over 32 bytes");				
			}		
		}
		
		this.dataHeader[arrayIndex++] = STX;
		setDataSize(paramNumber);
		this.dataHeader[arrayIndex++] = dataType;
		for(int i = arrayIndex; i < DATA_HD_SIZE; i++) {
			this.dataHeader[i] = NVL;			
		}
		
		System.out.print("DataHeader : constructor 2 created obj : ");
		for(int i = 0; i < DATA_HD_SIZE; i++) {			
			System.out.print(this.dataHeader[i]);
		}
	}
	
	
	public void setDataSize(int paramNumber) {
		byte[] byteArray = new byte[8];
		byteArray[0] = (byte) (paramNumber / 10000000);
		paramNumber -= byteArray[0] * 10000000;
		byteArray[1] = (byte) (paramNumber / 1000000);
		paramNumber -= byteArray[1] * 1000000;
		byteArray[2] = (byte) (paramNumber / 100000);
		paramNumber -= byteArray[2] * 100000;
		byteArray[3] = (byte) (paramNumber / 10000);
		paramNumber -= byteArray[3] * 10000;
		byteArray[4] = (byte) (paramNumber / 1000);
		paramNumber -= byteArray[4] * 1000;
		byteArray[5] = (byte) (paramNumber / 100);
		paramNumber -= byteArray[5] * 100;
		byteArray[6] = (byte) (paramNumber / 10);
		paramNumber -= byteArray[6] * 10;
		byteArray[7] = (byte) paramNumber;
		for(int i = 0 ; i < byteArray.length; i++) {
			dataHeader[arrayIndex++] = byteArray[i];
		}		
	}
	
	
	public byte[] getDataHeader() {
		return dataHeader;
	}

	public static byte[] getLoginDataSize() {
		return LOGIN_DATA_SIZE;
	}

	
	
}	
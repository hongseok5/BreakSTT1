package com.breakstt.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class DataBody {
	
	private final byte ETX = 0x03;
	private int dataSize = 0; 
	protected byte[] dataBody;
	private final static byte PARAM_SIZE = 32;
		
	public DataBody(byte bodyType, List<String> params) throws UnsupportedEncodingException {
		//assume that all parameter size is below 32 Bytes except main stt data.
		/*
		switch(bodyType) {
			case 68:
				this.dataSize = 1024 + 1;
				break;
				//delete param 
			case 73:
				this.dataSize = 2048 + 1;
				break;
				//insert param 
			case 76:
				this.dataSize = 64 + 1;
				break;
				//login data
			case (byte) 83:
				this.dataSize = 1024 + 1;
				break;
				//select param
			case (byte) 84:
				this.dataSize = 99999744 + 1;
				break;
				//stt data
			case (byte) 85:
				this.dataSize = 512 + 1;
				break;
				//update param
		}
		*/
		this.dataSize = params.size() * 32 + 1;
		System.out.println("DataBody : " + dataSize);
		dataBody = new byte[dataSize];
		dataBody[dataSize - 1] = ETX;
		fillWithNVL(params);
	}


	public void fillWithNVL(List<String> params) throws UnsupportedEncodingException {
		// fill 32byte of id and pw
		int index = 0;
		int paramNumbers = params.size();
		
		
		for(String param:params) {
			byte[] tempFilledData = param.getBytes("UTF-8");
			int gap = PARAM_SIZE - tempFilledData.length;
			for(int i = 0; i < tempFilledData.length; i++ ) {
				dataBody[index++] = tempFilledData[i];
			}
			for(int i = 0; i < gap; i++) {
				dataBody[index++] = 0x00;
			}
		}
	}
	
	
	public byte[] getDataBody() {
		return dataBody;
	}
		
	public int getDataSize() {
		return this.dataSize;
	}
}

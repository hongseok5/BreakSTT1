package com.breakstt.util;

public class DataType {
	
	private static final byte LOGIN = 76;
    private static final byte JOIN = 74;
    private static final byte INSERT = 73;
    private static final byte DELETE = 68;
    private static final byte SELECT = 83;
    private static final byte STT = 84;
    private static final byte UPDATE = 85;

    public static byte getLoginCode(){
        return LOGIN;
    }
    public static byte getJoinCode(){
        return JOIN;
    }
    public static byte getInsertCode(){
        return INSERT;
    }
    public static byte getDeleteCode(){
        return DELETE;
    }
    public static byte getSelectCode(){
        return SELECT;
    }
    public static byte getSttCode(){
        return STT;
    }
    public static byte getUpdateCode(){
        return UPDATE;
    }
    
}

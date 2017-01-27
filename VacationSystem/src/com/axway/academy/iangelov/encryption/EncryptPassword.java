package com.axway.academy.iangelov.encryption;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptPassword {

	private static EncryptPassword ep = null;
	private final static String salt="DGE$5SGr@3VsHYUMas2323E4d57vfBfFSTRU@!DSH(*%FDSdfg13sgfsg";
	
	private EncryptPassword(){
		
	}
	
	public static EncryptPassword getInstance(){
		if(ep == null){
			ep = new EncryptPassword();
		}
		return ep;
		
	}
	
	public String encrypt(String input){
		String output = "";
		if(input == null){
			return null;
		}
		input = input + salt;
		
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(input.getBytes(), 0, input.length());
			output = new BigInteger(1, digest.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Cannot encrypt password " + e.getMessage());
			e.printStackTrace();
		}
		return output;
	}
}

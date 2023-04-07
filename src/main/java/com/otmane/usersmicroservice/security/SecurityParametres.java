package com.otmane.usersmicroservice.security;

public interface SecurityParametres {

	public static final Long EXP_TIME= (long) (24*60*60*1000);
	public static final String SECRET="Elkastaliotmane@gmail.com";
	public static final String PREFIX="Bearer";
}

package com.onlinebankingsystem.springproject.util;

import java.io.Serializable;
import java.util.Random;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class Random4DigitIDGenerator implements IdentifierGenerator{
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) {
		long max = 9999L;
		long min = 1000L;
		Random random = new Random();
		
		long id;
		id = min + (long)(random.nextDouble()*(max-min));
		
		return id;
	}
}

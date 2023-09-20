package com.onlinebankingsystem.springproject.util;

import java.io.Serializable;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class Random8DigitIDGenerator implements IdentifierGenerator{
	
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		
		long max = 99999999L;
		long min = 10000000L;
		Random random = new Random();
		long id;
		id = min + (long)(random.nextDouble()*(max-min));
		
		return id;
	}
}

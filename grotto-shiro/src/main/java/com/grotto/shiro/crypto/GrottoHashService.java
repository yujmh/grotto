package com.grotto.shiro.crypto;

import org.apache.shiro.crypto.hash.ConfigurableHashService;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;

public class GrottoHashService {
	public static void main(String[] args) {
		ConfigurableHashService hashService = new DefaultHashService();
		hashService.setHashAlgorithmName("MD5");

		Long numOfIterations = 2L;
		hashService.setHashIterations(numOfIterations.intValue());

		String plaintext = "admin";
		String dynaSalt = "admin";
		HashRequest request = new HashRequest.Builder()
				.setSalt(dynaSalt)
				.setSource(plaintext)
				.build();

		System.out.println(hashService.computeHash(request).toHex());
	}
}

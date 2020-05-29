/**
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package com.company.movie.util;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import com.company.movie.exception.AppException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtil {

	public static String generateToken(Map<String, Object> claims, long expiryDuration, byte[] apiKey) {

		try {
			long currentTime = System.currentTimeMillis();
			Date currentDate = new Date(currentTime);
			expiryDuration = (expiryDuration * 60000); // converting minutes to milliseconds
			Date tokenExpirationDate = new Date(currentTime + expiryDuration);

			SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
			Key signingKey = new SecretKeySpec(apiKey, signatureAlgorithm.getJcaName());

			JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
					.setHeaderParam("alg", signatureAlgorithm.getValue()).setClaims(claims).setIssuedAt(currentDate)
					.setExpiration(tokenExpirationDate).signWith(signatureAlgorithm, signingKey);
			return builder.compact();
		} catch (Exception e) {
			throw new AppException("");
		}
	}

	public static Claims parseToken(String token, byte[] apiKey) {

		try {

			Claims claims = Jwts.parser().setSigningKey(apiKey).parseClaimsJws(token).getBody();
			return claims;
		} catch (Exception e) {
			throw new AppException("");
		}
	}
}

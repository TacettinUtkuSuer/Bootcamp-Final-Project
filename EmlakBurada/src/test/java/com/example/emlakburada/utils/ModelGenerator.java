package com.example.emlakburada.utils;

import com.example.emlakburada.dto.request.AdvertRequest;
import com.example.emlakburada.model.enums.AdvertStatus;
import com.example.emlakburada.model.enums.AdvertType;
import com.example.emlakburada.model.enums.CountryType;
import com.example.emlakburada.model.models.Address;
import com.example.emlakburada.model.models.Advert;
import com.example.emlakburada.model.models.AdvertProductPackage;
import com.example.emlakburada.model.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.util.ArrayList;
import java.util.List;

public class ModelGenerator {
	public static AdvertRequest generateAdvertRequest() {
		return new AdvertRequest(AdvertType.FORSALE, "test", "test", new Address(CountryType.ANKARA, "district", "fullAddress", null));
	}

	public static User generateUser() {
		return new User(1L, "123", "name", "surname", "email", "password", null, null);
	}

	public static Advert generateAdvert() {
		return new Advert(1L,
				AdvertType.FORSALE,
				"title",
				"definition",
				AdvertStatus.ACTIVE,
				new AdvertProductPackage(),
				new Address());
	}

	public static List<Advert> generateAdverts(int count) {
		List<Advert> adverts = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			adverts.add(new Advert((long)i,
					AdvertType.FORSALE,
					"title",
					"definition",
					AdvertStatus.ACTIVE,
					new AdvertProductPackage((long)i, null, null, null),
					new Address()));
		}
		return adverts;
	}

	public static Claims generateClaims(){
		String SECRET_KEY = "emlakburada-secret-key-rfgd234ffr5";
		String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InRhY2V0dGludXRrdXN1ZXJAZ21haWwuY29tIiwiaWF0IjoxNjQ3OTgwMTEyLCJzdWIiOiJ0YWNldHRpbnV0a3VzdWVyQGdtYWlsLmNvbSIsImlzcyI6ImVtbGFrYnVyYWRhLmNvbSIsImV4cCI6MTY0ODI4MDExMn0.1IYxcr101xQdMCUEsSg2LpqBfSOUsmjcPKaUlHbMSJQ";
		//// @formatter:off
		return Jwts
				.parserBuilder()
				.setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
				.build()
				.parseClaimsJws(token)
				.getBody();
		// @formatter:on
	}
}

package com.example.emlakburada.service.baseServices;

import com.example.emlakburada.dto.request.AdvertRequest;
import com.example.emlakburada.dto.response.AdvertResponse;
import com.example.emlakburada.model.enums.AdvertStatus;
import com.example.emlakburada.model.enums.AdvertType;
import com.example.emlakburada.model.enums.CountryType;
import com.example.emlakburada.model.models.Address;
import com.example.emlakburada.model.models.Advert;
import com.example.emlakburada.model.models.AdvertProductPackage;
import com.example.emlakburada.model.models.User;
import com.example.emlakburada.repository.UserRepository;
import com.flextrade.jfixture.JFixture;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class AdvertBaseServiceTest {
	private JFixture jFixture;

	@Mock
	UserRepository userRepository;

	@InjectMocks
	AdvertBaseService advertBaseService;

	@Before
	public void setUp() {
		jFixture = new JFixture();
	}

	@Test
	public void itShouldReturnAdvert_whenRequestIsValid() {
		// given
		long userId = jFixture.create(Long.class);
		AdvertRequest advertRequest = generateAdvertRequest();
		User user = generateUser();
		user.setId(userId);

		// when
		given(userRepository.getById(userId)).willReturn(user);
		Advert result = advertBaseService.convertFromAdvertRequestToAdvert(userId, advertRequest);

		// then
		assertThat(result).isNotNull();
		assertThat(result.getAdvertType()).isSameAs(advertRequest.getAdvertType());
		assertThat(result.getTitle()).isSameAs(advertRequest.getTitle());
		assertThat(result.getDefinition()).isSameAs(advertRequest.getDefinition());
		assertThat(result.getAdvertStatus()).isSameAs(AdvertStatus.IN_REVIEW);
		assertThat(result.getAddress().getFullAddress()).isSameAs(advertRequest.getAddress().getFullAddress());
	}

	@Test
	public void itShouldReturnConvertAdvertListToAdvertResponseList_whenRequestIsValid() {
		// given
		Advert advert = generateAdvert();
		List<Advert> adverts = List.of(advert);

		// when
		List<AdvertResponse> results = advertBaseService.convertFromAdvertListToAdvertResponseList(adverts);

		// then
		assertThat(results).isNotNull();
		assertThat(results.size()).isSameAs(1);
		assertThat(results.get(0).getTitle()).isSameAs(advert.getTitle());
		assertThat(results.get(0).getDefinition()).isSameAs(advert.getDefinition());
		assertThat(results.get(0).getAddress().getFullAddress()).isSameAs(advert.getAddress().getFullAddress());
	}

	@Test
	public void itShouldReturnConvertFromAdvertToAdvertResponse_whenAdvertIsValid() {
		// given
		Advert advert = generateAdvert();

		// when
		AdvertResponse result = advertBaseService.convertFromAdvertToAdvertResponse(advert);

		// then
		assertThat(result).isNotNull();
	}

	private AdvertRequest generateAdvertRequest() {
		return new AdvertRequest(AdvertType.FORSALE, "test", "test", new Address(CountryType.ANKARA, "district", "fullAddress", null));
	}

	private User generateUser() {
		return new User(1L, "123", "name", "surname", "email", "password", null, null);
	}

	private Advert generateAdvert() {
		return new Advert(1L,
				AdvertType.FORSALE,
				"title",
				"definition",
				AdvertStatus.ACTIVE,
				new AdvertProductPackage(),
				new Address());
	}
}

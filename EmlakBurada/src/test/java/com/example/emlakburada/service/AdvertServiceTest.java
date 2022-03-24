package com.example.emlakburada.service;

import com.example.emlakburada.dto.request.AdvertRabbitMQRequest;
import com.example.emlakburada.dto.request.AdvertRequest;
import com.example.emlakburada.dto.response.AdvertResponse;
import com.example.emlakburada.dto.response.ProcessStatusResponse;
import com.example.emlakburada.exception.AdvertNotFoundException;
import com.example.emlakburada.exception.EmlakBuradaException;
import com.example.emlakburada.model.enums.AdvertStatus;
import com.example.emlakburada.model.models.Advert;
import com.example.emlakburada.model.models.AdvertProductPackage;
import com.example.emlakburada.model.models.User;
import com.example.emlakburada.queue.QueueService;
import com.example.emlakburada.queue.RabbitMqService;
import com.example.emlakburada.repository.AdvertRepository;
import com.example.emlakburada.repository.UserRepository;
import com.example.emlakburada.utils.ModelGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.ArgumentMatchers.any;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AdvertServiceTest {
	@Mock
	UserRepository userRepository;
	@Mock
	AdvertRepository advertRepository;
	@Mock
	QueueService rabbitMqService;

	@InjectMocks
	AdvertService advertService;

	@Test
	public void create_itShouldReturnError_whenPackageDateIsExpired() {
		// given
		AdvertRequest advertRequest = ModelGenerator.generateAdvertRequest();
		User user = ModelGenerator.generateUser();
		user.setId(1L);
		user.setAdvertProductPackage(
				new AdvertProductPackage(1L,
						null,
						new Date(823101846).toLocalDate(),
						null));
		// when
		given(userRepository.getById(1L)).willReturn(user);
		ProcessStatusResponse result = advertService.create(1L, advertRequest);

		// then
		assertThat(result.getMessage()).isEqualTo("Your advert package has expired. Please buy new package.");
	}

	@Test
	public void create_itShouldReturnError_whenUserHasMoreThan10Adverts() {
		// given
		List<Advert> adverts = ModelGenerator.generateAdverts(11);
		AdvertRequest advertRequest = ModelGenerator.generateAdvertRequest();
		User user = ModelGenerator.generateUser();
		user.setId(1L);
		user.setAdvertProductPackage(
				new AdvertProductPackage(1L,
						adverts,
						LocalDate.of(2023, 1, 31),
						null));

		// when
		given(userRepository.getById(1L)).willReturn(user);
		ProcessStatusResponse result = advertService.create(1L, advertRequest);

		// then
		assertThat(result.getMessage()).isEqualTo("You can create a maximum of 10 ads. therefore the record could not be created.");
	}

	@Test
	public void readAll_itShouldReturnAllAdvert_whenUserIsValid() {
		// given
		List<Advert> adverts = ModelGenerator.generateAdverts(2);
		User user = ModelGenerator.generateUser();
		user.setId(1L);
		user.setAdvertProductPackage(
				new AdvertProductPackage(1L,
						adverts,
						LocalDate.of(2023, 1, 31),
						null));

		// when
		given(userRepository.getById(1L)).willReturn(user);
		List<AdvertResponse> result = advertService.readAll(1L);

		// then
		assertThat(result).isNotNull();
		assertThat(result.size()).isSameAs(2);
	}

	@Test
	public void readById_shouldReturnNull_whenUserDoesNotHaveAdvert() {
		// given
		long advertId = 1L;
		List<Advert> adverts = ModelGenerator.generateAdverts(2);
		Advert advert = adverts.get(0);
		User user = ModelGenerator.generateUser();
		user.setId(1L);
		user.setAdvertProductPackage(
				new AdvertProductPackage(1L,
						adverts,
						LocalDate.of(2023, 1, 31),
						null));

		advert.getAdvertProductPackage().setUser(user);

		// when
		given(advertRepository.getById(1L)).willReturn(advert);
		//AdvertResponse result = advertService.readById(2L, advertId);

		// then
		//assertThat(result).isNull();
		assertThrows(AdvertNotFoundException.class,()->{advertService.readById(2L, advertId);},"Reading unsuccessful");
	}

	@Test
	public void readById_shouldReturnAdvert_whenUserHasAdvert() {
		// given
		long advertId = 1L;
		List<Advert> adverts = ModelGenerator.generateAdverts(2);
		Advert advert = adverts.get(0);
		User user = ModelGenerator.generateUser();
		user.setId(1L);
		user.setAdvertProductPackage(
				new AdvertProductPackage(1L,
						adverts,
						LocalDate.of(2023, 1, 31),
						null));

		advert.getAdvertProductPackage().setUser(user);

		// when
		given(advertRepository.getById(1L)).willReturn(advert);
		AdvertResponse result = advertService.readById(1L, advertId);

		// then
		assertThat(result).isNotNull();
	}

	@Test
	public void updateById_itShouldReturnNull_whenPackageDateIsExpired() {
		// given
		AdvertRequest advertRequest = ModelGenerator.generateAdvertRequest();
		List<Advert> adverts = ModelGenerator.generateAdverts(2);
		User user = ModelGenerator.generateUser();
		user.setId(1L);
		user.setAdvertProductPackage(
				new AdvertProductPackage(1L,
						adverts,
						new Date(823101846).toLocalDate(),
						null));
		// when
		given(userRepository.getById(1L)).willReturn(user);
		AdvertResponse result = advertService.updateById(1L, 1L, advertRequest);

		// then
		assertThat(result).isNull();
	}

	@Test
	public void updateById_itShouldReturnNull_whenUserDoesNotHaveAdvert() {
		// given
		AdvertRequest advertRequest = ModelGenerator.generateAdvertRequest();
		List<Advert> adverts = ModelGenerator.generateAdverts(2);
		Advert advert = adverts.get(0);
		User user = ModelGenerator.generateUser();
		user.setId(1L);
		user.setAdvertProductPackage(
				new AdvertProductPackage(1L,
						adverts,
						LocalDate.of(2023, 1, 31),
						null));
		advert.getAdvertProductPackage().setUser(user);
		advert.getAdvertProductPackage().getUser().setId(2L);

		// when
		given(userRepository.getById(1L)).willReturn(user);
		given(advertRepository.getById(1L)).willReturn(advert);
		//AdvertResponse result = advertService.updateById(1L, 1L, advertRequest);

		// then
		assertThrows(AdvertNotFoundException.class,() -> {advertService.updateById(1L, 1L, advertRequest);},"Reading unsuccessful");
		//assertThat(result).isNull();
	}

	@Test
	public void updateById_itShouldUpdateAdvert_whenRequestIsValid() {
		// given
		AdvertRequest advertRequest = ModelGenerator.generateAdvertRequest();
		List<Advert> adverts = ModelGenerator.generateAdverts(2);
		Advert advert = adverts.get(0);
		User user = ModelGenerator.generateUser();
		user.setId(1L);
		user.setAdvertProductPackage(
				new AdvertProductPackage(1L,
						adverts,
						LocalDate.of(2023, 1, 31),
						null));
		advert.getAdvertProductPackage().setUser(user);
		advert.getAdvertProductPackage().getUser().setId(1L);

		// when
		given(userRepository.getById(1L)).willReturn(user);
		given(advertRepository.getById(1L)).willReturn(advert);
		AdvertResponse result = advertService.updateById(1L, 1L, advertRequest);
		ArgumentCaptor<AdvertRabbitMQRequest> captor = ArgumentCaptor.forClass(AdvertRabbitMQRequest.class);
		verify(rabbitMqService).sendMessageAndAdvertActivate(captor.capture());

		// then
		assertThat(result).isNotNull();
	}

	@Test
	public void deleteById_itShouldDelete_whenRequestIsValid() {
		// given
		List<Advert> adverts = ModelGenerator.generateAdverts(2);
		Advert advert = adverts.get(0);
		User user = ModelGenerator.generateUser();
		user.setId(1L);
		user.setAdvertProductPackage(
				new AdvertProductPackage(1L,
						adverts,
						LocalDate.of(2023, 1, 31),
						null));
		advert.getAdvertProductPackage().setUser(user);
		advert.getAdvertProductPackage().getUser().setId(1L);

		// when
		given(userRepository.getById(1L)).willReturn(user);
		given(advertRepository.getById(1L)).willReturn(advert);
		ProcessStatusResponse result = advertService.deleteById(1L, 1L);

		// then
		assertThat(result.getMessage()).isEqualTo("The ad has been deleted.");
	}

	@Test
	public void deleteById_itShouldNotBeDeleted_whenUserDoesNotHaveAdvert() {
		// given
		List<Advert> adverts = ModelGenerator.generateAdverts(2);
		Advert advert = adverts.get(0);
		User user = ModelGenerator.generateUser();
		user.setId(1L);
		user.setAdvertProductPackage(
				new AdvertProductPackage(1L,
						adverts,
						LocalDate.of(2023, 1, 31),
						null));
		advert.getAdvertProductPackage().setUser(user);
		advert.getAdvertProductPackage().getUser().setId(2L);

		// when
		given(advertRepository.getById(1L)).willReturn(advert);
//		ProcessStatusResponse result = advertService.deleteById(1L, 1L);

		// then
		//assertThat(result).isEqualTo("Advert could not be deleted.");

		assertThrows(AdvertNotFoundException.class, () -> {advertService.deleteById(1L, 1L);} ,"Advert could not be deleted.");
	}

	@Test
	public void activate_itShouldReturnNull_whenUserDoesNotHaveAdvert() {
		// given
		List<Advert> adverts = ModelGenerator.generateAdverts(2);
		Advert advert = adverts.get(0);
		User user = ModelGenerator.generateUser();
		user.setId(1L);
		user.setAdvertProductPackage(
				new AdvertProductPackage(1L,
						adverts,
						LocalDate.of(2023, 1, 31),
						null));
		advert.getAdvertProductPackage().setUser(user);
		advert.getAdvertProductPackage().getUser().setId(2L);

		// when
		given(advertRepository.getById(1L)).willReturn(advert);
		//AdvertResponse result = advertService.activate(1L, 1L);

		// then
		//assertThat(result).isNull();
		assertThrows(EmlakBuradaException.class,()->{advertService.activate(1L, 1L);},"Advert could not been activated.");
	}

	@Test
	public void activate_itShouldActivate_whenRequestIsValid() {
		// given
		List<Advert> adverts = ModelGenerator.generateAdverts(2);
		Advert advert = adverts.get(0);
		User user = ModelGenerator.generateUser();
		user.setId(1L);
		user.setAdvertProductPackage(
				new AdvertProductPackage(1L,
						adverts,
						LocalDate.of(2023, 1, 31),
						null));
		advert.getAdvertProductPackage().setUser(user);
		advert.getAdvertProductPackage().getUser().setId(1L);

		// when
		given(userRepository.getById(1L)).willReturn(user);
		given(advertRepository.getById(1L)).willReturn(advert);
		AdvertResponse result = advertService.activate(1L, 1L);

		// then
		assertThat(result).isNotNull();
		assertThat(result.getAdvertStatus()).isEqualTo(AdvertStatus.ACTIVE);
	}

	@Test
	public void deactivate_itShouldReturnNull_whenUserDoesNotHaveAdvert() {
		// given
		List<Advert> adverts = ModelGenerator.generateAdverts(2);
		Advert advert = adverts.get(0);
		User user = ModelGenerator.generateUser();
		user.setId(1L);
		user.setAdvertProductPackage(
				new AdvertProductPackage(1L,
						adverts,
						LocalDate.of(2023, 1, 31),
						null));
		advert.getAdvertProductPackage().setUser(user);
		advert.getAdvertProductPackage().getUser().setId(2L);

		// when
		given(advertRepository.getById(1L)).willReturn(advert);
		//AdvertResponse result = advertService.deactivate(1L, 1L);

		// then
		//assertThat(result).isNull();
		assertThrows(EmlakBuradaException.class,()->{advertService.deactivate(1L, 1L);},"Advert could not been deactivated.");
	}

	@Test
	public void deactivate_itShouldActivate_whenRequestIsValid() {
		// given
		List<Advert> adverts = ModelGenerator.generateAdverts(2);
		Advert advert = adverts.get(0);
		User user = ModelGenerator.generateUser();
		user.setId(1L);
		user.setAdvertProductPackage(
				new AdvertProductPackage(1L,
						adverts,
						LocalDate.of(2023, 1, 31),
						null));
		advert.getAdvertProductPackage().setUser(user);
		advert.getAdvertProductPackage().getUser().setId(1L);

		// when
		given(userRepository.getById(1L)).willReturn(user);
		given(advertRepository.getById(1L)).willReturn(advert);
		AdvertResponse result = advertService.deactivate(1L, 1L);

		// then
		assertThat(result).isNotNull();
		assertThat(result.getAdvertStatus()).isEqualTo(AdvertStatus.PASSIVE);
	}

	@Test
	public void readAllActive_itShouldReturnAdvertResponses_whenRequestIsValid() {
		// given
		List<Advert> adverts = ModelGenerator.generateAdverts(2);
		Advert advert = adverts.get(0);
		advert.setAdvertStatus(AdvertStatus.ACTIVE);
		User user = ModelGenerator.generateUser();
		user.setId(1L);
		user.setAdvertProductPackage(
				new AdvertProductPackage(1L,
						adverts,
						LocalDate.of(2023, 1, 31),
						null));
		advert.getAdvertProductPackage().setUser(user);
		advert.getAdvertProductPackage().getUser().setId(1L);

		// when
		given(userRepository.getById(1L)).willReturn(user);
		List<AdvertResponse> result = advertService.readAllByStatus(1L,AdvertStatus.ACTIVE);

		// then
		assertThat(result).isNotNull();
		assertThat(result.get(0).getAdvertStatus()).isEqualTo(AdvertStatus.ACTIVE);
	}

	@Test
	public void readAllPassive_itShouldReturnAdvertResponses_whenRequestIsValid() {
		// given
		List<Advert> adverts = ModelGenerator.generateAdverts(2);
		Advert advert = adverts.get(0);
		advert.setAdvertStatus(AdvertStatus.PASSIVE);
		User user = ModelGenerator.generateUser();
		user.setId(1L);
		user.setAdvertProductPackage(
				new AdvertProductPackage(1L,
						adverts,
						LocalDate.of(2023, 1, 31),
						null));
		advert.getAdvertProductPackage().setUser(user);
		advert.getAdvertProductPackage().getUser().setId(1L);

		// when
		given(userRepository.getById(1L)).willReturn(user);
		List<AdvertResponse> result = advertService.readAllByStatus(1L,AdvertStatus.PASSIVE);

		// then
		assertThat(result).isNotNull();
		assertThat(result.get(0).getAdvertStatus()).isEqualTo(AdvertStatus.PASSIVE);
	}

	@Test
	public void readAllInreview_itShouldReturnAdvertResponses_whenRequestIsValid() {
		// given
		List<Advert> adverts = ModelGenerator.generateAdverts(2);
		Advert advert = adverts.get(0);
		advert.setAdvertStatus(AdvertStatus.IN_REVIEW);
		User user = ModelGenerator.generateUser();
		user.setId(1L);
		user.setAdvertProductPackage(
				new AdvertProductPackage(1L,
						adverts,
						LocalDate.of(2023, 1, 31),
						null));
		advert.getAdvertProductPackage().setUser(user);
		advert.getAdvertProductPackage().getUser().setId(1L);

		// when
		given(userRepository.getById(1L)).willReturn(user);
		List<AdvertResponse> result = advertService.readAllByStatus(1L,AdvertStatus.IN_REVIEW);

		// then
		assertThat(result).isNotNull();
		assertThat(result.get(0).getAdvertStatus()).isEqualTo(AdvertStatus.IN_REVIEW);
	}
}

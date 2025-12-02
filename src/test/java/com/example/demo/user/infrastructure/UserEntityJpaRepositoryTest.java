package com.example.demo.user.infrastructure;

import com.example.demo.user.domain.UserStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

// DataJpaTest에 내장되어 있음
// @ExtendWith(SpringExtension.class)
// 기본 설정을 따라도 돼서 필요가 없었음
// @TestPropertySource("classpath:test-application.properties")
@DataJpaTest(showSql = true)
@Sql("/sql/user-repository-test-data.sql")
public class UserEntityJpaRepositoryTest {

	@Autowired
	private UserJpaRepository userJpaRepository;

	@Test
	void findByIdAndStatus_로_유저_데이터를_찾아올_수_있다() {
		// given

		// when
		Optional<UserEntity> result = userJpaRepository.findByIdAndStatus(1, UserStatus.ACTIVE);

		// then
		assertThat(result.isPresent()).isTrue();
	}

	@Test
	void findByIdAndStatus_는_데이터가_없으면_Optional_emtpy_를_내려준다() {
		// given

		// when
		Optional<UserEntity> result = userJpaRepository.findByIdAndStatus(1, UserStatus.PENDING);

		// then
		assertThat(result.isEmpty()).isTrue();
	}

	@Test
	void findByEmailAndStatus_로_유저_데이터를_찾아올_수_있다() {
		// given

		// when
		Optional<UserEntity> result = userJpaRepository.findByEmailAndStatus("kok202@naver.com", UserStatus.ACTIVE);

		// then
		assertThat(result.isPresent()).isTrue();
	}

	@Test
	void findByEmailAndStatus_는_데이터가_없으면_Optional_emtpy_를_내려준다() {
		// given

		// when
		Optional<UserEntity> result = userJpaRepository.findByEmailAndStatus("kok202@naver.com", UserStatus.PENDING);

		// then
		assertThat(result.isEmpty()).isTrue();
	}


}

package net.jqwik.time.api.times.duration.exhaustiveGeneration.precision;

import java.time.*;
import java.util.*;

import net.jqwik.api.*;
import net.jqwik.time.api.*;

import static java.time.temporal.ChronoUnit.*;
import static org.assertj.core.api.Assertions.*;

public class MinutesTests {

	@Example
	void betweenPositive() {
		Optional<ExhaustiveGenerator<Duration>> optionalGenerator =
			Times.durations()
				 .ofPrecision(MINUTES)
				 .between(
					 Duration.ofSeconds(4 * 60 + 55, 997_997_921),
					 Duration.ofSeconds(8 * 60 + 31, 1_213_999)
				 )
				 .exhaustive();
		assertThat(optionalGenerator).isPresent();

		ExhaustiveGenerator<Duration> generator = optionalGenerator.get();
		assertThat(generator.maxCount()).isEqualTo(4);
		assertThat(generator).containsExactly(
			Duration.ofSeconds(5 * 60, 0),
			Duration.ofSeconds(6 * 60, 0),
			Duration.ofSeconds(7 * 60, 0),
			Duration.ofSeconds(8 * 60, 0)
		);
	}

	@Example
	void betweenNegative() {
		Optional<ExhaustiveGenerator<Duration>> optionalGenerator =
			Times.durations()
				 .ofPrecision(MINUTES)
				 .between(
					 Duration.ofSeconds(-8 * 60 - 11, 997_123_998),
					 Duration.ofSeconds(-4 * 60 - 55, 1_999_999)
				 )
				 .exhaustive();
		assertThat(optionalGenerator).isPresent();

		ExhaustiveGenerator<Duration> generator = optionalGenerator.get();
		assertThat(generator.maxCount()).isEqualTo(4);
		assertThat(generator).containsExactly(
			Duration.ofSeconds(-8 * 60, 0),
			Duration.ofSeconds(-7 * 60, 0),
			Duration.ofSeconds(-6 * 60, 0),
			Duration.ofSeconds(-5 * 60, 0)
		);
	}

	@Example
	void betweenAroundZero() {
		Optional<ExhaustiveGenerator<Duration>> optionalGenerator =
			Times.durations()
				 .ofPrecision(MINUTES)
				 .between(
					 Duration.ofSeconds(-2 * 60 - 33, -2_321_392),
					 Duration.ofSeconds(60 + 28, 1_392_392)
				 )
				 .exhaustive();
		assertThat(optionalGenerator).isPresent();

		ExhaustiveGenerator<Duration> generator = optionalGenerator.get();
		assertThat(generator.maxCount()).isEqualTo(4);
		assertThat(generator).containsExactly(
			Duration.ofSeconds(-2 * 60, 0),
			Duration.ofSeconds(-60, 0),
			Duration.ZERO,
			Duration.ofSeconds(60, 0)
		);
	}

}

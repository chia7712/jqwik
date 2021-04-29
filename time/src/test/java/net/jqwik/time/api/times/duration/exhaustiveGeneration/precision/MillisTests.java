package net.jqwik.time.api.times.duration.exhaustiveGeneration.precision;

import java.time.*;
import java.util.*;

import net.jqwik.api.*;
import net.jqwik.time.api.*;

import static java.time.temporal.ChronoUnit.*;
import static org.assertj.core.api.Assertions.*;

public class MillisTests {

	@Example
	void betweenPositive() {
		Optional<ExhaustiveGenerator<Duration>> optionalGenerator =
			Times.durations()
				 .ofPrecision(MILLIS)
				 .between(
					 Duration.ofSeconds(183729, 997_997_921),
					 Duration.ofSeconds(183730, 1_213_999)
				 )
				 .exhaustive();
		assertThat(optionalGenerator).isPresent();

		ExhaustiveGenerator<Duration> generator = optionalGenerator.get();
		assertThat(generator.maxCount()).isEqualTo(4);
		assertThat(generator).containsExactly(
			Duration.ofSeconds(183729, 998_000_000),
			Duration.ofSeconds(183729, 999_000_000),
			Duration.ofSeconds(183730, 0),
			Duration.ofSeconds(183730, 1_000_000)
		);
	}

	@Example
	void betweenNegative() {
		Optional<ExhaustiveGenerator<Duration>> optionalGenerator =
			Times.durations()
				 .ofPrecision(MILLIS)
				 .between(
					 Duration.ofSeconds(-183730, 997_123_998),
					 Duration.ofSeconds(-183729, 1_999_999)
				 )
				 .exhaustive();
		assertThat(optionalGenerator).isPresent();

		ExhaustiveGenerator<Duration> generator = optionalGenerator.get();
		assertThat(generator.maxCount()).isEqualTo(4);
		assertThat(generator).containsExactly(
			Duration.ofSeconds(-183730, 998_000_000),
			Duration.ofSeconds(-183730, 999_000_000),
			Duration.ofSeconds(-183729, 0),
			Duration.ofSeconds(-183729, 1_000_000)
		);
	}

	@Example
	void betweenNegativeOneSecond() {
		Optional<ExhaustiveGenerator<Duration>> optionalGenerator =
			Times.durations()
				 .ofPrecision(MILLIS)
				 .between(
					 Duration.ofSeconds(-1, -1_999_999),
					 Duration.ofSeconds(0, -997_382_492)
				 )
				 .exhaustive();
		assertThat(optionalGenerator).isPresent();

		ExhaustiveGenerator<Duration> generator = optionalGenerator.get();
		assertThat(generator.maxCount()).isEqualTo(4);
		assertThat(generator).containsExactly(
			Duration.ofSeconds(-1, -1_000_000),
			Duration.ofSeconds(-1, 0),
			Duration.ofSeconds(0, -999_000_000),
			Duration.ofSeconds(0, -998_000_000)
		);
	}

	@Example
	void betweenAroundZero() {
		Optional<ExhaustiveGenerator<Duration>> optionalGenerator =
			Times.durations()
				 .ofPrecision(MILLIS)
				 .between(
					 Duration.ofSeconds(0, -2_321_392),
					 Duration.ofSeconds(0, 1_392_392)
				 )
				 .exhaustive();
		assertThat(optionalGenerator).isPresent();

		ExhaustiveGenerator<Duration> generator = optionalGenerator.get();
		assertThat(generator.maxCount()).isEqualTo(4);
		assertThat(generator).containsExactly(
			Duration.ofSeconds(0, -2_000_000),
			Duration.ofSeconds(0, -1_000_000),
			Duration.ZERO,
			Duration.ofSeconds(0, 1_000_000)
		);
	}

	@Example
	void betweenPositiveOneSecond() {
		Optional<ExhaustiveGenerator<Duration>> optionalGenerator =
			Times.durations()
				 .ofPrecision(MILLIS)
				 .between(
					 Duration.ofSeconds(0, 997_128_492),
					 Duration.ofSeconds(1, 1_039_392)
				 )
				 .exhaustive();
		assertThat(optionalGenerator).isPresent();

		ExhaustiveGenerator<Duration> generator = optionalGenerator.get();
		assertThat(generator.maxCount()).isEqualTo(4);
		assertThat(generator).containsExactly(
			Duration.ofSeconds(0, 998_000_000),
			Duration.ofSeconds(0, 999_000_000),
			Duration.ofSeconds(1, 0),
			Duration.ofSeconds(1, 1_000_000)
		);
	}

}

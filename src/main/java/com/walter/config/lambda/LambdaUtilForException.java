package com.walter.config.lambda;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by yhwang131 on 2017-06-29.
 */
public final class LambdaUtilForException {

	@FunctionalInterface
	public interface Consumer_WithExceptions<T> {
		void accept(T t) throws Exception;
	}

	@FunctionalInterface
	public interface Function_WithExceptions<T, R> {
		R apply(T t) throws Exception;
	}

	public static <T> Consumer<T> reThrowsConsumer(Consumer_WithExceptions<T> consumer) {
		return t -> {
			try {
				consumer.accept(t);
			} catch (Exception e) {
				throwAsUnchecked(e);
			}
		};
	}

	public static <T, R> Function<T, R> reThrowsFunction(Function_WithExceptions<T, R> function) {
		return t -> {
			try {
				return function.apply(t);
			} catch (Exception e) {
				throwAsUnchecked(e);
				return null;
			}
		};
	}

	@SuppressWarnings("unchecked")
	private static <E extends Throwable> void throwAsUnchecked(Exception e) throws E { throw (E)e; }
}

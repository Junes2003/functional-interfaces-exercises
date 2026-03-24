package org.example;

@FunctionalInterface
public interface Validator<T> {
    boolean validate(T t);

    default Validator<T> and(Validator<T> other) {
        return t -> this.validate(t) && other.validate(t);
    }

    default Validator<T> or(Validator<T> other) {
        return t -> this.validate(t) || other.validate(t);
    }

    default Validator<T> negate() {
        return t -> !this.validate(t);
    }
}
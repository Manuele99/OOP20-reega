/**
 *
 */
package reega.util;

import java.util.Objects;

/**
 * Class for holding a result that can be valid or invalid. If invalid a message can be provided
 *
 * @param <T> type of the resulting object
 */
public class ValueResult<T> {
    private final T value;
    private final String message;
    private final boolean valid;

    /**
     * Construct an invalid result.
     *
     * @param value   resulting value
     * @param message message associated with the invalid result
     */
    public ValueResult(final T value, final String message) {
        Objects.requireNonNull(message);
        this.value = value;
        this.message = message;
        this.valid = false;
    }

    /**
     * Construct an invalid result with a null value.
     *
     * @param message message associated with the invalid result
     */
    public ValueResult(final String message) {
        Objects.requireNonNull(message);
        this.value = null;
        this.message = message;
        this.valid = false;
    }

    /**
     * Construct a valid result.
     *
     * @param value resulting value
     */
    public ValueResult(final T value) {
        this.value = value;
        this.message = "";
        this.valid = true;
    }

    /**
     * Get the result value.
     *
     * @return the value
     */
    public T getValue() {
        return this.value;
    }

    /**
     * Get the message associated with the value.
     *
     * @return the message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Check if the result is valid.
     *
     * @return true if the result is valid, false if not
     */
    public boolean isValid() {
        return this.valid;
    }

    /**
     * Check if the result is invalid.
     *
     * @return true if the result is invalid, false otherwise
     */
    public boolean isInvalid() {
        return !this.valid;
    }

    /**
     * Merge this {@link ValueResult} with a {@link Void} {@link ValueResult} with an AND.
     *
     * @param otherValueResult other {@link ValueResult}
     * @return an invalid {@link ValueResult} with {@link ValueResult#getValue()} as the value and the messages
     * joined if
     *         <code>otherValueResult</code> is invalid; an invalid {@link ValueResult} with {@link ValueResult#getValue()} and
     *         the message of this object if this object is invalid; else return a valid {@link ValueResult}
     */
    public ValueResult<T> merge(final ValueResult<Void> otherValueResult) {
        if (otherValueResult.isInvalid()) {
            return new ValueResult<>(this.getValue(), this.getMessage() + "\n" + otherValueResult.getMessage());
        }
        if (this.isInvalid()) {
            return new ValueResult<>(this.getValue(), this.getMessage());
        }
        return this;
    }

}

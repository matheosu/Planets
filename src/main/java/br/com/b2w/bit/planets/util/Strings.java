package br.com.b2w.bit.planets.util;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * This class consists of {@code static} utility methods for operating
 * on strings. These utilities include {@code null}-safe and empty-safe.
 * This class is based on {@link Objects Objects}
 *
 * @author mcastro
 */
public final class Strings {

    private static final String HEX_STRING_FORMAT = "%1$032x";

    public static final CharSequence DEFAULT_BREAK_LINE = "\n";

    private Strings() {
    }

    /**
     * Checks that the specified object reference is not empty. This
     * method is designed primarily for doing parameter validation in methods
     * and constructors, as demonstrated below:
     * <blockquote><pre>
     * public Foo(String bar) {
     *     this.bar = Strings.requireNotEmpty(bar);
     * }
     * </pre></blockquote>
     *
     * @param value the string value reference to check for empty
     * @return {@code value} if not empty
     * @throws EmptyException if {@code value} is empty
     */
    public static String requireNotEmpty(String value) {
        if (value.isEmpty())
            throw new EmptyException();
        return value;
    }

    /**
     * Checks that the specified string reference is not empty and
     * throws a customized {@link EmptyException} if it is. This method
     * is designed primarily for doing parameter validation in methods and
     * constructors with multiple parameters, as demonstrated below:
     * <blockquote><pre>
     * public Foo(String bar, String baz) {
     *     this.bar = Strings.requireNotEmpty(bar, "bar must not be empty");
     *     this.baz = Strings.requireNotEmpty(baz, "baz must not be empty");
     * }
     * </pre></blockquote>
     *
     * @param value        the string value reference to check for empty
     * @param emptyMessage detail message to be used in the event that a {@code EmptyException} is thrown
     * @return {@code value} if not empty
     * @throws EmptyException if {@code value} is empty
     */
    public static String requireNotEmpty(String value, String emptyMessage) {
        if (value.isEmpty())
            throw new EmptyException(emptyMessage);
        return value;
    }

    /**
     * Checks that the specified string reference is not {@code null} and not empty. This
     * method is designed primarily for doing parameter validation in methods
     * and constructors, as demonstrated below:
     * <blockquote><pre>
     * public Foo(String bar) {
     *     this.bar = Strings.requireNonNullAndNotEmpty(bar);
     * }
     * </pre></blockquote>
     *
     * @param value the string reference to check for nullity and empty
     * @return {@code value} if not {@code null} and not empty
     * @throws NullPointerException if {@code obj} is {@code null}
     * @throws EmptyException       if {@code value} is empty
     */
    public static String requireNonNullAndNotEmpty(String value) {
        if (Objects.requireNonNull(value).isEmpty())
            throw new EmptyException();
        return value;
    }

    /**
     * Checks that the specified string reference is not {@code null} and not empty and
     * throws a customized {@link NullPointerException} or {@link EmptyException} if it is. This method
     * is designed primarily for doing parameter validation in methods and
     * constructors with multiple parameters, as demonstrated below:
     * <blockquote><pre>
     * public Foo(String bar, String baz) {
     *     this.bar = Strings.requireNonNullAndNotEmpty(bar, "bar must not be null", "bar must not be empty");
     *     this.baz = Strings.requireNonNullAndNotEmpty(baz, "baz must not be null", "baz must not be empty");
     * }
     * </pre></blockquote>
     *
     * @param value        the object reference to check for nullity and empty
     * @param nullMessage  detail message to be used in the event that a {@code NullPointerException} is thrown
     * @param emptyMessage detail message to be used in the event that a {@code EmptyException} is thrown
     * @return {@code value} if not {@code null} and not empty
     * @throws NullPointerException if {@code obj} is {@code null}
     * @throws EmptyException       if {@code value} is empty
     */
    public static String requireNonNullAndNotEmpty(String value, String nullMessage, String emptyMessage) {
        if (Objects.requireNonNull(value, nullMessage).isEmpty())
            throw new EmptyException(emptyMessage);
        return value;
    }

    public static byte[] string2Bytes(String value) {
        return string2Bytes(value, StandardCharsets.UTF_8);
    }

    public static byte[] string2Bytes(String value, Charset charset) {
        return (value != null && charset != null) ? value.getBytes(charset) : null;
    }

    public static String byte2String(byte[] value) {
        return byte2String(value, StandardCharsets.UTF_8);
    }

    public static String byte2String(byte[] value, Charset charset) {
        return new String(value, charset);
    }

    public static String bytes2Hex(byte[] value) {
        return value != null ? String.format(HEX_STRING_FORMAT, new BigInteger(1, value)) : null;
    }

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public static boolean isNotNullAndNotEmpty(String value) {
        return value != null && !value.isEmpty();
    }

    public static boolean isNotNullAndNotEmptyTrim(String value) {
        return value != null && !value.trim().isEmpty();
    }

    public static String removeBackSlash(String value) {
        return value.replaceAll("\\\\", "");
    }

    public static String convertISO2UTF8(String value) {
        if (value == null)
            return null;
        return new String(value.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }

    public static int countMatches(String value, String find) {
        if (isNotNullAndNotEmpty(value) && isNotNullAndNotEmpty(find)) {
            int idx = 0;
            int count = 0;
            while ((idx = value.indexOf(find, idx)) != -1) {
                count++;
                idx += find.length();
            }
            return count;
        }
        return 0;
    }

    public static boolean isNumber(String value) {
        return value.chars().allMatch(Character::isDigit);
    }

    public static boolean hasNumber(String value) {
        return value.chars().anyMatch(Character::isDigit);
    }

    public static boolean isBoolean(String value) {
        return Boolean.TRUE.toString().equalsIgnoreCase(value) || Boolean.FALSE.toString().equalsIgnoreCase(value);
    }

    public static class EmptyException extends RuntimeException {

        private static final long serialVersionUID = -6386264671278231903L;

        public EmptyException() {
        }

        public EmptyException(String message) {
            super(message);
        }
    }

}
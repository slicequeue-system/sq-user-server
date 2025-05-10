package app.slicequeue.sq_user.common.util;

import java.util.regex.Pattern;

public final class StringPatternMatchingUtil {

    public static class RegexValue {
        public static final String VALID_PASSWORD_LV3_REGEX = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*#?&])" +
                "[A-Za-z\\d@$!%*#?&]{8,20}$";
        public static final String VALID_PASSWORD_LV3_REGEX_DESCRIPTION = "비밀번호 체크 Lv3 8~20자리의 영문, 숫자, 특수문자 조합";

        public static final String VALID_EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        public static final String VALID_EMAIL_REGEX_DESCRIPTION = "이메일 주소 형식";
    }

    public enum RegexPattern {
        passwordValidRegexLv3(RegexValue.VALID_PASSWORD_LV3_REGEX_DESCRIPTION,
                RegexValue.VALID_PASSWORD_LV3_REGEX),
        emailValidRegex(RegexValue.VALID_EMAIL_REGEX_DESCRIPTION, RegexValue.VALID_EMAIL_REGEX),

        ;

        private final String description;
        public final String regex;

        RegexPattern(String description, String regex) {
            this.description = description;
            this.regex = regex;
        }
    }

    public static boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(RegexPattern.passwordValidRegexLv3.regex);
        return pattern.matcher(password).matches();
    }

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(RegexPattern.emailValidRegex.regex);
        return pattern.matcher(email).matches();
    }

    public static boolean isValidName(String name) {
        return !name.isBlank();
    }

    private StringPatternMatchingUtil() {
        throw new IllegalStateException("no StringPatternMatchingUtil");
    }

}

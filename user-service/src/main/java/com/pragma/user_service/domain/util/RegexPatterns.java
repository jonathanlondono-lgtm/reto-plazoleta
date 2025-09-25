package com.pragma.user_service.domain.util;

public class RegexPatterns {
    private RegexPatterns() {
    }

    public static final String EMAIL_CODE_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    public static final String PASSWORD_CODE_PATTERN = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    public static final String FULLNAME_PATTERN = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]{2,}$";
    public static final String ADDRESS_PATTERN = "^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9#.,\\- ]{5,}$";
    public static final String PHONE_PATTERN = "^\\+?\\d{1,12}$";
}

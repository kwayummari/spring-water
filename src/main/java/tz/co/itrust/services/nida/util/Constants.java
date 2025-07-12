package tz.co.itrust.services.nida.util;

/**
 * Application constants
 */
public final class Constants {

    private Constants() {
        // Utility class
    }

    // NIDA Response Codes
    public static final class ResponseCodes {
        public static final String SUCCESS = "00";
        public static final String PENDING_QUESTION = "120";
        public static final String VERIFICATION_FAILED = "122";
        public static final String CORRECT_ANSWER = "123";
        public static final String WRONG_ANSWER = "124";
        public static final String GENERAL_FAILURE = "130";
        public static final String SYSTEM_ERROR = "131";
        public static final String NIN_NOT_FOUND = "132";
    }

    // Verification Types
    public static final class VerificationTypes {
        public static final String QUESTIONNAIRE = "QUESTIONNAIRE";
        public static final String BIOMETRIC = "BIOMETRIC";
    }

    // SOAP Headers
    public static final class SoapHeaders {
        public static final String ACTION_NAMESPACE = "http://www.w3.org/2005/08/addressing";
        public static final String ACTION_PREFIX = "ns";
        public static final String RQ_VERIFICATION_ACTION = "http://tempuri.org/IGatewayService/RQVerification";
        public static final String BIOMETRIC_VERIFICATION_ACTION = "http://tempuri.org/IGatewayService/BiometricVerification";
    }

    // Security
    public static final class Security {
        public static final String RSA_ALGORITHM = "RSA";
        public static final String SHA256_ALGORITHM = "SHA-256";
        public static final String SHA1_ALGORITHM = "SHA-1";
        public static final int RIJNDAEL_KEY_SIZE = 32; // 256 bits
        public static final int RIJNDAEL_BLOCK_SIZE = 256;
        public static final int RANDOM_STRING_LENGTH = 32;
    }

    // Validation
    public static final class Validation {
        public static final String NIN_PATTERN = "^[0-9]{20}$";
        public static final String FINGER_CODE_PATTERN = "^[0-9]{2}$";
        public static final int MAX_RQ_CODE_LENGTH = 50;
        public static final int MAX_ANSWER_LENGTH = 200;
    }

    // API Paths
    public static final class ApiPaths {
        public static final String BASE_PATH = "/api/v1";
        public static final String NIDA_PATH = "/nida";
        public static final String VERIFY_PATH = "/verify";
        public static final String VERIFY_BIOMETRIC_PATH = "/verify-biometric";
        public static final String HEALTH_PATH = "/health";
    }
}
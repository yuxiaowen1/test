package cn.bdqn.exception;

/**
 * 用户类型错误异常
 * @author 余晓文
 * @version 1.0 2018-12-17
 */
public class UserTypeErrorException extends RuntimeException {
    public UserTypeErrorException() {
        super();
    }

    public UserTypeErrorException(String message) {
        super(message);
    }

    public UserTypeErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserTypeErrorException(Throwable cause) {
        super(cause);
    }

    protected UserTypeErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

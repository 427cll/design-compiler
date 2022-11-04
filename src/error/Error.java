package error;

/**
 * 用于记录 token 的行数和列数
 */
public class Error extends Exception {
    public static Integer lineno = 1;
    public static Integer column = 1;
}

package cn.net.powerinv.coss4.basic.util;

import lombok.NonNull;

/**
 * 错误代码及对应的错误信息
 *
 * 对错误代码的部分说明：
 *  1*** - 用户及权限系统相关错误，如登录超时、密码错误等
 *  2*** - 请求相关错误，如参数不足、参数类型不合法等
 *  3*** - 文件系统相关错误，如找不到文件等
 *  4*** - 数据库系统相关错误，如找不到查询结果等
 *  5*** - 其他类型错误，如服务器内部错误等
 *
 * @author PengJQ
 * @date 2019-12-18
 */
public enum MessageCode {
    USERNAME_OR_PASSWORD_NOT_TRUE(1000, "用户名或密码不正确"),
    TOKEN_NOT_FOUND(1100, "用户未登录"),
    TOKEN_TIMED_OUT(1200, "用户登录超时"),
    LOGIN_FAILED(1300, "用户登录失败"),
    PERMISSION_DENIED(1400, "权限不足"),
    PARAMETERS_NOT_ENOUGH(2000, "传入的参数不足"),
    PARAMETERS_TYPE_ILLEGAL(2100, "传入的参数类型不合法"),
    NO_SUCH_FILE(3000, "找不到对应的文件"),
    FILE_ALREADY_EXIST(3100, "文件已存在"),
    FILE_READ_ERROR(3200, "文件读取时出错"),
    FILE_WRITE_ERROR(3300, "文件写入时出错"),
    SERVER_INTERNAL_ERROR(5500, "服务器内部错误"),
    OTHER_ERROR(5900, "请求失败");

    private Integer code;
    private String msg;

    MessageCode(@NonNull Integer code, @NonNull String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() { return this.code; }
    public String getMsg() { return this.msg; }

    @Override
    public String toString() {
        return this.getMsg();
    }
}

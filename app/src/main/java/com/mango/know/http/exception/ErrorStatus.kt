package com.mango.know.http.exception

/**
 * Created by chenxz on 2018/4/21.
 */
object ErrorStatus {
    /**
     * 响应成功 正确返回
     */
    @JvmField
    val SUCCESS = "200"

    /**
     * Token 过期 或 错误信息
     */
    @JvmField
    val TOKEN_INVAILD = "100"

    /**
     * 未知错误
     */
    @JvmField
    val UNKNOWN_ERROR = 1002

    /**
     * 服务器内部错误
     */
    @JvmField
    val SERVER_ERROR = 1003

    /**
     * 网络连接超时
     */
    @JvmField
    val NETWORK_ERROR = 1004

    /**
     * API解析异常（或者第三方数据结构更改）等其他异常
     */
    @JvmField
    val API_ERROR = 1005
}
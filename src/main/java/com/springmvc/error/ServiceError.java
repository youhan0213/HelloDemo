package com.springmvc.error;

/**
 * @desc:错误码
 * @author:weihz
 * @time:2014-12-22下午03:13:10
 */
public enum ServiceError {
	/** 操作成功 */
	SUCCESS(0),
	/** 未登录或session失效 */
	AUTH_FAILED(2),
	/**操作失败*/
	FAILURE(-1),
	/**数据已经存在*/
	DataExist(-2),
	/**数据不存在*/
	DataNotExist(-9),
	/** 参数为空 */
	PARAMETER_EMPTY(101001),
	/** 参数错误 */
	PARAM_ERROR(101002),
	/** 签名错误 */
	SIGN_ERROR(101003),
	/** PARAM EXISTS */
	PARAM_EXISTS(101004),
	
	/** 调用基础服务错误 */
	RPC(109097),
	/** sql语句错误 */
	SQL(109098),
	/** 数据库错误 */
	DB(109099),
	/** 未知错误 */
	UNKNOWN(9999), 
	/** IO 错误 */
	IO(102001);

	private int code;

	private ServiceError(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}

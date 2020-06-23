package com.shop.util.enums;
/**
 * 运算符
 */
public enum Operator {

	/** 等于 */
	eq,

	/** 不等于 */
	ne,

	/** 大于 */
	gt,

	/** 小于 */
	lt,

	/** 大于等于 */
	ge,

	/** 小于等于 */
	le,

	/** 相似 */
	lk,
	/** 类似于aaaa% */
	slk,

	/** 包含 */
	in,

	/** 为Null */
	is,

	/** 不为Null */
	no;

	/**
	 * 从String中获取Operator
	 * 
	 * @param value
	 *            值
	 * @return String对应的operator
	 */
	public static Operator fromString(String value) {
		return Operator.valueOf(value.toLowerCase());
	}
}

// *** UMS通用函数 ***

/**
 * 打印提示消息
 */
function imsg(msg) {
	alert(msg);
}

/**
 * 打印正确消息
 */
function rmsg(msg) {
	alert(msg);
}

/**
 * 打印失败消息
 */
function fmsg(msg) {
	alert(msg);
}

/**
 * 打印错误消息
 */
function emsg(msg) {
	alert(msg);
}

/**
 * 是否提示框
 * @param msg
 * @param fn
 */
function cfMsg(msg, fn) {
	if (confirm(msg)) {
		fn();
	}
}

/**
 * 是否为空
 * @returns
 */
function isEmpty(val) {
	if (val == undefined) return true;
	if (val == null) return true;
	if (val == "") return true;
	if(Array.prototype.isPrototypeOf(val) && val.length == 0) return true;
	return false;
}

/**
 * 是否不为空
 * @returns
 */
function isNotEmpty(val) {
	return !isEmpty(val);
}

/**
 * 验证$http请求是否成功
 * @param result
 */
function httpSuccess(result) {
	return result.status == "200" && result.data.key == "0";
}

/**
 * 打印请求失败的消息
 * @param result
 * @returns
 */
function writeHttpErrorMsg(result) {
	var msg = result.data.msg;
	if (isEmpty(msg)) {
		msg = "未知错误";
	}
	fmsg(msg);
}





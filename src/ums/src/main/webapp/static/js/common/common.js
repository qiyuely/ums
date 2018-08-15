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







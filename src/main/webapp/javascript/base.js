var isIE = navigator.userAgent.toUpperCase().indexOf("MSIE") ? true : false;
var isFirefox = navigator.userAgent.toUpperCase().indexOf("Firefox") ? true : false;
var browser = null;
if (isIE == true) {
	browser = "isIE";
} else if (isFirefox == true) {
	browser = "isFirefox";
}

function getRootPath() {
	// 获取当前网址，
	var curWwwPath = window.document.location.href;
	// 获取主机地址之后的目录
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	// 获取主机地址
	var localhostPaht = curWwwPath.substring(0, pos);
	// 获取带"/"的项目名
	var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	return (localhostPaht + projectName);
};

function getLocalhostPath() {
	// 获取当前网址，
	var curWwwPath = window.document.location.href;
	// 获取主机地址之后的目录
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	// 获取主机地址
	var localhostPaht = curWwwPath.substring(0, pos);
	return localhostPaht;
};
var contextPath = getRootPath();
var localhostPath = getLocalhostPath(); //服务器
//var localhostPath = getRootPath(); //本地

// JS验证码倒计时
var wait = 60;// 时间
function timeCountAuthCode(o) {//o为按钮的对象
//	console.log("验证码 = " + o.value);
	if (wait == 0) {
		o.removeAttr("disabled");
		o.val("获取验证码");// 改变按钮中value的值
		wait = 60;
	} else {
		o.attr("disabled","true");// 倒计时过程中禁止点击按钮
		o.val("剩余：" + wait + "秒");// 改变按钮中value的值
		wait--;
		setTimeout(function() {
			timeCountAuthCode(o);// 循环调用
		}, 1000);
	}
};

var ua = navigator.userAgent.toLowerCase();
var isWeixin = ua.indexOf('micromessenger') != -1;
var isAndroid = ua.indexOf('android') != -1;
var isIos = (ua.indexOf('iphone') != -1) || (ua.indexOf('ipad') != -1);
var winP = ua.indexOf('Windows Phone') != -1;
//if (!isWeixin) {
//	document.head.innerHTML = '<title>抱歉，出错了</title><meta charset="utf-8"><meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=0"><link rel="stylesheet" type="text/css" href="https://res.wx.qq.com/connect/zh_CN/htmledition/style/wap_err1a9853.css">';
//	document.body.innerHTML = '<div class="page_msg"><div class="inner"><span class="msg_icon_wrp"><i class="icon80_smile"></i></span><div class="msg_content"><h4>请在微信客户端打开链接</h4></div></div></div>';
//}

function compareDateTime(currentTime, startTime, endTime){  
	var current = new Date(currentTime.replace("-", "/").replace("-","/"));
    var start=new Date(startTime.replace("-", "/").replace("-", "/"));  
    var end=new Date(endTime.replace("-", "/").replace("-", "/"));  
    if(start <= current && current <= end) {
    	return true;
    } else {
    	return false;
    }
}
var doorMaxBuyNum = 5;
var doorMinBuyNum = 1;
var shopMaxBuyNum = 10;
var shopMinBuyNum = 1;
var pageSize = 10;//全局变量pageSize
var cityCode="北京市";
//关闭窗口
function closeWebPage(){
	if(isWeixin){
		WeixinJSBridge.call('closeWindow');
	}else{
		if(navigator.userAgent.indexOf("MSIE") > 0) {
			if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
				window.opener = null;
				window.close();
			} else {
				window.open('', '_top');
				window.top.close();
			}
		} else if (navigator.userAgent.indexOf("Firefox") > 0) {
			window.location.href = 'about:blank ';
		} else {
			window.opener = null;
			window.open('', '_self', '');
			window.close();
		}
	}
} 

getBLen = function(str) {
	if (str == null) return 0;
	if (typeof str != "string"){
		str += "";
	}
	return str.replace(/[^\x00-\xff]/g,"01").length;
};
var mobilePhoneReg =new RegExp(/^1(3|4|5|7|8)\d{9}$/);

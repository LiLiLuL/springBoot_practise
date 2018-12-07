package com.example.springDemo;

import java.util.Map;

public class RestfulTools {
	public static ResultModel result(int Errcode, String Errmsg, Map<String, Object> map) {
		ResultModel model = new ResultModel();
		model.setErrcode(Errcode);
		switch (Errcode) {
		case 0:
			model.setErrmsg("成功");
			if (map != null) {
				model.setData(map);
			}
			break;
		case 1001:
			model.setErrmsg("请求传参错误 ");
			break;
		case 1002:
			model.setErrmsg("没有对应内容 ");
			break;
		case 1003:
			model.setErrmsg("此用户已存在");
			break;
		case 1004:
			model.setErrmsg("上传文件为空");
			break;
		case 404:
			model.setErrmsg(Errmsg);
			break;
		default:
			model.setErrmsg("未知错误");
			break;
		}
		return model;
	}
 
}


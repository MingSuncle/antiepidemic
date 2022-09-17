import globalData from '../api/global_data.js';
import axios from 'axios';
import qs from 'qs';

function login(params) {

	let res = axios({
		url: globalData.baseURL + '/login',
		method: 'post',
		data: qs.stringify(params),
		headers: {
		    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
		},
	}).then((res) => {
		if(res.data.code !== 2000) {
			return Promise.reject("登陆失败");
		} else {
			return Promise.resolve(res.data.data);
		}
	}).catch((error) => {
		return Promise.reject(error);
	});

	return res;
}

/**
 * 获取session中的信息
 * @param {String} name 名称
 * @returns 匹配的信息
 */
function getSessionInfo(name) {
	if(name === 'token') {
		return JSON.parse(sessionStorage.getItem('adminToken')).token;
	} else if (name === 'userId') {
		return JSON.parse(sessionStorage.getItem('adminToken')).userId;
	} else if (name === 'account') {
		return JSON.parse(sessionStorage.getItem('adminToken')).account;
	} else if (name === 'name') {
		return JSON.parse(sessionStorage.getItem('adminToken')).name;
	} else if (name === 'roleId') {
		return JSON.parse(sessionStorage.getItem('adminToken')).roleId;
	} else if (name === 'adminToken') {
		return JSON.parse(sessionStorage.getItem('adminToken'));
	}
}
function isFausy(value) {
	return value === 0 ? false : !value;
}
function cleanObject(obj) {
	const result = { ...obj };
	Object.keys(result).forEach(key => {
		if (isFausy(result[key])) delete result[key];
	});
	return result;
}


export default {
	login,
	getSessionInfo,
	cleanObject
}

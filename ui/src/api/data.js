import axios from '@/libs/api.request'
// 各种数据交互接口

export const getCustomerData = (params) => {
  return axios.request({
    url: '/customerInfo/page',
    method: 'get',
    params:params
  })
};
export const getCustomerDataById = (params) => {
  return axios.request({
    url: '/customerInfo/',
    method: 'get',
    params:params
  })
};
export const postCustomerData = (data) => {
  return axios.request({
    url: '/customerInfo/post',
    method: 'post',
    data:data
  })
};
export const getCustomerOfSearch = (params) => {
  return axios.request({
    url: '/customerInfo/search',
    method: 'get',
    params:params
  })
};
// 员工
export const getStaffInfoPage = (params) => {
  return axios.request({
    url: '/staffInfo/page',
    method: 'get',
    params:params
  })
};
export const getStaffInfoSearch = (params) => {
  return axios.request({
    url: '/staffInfo/search',
    method: 'get',
    params:params
  })
};
export const getStaffInfo = (id) => {
  return axios.request({
    url: '/staffInfo/'+id,
    method: 'get',
  })
};
export const postStaffInfo = (data) => {
  return axios.request({
    url: '/staffInfo/',
    method: 'post',
    data:data
  })
};

//部门结构
export const getDepartmentTree = (params) => {
  return axios.request({
    url: '/departmentInfo/tree',
    method: 'get',
    params:params
  })
};
export const getDepartmentById = (id) => {
  return axios.request({
    url: '/departmentInfo/'+id,
    method: 'get',
  })
};
export const postDepartment = (data) => {
  return axios.request({
    url: '/departmentInfo',
    method: 'post',
    data:data
  })
};
// 房间信息
export const getRoomInfoList = (params) => {
  return axios.request({
    url: '/roomInfo/list',
    method: 'get',
    params:params
  })
};
export const getRoomInfoListDTO = (params) => {
  return axios.request({
    url: '/roomInfo/listDTO',
    method: 'get',
    params:params
  })
};
//订单信息
export const getOrderPage = (params) => {
  return axios.request({
    url: '/orderInfo/page',
    method: 'get',
    params:params
  })
};
export const getOrderByRoom = (params) => {
  return axios.request({
    url: '/orderInfo/byRoom',
    method: 'get',
    params:params
  })
};
export const getOrderToday = (params) => {
  return axios.request({
    url: '/orderInfo/today',
    method: 'get',
    params:params
  })
};

export const getOrderPayInfo = (params) => {
  return axios.request({
    url: '/orderPayInfo/',
    method: 'get',
    params:params
  })
};
export const postOrderPayInfo = (data) => {
  return axios.request({
    url: '/orderPayInfo/add',
    method: 'post',
    data:data
  })
};
export const postOrderOk = (data) => {
  return axios.request({
    url: '/orderInfo/ok',
    method: 'post',
    data:data
  })
};
//Map映射的数据
export const getCustomerVip = () => {
  return axios.request({
    url: '/customerVip/list',
    method: 'get',
  })
};
export const getDepList= () => {
  return axios.request({
    url: '/departmentInfo/list',
    method: 'get',
  })
};
export const getRoomTypeList = (params) => {
  return axios.request({
    url: '/roomType/list',
    method: 'get',
    params:params
  })
};

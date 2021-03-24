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
export const getCustomerVip = () => {
  return axios.request({
    url: '/customerVip/list',
    method: 'get',
  })
};

export const getDragList = () => {
  return axios.request({
    url: 'get_drag_list',
    method: 'get'
  })
};

export const errorReq = () => {
  return axios.request({
    url: 'error_url',
    method: 'post'
  })
}

export const saveErrorLogger = info => {
  return axios.request({
    url: 'save_error_logger',
    data: info,
    method: 'post'
  })
}

export const uploadImg = formData => {
  return axios.request({
    url: 'image/upload',
    data: formData
  })
}

export const getOrgData = () => {
  return axios.request({
    url: 'get_org_data',
    method: 'get'
  })
}

export const getTreeSelectData = () => {
  return axios.request({
    url: 'get_tree_select_data',
    method: 'get'
  })
}

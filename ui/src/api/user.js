import axios from '@/libs/api.request'

export const login = ({ userName, password }) => {
  const data = {
    username:userName,
    password
  };
  return axios.request({
    url: '/login/login',
    data:data,
    method: 'post'
  })
};

export const getUserInfo = (token) => {
  return axios.request({
    url: '/login/current',
    params: {
      token
    },
    method: 'get'
  })
};

export const postUserInfo = (data) => {
  return axios.request({
    url: '/staffInfo/',
    data: data,
    method: 'post'
  })
};
export const postChangepwd = (data) => {
  return axios.request({
    url: '/login/changePwd',
    data: data,
    method: 'post'
  })
};
export const logout = (token) => {
  return axios.request({
    url: '/login/logout',
    method: 'post',
  })
};

export const getUnreadCount = () => {
  return axios.request({
    url: 'message/count',
    method: 'get'
  })
}

export const getMessage = () => {
  return axios.request({
    url: 'message/init',
    method: 'get'
  })
}

export const getContentByMsgId = msg_id => {
  return axios.request({
    url: 'message/content',
    method: 'get',
    params: {
      msg_id
    }
  })
}

export const hasRead = msg_id => {
  return axios.request({
    url: 'message/has_read',
    method: 'post',
    data: {
      msg_id
    }
  })
}

export const removeReaded = msg_id => {
  return axios.request({
    url: 'message/remove_readed',
    method: 'post',
    data: {
      msg_id
    }
  })
}

export const restoreTrash = msg_id => {
  return axios.request({
    url: 'message/restore',
    method: 'post',
    data: {
      msg_id
    }
  })
}

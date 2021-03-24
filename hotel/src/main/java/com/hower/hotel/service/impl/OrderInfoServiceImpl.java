package com.hower.hotel.service.impl;

import com.hower.hotel.model.entity.OrderInfo;
import com.hower.hotel.mapper.OrderInfoMapper;
import com.hower.hotel.service.IOrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ghower
 * @since 2021-03-21
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {

}

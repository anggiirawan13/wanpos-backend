package com.wanpos.app.impl;

import com.wanpos.app.dto.request.OrderDetailRequest;
import com.wanpos.app.dto.request.OrderInsertRequest;
import com.wanpos.app.dto.request.OrderUpdateRequest;
import com.wanpos.app.dto.response.BaseResponse;
import com.wanpos.app.entity.OrderEntity;
import com.wanpos.app.entity.OrderItemEntity;
import com.wanpos.app.repository.OrderDetailRepository;
import com.wanpos.app.repository.OrderRepository;
import com.wanpos.app.service.OrderService;
import com.wanpos.constanta.ResponseMessagesConst;
import com.wanpos.constanta.StatusConst;
import com.wanpos.handler.InternalServerErrorHandler;
import com.wanpos.helper.DateHelper;
import com.wanpos.helper.NullEmptyChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public BaseResponse saveOrder(OrderInsertRequest listOrderRequest) {
        try {
            List<OrderItemEntity> listItem = new ArrayList<>();
                OrderEntity newOrder = new OrderEntity();
                newOrder.setUuid(UUID.randomUUID().toString());
                newOrder.setUsername(listOrderRequest.getUsername());
                newOrder.setSubTotal(listOrderRequest.getSubTotal());
                newOrder.setTotalNet(listOrderRequest.getTotalNet());
                newOrder.setTax(listOrderRequest.getTax());
                newOrder.setServiceCharge(listOrderRequest.getServiceCharge());
                newOrder.setStatus(StatusConst.WAITING.toString());

                Timestamp dateNow = DateHelper.getTimestampNow();

                newOrder.setCreatedAt(dateNow);
                newOrder.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
                newOrder.setModifiedAt(dateNow);
                newOrder.setModifiedBy(SecurityContextHolder.getContext().getAuthentication().getName());

                for (OrderDetailRequest item : listOrderRequest.getItem()) {
                    OrderItemEntity itemEntity = new OrderItemEntity();
                    itemEntity.setUuid(newOrder.getUuid());
                    itemEntity.setProductCode(item.getProductCode());
                    itemEntity.setQuantity(item.getQuantity());
                    itemEntity.setTotalPrice(item.getTotalPrice());
                    itemEntity.setPrice(item.getPrice());

                    listItem.add(itemEntity);
                }

            orderRepository.save(newOrder);
            orderDetailRepository.saveAll(listItem);

            return new BaseResponse(HttpStatus.CREATED.value(), true, ResponseMessagesConst.INSERT_SUCCESS.toString(), null);
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse updateOrderByUUID(OrderUpdateRequest request) {
        try {
            OrderEntity oldOrder = orderRepository.getOrderByUUID(request.getUuid());
            if (NullEmptyChecker.isNullOrEmpty(oldOrder)) {
                return new BaseResponse(HttpStatus.NOT_FOUND.value(), false, ResponseMessagesConst.DATA_NOT_FOUND.toString(), null);
            }

            OrderEntity updateOrder = orderRepository.getOrderByUUID(request.getUuid());
            updateOrder.setUsername(request.getUsername());
            updateOrder.setStatus(request.getStatus());

            Timestamp dateNow = DateHelper.getTimestampNow();

            updateOrder.setModifiedAt(dateNow);
            updateOrder.setModifiedBy(SecurityContextHolder.getContext().getAuthentication().getName());

            OrderEntity order = orderRepository.save(updateOrder);

            return new BaseResponse(HttpStatus.OK.value(), true, ResponseMessagesConst.UPDATE_SUCCESS.toString(), order);
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse deleteOrderByUUID(String uuid) {
        try {
            OrderEntity oldOrder = orderRepository.getOrderByUUID(uuid);
            if (NullEmptyChecker.isNullOrEmpty(oldOrder)) {
                return new BaseResponse(HttpStatus.NOT_FOUND.value(), false, ResponseMessagesConst.DATA_NOT_FOUND.toString(), null);
            }

            orderRepository.delete(oldOrder);

            return new BaseResponse(HttpStatus.OK.value(), true, ResponseMessagesConst.UPDATE_SUCCESS.toString(), null);
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse getOrder(int page, int limit, String search) {
        try {
            List<OrderEntity> listOrder;
            if (NullEmptyChecker.isNullOrEmpty(page) || NullEmptyChecker.isNullOrEmpty(limit)) {
                listOrder = orderRepository.findAll();
            } else if (NullEmptyChecker.isNullOrEmpty(search)) {
                Pageable pageable = PageRequest.of(page, limit);
                listOrder = orderRepository.findAll(pageable).toList();
            } else {
                listOrder = orderRepository.getOrderByUsername(search);
            }

            return new BaseResponse(HttpStatus.OK.value(), true, ResponseMessagesConst.DATA_FOUND.toString(), listOrder);
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse getOrderByUUID(String uuid) {
        try {
            OrderEntity listOrder = orderRepository.getOrderByUUID(uuid);

            return new BaseResponse(HttpStatus.OK.value(), true, ResponseMessagesConst.DATA_FOUND.toString(), listOrder);
        } catch (Exception e) {
            return InternalServerErrorHandler.InternalServerError(e);
        }
    }
}

package com.wanpos.app.impls;

import com.wanpos.app.dtos.requests.OrdersDetailRequest;
import com.wanpos.app.dtos.requests.OrdersInsertRequest;
import com.wanpos.app.dtos.requests.OrdersUpdateRequest;
import com.wanpos.app.dtos.responses.BaseResponse;
import com.wanpos.app.entities.OrdersDetailEntity;
import com.wanpos.app.entities.OrdersEntity;
import com.wanpos.app.repositories.OrdersDetailRepository;
import com.wanpos.app.repositories.OrdersRepository;
import com.wanpos.app.services.OrdersService;
import com.wanpos.constanta.ResponseMessagesConst;
import com.wanpos.constanta.StatusConst;
import com.wanpos.handler.InternalServerError;
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
import java.util.logging.Logger;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrdersDetailRepository ordersDetailRepository;

    private Logger logger;

    @Override
    public BaseResponse saveOrder(OrdersInsertRequest listOrderRequest) {
        try {
            List<OrdersDetailEntity> listDetails = new ArrayList<>();
                OrdersEntity newOrder = new OrdersEntity();
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

                for (OrdersDetailRequest item : listOrderRequest.getDetails()) {
                    OrdersDetailEntity detailsEntity = new OrdersDetailEntity();
                    detailsEntity.setUuid(newOrder.getUuid());
                    detailsEntity.setProductCode(item.getProductCode());
                    detailsEntity.setQuantity(item.getQuantity());
                    detailsEntity.setTotalPrice(item.getTotalPrice());
                    detailsEntity.setPrice(item.getPrice());

                    listDetails.add(detailsEntity);
                }

            OrdersEntity resNewOrder = ordersRepository.save(newOrder);
            List<OrdersDetailEntity> listNewDetails = ordersDetailRepository.saveAll(listDetails);

            return new BaseResponse(HttpStatus.CREATED.value(), true, ResponseMessagesConst.INSERT_SUCCESS.toString(), resNewOrder);
        } catch (Exception e) {
            return InternalServerError.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse updateOrderByUUID(OrdersUpdateRequest request) {
        try {
            OrdersEntity oldOrder = ordersRepository.getOrderByUUID(request.getUuid());
            if (NullEmptyChecker.isNullOrEmpty(oldOrder)) {
                return new BaseResponse(HttpStatus.NOT_FOUND.value(), false, ResponseMessagesConst.DATA_NOT_FOUND.toString(), null);
            }

            OrdersEntity updateOrder = ordersRepository.getOrderByUUID(request.getUuid());
            updateOrder.setUsername(request.getUsername());
            updateOrder.setStatus(request.getStatus());

            Timestamp dateNow = DateHelper.getTimestampNow();

            updateOrder.setModifiedAt(dateNow);
            updateOrder.setModifiedBy(SecurityContextHolder.getContext().getAuthentication().getName());

            OrdersEntity order = ordersRepository.save(updateOrder);

            return new BaseResponse(HttpStatus.OK.value(), true, ResponseMessagesConst.UPDATE_SUCCESS.toString(), order);
        } catch (Exception e) {
            return InternalServerError.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse deleteOrderByUUID(String uuid) {
        try {
            OrdersEntity oldOrder = ordersRepository.getOrderByUUID(uuid);
            if (NullEmptyChecker.isNullOrEmpty(oldOrder)) {
                return new BaseResponse(HttpStatus.NOT_FOUND.value(), false, ResponseMessagesConst.DATA_NOT_FOUND.toString(), null);
            }

            ordersRepository.delete(oldOrder);

            return new BaseResponse(HttpStatus.OK.value(), true, ResponseMessagesConst.UPDATE_SUCCESS.toString(), null);
        } catch (Exception e) {
            return InternalServerError.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse getOrders(int page, int limit, String search) {
        try {
            List<OrdersEntity> listOrder;
            if (NullEmptyChecker.isNullOrEmpty(page) || NullEmptyChecker.isNullOrEmpty(limit)) {
                listOrder = ordersRepository.findAll();
            } else if (NullEmptyChecker.isNullOrEmpty(search)) {
                Pageable pageable = PageRequest.of(page, limit);
                listOrder = ordersRepository.findAll(pageable).toList();
            } else {
                listOrder = ordersRepository.getOrdersByUsername(search);
            }

            return new BaseResponse(HttpStatus.OK.value(), true, ResponseMessagesConst.DATA_FOUND.toString(), listOrder);
        } catch (Exception e) {
            return InternalServerError.InternalServerError(e);
        }
    }

    @Override
    public BaseResponse getOrderByUUID(String uuid) {
        try {
            OrdersEntity listOrder = ordersRepository.getOrderByUUID(uuid);

            return new BaseResponse(HttpStatus.OK.value(), true, ResponseMessagesConst.DATA_FOUND.toString(), listOrder);
        } catch (Exception e) {
            return InternalServerError.InternalServerError(e);
        }
    }
}

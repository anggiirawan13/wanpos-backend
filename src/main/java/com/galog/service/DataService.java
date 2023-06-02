package com.galog.service;

import com.galog.dto.DefaultResponse;
import com.galog.dto.request.DataRequest;
import com.galog.dto.response.DataResponse;
import com.galog.entity.DataEntity;
import com.galog.helper.NullEmptyChecker;
import com.galog.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class DataService {

    @Autowired
    private DataRepository dataRepository;

    public DataService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public ResponseEntity<DefaultResponse> getDataByErrorCode(String errorCode) {
        try {
            DefaultResponse response = new DefaultResponse();
            if (NullEmptyChecker.isNullOrEmpty(errorCode)) {
                response.setSuccess(false);
                response.setCode(HttpStatus.BAD_REQUEST.value());
                response.setMessage("PARAMETER_CODE_REQUIRED");
                response.setData(null);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            DataResponse result = dataRepository.findByErrorCode(errorCode);
            if (NullEmptyChecker.isNullOrEmpty(result)) {
                response.setSuccess(false);
                response.setCode(HttpStatus.NOT_FOUND.value());
                response.setMessage("DATA_NOT_FOUND");
                response.setData(null);

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            } else {
                response.setSuccess(true);
                response.setCode(HttpStatus.FOUND.value());
                response.setMessage("DATA_FOUND");
                response.setData(result);

                return ResponseEntity.status(HttpStatus.FOUND).body(response);
            }
        } catch (Exception e) {
            DefaultResponse response = new DefaultResponse();
            response.setSuccess(false);
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("INTERNAL_SERVER_ERROR");
            response.setData(null);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    public ResponseEntity<DefaultResponse> saveData(DataRequest req) {
        try {
            DefaultResponse response = new DefaultResponse();

            DataResponse dataExist = dataRepository.findByErrorCode(req.getErrorCode());
            if (NullEmptyChecker.isNotNullOrEmpty(dataExist)) {
                response.setSuccess(false);
                response.setCode(HttpStatus.CONFLICT.value());
                response.setMessage("ERROR_CODE_ALREADY_EXIST");
                response.setData(null);

                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }

            if (NullEmptyChecker.isNullOrEmpty(req.getErrorCode())) {
                response.setSuccess(false);
                response.setCode(HttpStatus.BAD_REQUEST.value());
                response.setMessage("ERROR_CODE_REQUIRED");
                response.setData(null);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            } else if (NullEmptyChecker.isNullOrEmpty(req.getDescError())) {
                response.setSuccess(false);
                response.setCode(HttpStatus.BAD_REQUEST.value());
                response.setMessage("DESC_ERROR_REQUIRED");
                response.setData(null);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            Timestamp dateNow = new Timestamp(new Date().getTime());

            DataEntity data = new DataEntity();
            data.setErrorCode(req.getErrorCode());
            data.setDescError(req.getDescError());
            data.setCreatedBy(req.getCreatedBy());
            data.setCreatedAt(dateNow);
            data.setModifiedBy(req.getModifiedBy());
            data.setModifiedAt(dateNow);

            DataEntity newData = dataRepository.save(data);

            response.setSuccess(true);
            response.setCode(HttpStatus.CREATED.value());
            response.setMessage("SAVE_SUCCES");
            response.setData(newData);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            DefaultResponse response = new DefaultResponse();
            response.setSuccess(false);
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("INTERNAL_SERVER_ERROR");
            response.setData(null);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}

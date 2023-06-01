package com.galog.service;

import com.galog.dto.DefaultResponse;
import com.galog.dto.in.DataDTOIn;
import com.galog.dto.out.DataDTOOut;
import com.galog.entity.DataEntity;
import com.galog.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataService {

    @Autowired
    private DataRepository dataRepository;

    public DefaultResponse getData(String errCode) {
        DataDTOOut result = dataRepository.findByErrorCode(errCode);

        DefaultResponse response = new DefaultResponse();
        response.setSuccess(true);
        response.setCode(200);
        response.setMessage("Error berhasil ditemukan");
        response.setData(result);

        return response;
    }

    public void saveData(DataDTOIn req) {
        DataEntity data = new DataEntity();
        data.setErrorCode(req.getErr_code());
        data.setInfoError(req.getInfo_err());

        dataRepository.save(data);
    }

}

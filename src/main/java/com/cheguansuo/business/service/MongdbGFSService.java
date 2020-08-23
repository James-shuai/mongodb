package com.cheguansuo.business.service;

import com.cheguansuo.business.entity.MongoEntity;

public interface MongdbGFSService {

    String saveFile(MongoEntity temp);


    void deleteFile(String file);

}

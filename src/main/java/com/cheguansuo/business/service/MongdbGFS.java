package com.cheguansuo.business.service;

import com.cheguansuo.business.entity.MongoEntity;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class MongdbGFS implements MongdbGFSService {

    @Autowired
    private MongoTemplate mongoTemplate;


    public String saveFile(MongoEntity temp) {
        GridFS gridFS = new GridFS((DB) mongoTemplate.getDb(),"photo_is");
        GridFSInputFile gridFSInputFile = gridFS.createFile(temp.getZp());
        //设置转储照片的名称
        gridFSInputFile.setFilename(temp.getName());
        //创建元数据集合
        DBObject dbObject = new BasicDBObject();
        //存储流水号到元数据
        dbObject.put("lsh", temp.getLsh());
        //存储照片种类到元数据
        dbObject.put("zpzl", temp.getZpzl());
        dbObject.put("zpxh", temp.getZpxh());
        gridFSInputFile.setMetaData(dbObject);
        gridFSInputFile.save();
        //保存以后MongoDB的ID
        String mongoDbId = gridFSInputFile.getId().toString();
        return mongoDbId;
    }



    public void deleteFile(String file) {
//        MongoDatabase db = mongoTemplate.getDb();
//        GridFSBucket bucket = GridFSBuckets.create(db,"photo_is");
//        bucket.find()
//        bucket.delete();
//        MongoDatabase db1 = mongoTemplate.getDb();
//        db1.createCollection();
        GridFS gridFS = new GridFS(mongoTemplate.getDb(), "photo_is");
        gridFS.remove(file);
    }

    public void close() {
        //MongdbGFS.mongoClient.close();
    }
}

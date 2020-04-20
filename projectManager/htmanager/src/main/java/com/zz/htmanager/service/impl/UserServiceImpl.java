package com.zz.htmanager.service.impl;

import com.zz.htmanager.entity.FrameworkDirectory;
import com.zz.htmanager.entity.UserEntity;
import com.zz.htmanager.service.UserService;
import com.zz.htmanager.utils.DataUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public String login(UserEntity userEntity) {
        String result = "error";
        String userName = DataUtils.select("userName");
        String password = DataUtils.select("password");

        if (userEntity.getUserName().equals(userName) && userEntity.getPassword().equals(password)) {
            result = "success";
        }
        return result;
    }

    @Override
    public String getFrameworkDirectory() {
        List<FrameworkDirectory> result = new ArrayList<>();
        try {
            String[] allTitle = DataUtils.select("allTitle").split(",");
            String[] title = DataUtils.select("title").split(",");
            String[] titleRelevance = DataUtils.select("titleRelevance").split(",");
            Map<String, String> allTitleMap = new HashMap();
            Map<String, String> titleMap = new HashMap();
            Map<String, String> titleRelevanceMap = new HashMap();
            for (String at : allTitle) {
                allTitleMap.put(at.split(":")[0], at.split(":")[1]);
            }
            for (String t : title) {
                titleMap.put(t.split(":")[0], t.split(":")[1]);
            }
            for (String tr : titleRelevance) {
                titleRelevanceMap.put(tr.split(":")[0], tr.split(":")[1]);
            }
            //赋值到集合对象里面去
            for (String trm : titleRelevanceMap.keySet()) {
                FrameworkDirectory frameworkDirectory = new FrameworkDirectory();
                frameworkDirectory.setKey(allTitleMap.get(trm));
                frameworkDirectory.setValue(titleMap.get(allTitleMap.get(trm)));
                if (titleRelevanceMap.get(trm) != null && titleRelevanceMap.get(trm).equals("")) {

                }
            }
        } catch (Exception e) {
            System.out.println("配置文件可能并没有配置相关信息");
            e.printStackTrace();
        }

        return null;
    }
}

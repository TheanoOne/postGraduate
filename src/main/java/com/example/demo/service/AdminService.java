package com.example.demo.service;

import java.util.ArrayList;

import com.example.demo.entity.Admin;
import com.example.demo.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService{
    AdminMapper adminMapper;
    public int logIn(String account, String password) {
        Boolean isexist = existAccount(account);
        if (isexist) {
            Admin admin = getAdminByAccount(account);
            if (admin.getPassword().equals(password)) {
                return 2;
            }
            return 1;
        }
        return 0;
    }

    public int addAdmin(Admin admin) {
        Boolean isexist= existAccount(admin.getAccount());
        if(isexist){
            return 1;
        }else{
            adminMapper.addAdmin(admin);
            return 2;
        }
    }

    public boolean updataAdmin(Admin admin) {
        return adminMapper.updataAdmin(admin)>0;
    }

    public boolean existAccount(String account) {
        return adminMapper.isExistAccount(account)>0;
    }

    public Admin getAdminByAccount(String account) {
        return adminMapper.selectByAccount(account);
    }

    public int deleteByID(int id){
        return adminMapper.deleteByID(id);
    }

    public Admin getAdminByID(int ID) {
        return adminMapper.selectByID(ID);
    }

    public	ArrayList<Admin> allAdmin() {
        return adminMapper.allAdmin();
    }

    public ArrayList<Admin> search(String keyword) {
        // TODO Auto-generated method stub
        return adminMapper.search(keyword);
    }


}

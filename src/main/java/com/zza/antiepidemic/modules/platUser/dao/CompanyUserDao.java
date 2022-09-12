package com.zza.antiepidemic.modules.platUser.dao;


import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import com.zza.antiepidemic.modules.platUser.entity.CompanyUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanyUserDao extends MppBaseMapper<CompanyUser> {
    void activateUser(Long uid);

    void frozeUser(Long uid);
}

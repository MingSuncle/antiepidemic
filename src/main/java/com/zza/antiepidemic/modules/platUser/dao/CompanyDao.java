package com.zza.antiepidemic.modules.platUser.dao;

import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import com.zza.antiepidemic.modules.platUser.entity.Company;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanyDao extends MppBaseMapper<Company> {
}

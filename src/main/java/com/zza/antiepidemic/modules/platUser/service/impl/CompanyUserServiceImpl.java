package com.zza.antiepidemic.modules.platUser.service.impl;


import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import com.zza.antiepidemic.modules.platUser.dao.CompanyUserDao;
import com.zza.antiepidemic.modules.platUser.entity.CompanyUser;
import com.zza.antiepidemic.modules.platUser.service.CompanyUserService;
import org.springframework.stereotype.Service;

@Service
public class CompanyUserServiceImpl extends MppServiceImpl <CompanyUserDao, CompanyUser> implements CompanyUserService {

}

package cn.nanchengyu.lease.web.admin.mapper;


import cn.nanchengyu.lease.model.entity.SystemUser;
import cn.nanchengyu.lease.web.admin.vo.system.user.SystemUserInfoVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
* @author liubo
* @description 针对表【system_user(员工信息表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity nanchengyu.lease.model.SystemUser
*/
public interface SystemUserMapper extends BaseMapper<SystemUser> {

    SystemUser selectOneByUsername(String username);

    SystemUserInfoVo getLoginUserInfo(Long userId);
}





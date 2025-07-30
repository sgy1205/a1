package cn.smxy.yeardesign.service;

import cn.smxy.yeardesign.domain.entity.User;
import cn.smxy.yeardesign.domain.param.other.LoginBodyDTO;

public interface ILoginService {

    String login(LoginBodyDTO loginBodyDTO);

}

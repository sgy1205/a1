package cn.smxy.forum.service;

import cn.smxy.forum.domain.param.other.LoginBodyDTO;

public interface ILoginService {

    String login(LoginBodyDTO loginBodyDTO);

}

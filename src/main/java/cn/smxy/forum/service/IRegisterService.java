package cn.smxy.forum.service;

import cn.smxy.forum.domain.param.insert.RegisterDTO;

public interface IRegisterService {

    boolean register(RegisterDTO registerDTO);

}

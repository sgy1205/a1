package cn.smxy.forum.service;

import cn.smxy.forum.domain.param.insert.RegisterDTO;
import cn.smxy.forum.utils.R;

public interface IRegisterService {

    R register(RegisterDTO registerDTO);

}

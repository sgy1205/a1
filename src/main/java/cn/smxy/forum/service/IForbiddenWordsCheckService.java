package cn.smxy.forum.service;

import cn.smxy.forum.domain.vo.ForbiddenWordCheckResultVo;

import java.util.List;

public interface IForbiddenWordsCheckService {

    public ForbiddenWordCheckResultVo checkForbiddenWords(String text);

    public List<String> getWordList();

    public List<String> getRegexList();

}

package cn.smxy.forum.service.Impl;

import cn.smxy.forum.domain.entity.ForbiddenWords;
import cn.smxy.forum.domain.vo.ForbiddenWordCheckResultVo;
import cn.smxy.forum.service.IForbiddenWordsCheckService;
import cn.smxy.forum.service.IForbiddenWordsService;
import cn.smxy.forum.utils.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class ForbiddenWordsCheckServiceImpl implements IForbiddenWordsCheckService {

    @Autowired
    private IForbiddenWordsService forbiddenWordsService;
    @Autowired
    private RedisUtil redisUtil;

    public ForbiddenWordCheckResultVo checkForbiddenWords(String text) {
        // 1 获取违禁词列表（普通字符串）
        List<String> wordList = getWordList();

        // 2 获取正则表达式类型的违禁词列表（如模糊匹配）
        List<String> regexList = getRegexList();

        // 3 用于保存发现的违禁词，使用 LinkedHashSet 避免重复，并保持插入顺序
        Set<String> words = new LinkedHashSet<>();

        // 4 构建返回结果对象
        ForbiddenWordCheckResultVo resultVo = new ForbiddenWordCheckResultVo();

        // 5 如果 text 为空 或者 没有任何违禁词规则，直接返回“无违禁词”
        if (text == null || (wordList == null && regexList == null)) {
            resultVo.setResult(true); // 表示没有违禁词
            resultVo.setForbiddenWords(null);
            return resultVo;
        }

        // 6 将输入文本统一转为小写，用于普通字符串匹配（忽略大小写）
        String lowerText = text.toLowerCase(Locale.ROOT);

        // 7 检查普通违禁词（字符串匹配）
        if (wordList != null) {
            for (String word : wordList) {
                // 确保违禁词非空
                if (word != null && !word.trim().isEmpty() && lowerText.contains(word.toLowerCase())) {
                    // 发现违禁词，加入集合
                    words.add(word);
                }
            }
        }

        // 8 检查正则表达式类型的违禁词
        if (regexList != null) {
            // 将输入文本按 " | " 分割成多个块，分别进行正则匹配
            // 这样可以避免跨块匹配到非法内容（例如：前一块是“赌”，后一块是“博”，拼起来才是违禁词）
            String[] blocks = text.split(" \\| ", -1);   // 与业务分隔符保持一致

            for (String regex : regexList) {
                if (regex == null || regex.trim().isEmpty()) continue;

                try {
                    // 编译正则表达式，忽略大小写
                    Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
                    for (String block : blocks) {
                        Matcher m = p.matcher(block);
                        // 找到所有匹配项，加入违禁词集合
                        while (m.find()) {
                            words.add(m.group());
                        }
                    }
                } catch (Exception ignore) {
                    // 正则表达式异常不中断流程，仅忽略（如用户输入非法正则）
                }
            }
        }

        // 9 判断最终结果
        if (words.isEmpty()) {
            // 没有发现违禁词
            resultVo.setResult(true);
            resultVo.setForbiddenWords(null);
        } else {
            // 发现违禁词
            resultVo.setResult(false);
            resultVo.setForbiddenWords(new ArrayList<>(words)); // 转为 List 返回
        }

        return resultVo;
    }

    public List<String> getWordList(){
        String verifyKey="forbiddenWordList";
        List<String> wordList=redisUtil.getCacheObject(verifyKey);
        if (wordList==null) {
            LambdaQueryWrapper<ForbiddenWords> lqw = new LambdaQueryWrapper<>();
            lqw.eq(ForbiddenWords::getType,"0");
            lqw.eq(ForbiddenWords::getDelFlag,"0");
            lqw.eq(ForbiddenWords::getStatus,"0");

            wordList=forbiddenWordsService.list(lqw)
                    .stream()
                    .map(ForbiddenWords::getForbiddenWord) // 提取字段
                    .filter(Objects::nonNull)                // 过滤 null 值（可选）
                    .collect(Collectors.toList());
            redisUtil.setCacheObject(verifyKey,wordList,1440, TimeUnit.MINUTES);
            return wordList;
        }else{
            return wordList;
        }
    }

    public List<String> getRegexList(){
        String verifyKey="forbiddenWordRegexList";
        List<String> regexList=redisUtil.getCacheObject(verifyKey);
        if (regexList==null) {
            LambdaQueryWrapper<ForbiddenWords> lqw = new LambdaQueryWrapper<>();
            lqw.eq(ForbiddenWords::getType,"1");
            lqw.eq(ForbiddenWords::getDelFlag,"0");
            lqw.eq(ForbiddenWords::getStatus,"0");

            regexList=forbiddenWordsService.list(lqw)
                    .stream()
                    .map(ForbiddenWords::getForbiddenWord)
                    .filter(Objects::nonNull)
                    // 统一把 *、+、{n,} 变成非贪婪
                    .map(s -> s.replaceAll("([*+{][^?}]*)(?=[^?])", "$1?"))
                    .collect(Collectors.toList());
            redisUtil.setCacheObject(verifyKey,regexList,1440, TimeUnit.MINUTES);
            return regexList;
        }else {
            return regexList;
        }

    }
}

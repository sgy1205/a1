package cn.smxy.forum.utils;

import cn.smxy.forum.domain.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Calendar;

public class JWTUtil {
    /**加密的秘钥封装一下*/
    private static final String SECRET = "shigy";
    /**userId字段*/
    private static final String ID_FIELD = "userId";
    /**token的有效时间 30 天*/
    private static final Integer TIME_OUT_DAY = 15;

    /**
     * 创建token
     *
     * @param user 登陆的用户
     * @return 返回Token字符串
     */
    public static String createToken(User user) {
        // 获取日历对象实例
        Calendar calendar = Calendar.getInstance();
        // 在当前日期加上 TIME_OUT_DAY 的时间，用于设置过期时间
        calendar.add(Calendar.DATE, TIME_OUT_DAY);
        System.out.println(user.getUserId());
        // 创建jwt
        return JWT.create()
            // 可以在token中设置数据,设置一个userId为用户的id
            // 后续可以直接在token中获取id
            .withClaim(ID_FIELD, user.getUserId())
            // 设置过期时间
            .withExpiresAt(calendar.getTime())
            // Algorithm.HMAC256(SECRET) 使用HMAC256的加密方式
            // secret 指的是秘钥，在这个秘钥的基础上，进行加密，加大破解的难度这个秘钥爱写什么写什么
            .sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * 验证JWT，返回为false的时候表示验证失败
     *
     * @param token token字符串
     * @return 返回boolean 表示是否登录成功
     */
    public static boolean verifyToken(String token) {
        try {
            // 验证JWT，验证不通过会报错
            JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取用户id，返回值是0表示没有找到id
     *
     * @param token token 字符串
     * @return 返回对应的用户id，如果为0则表示没有用户
     */
    public static Long getUserId(String token) {
        try {
            // 获取id，没有id则会报错
            return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token).getClaim(ID_FIELD).asLong();
        } catch (Exception e) {
            // 如果报错就返回null表示没有找到对应的用户
            return 0L;
        }
    }
}

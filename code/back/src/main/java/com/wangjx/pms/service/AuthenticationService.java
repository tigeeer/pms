package com.wangjx.pms.service;

import com.wangjx.common.util.codec.CodeUtil;
import com.wangjx.common.util.image.ImageCodeUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2017/10/27
 * Time: 16:26
 */
@Service
public class AuthenticationService {

    private static final String IMAGE_CAPTCHA = "IMAGE_CAPTCHA";

    private RedisTemplate<String, String> redisTemplate;

    public AuthenticationService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public ImageCodeUtil getImageCode(String cookie) {
        String code = CodeUtil.getB62t(4);

        if (cookie != null) {
            redisTemplate.opsForValue().set(getImageCodeKey(cookie), code.toLowerCase(), 3, TimeUnit.MINUTES);
        }

        return new ImageCodeUtil(code);
    }

    public boolean verifyImageCode(String imageCode, String cookie) {
        String cacheImageCode = redisTemplate.opsForValue().get(getImageCodeKey(cookie));

        return cacheImageCode != null && imageCode.equals(cacheImageCode);
    }

    private static String getImageCodeKey(String cookie) {
        return IMAGE_CAPTCHA + ":" + cookie;
    }
}

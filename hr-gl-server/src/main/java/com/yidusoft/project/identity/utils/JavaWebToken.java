package com.yidusoft.project.identity.utils;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Map;

/**开发API接口 token生成与校验合法性
 * Created by smy on 2018/1/5.
 */
public class JavaWebToken {

    private static Logger log = LoggerFactory.getLogger(JavaWebToken.class);

    private final static String TOKEN_SECRET = "dhh897jnmbvgrt2354685hgyf";//token 签名加密密钥

    public final static int TOKEN_VALID_TIME = 3600000;//token 有效时间


    //该方法使用HS256算法和Secret:bankgl生成signKey
    private static Key getKeyInstance() {
        //We will sign our JavaWebToken with our ApiKey secret
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(TOKEN_SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        return signingKey;
    }

    //使用HS256签名算法和生成的signingKey最终的Token,claims中是有效载荷
    public static String createJavaWebToken(Map<String, Object> claims) {
        Date date = new Date(System.currentTimeMillis()+ JavaWebToken.TOKEN_VALID_TIME);
        return Jwts.builder().setClaims(claims)
                    .setExpiration(date)
                    .signWith(SignatureAlgorithm.HS256, getKeyInstance())
                    .compact();
       }

    //解析Token，同时也能验证Token，当验证失败返回null
    public static Map<String, Object> parserJavaWebToken(String jwt) {
        try {
            Map<String, Object> jwtClaims =
                    Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(jwt).getBody();
            return jwtClaims;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("json web token verify failed");
            return null;
        }
    }
}

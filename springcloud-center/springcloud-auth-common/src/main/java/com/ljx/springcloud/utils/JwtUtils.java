package com.ljx.springcloud.utils;

import com.ljx.springcloud.entiy.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;

import java.security.PrivateKey;
import java.security.PublicKey;

//创建token，并使用密钥进行加密，公钥进行解密
public class JwtUtils {

    //创建token，使用私钥进行加密
    public static String generateToken(UserInfo userInfo, PrivateKey privateKey,int expireMinutes)throws Exception{
        return Jwts.builder()
                //把用户信息加入到载荷中
                .claim(JwtConstans.JWT_KEY_ID,userInfo.getId())
                .claim(JwtConstans.JWT_KEY_USER_NAME,userInfo.getUsername())
                //设置token的过期时间
                .setExpiration(DateTime.now().plusMinutes(expireMinutes).toDate())
                //
                .signWith(SignatureAlgorithm.RS256,privateKey)
                .compact();
    }

    //私钥加密 -- 私钥字节数组
    public static String generateToken(UserInfo userInfo, byte[] privateKey,int expireMinutes)throws Exception{
        return Jwts.builder()
                .claim(JwtConstans.JWT_KEY_ID,userInfo.getId())
                .claim(JwtConstans.JWT_KEY_USER_NAME,userInfo.getUsername())
                .setExpiration(DateTime.now().plusMinutes(expireMinutes).toDate())
                .signWith(SignatureAlgorithm.RS256,RsaUtils.getPrivateKey(privateKey))
                .compact();
    }

    //公钥解密
    private static Jws<Claims> parserToken(String token, PublicKey publicKey){
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }

    //公钥解密 -- 公钥字节数组
    private static Jws<Claims> parserToken(String token, byte[] publicKey)throws Exception{
        return Jwts.parser().setSigningKey(RsaUtils.getPublicKey(publicKey)).parseClaimsJws(token);
    }

    //获取token中的用户信息
    public static UserInfo getInfoFromToken(String token,PublicKey publicKey)throws Exception{
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();
        return new UserInfo(
                ObjectUtils.toInt(body.get(JwtConstans.JWT_KEY_ID)),
                ObjectUtils.toString(body.get(JwtConstans.JWT_KEY_USER_NAME))
        );
    }

    //获取token中的用户信息
    public static UserInfo getInfoFromToken(String token,byte[] publicKey)throws Exception{
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();
        return new UserInfo(
                ObjectUtils.toInt(body.get(JwtConstans.JWT_KEY_ID)),
                ObjectUtils.toString(body.get(JwtConstans.JWT_KEY_USER_NAME))
        );
    }

}

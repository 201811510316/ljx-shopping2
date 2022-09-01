package com.ljx.springbootTest;

import com.ljx.springcloud.entiy.UserInfo;
import com.ljx.springcloud.utils.JwtUtils;
import com.ljx.springcloud.utils.RsaUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.PrivateKey;
import java.security.PublicKey;

@SpringBootTest
public class springbootTest {

    private static final String pubKeyPath = "D:\\IDEA_springcloud_file\\rsa\\rsa.pub";

    private static final String priKeyPath = "D:\\IDEA_springcloud_file\\rsa\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Test
    public void test()throws Exception{
        RsaUtils.generateKey(pubKeyPath,priKeyPath,"234");
    }

    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
        String token = JwtUtils.generateToken(new UserInfo(20, "jack"), privateKey, 5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTY2MjAxNDkzMX0.MmiIYC5otp8JPJGAoemq7BXF7SM15p2np6CUOha6umF7R2O4v-QVNA00LyWvyN76ekGbwhZw8VaD9WXrNlUBv4LD5JZWjiwkkbmxPiKqORdfwyPbHFebOpDqRSSEEWf15jQqLnz3WwRMa7FyDBeybtcXnzPl2sQmDWsB40LL6vA";

        // 解析token
        UserInfo user = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getUsername());
    }
}

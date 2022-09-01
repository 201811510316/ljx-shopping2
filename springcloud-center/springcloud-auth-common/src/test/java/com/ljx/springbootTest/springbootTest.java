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
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTY2MjAyMDEzMn0.fU-aiFWPJj8ZgKrbJKhrh5hddfX3pTkbJ1hSVAM_3466qf-upk9J9Ij_JE8mw0AvVH-rsex_a5yHUiWwHHyUqOzZDNRA2ljMVw9dEgfoEvQ2muZNVEBsOK9GxzoTehpz1U_sbkJFovjylZ_l7v72-v4SvgcdRLunSR5HY28h8z4";

        // 解析token
        UserInfo user = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getUsername());
    }
}

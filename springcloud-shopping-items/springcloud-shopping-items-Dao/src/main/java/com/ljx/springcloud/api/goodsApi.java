package com.ljx.springcloud.api;

import com.ljx.springcloud.pojo.goods;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface goodsApi {

    @PutMapping("/spu/update")
    public ResponseEntity<Void> updateGoods(@RequestBody goods goods);

    @GetMapping("/spu/goodsid")
    public ResponseEntity<goods> querySpuDetailById(@RequestParam("id")Integer id);
}

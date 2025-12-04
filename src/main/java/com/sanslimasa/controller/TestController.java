package com.sanslimasa.controller;

import com.sanslimasa.service.TableService;
import com.sanslimasa.service.LotteryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    private final LotteryService lotteryService;

    public TestController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @GetMapping("/run-lottery")
    public String runLottery() {
        lotteryService.runLottery();
        return "Lottery triggered manually!";
    }
}

package com.sanslimasa.controller;

import com.sanslimasa.service.LotteryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TvController {

    private final LotteryService lotteryService;

    @GetMapping("/api/last-winner")
    public int getLastWinner() {
        return lotteryService.getLastWinnerNumber();
    }

    @PostMapping("/test/spin")
    public void testSpin() {
        lotteryService.broadcastSpinStart();
    }

    @PostMapping("/test/winner")
    public void testWinner(@RequestParam int table) {
        lotteryService.runLottery(); // random çalışacak ama dilersen direkt gönderirim
    }
}

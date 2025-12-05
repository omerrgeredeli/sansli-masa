package com.sanslimasa.controller;

import com.sanslimasa.service.LotteryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TvController {

    private final LotteryService lotteryService;

    public TvController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @GetMapping("/tv")
    public String tv() {
        return "redirect:/tv.html";
    }

    @GetMapping("/admin-panel")
    public String admin() {
        return "redirect:/admin.html";
    }

    /**
     * TV ekranının yüklenirken son çekiliş sonucunu alması için REST API.
     * @return En son kazanan masa numarası (Yoksa '0' veya 'null' olabilir).
     */
    @GetMapping("/api/last-winner")
    @ResponseBody
    public Integer getLastWinner() {
        return lotteryService.getLastWinnerNumber();
    }
}
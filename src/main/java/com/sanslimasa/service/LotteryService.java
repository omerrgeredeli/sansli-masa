package com.sanslimasa.service;

import com.sanslimasa.model.Table;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

@Service
public class LotteryService {

    private final TableService tableService;
    private final SimpMessagingTemplate messagingTemplate;
    private final Random random = new Random();

    public LotteryService(TableService tableService, SimpMessagingTemplate messagingTemplate) {
        this.tableService = tableService;
        this.messagingTemplate = messagingTemplate;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void runLottery() {

        List<Table> active = tableService.getActiveTables();
        if (active.isEmpty()) return;

        Table winner = active.get(random.nextInt(active.size()));

        messagingTemplate.convertAndSend("/topic/winner", winner.getNumber());

        System.out.println("Winner table: " + winner.getNumber());
    }
}

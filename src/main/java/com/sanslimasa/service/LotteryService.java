package com.sanslimasa.service;

import com.sanslimasa.model.TableEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class LotteryService {

    private final TableService tableService;
    private final SimpMessagingTemplate messaging;

    private Integer lastWinner = null;

    public Integer getLastWinnerNumber() {
        return lastWinner;
    }

    // TV ekranına "çark dönmeye başladı" mesajı gönderir
    public void broadcastSpinStart() {
        messaging.convertAndSend("/topic/spin", "start");
    }

    // Manuel çekiliş (admin veya test için)
    public int runLottery() {
        return runLotteryInternal();
    }

    // Otomatik çekiliş (her saat)
    @Scheduled(cron = "0 0 * * * *")
    public void runLotteryAuto() {
        runLotteryInternal();
    }

    private int runLotteryInternal() {

        List<TableEntity> activeTables = tableService.getActiveTables();

        int winner;
        if (activeTables.isEmpty()) {
            winner = 0;
        } else {
            Random rnd = new Random();
            int index = rnd.nextInt(activeTables.size());
            winner = activeTables.get(index).getNumber();
        }

        lastWinner = winner;

        // TV ekranına kazanan numarayı gönder
        messaging.convertAndSend("/topic/winner", String.valueOf(winner));

        return winner;
    }
}

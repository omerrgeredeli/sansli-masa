package com.sanslimasa.service;

import com.sanslimasa.model.Table;
import lombok.Getter;
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

    // TV ekranının sayfa yenilemede son kazananı çekmesi için metot
    // Eğer hiç çekiliş yapılmadıysa veya son çekilişte aktif masa yoksa 0 döndürebiliriz
    // Son kazanan masa numarasını tutar
    @Getter
    private Integer lastWinnerNumber = null;

    public LotteryService(TableService tableService, SimpMessagingTemplate messagingTemplate) {
        this.tableService = tableService;
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * Her saat başında (00 dakika, 00 saniye) çalışır.
     * Cron: "0 0 * * * *"
     */
    @Scheduled(cron = "0 0 * * * *")
    public void runLottery() {
        System.out.println("--- Çekiliş Başladı: " + java.time.LocalDateTime.now() + " ---");

        List<Table> active = tableService.getActiveTables();

        if (active.isEmpty()) {
            System.out.println("Çekilişe katılan aktif masa bulunamadı.");
            // Eğer aktif masa yoksa, kazananı "YOK" olarak yayınlayabiliriz.
            messagingTemplate.convertAndSend("/topic/winner", 0); // TV'ye "0" gönderelim
            lastWinnerNumber = 0;
            return;
        }

        // Aktif masalar arasından rastgele birini seç
        Table winner = active.get(random.nextInt(active.size()));

        // Sonucu bellekte tut
        this.lastWinnerNumber = winner.getNumber();

        // WebSocket üzerinden sonucu TV ekranlarına gönder
        messagingTemplate.convertAndSend("/topic/winner", winner.getNumber());

        System.out.println("KAZANAN MASA: " + winner.getNumber());
        System.out.println("-------------------------------------------------");
    }

}
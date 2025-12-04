🎉 Şanslı Masa — Saatlik Çekiliş Sistemi

Bu proje, bir kafede bulunan Android TV ekranlarında saat başı otomatik çekiliş yapan basit ve hızlı bir sistem sağlar.
Kafe sahibi masaların aktif/pasif durumunu bir admin panelinden yönetir.
Tüm veriler in-memory tutulur; veritabanı kullanılmaz.

📌 Özellikler

✔ Saat başı otomatik çekiliş
✔ Kazanan masa TV ekranlarında görünür
✔ Admin paneli üzerinden masa yönetimi
✔ WebSocket ile canlı bildirim
✔ In-memory veri yapısı (DB yok)
✔ Dikey Android TV ekranlarında çalışır
✔ TV’de tam ekran web sayfası olarak çalışabilir
✔ Minimal ve hızlı Spring Boot uygulaması

🏗 Mimari
Admin Panel  → REST API → In-Memory List
↑
Android TV ← WebSocket ← Lottery Service (CRON)

🚀 Çalıştırma
Gerekenler:

Java 17+

Maven

Çalıştır:
mvn spring-boot:run

🌐 URL’ler
Yol	Açıklama
/tv	Android TV ekranı (sayaç + kazanan masa)
/admin-panel	Masa yönetimi paneli
/admin/tables	Masa liste/ekle/güncelle API’leri
/ws	WebSocket bağlantısı
🔄 Çekiliş Mekanizması

Saat başı otomatik olarak çalışır:

@Scheduled(cron = "0 0 * * * *")


Yani:

10:00

11:00

12:00
... gibi her saat başında çekiliş yapılır.

📁 Klasör Yapısı

(klasör yapısı eklenir)

⚠ Notlar

Sistem restart olursa masalar sıfırlanır (bilerek böyle).

Masalar "Admin Panel" üzerinden tekrar hızlıca eklenebilir.

İstenirse JSON'a yazıp geri yükleme özelliği eklenebilir.

🧑‍💻 Katkı

Pull request’lere açıktır.
Fikir, geliştirme ve iyileştirme önerilerine her zaman açığız.

⭐ Proje sahibi

Bu proje Şanslı Masa Android TV çekiliş sisteminin temel iskeletidir.
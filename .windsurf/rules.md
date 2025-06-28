# Proje Kuralları

## Kod Stili ve Formatlama
- Java kodları için Google Java Format kullanılacak
- React component'leri functional component olarak yazılacak
- Değişken ve fonksiyon isimleri camelCase olacak
- Class isimleri PascalCase olacak
- Sabitler UPPER_CASE ile yazılacak

## Commit Mesajları
- feat: Yeni özellik eklendiğinde
- fix: Hata düzeltmelerinde
- docs: Dokümantasyon değişikliklerinde
- style: Kod stilindeki değişikliklerde
- refactor: Kod iyileştirmelerinde
- test: Test ekleme veya düzeltmelerinde
- chore: Build işlemleri veya bağımlılıkların güncellenmesinde

## Güvenlik
- Tüm API endpoint'leri JWT ile korunacak
- Hassas veriler (şifreler, API anahtarları) .env dosyasında saklanacak
- SQL injection'a karşı parametreli sorgular kullanılacak
- XSS saldırılarına karşı input validasyonu yapılacak

## Test
- Backend testleri JUnit ile yazılacak
- Frontend testleri için Jest ve React Testing Library kullanılacak
- Kod kapsamı minimum %80 olmalıdır

---
trigger: always_on
---

# Quiz Uygulaması Global Prompt

Bu proje, kullanıcıların çevrimiçi quizler oluşturup çözebileceği bir web uygulamasıdır.

## Backend Mimarisi

### Genel Yapı
- **Teknoloji Yığını**:
  - Java 17+
  - Spring Boot 3.x
  - Spring Security
  - JPA/Hibernate
  - PostgreSQL
  - JWT Authentication
  - Maven

### Paket Yapısı
```
com.vector.quiz
├── common/                  # Ortak konfigürasyon ve yardımcı sınıflar
│   ├── config/             # Spring konfigürasyonları
│   ├── constants/          # Sabit değerler
│   ├── exception/          # Özel exception sınıfları
│   ├── security/           # Güvenlik ile ilgili sınıflar
│   └── util/               # Yardımcı metodlar
│
├── modules/               # İş modülleri
│   ├── auth/               # Kimlik doğrulama modülü
│   ├── quiz/               # Quiz işlemleri modülü
│   │   ├── controller/     # API endpoint'leri
│   │   ├── dto/            # Veri transfer nesneleri
│   │   ├── entity/         # Veritabanı entity'leri
│   │   ├── repository/     # Veritabanı erişim katmanı
│   │   └── service/        # İş mantığı katmanı
│   └── user/               # Kullanıcı yönetim modülü
│
└── QuizApplication.java   # Uygulama giriş noktası
```

### Katmanlı Mimari
1. **Controller Katmanı**
   - HTTP isteklerini karşılar
   - DTO'lar ile çalışır
   - Servis katmanına yönlendirme yapar
   - `@RestController` ve `@RequestMapping` anatasyonlarını kullanır
   - Dönüşümler için `ModelMapper` kullanılır

2. **Service Katmanı**
   - İş mantığını içerir
   - `@Service` ile işaretlenir
   - Repository'ler ile etkileşime girer
   - Transaction yönetimi bu katmanda yapılır

3. **Repository Katmanı**
   - Veritabanı işlemlerini yönetir
   - JPA Repository interface'leri kullanılır
   - `@Repository` anatasyonu ile işaretlenir

4. **Entity Katmanı**
   - Veritabanı tablolarını temsil eder
   - `@Entity` ve `@Table` ile işaretlenir
   - İlişkiler JPA ilişki tanımlamaları ile yapılır

5. **DTO Katmanı**
   - Veri transfer nesneleri
   - Request ve Response DTO'ları ayrıdır
   - Record veya class olarak tanımlanır

### Güvenlik
- JWT tabanlı kimlik doğrulama
- Role tabanlı yetkilendirme (ROLE_USER, ROLE_ADMIN)
- Tüm API endpoint'leri varsayılan olarak güvenlidir
- `/api/auth/**` ve `/swagger-ui/**` hariç tüm istekler için authentication zorunludur

### API Dokümantasyonu
- Swagger/OpenAPI ile otomatik dokümantasyon
- `http://localhost:8080/swagger-ui.html` adresinden erişilebilir

## ModelMapper Kullanımı

### Konfigürasyon
```java
@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        
        // İsteğe bağlı özelleştirmeler
        modelMapper.getConfiguration()
            .setMatchingStrategy(MatchingStrategies.STRICT)
            .setFieldMatchingEnabled(true)
            .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
            
        return modelMapper;
    }
}
```

### Temel Kullanım
- Entity'den DTO'ya dönüşüm:
  ```java
  UserDto userDto = modelMapper.map(userEntity, UserDto.class);
  ```

- DTO'dan Entity'ye dönüşüm:
  ```java
  User user = modelMapper.map(userDto, User.class);
  ```

### Özel Eşleştirmeler
```java
modelMapper.typeMap(SourceClass.class, TargetClass.class)
    .addMappings(mapper -> {
        mapper.map(SourceClass::getFullName, TargetClass::setName);
        mapper.skip(TargetClass::setSensitiveField);
    });
```

### Servis Katmanında Kullanım
```java
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    
    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
    
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            
        return modelMapper.map(user, UserDto.class);
    }
}
```

### Kimlik Doğrulama ve Kullanıcı Bilgilerine Erişim

#### AuthenticationServiceImpl Kullanımı
- Mevcut kullanıcı bilgilerine erişmek için her zaman `AuthenticationServiceImpl` kullanılmalıdır
- Doğrudan `SecurityContextHolder` kullanmak yerine bu servis üzerinden erişim sağlanmalıdır

```java
@Service
public class SomeService {
    private final AuthenticationServiceImpl authenticationService;
    
    @Autowired
    public SomeService(AuthenticationServiceImpl authenticationService) {
        this.authenticationService = authenticationService;
    }
    
    public void someMethod() {
        // Mevcut kullanıcıyı almak için
        User currentUser = authenticationService.getCurrentUser();
        
        // Sadece kullanıcı ID'si gerekiyorsa
        Long currentUserId = authenticationService.getCurrentUserId();
    }
}
```

#### Neden AuthenticationServiceImpl Kullanmalıyız?
1. Merkezi yönetim sağlar
2. Test edilebilirliği artırır
3. Güvenlik kontrolleri tek bir noktadan yönetilir
4. Kod tekrarını önler
5. İleride yapılacak değişiklikler için tek noktadan yönetim sağlar

### Dikkat Edilmesi Gerekenler
- Döngüsel bağımlılıklardan kaçının
- Gereksiz alan kopyalamalarına dikkat edin
- Performans açısından `ModelMapper` instance'ını singleton olarak kullanın
- Karmaşık dönüşümler için özel `Converter`'lar yazabilirsiniz
- Kullanıcı bilgilerine erişirken her zaman `AuthenticationServiceImpl` kullanın

## Frontend Mimarisi
- React 18+
- TypeScript
- Redux Toolkit
- React Router
- Axios
- Material-UI

## Veritabanı
- PostgreSQL
- Flyway ile veritabanı migrasyonları
- Tablo isimleri snake_case olarak adlandırılır

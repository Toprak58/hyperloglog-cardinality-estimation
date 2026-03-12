# HyperLogLog Cardinality Estimation (Benzersiz Eleman Sayısı Tahmini)

Bu proje, Büyük Veri Analitiğinde kullanılan **HyperLogLog (HLL)** algoritmasının Java programlama dili ile sıfırdan gerçekleştirilmiş bir implementasyonudur.

HyperLogLog algoritması, çok büyük veri kümelerinde **benzersiz eleman sayısını (cardinality)** düşük bellek kullanarak yaklaşık olarak tahmin etmek için kullanılan olasılıksal bir veri yapısıdır.

---

# Projenin Amacı

Bu projenin amacı, büyük veri kümelerinde karşılaşılan **Cardinality Estimation** problemini çözmek için kullanılan HyperLogLog algoritmasını tasarlamak ve uygulamaktır.

HyperLogLog algoritması, tüm verileri saklamak yerine yalnızca istatistiksel bilgileri tutarak veri kümesinin büyüklüğünü tahmin eder. Bu sayede çok düşük bellek kullanımı ile yüksek performans sağlar.

---

# Algoritmanın Temel Bileşenleri

Bu projede HyperLogLog algoritmasının aşağıdaki temel bileşenleri gerçekleştirilmiştir:

- Hash Fonksiyonu
- Bucketing (Kova) Mekanizması
- Register Yapısı
- Leading Zero (Ardışık Sıfır) Hesaplama
- Harmonik Ortalama ile Cardinality Tahmini
- Küçük Veri Setleri için Düzeltme (Small Range Correction)
- HyperLogLog Yapılarının Birleştirilmesi (Merge)

---

# Algoritmanın Çalışma Mantığı

HyperLogLog algoritması aşağıdaki adımlar ile çalışmaktadır:

1. Veri kümesine eklenen her eleman bir **hash fonksiyonundan geçirilir**.
2. Hash değerinin ilk bitleri kullanılarak verinin hangi **bucket (kova)** içine gireceği belirlenir.
3. Hash değerinin kalan kısmındaki **leading zero sayısı** hesaplanır.
4. Her bucket için en büyük leading zero değeri register içinde saklanır.
5. Tüm register değerleri kullanılarak **harmonik ortalama yöntemi** ile cardinality tahmini yapılır.

---

# Matematiksel Model

HyperLogLog algoritmasında cardinality tahmini aşağıdaki formül ile hesaplanmaktadır:

E = α * m² / Σ(2^-M[i])

Burada:

- **m** : bucket (kova) sayısını temsil eder  
- **M[i]** : register değerlerini temsil eder  
- **α** : bias düzeltme sabitidir

---

# Hata Analizi

HyperLogLog algoritmasının teorik hata oranı şu formül ile hesaplanmaktadır:


1.04 / √m


Burada **m = 2^p** bucket sayısını ifade eder.

Bucket sayısı arttıkça tahmin doğruluğu artmaktadır.

---

# Merge (Birleştirme) Özelliği

HyperLogLog algoritmasının önemli özelliklerinden biri **iki farklı HLL yapısının birleştirilebilmesidir.**

İki HyperLogLog yapısı şu şekilde birleştirilebilir:


R[i] = max(R1[i], R2[i])


Bu özellik özellikle **dağıtık sistemlerde** büyük veri analizlerinde kullanılmasını sağlar.

---

# Proje Yapısı

Proje aşağıdaki dosya yapısına sahiptir:


src/
├── HyperLogLog.java
├── HashFunction.java
└── Main.java


Dosyaların görevleri:

- **HyperLogLog.java** → Algoritmanın ana implementasyonu
- **HashFunction.java** → Hash fonksiyonunun tanımı
- **Main.java** → Algoritmanın test edilmesi

---

# Kullanılan Teknolojiler

Bu projede aşağıdaki teknolojiler kullanılmıştır:

- Java Programlama Dili
- HyperLogLog Algoritması
- Hash Fonksiyonları
- Olasılıksal Veri Yapıları

---

# Örnek Program Çıktısı

Program çalıştırıldığında aşağıdaki gibi bir çıktı üretmektedir:


Gerçek Cardinality: 78342
HLL Tahmini: 79120


Bu fark algoritmanın olasılıksal doğasından kaynaklanan beklenen hata oranını temsil etmektedir.

---

# Sonuç

HyperLogLog algoritması büyük veri kümelerinde:

- düşük bellek kullanımı
- hızlı hesaplama
- yüksek ölçeklenebilirlik

gibi avantajlar sağlamaktadır.

Bu projede HyperLogLog algoritması Java dili kullanılarak sıfırdan gerçekleştirilmiş ve teorik hata analizi incelenmiştir.

---

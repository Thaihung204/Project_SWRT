    package com.example.SV_Market.entity;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    @Entity
    @Table(name = "SubscriptionPackage")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class SubscriptionPackage {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "package_name", length = 100, nullable = false)
        private String packageName;

        @Column(name = "price", nullable = false)
        private Double price;

        @Column(name = "role_name", length = 50, nullable = false)
        private String roleName;
    }


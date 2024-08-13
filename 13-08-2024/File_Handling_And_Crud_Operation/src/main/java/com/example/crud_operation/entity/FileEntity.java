package com.example.crud_operation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FileEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String fileName;
        private String fileType;

        @Lob
        @Column(name = "data", columnDefinition="LONGBLOB")
        private byte[] data;

        private LocalDateTime uploadDateTime;

        @ManyToOne
        @JoinColumn(name = "recordId", nullable = false)
        private HotelManagement recordId;



}

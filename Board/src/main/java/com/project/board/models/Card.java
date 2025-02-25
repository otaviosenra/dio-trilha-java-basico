package com.project.board.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;
	
	@Column(nullable = false)
    private Boolean deleted;

    @ManyToOne
    @JoinColumn(name = "column_id", nullable = false)
    private Coluna column;
}
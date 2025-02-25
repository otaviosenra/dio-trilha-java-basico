package com.project.board.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "columns")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coluna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
	
	@Column(nullable = false)
    private Boolean deleted;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @OneToMany(mappedBy = "column", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cards;
}

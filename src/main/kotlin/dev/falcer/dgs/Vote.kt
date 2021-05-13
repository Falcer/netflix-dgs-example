package dev.falcer.dgs

import javax.persistence.*

@Entity
data class Vote(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    @Column(nullable = false)
    var count: Int = 0
)

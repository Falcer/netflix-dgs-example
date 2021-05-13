package dev.falcer.dgs

import org.springframework.data.jpa.repository.JpaRepository

interface VoteRepository : JpaRepository<Vote, Int> {
}
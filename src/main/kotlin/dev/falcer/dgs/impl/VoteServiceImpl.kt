package dev.falcer.dgs.impl

import dev.falcer.dgs.Vote
import dev.falcer.dgs.VoteRepository
import dev.falcer.dgs.VoteService
import org.reactivestreams.Publisher
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink
import reactor.core.publisher.ConnectableFlux

@Suppress("unused")
@Service
class VoteServiceImpl constructor(val voteRepository: VoteRepository): VoteService {
    private lateinit var voteStream: FluxSink<Vote>
    private lateinit var votePublisher: ConnectableFlux<Vote>

    @PostConstruct
    private fun createReviews() {
        val publisher: Flux<Vote> = Flux.create { emitter: FluxSink<Vote> ->
            voteStream = emitter
        }
        votePublisher = publisher.publish()
        votePublisher.connect()
    }

    fun votes(id: Int?): List<Vote> {
        return if (id != null) {
            listOf(voteRepository.findById(id).orElse(null))
        } else {
            voteRepository.findAll()
        }
    }

    fun incrementVote(id: Int): Vote {
        val res = voteRepository.findById(id).orElse(null)
        res.count++
        voteRepository.save(res)
        return res
    }

    fun addVote(): Vote {
        return voteRepository.save(Vote())
    }

    fun  getVotePublisher() : Publisher<Vote> {
        return votePublisher
    }
}
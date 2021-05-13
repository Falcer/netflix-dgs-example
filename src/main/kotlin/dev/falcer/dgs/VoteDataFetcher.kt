package dev.falcer.dgs

import com.netflix.graphql.dgs.*
import dev.falcer.dgs.impl.VoteServiceImpl
import org.reactivestreams.Publisher

@Suppress("unused")
@DgsComponent
class VoteDataFetcher constructor(val voteService: VoteServiceImpl) {

    @DgsQuery
    fun votes(@InputArgument id: Int?): List<Vote> {
        return voteService.votes(id)
    }

    @DgsSubscription
    fun realVote(@InputArgument id: Int?): Publisher<Vote> {
        return voteService.getVotePublisher()
    }

    @DgsMutation
    fun incrementVote(@InputArgument id: Int): Vote {
        return voteService.incrementVote(id)
    }

    @DgsMutation
    fun addVote(): Vote {
        return voteService.addVote()
    }

}
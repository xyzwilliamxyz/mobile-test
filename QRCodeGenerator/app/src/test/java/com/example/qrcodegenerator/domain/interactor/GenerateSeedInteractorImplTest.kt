package com.example.qrcodegenerator.domain.interactor

import com.example.qrcodegenerator.core.utils.TimeProvider
import com.example.qrcodegenerator.core.utils.toDate
import com.example.qrcodegenerator.data.remote.response.SeedResponse
import com.example.qrcodegenerator.data.repository.QRCodeRepository
import com.example.qrcodegenerator.domain.mapper.SeedDataMapper
import com.example.qrcodegenerator.domain.model.Seed
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.Date

class GenerateSeedInteractorImplTest {

    private lateinit var interactor: GenerateSeedInteractorImpl
    private val qrCodeRepository: QRCodeRepository = mockk()
    private val timeProvider = object : TimeProvider {
        override fun now(): Date {
            return "2025-05-15T02:39:34.099Z".toDate()!!
        }
    }
    private val seedDataMapper = SeedDataMapper(timeProvider)

    @Before
    fun setUp() {
        interactor = GenerateSeedInteractorImpl(qrCodeRepository, seedDataMapper)
    }

    @Test
    fun `invoke returns mapped Seed when repository call is successful`() = runTest {
        val seedResponse = SeedResponse(seed = "test-seed", expiresAt = "2025-05-15T02:39:49.099Z")
        val expectedSeed = Seed(seed = "test-seed", secondsRemaining = 15, expiresAt = "2025-05-15T02:39:49.099Z")

        coEvery { qrCodeRepository.getSeed().getBodyOrThrow() } returns seedResponse

        val result = interactor.invoke()

        assertEquals(expectedSeed, result)
    }

    @Test(expected = Exception::class)
    fun `invoke throws exception when repository call fails`() {
        runTest {
            coEvery { qrCodeRepository.getSeed().getBodyOrThrow() } throws Exception("Error")

            interactor.invoke()
        }
    }
}
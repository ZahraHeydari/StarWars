package com.android.domain.usecase

abstract class BaseUseCase<in Params, out Result> {

    suspend operator fun invoke(params: Params? = null): Result {
        return execute(params)
    }

    protected abstract suspend fun execute(params: Params?): Result
}
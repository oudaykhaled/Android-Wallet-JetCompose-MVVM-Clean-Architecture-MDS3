package com.ouday.cryptowalletsample.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Sealed class representing the different states of a Flow.
 * This allows for a unified way to handle loading, success, and error states in the UI.
 */
sealed class FlowState<out T> {
    /**
     * Represents the loading state before the data is fetched.
     * No data is associated with this state.
     */
    object Loading : FlowState<Nothing>()

    /**
     * Represents the success state with data.
     * @param data The data fetched successfully.
     */
    data class Success<T>(val data: T) : FlowState<T>()

    /**
     * Represents the error state when something goes wrong in data fetching.
     * @param message The error message describing what went wrong.
     */
    data class Error(val message: String) : FlowState<Nothing>()
}

/**
 * Extension function for Flow to convert it into a Flow emitting FlowState objects.
 * This function transforms a regular Flow into one that emits states representing
 * loading, success, and error states, suitable for UI state management.
 *
 * @return A Flow emitting FlowState objects.
 */
fun <T> Flow<T>.asFlowState(): Flow<FlowState<T>> = flow {
    try {
        emit(FlowState.Loading) // Emit Loading state initially
        collect { data ->
            emit(FlowState.Success(data)) // Emit Success state with data when available
        }
    } catch (e: Exception) {
        emit(FlowState.Error(e.message ?: "Unknown error")) // Emit Error state in case of exception
    }
}.flowOn(Dispatchers.IO) // Switch the context to IO for network/database operations


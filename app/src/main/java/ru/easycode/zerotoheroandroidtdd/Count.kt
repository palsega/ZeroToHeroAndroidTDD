package ru.easycode.zerotoheroandroidtdd

interface Count {
    fun initial(number: String): UiState

    fun increment(number: String): UiState

    fun decrement(number: String): UiState

    data class Base(val step: Int, val max: Int, val min: Int) : Count {

        init {
            if (step < 1) throw IllegalStateException("step should be positive, but was $step")
            if (max < 1) throw IllegalStateException("max should be positive, but was $max")
            if (max < step) throw IllegalStateException("max should be more than step")
            if (max < min) throw IllegalStateException("max should be more than min")
        }

        override fun initial(number: String): UiState {
            val numberAsInt = number.toInt()
            return when (numberAsInt) {
                min -> UiState.Min(number)
                max -> UiState.Max(number)
                else -> UiState.Base(number)
            }
        }

        override fun increment(number: String): UiState {
            val resultAsInt = number.toInt() + step
            val result = resultAsInt.toString()
            return if (resultAsInt < max) UiState.Base(result)
            else UiState.Max(result)
        }

        override fun decrement(number: String): UiState {
            val resultAsInt = number.toInt() - step
            val result = resultAsInt.toString()
            return if (resultAsInt > min) UiState.Base(result)
            else UiState.Min(result)
        }
    }
}

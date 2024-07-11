// TimerViewModel.kt

import androidx.lifecycle.ViewModel

class TimerViewModel : ViewModel() {
    var secondsElapsed: Int = 0
    var isTimerRunning: Boolean = false
}

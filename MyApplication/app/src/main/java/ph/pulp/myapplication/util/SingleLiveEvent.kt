package ph.pulp.myapplication.util

import android.content.ContentValues.TAG
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import timber.log.Timber
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvent<T> : MutableLiveData<T> {

    constructor() : super()
    constructor(value: T) : super(value)

    private val pending = AtomicBoolean(false)

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()) {
            Timber.tag(TAG).e(
                Exception("Multiple observers registered but only one will be notified of changes.")
            )
        }

        super.observe(owner) { t ->
            if (pending.weakCompareAndSet(true, false)) {
                observer.onChanged(t)
            }
        }
    }
}
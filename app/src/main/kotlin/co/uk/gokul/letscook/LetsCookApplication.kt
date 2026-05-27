package co.uk.gokul.letscook

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Base [Application] class for the Lets Cook app.
 * Annotated with [HiltAndroidApp] to trigger Hilt's code generation.
 */
@HiltAndroidApp
class LetsCookApplication : Application()

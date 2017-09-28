package io.github.hismethod.listenyou.Recorder

import android.media.MediaRecorder
import android.util.Log

import java.io.IOException

import android.content.ContentValues.TAG

class CallRecorder private constructor(builder: Builder) : MediaRecorder() {

    var OUTFILE: String? = null
    var SOURCE = AudioSource.VOICE_CALL
    var FORMAT = OutputFormat.THREE_GPP
    var ENCODING = AudioEncoder.AMR_NB

    class Builder {
        var source = AudioSource.VOICE_CALL
        var format = OutputFormat.THREE_GPP
        var encoding = AudioEncoder.AMR_NB
        var filePath: String? = null

        fun source(source: Int): Builder {
            this.source = source
            return this
        }

        fun format(format: Int): Builder {
            this.format = format
            return this
        }

        fun encoding(encoding: Int): Builder {
            this.encoding = encoding
            return this
        }

        fun file(filePath: String): Builder {
            this.filePath = filePath
            return this
        }

        fun build(): CallRecorder {
            return CallRecorder(this)
        }
    }

    init {
        this.SOURCE = builder.source
        this.FORMAT = builder.format
        this.ENCODING = builder.encoding
        this.OUTFILE = builder.filePath
    }

    fun init() {
        this.setAudioSource(SOURCE)
        this.setOutputFormat(FORMAT)
        this.setAudioEncoder(ENCODING)
        this.setOutputFile(OUTFILE)
        try {
            this.prepare()
        } catch (e: IOException) {
            e.printStackTrace()
            Log.e(TAG, "init: " + "오류발생")
        }

    }

    fun record() {
        super.start()
    }

    fun stopForReuse() {
        super.stop()
    }
}

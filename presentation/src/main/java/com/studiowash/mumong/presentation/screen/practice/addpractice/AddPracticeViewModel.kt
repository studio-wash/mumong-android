package com.studiowash.mumong.presentation.screen.practice.addpractice

import android.content.ContentValues
import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.webkit.MimeTypeMap
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.studiowash.mumong.presentation.AppConst.EXTERNAL_DIRECTORY_NAME
import com.studiowash.mumong.presentation.screen.practice.addpractice.record.AudioRecordUtil
import com.studiowash.mumong.presentation.screen.practice.addpractice.record.RecordingFileType
import com.studiowash.mumong.presentation.screen.practice.addpractice.record.RecordingStatus
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.io.OutputStream
import java.util.*

class AddPracticeViewModel: ViewModel() {
    val metronomeTunerStatus: LiveData<MetronomeTunerStatus> get() = _metronomeTunerStatus
    private val _metronomeTunerStatus = MutableLiveData(MetronomeTunerStatus.None)

    val todayPracticeTimeSec: LiveData<Int> get() = _todayPracticeTimeSec
    private val _todayPracticeTimeSec = MutableLiveData(0)

    val recordingStatusLiveData: LiveData<RecordingStatus> get() = _recordingStatusLiveData
    private val _recordingStatusLiveData = MutableLiveData(RecordingStatus.IDLE)

    private var practiceTimer: Timer? = null
    private var player : MediaPlayer? = null

    private val audioRecordUtil = AudioRecordUtil.instance
    private var cachePcmFileUri: Uri? = null
    private var cacheWavFileUri: Uri? = null

    fun updateMetronomeTunerStatus(status: MetronomeTunerStatus) {
        _metronomeTunerStatus.value = status
    }

    fun startCountingTime() {
        practiceTimer = Timer()
        // todo: create fun
        val timerTask = object: TimerTask() {
            override fun run() {
                println("RUN TASK")
                _todayPracticeTimeSec.postValue(_todayPracticeTimeSec.value?.plus(1))
            }
        }
        practiceTimer?.schedule(timerTask, 0, SECOND_IN_MILLI_LONG)
    }

    fun stopCountingTime() {
        practiceTimer?.cancel()
    }

    fun startRecording(context: Context) {
        _recordingStatusLiveData.value = RecordingStatus.RECORDING
        audioRecordUtil.createAudioRecord(context) { cachePcmFileUri, cacheWavFileUri ->
            this.cachePcmFileUri = cachePcmFileUri
            this.cacheWavFileUri = cacheWavFileUri
        }
    }

    fun stopRecording() {
        _recordingStatusLiveData.value = RecordingStatus.DONE_RECORDING
        audioRecordUtil.stopRecord()
    }

    fun startPlayingRecentRecording(context: Context) {
        _recordingStatusLiveData.value = RecordingStatus.PLAYING
        initializePlayerAndStart(context, cacheWavFileUri)
    }

    fun stopPlayingRecentRecording() {
        _recordingStatusLiveData.value = RecordingStatus.DONE_RECORDING
        if (player?.isPlaying == true) {
            player?.stop()
            player?.release()
            player = null
        }
    }

    fun discardRecentRecording() {
        _recordingStatusLiveData.value = RecordingStatus.IDLE
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun saveRecentRecording(context: Context) {
        _recordingStatusLiveData.value = RecordingStatus.IDLE
        copyFromCacheToStorage(context, cacheWavFileUri) // todo...
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun copyFromCacheToStorage(context: Context, inputUri: Uri?) {
        inputUri ?: return
        val fileName = "MuMong-${System.currentTimeMillis()}.${RecordingFileType.Wav.mimeType}"
        val resolver = context.contentResolver
        val mimeTypeStr = fileName.substring(fileName.indexOf(".") + 1, fileName.length)
        val mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(mimeTypeStr)
        val inputStream: InputStream?
        val outputStream: OutputStream?
        try {
            val fileReader = ByteArray(4096)
            var fileSizeDownloaded: Long = 0
            inputStream = resolver.openInputStream(inputUri)
            val outputUri = resolver.insert(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                    put(
                        MediaStore.MediaColumns.MIME_TYPE,
                        MimeTypeMap.getSingleton().getMimeTypeFromExtension(mimeType)
                    )
                    put(
                        MediaStore.MediaColumns.RELATIVE_PATH,
                        Environment.DIRECTORY_RECORDINGS + File.separator + EXTERNAL_DIRECTORY_NAME
                    )
                }
            )
            outputStream = outputUri?.let { resolver.openOutputStream(it) }

            if (outputStream != null) {
                while (true) {
                    val read: Int = inputStream?.read(fileReader) ?: return
                    if (read == -1) {
                        break
                    }
                    outputStream.write(fileReader, 0, read)
                    fileSizeDownloaded += read.toLong()
                }
                outputStream.flush()
                outputStream.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initializePlayerAndStart(context: Context, uri: Uri?) {
        uri ?: return
        player = MediaPlayer()
        player!!.setAudioAttributes(
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setLegacyStreamType(AudioManager.STREAM_MUSIC)
                .build()
        )
        try {
            player!!.setOnPreparedListener { mp -> mp.start() }
            val inputStream = context.contentResolver.openInputStream(uri) as FileInputStream
            player!!.setDataSource(inputStream.fd)
            player!!.prepareAsync()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    enum class MetronomeTunerStatus { Metronome, Tuner, None }

    private companion object {
        const val SECOND_IN_MILLI_LONG = 1000L
    }
}
package com.studiowash.mumong.presentation.screen.practice.addpractice.record

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.webkit.MimeTypeMap
import androidx.annotation.RequiresApi
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import kotlin.concurrent.thread
import androidx.core.net.toUri
import com.studiowash.mumong.presentation.AppConst.EXTERNAL_DIRECTORY_NAME
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class AudioRecordUtil private constructor(){

    private var audioRecord: AudioRecord?=null
    private var recordBufferSize = AudioRecord.getMinBufferSize(SAMPLE_RATE, CHANNEL_CONFIG, AUDIO_FORMAT)
    private var isRecording = false
    private val pcmToWavUtil = PcmToWavUtil(SAMPLE_RATE, CHANNEL_CONFIG, AUDIO_FORMAT)

    @SuppressLint("MissingPermission")
    fun createAudioRecord(context: Context, onStopRecord: (cachePcmFileUri: Uri?, cacheWavFileUri: Uri?) -> Unit) {
        audioRecord = AudioRecord(AUDIO_SOURCE, SAMPLE_RATE, CHANNEL_CONFIG, AUDIO_FORMAT, recordBufferSize)

        val cacheDir = context.cacheDir
        val fileName = CACHE_PCM_FILE_NAME
        val pcmFile = File(cacheDir, fileName)

        isRecording = true
        audioRecord?.startRecording()

        val data = ByteArray(recordBufferSize)
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val fileOutputStream = FileOutputStream(pcmFile)
                while (isRecording){
                    val read = audioRecord?.read(data,0,recordBufferSize)
                    if (read != AudioRecord.ERROR_INVALID_OPERATION){
                        fileOutputStream.write(data)
                    }
                }
                fileOutputStream.close()
                delay(300) // for well-formed wav file...?
                val wavFileUri = makeWavFileInCache(pcmFile.toUri(), context)
                onStopRecord(pcmFile.toUri(), wavFileUri)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }


    fun stopRecord(){
        isRecording = false
        if (audioRecord!=null){
            audioRecord?.stop()
            audioRecord?.release()
            audioRecord = null
        }
    }

    fun makeWavFileInCache(pcmUri: Uri, context: Context) : Uri {
        return pcmToWavUtil.pcmToWav(pcmUri, context)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun copyFromCacheToStorage(context: Context, file: File): String? {
        val fileName = file.name
        val resolver = context.contentResolver
        val mimeTypeStr = fileName.substring(fileName.indexOf(".") + 1, fileName.length)
        val mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(mimeTypeStr)
        val inputStream: InputStream?
        val outputStream: OutputStream?
        try {
            val fileReader = ByteArray(4096)
            var fileSizeDownloaded: Long = 0
            inputStream = file.inputStream()
            val uri = resolver.insert(
                MediaStore.Files.getContentUri("external"),
                ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                    put(
                        MediaStore.MediaColumns.MIME_TYPE,
                        MimeTypeMap.getSingleton().getMimeTypeFromExtension(mimeType)
                    )
                    put(
                        MediaStore.MediaColumns.RELATIVE_PATH,
                        Environment.DIRECTORY_DOWNLOADS + File.separator + EXTERNAL_DIRECTORY_NAME
                    )
                }
            )
            outputStream = uri?.let { resolver.openOutputStream(it) }

            if (outputStream != null) {
                while (true) {
                    val read: Int = inputStream.read(fileReader)
                    if (read == -1) {
                        break
                    }
                    outputStream.write(fileReader, 0, read)
                    fileSizeDownloaded += read.toLong()
                }
                outputStream.flush()
                outputStream.close()
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }

        val new = file.absolutePath
        println("copied file is...$new")
        return new
    }

    companion object{
        val instance:AudioRecordUtil by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            AudioRecordUtil()
        }

        const val TAG = "AudioRecordUtil"

        const val AUDIO_SOURCE = MediaRecorder.AudioSource.MIC
        const val SAMPLE_RATE = 44100
        const val CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_MONO
        const val AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT
        const val CACHE_PCM_FILE_NAME = "recent_recording.pcm"
        const val CACHE_WAV_FILE_NAME = "recent_recording.wav"
    }
}
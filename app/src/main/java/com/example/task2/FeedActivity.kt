package com.example.task2

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.MediaController
import android.widget.SeekBar
import com.example.task2.databinding.ActivityFeedBinding

class FeedActivity : AppCompatActivity() {
    lateinit var binding: ActivityFeedBinding
    var mediaController: MediaController? = null
    private var playing = false
    lateinit var runnable: Runnable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        binding = ActivityFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (mediaController == null) {
            mediaController = MediaController(this)
            binding.videoView.setMediaController(mediaController)
        }

        binding.videoView.apply {
            setMediaController(mediaController)
            setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.pexels_peter_fowler))
            requestFocus()
            setOnClickListener {
                playing = if (playing) {
                    pause()
                    false
                } else {
                    start()
                    true
                }
            }
//            setOnCompletionListener {
//                Toast.makeText(context, "the video is completed", Toast.LENGTH_SHORT).show()
//            }

        }

        binding.introductionText.setOnClickListener {
            binding.seekBar1.visibility = View.VISIBLE
            binding.stopButton.visibility = View.VISIBLE

            val mediaPlayer = MediaPlayer.create(this, R.raw.what_is_instagram)
            mediaPlayer.start()
            binding.stopButton.setOnClickListener {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.pause()
                    binding.stopButton.setImageResource(android.R.drawable.ic_media_play)
                } else {
                    mediaPlayer.start()
                    binding.stopButton.setImageResource(android.R.drawable.ic_media_pause)
                }
            }

            val handler = Handler(Looper.getMainLooper())
            runnable = Runnable {
                binding.seekBar1.progress = mediaPlayer.currentPosition
                handler.postDelayed(runnable, 1000)
            }
            handler.postDelayed(runnable, 1000)

            binding.seekBar1.apply {
                max = mediaPlayer.duration
                setOnSeekBarChangeListener(object :
                    SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(
                        seek: SeekBar, progress: Int, fromUser: Boolean
                    ) {
                        mediaPlayer.seekTo(progress * 1000)
                    }

                    override fun onStartTrackingTouch(seek: SeekBar) {
                    }

                    override fun onStopTrackingTouch(seek: SeekBar) {}
                })
            }
        }
    }
}
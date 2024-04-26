package com.google.mediapipe.examples.handlandmarker.AgoraPart


import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.mediapipe.examples.handlandmarker.MainActivity
import com.google.mediapipe.examples.handlandmarker.R
import io.agora.rtc2.RtcEngine



class AgoraStart : AppCompatActivity() {
    private val REQUIRED_PERMISSIONS = arrayOf<String>(android.Manifest.permission.RECORD_AUDIO,android.Manifest.permission.CAMERA,
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
    )
    private val REQUEST_CODE_PERMISSIONS = 10
    protected var agoraEngine: RtcEngine? = null // The RTCEngine instance
    protected var mListener: AgoraManager.AgoraManagerListener? = null // The event handler to notify the UI of agoraEngine events
    protected val appId: String = "" // Your App ID from Agora console
    lateinit var channelName: String // The name of the channel to join
    var localUid: Int = 0 // UID of the local user
    var remoteUids = HashSet<Int>() // An object to store uids of remote users
    var isJoined = false // Status of the video call
        private set
    var isBroadcaster = true // Local user role







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)
        var b1 = findViewById<Button>(R.id.Send)
        b1.setOnClickListener(
            View.OnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            }
        )
        if (allPermissionsGranted()) {
        } else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                // Handle permissions granted
            } else {
                Toast.makeText(this, "Permissions not granted by the user.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun allPermissionsGranted(): Boolean {
        for (permission in REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }


}
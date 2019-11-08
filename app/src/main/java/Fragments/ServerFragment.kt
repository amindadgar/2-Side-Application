package Fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.MainThread
import com.amindadgar.a2sideapp.R
import kotlinx.android.synthetic.main.fragment_server.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.doAsyncResult
import org.jetbrains.anko.support.v4.onUiThread
import org.jetbrains.anko.support.v4.runOnUiThread
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.support.v4.uiThread
import org.jetbrains.anko.uiThread
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.io.OutputStream
import java.net.ServerSocket
import java.net.Socket


class ServerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val layout = inflater.inflate(R.layout.fragment_server, container, false)
        val text = layout.findViewById<TextView>(R.id.serverText)
//        val button = layout.findViewById<Button>(R.id.button)
//        button.setOnClickListener {
                Connction(text)
//        }





        return layout
    }

    fun Connction(Text: TextView) {

        doAsync {
            var clientSentence: String? = null
            val WelcomSocket = ServerSocket(6785)
            val ConnectionSocket: Socket = WelcomSocket.accept()
            while (true) {

                val outToClient = DataOutputStream(ConnectionSocket.getOutputStream())

                    val inFromClient: BufferedReader =
                        BufferedReader(InputStreamReader(ConnectionSocket.getInputStream()))
                clientSentence = inFromClient.readLine()
                clientSentence = clientSentence.toUpperCase() + "\n"
                uiThread {
                        Text.text = clientSentence + "\n Sending Uppercase to Client ..."
                    }


                outToClient.writeBytes(clientSentence)
//                        toast("inServer Running")
                Log.d("inSerever", "inLoop")
            }
        }


    }
}



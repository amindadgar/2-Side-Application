package Fragments


import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.amindadgar.a2sideapp.R
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.runOnUiThread
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.support.v4.uiThread
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.ServerSocket
import java.net.Socket
import java.util.*
import kotlin.concurrent.schedule


class ServerFragment : Fragment() {

    var clientSentence:String? = null
    var WelcomSocket:ServerSocket? = null
    var ConnectionSocket:Socket? = null
    var outToClient:DataOutputStream? = null
    var inFromClient:BufferedReader? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val layout = inflater.inflate(R.layout.fragment_server, container, false)
        val textview = layout.findViewById<TextView>(R.id.serverText)

        doAsync{

            WelcomSocket = ServerSocket(6785)
            ConnectionSocket = WelcomSocket!!.accept()

            while (true) {

                outToClient = DataOutputStream(ConnectionSocket!!.getOutputStream())
                inFromClient = BufferedReader(InputStreamReader(ConnectionSocket!!.getInputStream()))
                clientSentence = inFromClient!!.readLine()

                clientSentence = clientSentence!!.toUpperCase() + "\n"
            runOnUiThread {
                if (clientSentence!=null || clientSentence!="")
                    textview.text = clientSentence + "\n Sending Uppercase to Client ..."
                toast("yes")
            }

                outToClient!!.writeBytes(clientSentence)

            }
        }

        return layout
    }
}



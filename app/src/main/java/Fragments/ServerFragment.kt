package Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.amindadgar.a2sideapp.R
import kotlinx.android.synthetic.main.fragment_server.*
import org.jetbrains.anko.doAsync
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.io.OutputStream
import java.net.ServerSocket
import java.net.Socket


class ServerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,savedInstanceState: Bundle?): View? {

        val layout = inflater.inflate(R.layout.fragment_server, container, false)
        val text = layout.findViewById<TextView>(R.id.serverText)
        Connction(text)


        return layout
    }
    fun Connction(Text:TextView){
        doAsync {
            var clientSentence:String?=null
            val WelcomSocket= ServerSocket(6789)
            val ConnectionSocket:Socket = WelcomSocket.accept()
            while (true) {
                val inFromClient: BufferedReader =
                    BufferedReader(InputStreamReader(ConnectionSocket.getInputStream()))

                val outToClient = DataOutputStream(ConnectionSocket.getOutputStream())
                clientSentence = inFromClient.readLine()
                clientSentence = clientSentence.toUpperCase() + "\n"
                Text.text = clientSentence + "\n Sending Uppercase to Client ..."
                outToClient.writeBytes(clientSentence)
            }
        }
    }


}

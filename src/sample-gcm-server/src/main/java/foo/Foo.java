
package foo;

import java.io.IOException;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class Foo {

    public static void main(String[] args) throws IOException {
        Sender sender = new Sender("AIzaSyB_6rJ-Vwb0TcixmdPEWb3sRgmc_2EBvCo");
        Message message = new Message.Builder().addData("message", "てすと").build();
        Result result = sender.send(message, "APA91bHXeRcx8YYYcZukYwEEpqlaGKDErAYQGZ8fcKBmVMEExSz-Ix23QVDwlEOaNfaAGXTS37D-1p2YTSC3pf7tEzMUSy2DCx9qpHfDkJQG6RwheTq54t-CdX8AFB4H4uP34CSChRHxAQAnh5iRwDBHAzs4_tFc9Q", 1);
        System.out.println(result.toString());
    }

}

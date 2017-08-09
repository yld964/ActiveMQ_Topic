package activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by lidongyang on 2017/8/9 0009.
 */
public class Sender {

    public static void main(String[] args) {
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                    ActiveMQConnection.DEFAULT_USER,
                    ActiveMQConnection.DEFAULT_PASSWORD,
                    "tcp://localhost:61616"
            );
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("mytopic");
            MessageProducer messageProducer = session.createProducer(topic);
            messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText("我爱你");
            messageProducer.send(textMessage);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}

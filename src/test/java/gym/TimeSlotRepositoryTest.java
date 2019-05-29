package gym;

import com.gym.model.Client;
import com.gym.model.TimeSlot;
import com.gym.model.Trainer;
import com.gym.repository.TimeSlotRepository;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

public class TimeSlotRepositoryTest {

    @Test
    public void generateTimeSlotDBTest() {
        try {
            TimeSlot timeSlot1 = new TimeSlot();
            timeSlot1.setId(1);
            timeSlot1.setStart(LocalDateTime.now());
            Trainer trainer1 = new Trainer();
            trainer1.setId(72);
            timeSlot1.setTrainer(trainer1);
            Client client1 = new Client();
            Client client2 = new Client();
            Client client3 = new Client();
            client1.setId(10);
            client2.setId(20);
            client3.setId(30);
            Set<Client> clientSet = new LinkedHashSet<>();
            clientSet.add(client1);
            clientSet.add(client2);
            clientSet.add(client3);
            timeSlot1.setClients(clientSet);

            TimeSlot timeSlot2 = new TimeSlot();
            timeSlot2.setId(3);
            timeSlot2.setStart(LocalDateTime.now());
            Trainer trainer2 = new Trainer();
            trainer2.setId(65);
            timeSlot2.setTrainer(trainer2);
            Client client4 = new Client();
            Client client5 = new Client();
            client4.setId(87);
            client5.setId(57);
            Set<Client> clientSet2 = new LinkedHashSet<>();
            clientSet2.add(client4);
            clientSet2.add(client5);
            timeSlot2.setClients(clientSet2);

            Set<TimeSlot> testSet = new LinkedHashSet<>();
            testSet.add(timeSlot1);
            testSet.add(timeSlot2);
            TimeSlotRepository.generateTimeSlotDB(testSet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

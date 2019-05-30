package gym;

import com.gym.model.Client;
import com.gym.repository.ClientRepository;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

public class ClientRepositoryTest {
    @Test
    public void generateClientDBTest() throws IOException {
        Set<Client> testClientsSet = new LinkedHashSet<>();
        Client client1 = new Client();
        Client client2 = new Client();
        Client client3 = new Client();

        client1.setId(10);
        client2.setId(20);
        client3.setId(30);

        client1.setName("Joe Cole");
        client2.setName("John Doe");
        client3.setName("Jane Doe");

        client1.setPhonenumber("123456789");
        client2.setPhonenumber("987654321");
        client3.setPhonenumber("123456749");

        client1.setSubscribedSlots(null);
        client2.setSubscribedSlots(null);
        client3.setSubscribedSlots(null);

        testClientsSet.add(client1);
        testClientsSet.add(client2);
        testClientsSet.add(client3);

        ClientRepository.generateTimeSlotDB(testClientsSet);

    }
}
